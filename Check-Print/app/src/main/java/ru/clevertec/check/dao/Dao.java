package ru.clevertec.check.dao;

import ru.clevertec.check.bean.Product;
import java.util.ArrayList;

public interface Dao {

    ArrayList<Product> getWarehouse();

    ArrayList<Product> getWarehouseDB();

    ArrayList<Product> getWarehouseWithFile();

}
