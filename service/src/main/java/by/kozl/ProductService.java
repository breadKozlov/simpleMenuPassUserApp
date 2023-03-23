package by.kozl;

import by.kozl.dao.ProductDao;
import by.kozl.entity.Product;

import java.util.*;

public class ProductService {

    private final ProductDao productDao = new ProductDao();
    public Optional<ProductDto> getProduct(int id) {
        return productDao.findById(id).map(it -> new ProductDto(it.getName(),it.getDescription()));
    }

    public List<Optional<ProductDto>> getAllProducts() {

        List<Optional<Product>> listProducts = productDao.getAllUsers();
        List<Optional<ProductDto>> listProductsDto = new ArrayList<>();

        for(Optional<Product> product: listProducts) {

            listProductsDto.add(product.map(it -> new ProductDto(it.getId(),it.getName(), it.getDescription())));
        }
        return listProductsDto;
    }

    public boolean deleteProduct(int id) {
        return productDao.deleteProduct(id);
    }

    public void addProduct(ProductDto productDto) {
        productDao.createProduct(new Product(productDto.getName(),productDto.getDescription()));
    }
    public void renameProduct(int id, ProductDto productDto) {
        productDao.renameProduct(id,new Product(productDto.getName(),productDto.getDescription()));
    }
}
