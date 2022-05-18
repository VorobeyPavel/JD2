package by.vorobey.shoppingBasket.dao;

import by.vorobey.shoppingBasket.model.Product;

import java.util.List;

public interface DAOTable {

    void createTable();
    void insertTable();
    void deleteTable();

    List<Product> findAll();

    Product findProduct(int id);
}
