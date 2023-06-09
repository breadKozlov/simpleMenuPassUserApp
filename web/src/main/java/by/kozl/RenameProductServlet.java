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

@WebServlet("/renameProduct")
public class RenameProductServlet extends HttpServlet {

    private final ProductServiceDB productServiceDB = ProductServiceDB.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String login = request.getParameter("login");
        ProductDto productDto = new ProductDto(id,name,description);
        productServiceDB.renameProduct(productDto);
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><body>");
        writer.println("<h2>Product with id: \"" + id + "\" renamed</h2>");
        writer.println("<p><a href=\"./authentication?login=" + login + "\">Return to the menu page</a></p>");
        writer.println("</body></html>");
        writer.close();
    }
}
