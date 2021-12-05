package ru.kata_academy.http.calculator;

public class ArithmeticOperation {

    static String operation;
    static int indexOperation;

    /*
    *  Метод для поиска операции
    * */

    public static int searIndexOperation(String inputString) throws Exception {
        indexOperation=0;
        if(inputString.indexOf('+')!=-1){
            indexOperation=inputString.indexOf('+');
        }
        if(inputString.indexOf('-')!=-1){
            indexOperation=inputString.indexOf('-');
        }
        if(inputString.indexOf('*')!=-1){
            indexOperation=inputString.indexOf('*');
        }
        if(inputString.indexOf('/')!=-1){
            indexOperation=inputString.indexOf('/');
        }
        if (indexOperation==0){
            throw new Exception("Вы ввели неверный оператор! Попробуйте еще раз.");
        }
        return indexOperation;
    }

    /*
    * метод для нахождения индекса операции
    * */

    public static String searOperation(String inputString, int indexOperation){
        operation=inputString.substring(indexOperation,++indexOperation);
        return operation;
    }
}
