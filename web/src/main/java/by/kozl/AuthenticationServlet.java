package by.kozl;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/authentication")
public class AuthenticationServlet extends HttpServlet {
    private static final UserService userService = new UserService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String path = "/menu.jsp";

        if (userService.checkPassword(login,password)) {

            Optional<UserDto> userDto = userService.getUser(login);
            String name = userDto.orElseThrow().getName();
            int age = userDto.orElseThrow().getAge();
            String email = userDto.orElseThrow().getEmail();
            req.setAttribute("name",name);
            req.setAttribute("age",age);
            req.setAttribute("email",email);
        } else {
            path = "/error.jsp";
            String message = "No user with this name. Please try again or register in the system";
            req.setAttribute("message",message);
        }
        getServletContext().getRequestDispatcher(path).forward(req,resp);
    }
}
