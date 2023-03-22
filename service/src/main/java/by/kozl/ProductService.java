package by.kozl;

import by.kozl.dao.ProductDao;
import by.kozl.entity.Product;

import java.util.*;

public class ProductService {

    private final ProductDao userDao = new ProductDao();
    public Optional<ProductDto> getProduct(int id) {
        return userDao.findById(id).map(it -> new ProductDto(it.getName(),it.getDescription()));
    }

    public List<Optional<ProductDto>> getAllUsers() {

        List<Optional<Product>> listProducts = userDao.getAllUsers();
        List<Optional<ProductDto>> listProductsDto = new ArrayList<>();

        for(Optional<Product> product: listProducts) {

            listProductsDto.add(product.map(it -> new ProductDto(it.getId(),it.getName(), it.getDescription())));
        }
        return listProductsDto;
    }

    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    public void addUser(ProductDto productDto) {
        userDao.createUser(new Product(productDto.getName(),productDto.getDescription()));
    }
    public void renameUser(int id, ProductDto productDto) {
        userDao.renameUser(id,new Product(productDto.getName(),productDto.getDescription()));
    }
}
