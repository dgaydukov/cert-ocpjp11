package com.java.test;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class App extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        res.getWriter().write("<h1>hello world</h1>");
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("application/json");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10_000; i++){
            sb.append("x");
        }
        // this will throw 500, cause default header size is 8KB
        // to increase header size you should configure jetty server with configuration for max header size
        // org.eclipse.jetty.server.HttpConfiguration.responseHeaderSize
        res.setHeader("veryLongHeader", sb.toString());
        res.getOutputStream().write("hello".getBytes());
    }
}