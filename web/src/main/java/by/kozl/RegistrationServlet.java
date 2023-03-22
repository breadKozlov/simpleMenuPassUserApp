package by.kozl;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    UserService userService = new UserService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserDto userDto = new UserDto(name,age,email,login,password);
        String path = null;
        String message = null;

        if (userService.registerUser(userDto)) {
            path = "/error.jsp";
            message = "Registration failed. The user with the given\n" +
                    "login already exists. Please change your login.";
        } else {
            path = "/success.jsp";
            message = "Registration went successfully!";
            req.setAttribute("login",login);
        }

        req.setAttribute("message",message);
        getServletContext().getRequestDispatcher(path).forward(req,resp);
    }



}
