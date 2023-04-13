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
import java.util.List;
import java.util.Optional;

@WebServlet("/products")
public class AllProductsServlet extends HttpServlet {

    private final ProductServiceDB productServiceDB = ProductServiceDB.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        List<Optional<ProductDto>> products = productServiceDB.getAllProducts();
        String login = request.getParameter("login");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head><style>" +
                "table,th,td{ width: 300px;" +
                "height:30px;" +
                "border: solid 1px silver;" +
                "text-align:center;" + "border-collapse: collapse;}" +
                "</style></head>");
        writer.println("<body><h2>Current user: " + login + " </h2><h3>Products:</h3>");
        writer.println("<table><thead>");
        writer.println("<tr> <th>Id</th> <th>Name</th> <th>Description</th></tr>");
        writer.println("</thead><tbody>");

        for( Optional<ProductDto> product : products) {
            writer.println("<tr><td>" + product.orElseThrow().getId() + "</td><td>" +
                    product.orElseThrow().getName() + "</td><td>" +
                    product.orElseThrow().getDescription() + "</td><td>"
                    + "<a href=\"./deleteProduct?id=" + product.orElseThrow().getId() + "&login=" + login + "\">Delete</a></td>"
                    + "<td><a href=\"./renameProduct.jsp?id=" + product.orElseThrow().getId() + "&login=" + login + "\">Rename</a></td></tr>");
        }
        writer.println("</tbody></table>");
        writer.println("<p><a href=\"./authentication?login=" + login + "\">Return to the menu page</a></p>");
        writer.println("</body></html>");
        writer.close();
    }
}
