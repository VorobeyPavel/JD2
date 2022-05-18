package ru.clevertec.check.view;

import ru.clevertec.check.bean.Product;

import java.util.ArrayList;

public interface Print {

    void printCheckToConsole(ArrayList<Product> listProductAndPrice, double quantityDiscount, int numberCard);

    void printCheckToFile(ArrayList<Product> listProductAndPrice, double quantityDiscount, int numberCard);

    StringBuilder printCheckToEmail(ArrayList<Product> listProductAndPrice, double quantityDiscount, int numberCard);

    String checkHeader();

    StringBuilder bodyCheck(ArrayList<Product> listProductAndPrice, double quantityDiscount, int numberCard);
}
