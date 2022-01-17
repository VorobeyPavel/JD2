package ru.clevertec.check.view;

import ru.clevertec.check.bean.Product;

import java.util.ArrayList;

public interface Print {

    public void printCheckToConsole(ArrayList<Product> listProductAndPrice, double quantityDiscount, int numberCard);

    public void printCheckToFile(ArrayList<Product> listProductAndPrice, double quantityDiscount, int numberCard);
}
