package ru.kata_academy.http.calculator;

import java.util.ArrayList;

public class WorkWithParameter {

    public static ArrayList<String> list = new ArrayList<>();

    /*
    * Метод для поиска введенных чисел и их сотвествие условию
    * */

    public static int searchNumberParameter( String inputString, int start, int finish) throws Exception {
        int intParameter = 0;
        String parameter = null;
        parameter = inputString.substring(start,finish).trim();

        if (parameter.matches("^([1-9][0]?|10)$")){
            intParameter = Integer.parseInt(parameter);
            list.add("arabic");
        }
        if (parameter.equals("I") || parameter.equals("II") || parameter.equals("III") || parameter.equals("IV")
                || parameter.equals("V") || parameter.equals("VI") || parameter.equals("VII")
                || parameter.equals("VIII") || parameter.equals("IX") || parameter.equals("X")){

            intParameter=translateRomanNumeralsToArabic(parameter);
            list.add("roman");
        }
        if (list.size()!=0 && list.size()%2==0){
            if (!list.get(list.size()-2).equals(list.get(list.size()-1))){
                throw new Exception("Вы ввели числа разных типов");
            }
        }
        return intParameter;
    }

    /*
     * Метод проверяет результат опереции с римскими числами.
     * Проверяет не отрицательный ли результат.
     * */

    public static String parameterTypeChecking(ArrayList<String> list, int result) throws Exception {
        String answer = ""+result;
        if (list.get(list.size() - 2).equals("roman") && list.get(list.size() - 2).equals("roman")){
            if (result<=0){
                throw new Exception("Результатом работы с римскими числами могут быть только положительные числа");
            }
            answer = IntegerToRomanNumeral(result);
        }
        return answer;
    }

    /*
     * Метод для проебразования римских чисел в арабские
     * */

    public static int translateRomanNumeralsToArabic (String parameter){
        int param = 0;
        switch (parameter){
            case "I": param = 1;
                break;
            case "II": param = 2;
                break;
            case "III": param = 3;
                break;
            case "IV": param = 4;
                break;
            case "V": param = 5;
                break;
            case "VI": param = 6;
                break;
            case "VII": param = 7;
                break;
            case "VIII": param = 8;
                break;
            case "IX": param = 9;
                break;
            case "X": param = 10;
                break;
        }
        return param;
    }

    /*
     * Метод для проебразования арабских чисел в римские
     * */

    public static String IntegerToRomanNumeral(int input) {
        if (input < 1 || input > 3999)
            return "Invalid Roman Number Value";
        StringBuilder s = new StringBuilder();
        while (input >= 1000) {
            s.append("M");
            input -= 1000;        }
        while (input >= 900) {
            s.append("CM");
            input -= 900;
        }
        while (input >= 500) {
            s.append("D");
            input -= 500;
        }
        while (input >= 400) {
            s.append("CD");
            input -= 400;
        }
        while (input >= 100) {
            s.append("C");
            input -= 100;
        }
        while (input >= 90) {
            s.append("XC");
            input -= 90;
        }
        while (input >= 50) {
            s.append("L");
            input -= 50;
        }
        while (input >= 40) {
            s.append("XL");
            input -= 40;
        }
        while (input >= 10) {
            s.append("X");
            input -= 10;
        }
        while (input >= 9) {
            s.append("IX");
            input -= 9;
        }
        while (input >= 5) {
            s.append("V");
            input -= 5;
        }
        while (input >= 4) {
            s.append("IV");
            input -= 4;
        }
        while (input >= 1) {
            s.append("I");
            input -= 1;
        }
        return s.toString();
    }

}
