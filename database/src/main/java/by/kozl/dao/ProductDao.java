package by.kozl.dao;

import by.kozl.createDB.CreateProductDB;
import by.kozl.entity.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ProductDao {

    private List<Product> products;
    private static final Map<Integer,Product> dataBase = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String path = CreateProductDB.PATH_TO_DB;
    private static final String file = CreateProductDB.NAME_OF_DB;

    public ProductDao() {
        try {
            CreateProductDB createDB = CreateProductDB.getInstance();
            products = createDB.getDataBase();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public Optional<Product> findById(int id) {

        products = this.readOnDB();
        Product out = null;
        for (Product product: products) {
            if (product.getId() == id) {
                out = product;
                break;
            }
        }
        return Optional.ofNullable(out);
    }

    public List<Optional<Product>> getAllUsers() {

        products = this.readOnDB();
        List<Optional<Product>> optionals = new ArrayList<>();
        for (Product product: products) {
            optionals.add(Optional.ofNullable(product));
        }
        return optionals;
    }

    public void createUser(Product product){

        products = this.readOnDB();
        int id = 1;
        boolean flag = true;
        while (flag) {
            for (Product product1: products) {
                if (product1.getId() == id) {
                    id++;
                    flag = true;
                    break;
                } else {flag = false;}}}
        product.setId(id);
        products.add(product);
        this.refreshDB(products);
    }

    public boolean deleteUser(int id) {
        boolean isDelete = false;
        products = this.readOnDB();
        for (Product product: products) {
            if (product.getId() == id) {
                products.remove(product);
                isDelete = true;
                break;
            }
        }
        this.refreshDB(products);
        return isDelete;
    }

    public void renameUser(int id,Product product) {
        products = this.readOnDB();
        for (Product product1: products) {
            if (product1.getId() == id) {
                product1 = product;
                product1.setId(id);
                break;
            }
        }
        this.refreshDB(products);
    }

    private void refreshDB(List<Product> list) {

        try{
            objectMapper.writeValue(new File(path + "/" + file),list);
            System.out.println("Saved");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private List<Product> readOnDB() {
        try{
            products = objectMapper.readValue(new File(path + "/" + file),new TypeReference<>(){});
            System.out.println("Read");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }
}
