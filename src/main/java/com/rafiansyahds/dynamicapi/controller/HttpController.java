package com.rafiansyahds.dynamicapi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rafiansyahds.dynamicapi.service.HttpServiceImpl;
import com.rafiansyahds.dynamicapi.service.interfaces.HttpService;

@WebServlet("/*")
public class HttpController extends HttpServlet{

    private HttpService logic;

    @Override
    public void init() throws ServletException {
        super.init();
        this.logic = new HttpServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getRequestURI();
        String method = req.getMethod();
        res.setContentType("application/json");
        
        switch(method + ":" + path){
            case "GET:/api/login" : logic.authenticate(req, res); break;
            
            case "GET:/api/mt/users" : logic.getAllUser(res); break;
            case "DELETE:/api/mt/delete-user": logic.deleteUser(req, res); break;
            case "PATCH:/api/mt/update-user": logic.updateUser(req, res);break;
            case "POST:/api/mt/create-user" : logic.createUser(req, res);break;

            case "POST:/api/mt/create-dynapi": logic.createDynamicApi(req, res); break;
            case "GET:/api/mt/dynapi": logic.getAllDynamicApi(res); break;
            case "PATCH:/api/mt/update-dynapi": logic.updateDynamicApi(req, res); break;
            case "DELETE:/api/mt/delete-dynapi": logic.deleteDynamicApi(req, res); break;
            
            default: logic.dynamicApi(req, res);
        }
    }
    
}
