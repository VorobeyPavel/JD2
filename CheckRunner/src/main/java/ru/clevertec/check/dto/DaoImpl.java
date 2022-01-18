package ru.clevertec.check.dto;

import ru.clevertec.check.bean.Product;

import java.io.*;
import java.util.ArrayList;

public class DaoImpl implements dao{

    private static DaoImpl instance;

    public DaoImpl() {
    }

    public static DaoImpl getInstance(){
        if (instance==null){
            instance = new DaoImpl();
        }
        return instance;
    }

    /*
    Метод формирует коллекцию доступных для заказа товаров.
    */
    @Override
    public ArrayList<Product> getWarehouse() {
        ArrayList<Product> warehouse = new ArrayList<>();
        warehouse.add(new Product(1,"Avocado",7.99 ));
        warehouse.add(new Product(2,"Pineapple", 5.29));
        warehouse.add(new Product(3,"Orange", 3.49));
        warehouse.add(new Product(4,"Banana", 3.69));
        warehouse.add(new Product(5,"Grape", 7.79));
        warehouse.add(new Product(6,"Pear", 4.39));
        warehouse.add(new Product(7,"Melon", 5.49));
        warehouse.add(new Product(8,"Kiwi", 4.49));
        warehouse.add(new Product(9,"Lemon", 3.99));
        warehouse.add(new Product(10,"Mandarin", 4.29));
        return warehouse;
    }

    /*
    Метод формирует коллекцию доступных для заказа товаров полученных из файла.
    Файл хранит данные с следующем формате: id-nameProduct-price (1-Avocado-7.99).
    id - номер товара на складе. nameProduct - название товара. price - цена.
    */
    @Override
    public ArrayList<Product> getWarehouseWithFile(){
        ArrayList<Product> warehouse = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите путь к файлу с товарами");

        try {
            String nameFile = bufferedReader.readLine();

            if (!nameFile.matches("([A-Z|a-z]://[^*|\"<>?\\n]*)|(////.*?//.*)+")){
                System.out.println("Файла не существует. Введите корректный путь к файлу(для Windows). " +
                        "Примерный вариант: C://Users//Pavel//Desktop//Warehouse.txt");
                System.exit(0);
            }

            FileReader fileReader = new FileReader(nameFile);
            BufferedReader bufferedReader1 = new BufferedReader(fileReader);

            while (bufferedReader1.ready()){
                String productLine = bufferedReader1.readLine();

                String [] products = productLine.split("-");
                int idProduct = Integer.parseInt(products[0]);
                String nameProduct = products[1];
                double priceProduct = Double.parseDouble(products[2]);
                Product product = new Product(idProduct, nameProduct, priceProduct);
                warehouse.add(product);
            }
        }catch (IOException e){
            System.out.println("Файла не существует. Введите корректное имя файла");
            return null;
        }
        return warehouse;
    }

    /*
    Метод возвращает коллекцию типа Product (список продуктов которые желает прибрести покупатель).
    Объекты Product содержат ID продукта и количество приобретаемого продукта.
    */
    @Override
    public ArrayList<Product> shoppingList(String productLine) {
        ArrayList<Product> listProducts = new ArrayList<>();

        String[] idAndCount = productLine.split(" ");
        for (String s : idAndCount) {
            int index = s.indexOf("-");
            try {
                if (!s.substring(0, index).equals("card")){

                    int id = Integer.parseInt(s.substring(0,index));
                    int count = Integer.parseInt(s.substring(++index));

                    Product product = new Product(id, count);
                    listProducts.add(product);
                }
            }catch (StringIndexOutOfBoundsException e){
                System.out.println("Вы ввели неверный формат данных. Допустимый формат данных: 3-2 2-5 5-3 card-1234");
                System.exit(0);
            } catch (NumberFormatException | IndexOutOfBoundsException e){
                System.out.println("Вы ввели недоступный ID товара или неверный формат данных количества товара. " +
                        "Значение дисконтной карты начинается со слова card.\n" +
                        "ID может иметь значение от 1 до 10. Количество товара целочисленное значение. ");
                System.exit(0);
            }
        }
        return listProducts;
    }

    /*
    Метод возвращает номер дисконтной карты, если она введена корректно. Дисконтная карта дает общую скидку 5%.
    В противном случает общая цена будет подсчитана без скидки.
    */
    @Override
    public int getNumberCard(String productLine) {
        String[] idAndCount = productLine.split(" ");
        int numberCard = 0;

        for (String s : idAndCount) {
            int index = s.indexOf("-");
            if (s.substring(0, index).equals("card")) {
                try {
                    numberCard = Integer.parseInt(s.substring(++index));
                }catch (NumberFormatException e){
                    System.out.println("Дисконтная карта не считана. Номер дисконтной карты должен иметь целочисленное значение.");
                    return 0;
                }
            }
        }
        return numberCard;
    }

    /*
    Данный метод проверяет есть ли в наличие id желаемого товар для покупателя на складе.
    */
    @Override
    public boolean productAvailabilityCheck(ArrayList<Product> shoppingList, ArrayList<Product> warehouse){

        boolean productAvailability = false;

        for (Product product : shoppingList) {
            int idProduct = product.getID();

            for (Product product1 : warehouse) {
                productAvailability = false;
                if (product1.getID() == idProduct) {
                    productAvailability = true;
                    break;
                }
            }
            if (!productAvailability){
                System.out.println("Вы ввели недоступный ID товара или неверный формат данных количества товара.\n" +
                        "ID может иметь значение от 1 до 10. Количество товара целочисленное значение.");
                System.exit(0);
                return false;
            }
        }
        return productAvailability;
    }

    /*
    Метод возвращает коллекцию товаров с ценой каждого товара и общей ценой каждой позиции (учитывая количество заказов).
    */
    @Override
    public ArrayList<Product> priceProduct(ArrayList<Product> shoppingList, ArrayList<Product> warehouse) {

        productAvailabilityCheck(shoppingList, warehouse);

        ArrayList<Product> listProductAndPrice = new ArrayList<>();

        for (Product product : shoppingList) {
            int idProduct = product.getID();

            double priceProduct = 0;
            double priceTotal = 0;
            String nameProduct = null;

            for (Product product1 : warehouse) {
                if (product1.getID()==idProduct){
                    priceProduct = product1.getPriceProduct();
                    nameProduct = product1.getName();
                }
            }
            priceTotal = priceProduct*product.getCount();
            listProductAndPrice.add(new Product(product.getID(), nameProduct, priceProduct, product.getCount(), priceTotal));
        }
        return listProductAndPrice;
    }

    /*
    Метод предоставляет скидку по количеству заказанных товаров. Если количество кого либо приобретаемого товара больше 5,
    то по данной позиции предоставляется скидка 10%.
    */
    @Override
    public double quantityDiscount (ArrayList<Product> shoppingList) {
        double quantityDiscount = 0;
        for (Product product : shoppingList) {
            if (product.getCount()>5){
                quantityDiscount += product.getCount()*product.getPriceProduct()*0.1;
            }
        }
        return quantityDiscount;
    }

}
