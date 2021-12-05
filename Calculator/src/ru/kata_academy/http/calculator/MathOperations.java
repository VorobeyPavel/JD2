package ru.kata_academy.http.calculator;

public class MathOperations {

    public static int sum (int firstParameter, int twoParameter){
        return firstParameter + twoParameter;
    }

    public static int subtraction (int firstParameter, int twoParameter) {
        return firstParameter - twoParameter;
    }

    public static int multiply (int firstParameter, int twoParameter) {
        return firstParameter * twoParameter;
    }

    public static int division (int firstParameter, int twoParameter) throws Exception {
        if (twoParameter==0){
            throw new Exception("На ноль делить нельзя. Введите другие данные.");
        }
        return firstParameter / twoParameter;
    }

    public static int mathMethod (String operation, int firstParameter, int twoParameter) throws Exception {
        if (operation.equals("+")){
            return sum(firstParameter,twoParameter);
        }
        if (operation.equals("-")){
            return subtraction(firstParameter,twoParameter);
        }
        if (operation.equals("*")){
            return multiply(firstParameter,twoParameter);
        }
        if (operation.equals("/")){
            return division(firstParameter,twoParameter);
        }
        return 0;
    }


}
