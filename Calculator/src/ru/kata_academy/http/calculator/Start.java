package ru.kata_academy.http.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Start {
    public static void main(String[] args) throws Exception {

        String inputLine;
        String operation;
        int indexOperation;
        int lengthInputString;

        System.out.println("Введите арифметическую операцию");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (!(inputLine = bufferedReader.readLine()).equals("exit")){

            indexOperation = ArithmeticOperation.searIndexOperation(inputLine);
            operation = ArithmeticOperation.searOperation(inputLine, indexOperation);
            lengthInputString = inputLine.length();

            int oneParameter = WorkWithParameter.searchNumberParameter(inputLine, 0, indexOperation);
            int twoParameter = WorkWithParameter.searchNumberParameter(inputLine, ++indexOperation, lengthInputString);

            if (oneParameter==0 || twoParameter==0){
                throw new Exception("Некорректные данные. Попробуйте еще раз.");
            }

            int result = MathOperations.mathMethod(operation, oneParameter, twoParameter);

            System.out.println("Результат вашей операции:"
                    +WorkWithParameter.parameterTypeChecking(WorkWithParameter.list, result));
            System.out.println("Введите следующую операцию или для выхода введите exit");
        }

    }
}
