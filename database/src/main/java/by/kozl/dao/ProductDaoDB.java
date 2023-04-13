package by.kozl.dao;

import by.kozl.entity.Product;
import by.kozl.exception.DaoException;
import by.kozl.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoDB implements Dao<Integer,Product>{

    private static final ProductDaoDB INSTANCE = new ProductDaoDB();

    private static String SAVE_SQL = """
            INSERT INTO products (name_of_product,description) 
            VALUES (?, ?)
            """;

    private static String DELETE_SQL = """
            DELETE FROM products
            WHERE id = ?
            """;

    private static String FIND_ALL = """
            SELECT id,name_of_product,description       
            FROM products
            ORDER BY id
            """;

    private static String UPDATE_SQL = """
            UPDATE products SET
            name_of_product = ?,
            description = ?
            WHERE id = ?
            """;

    private static String FIND_BY_ID = """
            SELECT id,name_of_product,description       
            FROM products
            WHERE id = ?
            """;

    @Override
    public boolean update(Product product) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1,product.getName());
            statement.setString(2,product.getDescription());
            statement.setInt(3,product.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Product> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID)) {
            Product product = null;
            statement.setInt(1, id);
            var result = statement.executeQuery();
            if (result.next())
                product = buildProduct(result);
            return Optional.ofNullable(product);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL)) {
            List<Product> products = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                products.add(buildProduct(result));

            return products;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Product save(Product product) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1,product.getName());
            statement.setString(2,product.getDescription());
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                product.setId(generatedKeys.getInt("id"));
            return product;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Product buildProduct(ResultSet result) throws SQLException {

        return new Product(result.getInt("id"),
                result.getString("name_of_product"),
                result.getString("description"));

    }

    private ProductDaoDB() {}

    public static ProductDaoDB getInstance() {
        return INSTANCE;
    }
}
