package com.rafiansyahds.dynamicapi.service.interfaces;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface  HttpService {
    public void getAllUser(HttpServletResponse res) throws IOException;
    public void authenticate(HttpServletRequest req,HttpServletResponse res) throws IOException;
    public void createUser(HttpServletRequest req, HttpServletResponse res) throws IOException;
    public void updateUser(HttpServletRequest req, HttpServletResponse res) throws IOException;
    public void deleteUser(HttpServletRequest req, HttpServletResponse res) throws IOException;

    public void dynamicApi(HttpServletRequest req, HttpServletResponse res) throws IOException;

    public void getAllDynamicApi(HttpServletResponse res) throws IOException;
    public void createDynamicApi(HttpServletRequest req, HttpServletResponse res) throws IOException;
    public void updateDynamicApi(HttpServletRequest req, HttpServletResponse res) throws IOException;
    public void deleteDynamicApi(HttpServletRequest req, HttpServletResponse res) throws IOException;


    public void response(Object data, HttpServletResponse res) throws IOException;
    public void responseDynapi(Object data, HttpServletResponse res) throws IOException;
}
