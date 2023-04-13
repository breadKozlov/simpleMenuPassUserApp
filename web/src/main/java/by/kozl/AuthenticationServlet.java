package by.kozl;

import by.kozl.service.UserServiceDB;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authentication")
public class AuthenticationServlet extends HttpServlet {
    private static final UserServiceDB userServiceDB = UserServiceDB.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        req.setAttribute("login",login);
        getServletContext().getRequestDispatcher("/menu.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String path = "/menu.jsp";

        if (userServiceDB.checkPassword(login,password)) {
            req.setAttribute("login",login);
        } else {
            path = "/error.jsp";
            String message = "No user with this name or password incorrect. Please try again or register in the system";
            req.setAttribute("message",message);
        }
        getServletContext().getRequestDispatcher(path).forward(req,resp);
    }
}
