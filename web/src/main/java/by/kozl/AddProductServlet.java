package by.kozl;

import by.kozl.dto.ProductDto;
import by.kozl.service.ProductServiceDB;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {

    private final ProductServiceDB productServiceDB = ProductServiceDB.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String login = request.getParameter("login");
        ProductDto productDto = new ProductDto(name,description);
        productServiceDB.addProduct(productDto);
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><body>");
        writer.println("<h2>Product: " + name + " added</h2>");
        writer.println("<p><a href=\"./authentication?login=" + login + "\">Return to the menu page</a></p>");
        writer.println("</body></html>");
        writer.close();
    }
}
