package by.vorobey.shoppingBasket.view;

import by.vorobey.shoppingBasket.model.Product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Repository;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@Repository
public class PrintImpl implements Print{

    @Override
    public void printWarehouse(ArrayList<Product> list) {
        System.out.println("Вам доступен следующий список товаров");
        System.out.println("id - name_Product - price");
        for (Product product : list) {
            System.out.println(product.getId()+" - "+product.getName()+" - "+product.getPrice());
        }
    }

    @Override
    public void printCheck(Object object) {
        System.out.println("Выберите куда вывести информацию о чеке.\n " +
                "Для этого введите 1 для вывода в консоль или 2 в файл");
        Scanner scanner = new Scanner(System.in);
        String print = scanner.nextLine();
        if (print.equals("1")){
            printCheckToConsole(object);
        }
        else if (print.equals("2")){
            printCheckToFile(object);
        }
        else {
            System.out.println("Вы ввели неверный формат данных. Допустимое значение 1 или 2");
            printCheck(object);
        }
    }


    @Override
    public void printCheckToConsole(Object object)  {
        String json = convertorObjectToJson(object);
        System.out.println(json);
    }

    @Override
    public void printCheckToFile(Object object) {
        String json = convertorObjectToJson(object);
        try(FileWriter writer = new FileWriter("output.txt")) {
            writer.write(json);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String convertorObjectToJson(Object object){
        String json = null;
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(object);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return json;
    }


}
