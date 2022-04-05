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
        PrintWriter writer = res.getWriter();
        writer.println("<h1>hello world</h1>");
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("application/json");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10_000; i++){
            sb.append("x");
        }
        res.setHeader("veryLongHeader", sb.toString());
        res.getOutputStream().write("hello".getBytes());
    }
}