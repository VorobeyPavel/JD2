package ru.clevertec.check.view;

import org.springframework.stereotype.Component;
import ru.clevertec.check.bean.Product;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Component
public class PrintImpl implements Print{

    String headerCheck = checkHeader();

    /*
    Метод распечатывает чек в консоль
    */
    @Override
    public void printCheckToConsole(ArrayList<Product> listProductAndPrice, double quantityDiscount, int numberCard){

        System.out.println(headerCheck);
        System.out.println(bodyCheck(listProductAndPrice, quantityDiscount, numberCard));
    }

    /*
    Метод распечатывает чек в файл
    */
    @Override
    public void printCheckToFile(ArrayList<Product> listProductAndPrice, double quantityDiscount, int numberCard) {
        System.out.println("Введите путь к файлу куда будет распечатан чек. " +
                "Примерный вариант: C://Users//Pavel//Desktop//CheckToFile.txt");


        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            String nameFile = bufferedReader.readLine();

            if (!nameFile.matches("([A-Z|a-z]://[^*|\"<>?\\n]*)|(////.*?//.*)+")){
                System.out.println("Файла не существует. Введите корректный путь к файлу(для Windows). " +
                        "Примерный вариант: C://Users//Pavel//Desktop//CheckToFile.txt");
                return;
            }

            FileWriter fileWriter = new FileWriter(nameFile, false);
            fileWriter.write(headerCheck);
            String bodyCheck = String.valueOf(bodyCheck(listProductAndPrice, quantityDiscount, numberCard));
            fileWriter.write(bodyCheck);
            fileWriter.close();
        }catch (IOException e){
            System.out.println("Файла не существует");
        }
    }

    @Override
    public StringBuilder printCheckToEmail(ArrayList<Product> listProductAndPrice, double quantityDiscount, int numberCard) {

        StringBuilder result = new StringBuilder();
        result.append(headerCheck);
        result.append(bodyCheck(listProductAndPrice, quantityDiscount, numberCard));
        return result;
    }

    @Override
    public String checkHeader() {
        int cashier = (int) (Math.random() * 10000);
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formater2 = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        return "            CASH RECEIPT\n" +
                "           supermarket 123\n" +
                "          Tel :123-456-789\n" +
                "CASHIER: №"+cashier + "       DATE: "+formater.format(date)+"\n"+
                "                     TIME: "+formater2.format(date)+"\n" +
                "-------------------------------------"+"\n" +
                "QTY DESCRIPTION        PRICE    TOTAL"+"\n";
    }

    @Override
    public StringBuilder bodyCheck(ArrayList<Product> listProductAndPrice, double quantityDiscount, int numberCard) {

        StringBuilder bodyCheck = new StringBuilder();
        double total = 0;
        double taxableTot = 0;
        DecimalFormat decimalFormat = new DecimalFormat( "#.##" );

        for (Product product : listProductAndPrice) {
            total+=product.getPriceTotal();

            String nameProduct = product.getName();
            int lengthName = nameProduct.length();
            if (lengthName>16){
                nameProduct = nameProduct.substring(0,16);
            }
            if (lengthName<16){
                while (lengthName<16){
                    nameProduct = nameProduct + " ";
                    ++lengthName;
                }
            }
            String priceProduct = "$"+(decimalFormat.format(product.getPriceProduct()));
            int length = priceProduct.length();
            if (length<7){
                while (length<7){
                    priceProduct = " "+priceProduct;
                    length++;
                }
            }
            String priceTotal = "$"+(decimalFormat.format(product.getPriceTotal()));
            int lengthTotal = priceTotal.length();
            if (lengthTotal<8){
                while (lengthTotal<8){
                    priceTotal = " "+priceTotal;
                    lengthTotal++;
                }
            }
            bodyCheck.append(" "+product.getCount()+"  "+nameProduct+" "+priceProduct+" "+priceTotal+"\n");
        }
        bodyCheck.append("=====================================\n");

        double totalDiscount = quantityDiscount;
        if (numberCard!=0){
            totalDiscount+=total*0.05;
        }

        taxableTot = total-totalDiscount;

        String taxableTotString = "$"+decimalFormat.format(taxableTot);
        if ((""+taxableTot).length()<24){
            while (taxableTotString.length()<24){
                taxableTotString = " "+taxableTotString;
            }
        }
        bodyCheck.append("TAXABLE TOT. "+taxableTotString+"\n");

        String discount = "$" + decimalFormat.format(totalDiscount);
        if ((""+discount).length()<28){
            while (discount.length()<28){
                discount = " "+discount;
            }
        }
        bodyCheck.append("Discount "+discount+"\n");

        String totalString = "$" + decimalFormat.format(total);
        if ((""+totalString).length()<31){
            while (totalString.length()<31){
                totalString = " "+totalString;
            }
        }
        bodyCheck.append("TOTAL "+totalString+"\n");

        return bodyCheck;
    }
}
