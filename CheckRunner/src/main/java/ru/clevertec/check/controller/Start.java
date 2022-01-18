package ru.clevertec.check.controller;

import ru.clevertec.check.bean.Product;
import ru.clevertec.check.dto.DaoImpl;
import ru.clevertec.check.view.PrintImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) {

        System.out.println("Введите набор параметров в формате itemId-quantity+номер дисконтной карты" +
                "\n(itemId - идентификатор товара, quantity - его количество)" +
                "\nпример для ввода: 3-2 2-5 5-3 card-1234");

        Scanner sc = new Scanner(System.in);
        String productLine = sc.nextLine();

        DaoImpl dao = DaoImpl.getInstance();

        ArrayList<Product> listProduct = dao.shoppingList(productLine);

        /*
        В зависимости от того откуда мы хотим брать доступные для заказа товары(из коллекции в классе DaoImpl или
        из файла, используем соответствующий параметр в методе.
        */

        // Вариант получения товаров из коллекции в классе DaoImpl. Используем dao.getWarehouse()
        ArrayList<Product> listProductAndPrice = dao.priceProduct(listProduct, dao.getWarehouse());

        //Вариант получения товаров из файла. Используем getWarehouseWithFile()
        //ArrayList<Product> listProductAndPrice = dao.priceProduct(listProduct, dao.getWarehouseWithFile());


        /*Получаем скидку по количеству заказанных товаров. Если количество кого либо приобретаемого товара больше 5,
        то по данной позиции предоставляется скидка 10%.*/
        double quantityDiscount = dao.quantityDiscount(listProductAndPrice);

        int numberCard = dao.getNumberCard(productLine);

        PrintImpl print = PrintImpl.getInstance();

        // Метод для вывода чека в консоль
        print.printCheckToConsole(listProductAndPrice, quantityDiscount, numberCard);
        //Метод для вывода чека в файл
        print.printCheckToFile(listProductAndPrice, quantityDiscount, numberCard);

    }
}
