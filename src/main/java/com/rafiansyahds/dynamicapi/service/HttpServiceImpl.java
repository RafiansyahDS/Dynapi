package com.rafiansyahds.dynamicapi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rafiansyahds.dynamicapi.models.SVCData;
import com.rafiansyahds.dynamicapi.models.SVCRest;
import com.rafiansyahds.dynamicapi.models.User;
import com.rafiansyahds.dynamicapi.service.interfaces.HttpService;
import com.rafiansyahds.dynamicapi.service.interfaces.SVCService;
import com.rafiansyahds.dynamicapi.service.interfaces.UserService;

public class HttpServiceImpl implements HttpService{
    
    private final UserService userService;
    private final SVCService svcService;
    private static final HttpServiceImpl instance = new HttpServiceImpl();
    private final Gson gson;

    public HttpServiceImpl(){
        this.userService = new UserServiceImpl();
        this.svcService = new SVCServiceImpl();
        this.gson = new Gson();
    }

    @Override
    public void getAllUser(HttpServletResponse res) throws IOException {
        response(userService.getAllUser(), res);
    }

    @Override
    public void authenticate(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String userId = req.getParameter("userId");
        String passwd = req.getParameter("passwd");
        String auth = userService.getUserByIdPass(userId, passwd);
        switch(auth){
            case "S":  response("Berhasil Masuk!", res); break;
            case "I":  {
                            res.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                            response("Akun Telah Dinonaktifkan, Silahkan Hubungi Admin", res);
                        }break;
            default:    {
                            res.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                            response("Gagal Masuk!", res);
                        }
        }
            
    }

