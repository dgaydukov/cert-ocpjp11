package com.java.test;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//here we run jetty programatically as embedded server inside java
public class App extends HttpServlet {
    public static void main(String[] args) throws Exception {
        Server server = new Server();

        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setResponseHeaderSize(10000);
        ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory(httpConfig));
        connector.setPort(8080);
        server.addConnector(connector);

        // create context and add servlet, use code instead of web.xml file
        WebAppContext handler = new WebAppContext("src/main", "/");
        handler.addServlet(new ServletHolder(new App()), "/");
        server.setHandler(handler);

        // run server
        server.start();
        server.join();

    }
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
        res.setHeader("veryLongHeader", sb.toString());
        res.getOutputStream().write("hello".getBytes());
    }
}