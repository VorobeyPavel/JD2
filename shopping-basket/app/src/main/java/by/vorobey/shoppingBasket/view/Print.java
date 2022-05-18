package by.vorobey.shoppingBasket.view;

import by.vorobey.shoppingBasket.model.Product;
import java.util.ArrayList;

public interface Print {

    void printWarehouse(ArrayList<Product> list);

    void printCheck(Object object);

    void printCheckToConsole(Object object);

    void printCheckToFile(Object object);

    String convertorObjectToJson(Object object);

}
