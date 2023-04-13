package by.kozl.service;

import by.kozl.dto.ProductDto;
import by.kozl.dao.ProductDaoDB;
import by.kozl.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductServiceDB {

    private static final ProductServiceDB INSTANCE = new ProductServiceDB();
    private static final ProductDaoDB productDaoDB = ProductDaoDB.getInstance();

    public Optional<ProductDto> getProduct(int id) {
        return productDaoDB.findById(id).map(it -> new ProductDto(it.getName(),it.getDescription()));
    }

    public List<Optional<ProductDto>> getAllProducts() {

        List<Optional<Product>> listProducts = productDaoDB.findAll()
                .stream().map(Optional::ofNullable).toList();
        List<Optional<ProductDto>> listProductsDto = new ArrayList<>();

        for(Optional<Product> product: listProducts) {
            listProductsDto.add(product.map(it -> new ProductDto(it.getId(),it.getName(), it.getDescription())));
        }
        return listProductsDto;
    }

    public boolean deleteProduct(int id) {

        return productDaoDB.delete(id);
    }

    public void addProduct(ProductDto productDto) {
        productDaoDB.save(new Product(productDto.getName(),productDto.getDescription()));
    }
    public void renameProduct(ProductDto productDto) {
        productDaoDB.update(new Product(productDto.getId(),productDto.getName(),productDto.getDescription()));
    }

    private ProductServiceDB() {
    }

    public static ProductServiceDB getInstance() {
        return INSTANCE;
    }


}