    @Override
    public void createUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
       User dataUser = gson.fromJson(readRequestBody(req), User.class);
       try {
           userService.createUser(dataUser);
           response("Sukses Membuat User", res);
       }catch (SQLIntegrityConstraintViolationException e){
            res.setStatus(HttpServletResponse.SC_CONFLICT);
            response("User dengan ID tersebut sudah ada", res);
       }
        catch (SQLException e) {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.out.println(e);
            response("Gagal Membuat User", res);
       }
    }

    @Override
    public void updateUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
       try{
            User data = gson.fromJson(readRequestBody(req), User.class);
                List<User> users = userService.getUserById(data.getId());
                if(!users.isEmpty()){
                    for(User dateData : users){
                            data.setCreatedAt(dateData.getCreatedAt());
                            data.setUserId(data.getUserId());
                            userService.updateUser(data);
                            response("Sukses Update User", res);
                    }
                }else{
                    res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response("User Tidak Ditemukan!", res);
                }
            }catch(SQLException e){
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response("Gagal Update User", res);
            }
    }

    @Override
    public void deleteUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            String id = req.getParameter("userId");
            if(id != null){
                userService.deleteUser(id);
                response("Sukses Hapus User", res);
            }else{
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response("Format Error", res);
            }
        } catch (SQLException e) {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response("Gagal Hapus User", res);
        }
    }

    @Override
    public void createDynamicApi(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String reqBody = readRequestBody(req);
        SVCRest svcRest = gson.fromJson(reqBody, SVCRest.class);
        JsonObject jsonObject = JsonParser.parseString(reqBody).getAsJsonObject();
        JsonObject data = jsonObject.getAsJsonObject("data");
    
        if (data != null && !data.isJsonNull()) {
            JsonObject header = data.getAsJsonObject("header");
            JsonElement bodyarr = data.get("body");

            if (header != null && (bodyarr != null)) {
                try {
                    svcService.createDynamicApi(svcRest);
                    svcService.createApiData(new SVCData(svcRest.getId(), data.toString()));
                    response("Sukses Membuat API", res);
                } catch (SQLIntegrityConstraintViolationException e) {
                    res.setStatus(HttpServletResponse.SC_CONFLICT);
                    response("API dengan ID tersebut sudah ada", res);
                } catch (SQLException e) {
                    res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response("Gagal Upload API", res);
                }
            } else {
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response("Format Data Salah", res);
            }
        } else {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response("Data Kosong!", res);
        }
    }
    

    @Override
    public void getAllDynamicApi(HttpServletResponse res) throws IOException{
            response(svcService.getAllDynamicApi(), res);
    }

    @Override
    public void updateDynamicApi(HttpServletRequest req, HttpServletResponse res) throws IOException{
        String reqBody = readRequestBody(req);
        try {
            SVCRest data = gson.fromJson(reqBody, SVCRest.class);
            JsonObject jsonObject = JsonParser.parseString(reqBody).getAsJsonObject();
            JsonObject jsonData = jsonObject.getAsJsonObject("data");
        
            // Check if data, header, and body are not null
            if (jsonData != null && !jsonData.isJsonNull()) {
                JsonObject header = jsonData.getAsJsonObject("header");
                JsonObject body = jsonData.getAsJsonObject("body");
        
                if (header != null && body != null) {
                    // Your update logic here
                    for (SVCRest dateData : svcService.getDynamicApiById(data.getId())) {
                        if (dateData != null) {
                            data.setCreatedAt(dateData.getCreatedAt());
                            data.setId(data.getId());
                            svcService.updateDynamicApi(data);
                            svcService.updateApiData(new SVCData(data.getId(), jsonData.toString()));
                            response("Sukses Update API", res);
                        }
                    }
                } else {
                    res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response("Format Data Salah", res);
                }
            } else {
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response("Data Kosong!", res);
            }
        } catch (SQLException e) {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response("Gagal Update API", res);
        }
    }

    @Override
    public void deleteDynamicApi(HttpServletRequest req, HttpServletResponse res) throws IOException{
        try {
            String id = req.getParameter("id");
            id = java.net.URLDecoder.decode(id, "UTF-8");
            if(id != null){
                svcService.deleteApiData(id);
                svcService.deleteDynamicApi(id);
                response("Sukses Hapus Api", res);
            }else{
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response("Format Error", res);
            }
        } catch (SQLException e) {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response("Gagal Hapus API", res);
        }
    }

    @Override
    public void dynamicApi(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String method = req.getMethod();
        String path = req.getRequestURI();
        String route = "/api/dynamic";
        

        if (path.startsWith(route)){
            String dynamicPath = path.substring(route.length());
            SVCRest apiPath = null;
            List<SVCRest> list = svcService.getDynamicApi(method, dynamicPath);
            if(!list.isEmpty()){
                apiPath = list.get(0);
            }
            if(apiPath != null && apiPath.getHttpURI().equals(dynamicPath) && apiPath.getHttpMethod().equals(method) && apiPath.getIsActive().equals("Y")){
                switch(apiPath.getHttpMethod()){
                    case "GET" : handleRequest(apiPath, res); break;
                    case "POST": handleRequest(apiPath, res); break;
                    case "PATCH":  handleRequest(apiPath, res);break;
                    case "DELETE":  handleRequest(apiPath, res);break; 
                }
            }else{
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response("Path Inactive or Not Found!", res);
            }
        }else{
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response("Not Found!", res);
        }
    }
    
    @Override
    public void response(Object data, HttpServletResponse res) throws IOException{
        res.getWriter().println(gson.toJson(toMap(data, res)));
    }

    @Override
    public void responseDynapi(Object data, HttpServletResponse res) throws IOException{
        res.getWriter().println(gson.toJson(data));
    }

    private void handleRequest(SVCRest req, HttpServletResponse res) throws IOException{
        String data = null;
        List<SVCData> svcdata = svcService.getApiData(req.getId());
        if(svcdata != null){
            data = svcdata.get(0).getData();
        }
        
        if(data != null){
            JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
            JsonObject header = jsonObject.getAsJsonObject("header");
            JsonElement body = jsonObject.get("body");

            for (Map.Entry<String, JsonElement> entry : header.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().getAsString();
                res.setHeader(key, value);
            }
            responseDynapi(body, res);
        }else{
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response("Data Tidak Ditemukan", res);
        }
    }

    private String readRequestBody(HttpServletRequest req) throws IOException{
        StringBuilder requestBody;
        try (BufferedReader reader = req.getReader()) {
            requestBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }
        return requestBody.toString();
    }

    private Map<String, Object> toMap(Object data, HttpServletResponse res){
        HashMap<String, Object> response = new HashMap<>();
        response.put("Status", res.getStatus());
        response.put("data", data);
        return response;
    }

    
    
    public static HttpServiceImpl getInstance(){
        return instance;
    }
}
