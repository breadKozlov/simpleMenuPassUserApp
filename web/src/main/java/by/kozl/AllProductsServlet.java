package by.kozl;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/products")
public class AllProductsServlet extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        List<Optional<ProductDto>> products = productService.getAllUsers();
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head><style>" +
                "table,th,td{ width: 300px;" +
                "height:30px;" +
                "border: solid 1px silver;" +
                "text-align:center;" + "border-collapse: collapse;}" +
                "</style></head>");
        writer.println("<body><h2>Products:</h2>");
        writer.println("<table><thead>");
        writer.println("<tr> <th>Id</th> <th>Name</th> <th>Description</th></tr>");
        writer.println("</thead><tbody>");

        for( Optional<ProductDto> product : products) {
            writer.println("<tr><td>" + product.orElseThrow().getId() + "</td><td>" +
                    product.orElseThrow().getName() + "</td><td>" +
                    product.orElseThrow().getDescription() + "</td><td>"
                    + "<a href=\"./deleteProduct?id=" + product.orElseThrow().getId() + "\">Delete</a></td>"
                    + "<td><a href=\"./renameProduct.jsp?id=" + product.orElseThrow().getId() + "\">Rename</a></td></tr>");
        }
        writer.println("</tbody></table>");
        writer.println("<p><a href=\"./menu.jsp\">Return to the menu page</a></p>");
        writer.println("</body></html>");
        writer.close();
    }
}
