package by.kozl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String log = req.getParameter("login");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<!DOC TYPE html><html>");
        printWriter.println("<head><meta charset=\"UTF-8\"><title>User Form</title>");
        printWriter.println("</head><body>");
        printWriter.println("<h2>Hello " + log + "</h2>");
        printWriter.println("</body></html>");
        printWriter.close();
    }
}
