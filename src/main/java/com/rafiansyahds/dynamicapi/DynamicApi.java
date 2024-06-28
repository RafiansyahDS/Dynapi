/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.rafiansyahds.dynamicapi;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.rafiansyahds.dynamicapi.controller.HttpController;

/**
 *
 * @author rafia
 */
public class DynamicApi {

    public static void main(String[] args) throws Exception{
        Server server = new Server(8081);
                ServletContextHandler context = new ServletContextHandler();
                context.setContextPath("/");
        
                context.addServlet(new ServletHolder(new HttpController()), "/api/*");
                server.setHandler(context);
                server.start();
                server.join();
        
    }
}
