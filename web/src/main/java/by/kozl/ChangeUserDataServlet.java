package by.kozl;

import by.kozl.dto.UserDto;
import by.kozl.service.UserServiceDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/change")
public class ChangeUserDataServlet extends HttpServlet {

    private static final UserServiceDB userServiceDB = UserServiceDB.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        Optional<UserDto> user = userServiceDB.getUser(login);
        req.setAttribute("name",user.orElseThrow().getName());
        req.setAttribute("age",Integer.toString(user.orElseThrow().getAge()));
        req.setAttribute("email",user.orElseThrow().getEmail());
        req.setAttribute("login",login);
        req.setAttribute("password",user.orElseThrow().getPassword());
        req.getServletContext().getRequestDispatcher("/changeUserData.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserDto userDto = new UserDto(name,age,email,login,password);
        String path;
        String message;

        if (userServiceDB.updateUser(userDto)) {
            path = "/success.jsp";
            message = "Update went successfully!";

        } else {
            path = "/error.jsp";
            message = "Update failed";
        }

        req.setAttribute("login",login);
        req.setAttribute("message",message);
        getServletContext().getRequestDispatcher(path).forward(req,resp);
    }
}
