package my;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class App extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter writer = res.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Java Application Servlet</title>");
        writer.println("</head>");
        writer.println("<body bgcolor=white>");
        writer.println("<h1>Java Application Servlet</h1>");
        writer.println("This is the simple java tomcat web app");
        writer.println("</body>");
        writer.println("</html>");
    }
}