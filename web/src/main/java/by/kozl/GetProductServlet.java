package by.kozl;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/getProduct")
public class GetProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));
        Optional<ProductDto> product = productService.getProduct(id);
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        if (product.isPresent()) {
            writer.println("<html><body>");
            writer.println("<h2>User with id - \"" + id + "\" is:</h2>");
            writer.println("<p style='color:Tomato'> " + product.get().getName() + " " +
                    product.get().getDescription() + "</p>");
            writer.println("<p><a href=\"./menu.jsp\">Return to the menu page</a></p>");
            writer.println("</body></html>");
            writer.close();
        } else {
            request.setAttribute("message", "This id doesn't exist in the database. Please retry enter.");
            getServletContext().getRequestDispatcher("/errorGetUser.jsp").forward(request, response);
        }
    }
}
