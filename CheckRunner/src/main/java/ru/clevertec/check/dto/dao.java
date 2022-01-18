package ru.clevertec.check.dto;

import ru.clevertec.check.bean.Product;
import java.util.ArrayList;

public interface dao {

    public ArrayList<Product> getWarehouse();

    public ArrayList<Product> getWarehouseWithFile();

    public ArrayList<Product> shoppingList(String productLine);

    public boolean productAvailabilityCheck(ArrayList<Product> shoppingList, ArrayList<Product> warehouse);

    public int getNumberCard(String productLine);

    public ArrayList<Product> priceProduct(ArrayList<Product> shoppingList, ArrayList<Product> warehouse);

    public double quantityDiscount (ArrayList<Product> shoppingList);


}
