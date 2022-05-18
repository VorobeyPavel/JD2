package ru.clevertec.check.service;

import ru.clevertec.check.bean.Product;
import java.util.ArrayList;


public interface Service {

    ArrayList<Product> shoppingList(String productLine);

    boolean productAvailabilityCheck(ArrayList<Product> shoppingList, ArrayList<Product> warehouse);

    int getNumberCard(String productLine);

    ArrayList<Product> priceProduct(ArrayList<Product> shoppingList, ArrayList<Product> warehouse);

    double quantityDiscount (ArrayList<Product> shoppingList);

    ArrayList<Product> getWarehouse();

    ArrayList<Product> getWarehouseDB();

    ArrayList<Product> getWarehouseWithFile();


}
