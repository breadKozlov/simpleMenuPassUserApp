package by.kozl.createDB;

import by.kozl.entity.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateProductDB {

    public static final String PATH_TO_DB = "./webAppDB/json";
    public static final String NAME_OF_DB = "Products.json";

    private static CreateProductDB instance;
    private List<Product> products = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    private CreateProductDB() {}

    public static CreateProductDB getInstance() {
        if (instance == null) {
            instance = new CreateProductDB();
        }
        return instance;
    }

    public List<Product> getDataBase() throws IOException {

        if (!instance.checkDataBase()) {
            instance.createDB();
        }
        products = mapper.readValue(new File(PATH_TO_DB + "/" + NAME_OF_DB),new TypeReference<>(){});
        return products;
    }

    private void createDB() throws IOException {

        products.add(new Product(1,"Apple","Iphone"));
        products.add(new Product(2,"Apple","Imac"));
        this.saveDB(products);
    }

    private void saveDB(List<Product> products) throws IOException {
        File file = new File(PATH_TO_DB);
        if (file.mkdirs()) {
            System.out.println("Directory is created");
        }
        mapper.writeValue(new File(file.getPath() + "/" + NAME_OF_DB),products);
    }

    private boolean checkDataBase() {

        boolean result = false;
        File file = new File(PATH_TO_DB);
        if(file.isDirectory() && Objects.requireNonNull(file.listFiles()).length > 0) {

            for (File f : Objects.requireNonNull(file.listFiles())) {

                if (f.isFile() && f.getName().equalsIgnoreCase(NAME_OF_DB)) {
                    result = true;
                    break;
                }
            }
        }
        System.out.println(result);
        return result;
    }
}

