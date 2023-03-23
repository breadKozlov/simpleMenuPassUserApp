package by.kozl;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));
        if (productService.deleteUser(id)) {
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("<html><body>");
            writer.println("<h2>User with id: \"" + id + "\" deleted</h2>");
            writer.println("<p><a href=\"./menu.jsp\">Return to the menu page</a></p>");
            writer.println("</body></html>");
            writer.close();
        } else {
            request.setAttribute("message", "Sorry incorrect id. Retry please");
            getServletContext().getRequestDispatcher("/errorGetProduct.jsp").forward(request, response);
        }
    }
}
