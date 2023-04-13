package by.kozl;

import by.kozl.dao.ProductDaoDB;
import by.kozl.dao.UserDaoDB;

public class JDBCRunner {

    public static void main(String[] args) {

        var productDao = ProductDaoDB.getInstance();
        System.out.println(productDao.findAll());
        var userDao = UserDaoDB.getInstance();
        System.out.println(userDao.findAll());
    }
}
