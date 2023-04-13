package by.kozl;

import by.kozl.dto.UserDto;
import by.kozl.service.UserServiceDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private static final UserServiceDB userServiceDB = UserServiceDB.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String log = req.getParameter("login");
        Optional<UserDto> user = userServiceDB.getUser(log);
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<!DOC TYPE html><html>");
        printWriter.println("<head><meta charset=\"UTF-8\"><title>User Form</title>");
        printWriter.println("</head><body>");
        printWriter.println("<h2>Hello, " + log + "</h2>");
        printWriter.println("<p>Here is your data: </p>");
        printWriter.println("<p>Name: " + user.orElseThrow().getName() + " </p>");
        printWriter.println("<p>Age: " + user.orElseThrow().getAge() + " </p>");
        printWriter.println("<p>Email: " + user.orElseThrow().getEmail() + " </p>");
        printWriter.println("<p>Login: " + user.orElseThrow().getLogin() + " </p>");
        printWriter.println("<p>Password: " + user.orElseThrow().getPassword() + " </p>");
        printWriter.println("<p><a href=\"./change?login=" + user.orElseThrow().getLogin() + "\">Change your data</a></p>");
        printWriter.println("<p><a href=\"./authentication?login=" + user.orElseThrow().getLogin() + "\">Return to menu page</a></p>");
        printWriter.println("</body></html>");
        printWriter.close();
    }
}
