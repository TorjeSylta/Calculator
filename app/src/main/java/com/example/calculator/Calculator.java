package com.example.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

import kotlin.reflect.KParameter;

public class Calculator {

    public String calculate(String expression) throws Exception {

        Stack<Integer> parentheses = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            if(expression.charAt(i) == '('){
                parentheses.push(i);
                System.out.println(i + "   " + expression);
                System.out.println(parentheses.toString());
                i++;
            }else if (expression.charAt(i) == ')'){
                int firstParenthesis = parentheses.pop();
                String solvedValue = evaluateExpression (expression.substring(firstParenthesis+1, i));
                expression = expression.substring(0, firstParenthesis) + solvedValue + expression.substring(i+1);
                if(!parentheses.isEmpty()){
                    i = parentheses.peek() + 1;
                }else{
                    i = 0;
                }
            }else {
                i++;
            }
        }
        StringBuilder expressionBuilder = new StringBuilder(expression);
        while(!parentheses.isEmpty()){
            System.out.println("1:   " + expressionBuilder.toString());
            int forwardParenthesis = parentheses.pop();
            System.out.println(forwardParenthesis);
            expressionBuilder.append(')');
            System.out.println("2:   " + expressionBuilder.toString());
            String solvedValue = evaluateExpression (expressionBuilder.substring(forwardParenthesis+1, expressionBuilder.length()-1));

            expressionBuilder = new StringBuilder(expressionBuilder.substring(0, forwardParenthesis) + solvedValue + expressionBuilder.substring(expressionBuilder.length()));
            System.out.println("3:   " + expressionBuilder.toString());
        }

        expression = expressionBuilder.toString();
        expression = evaluateExpression(expression);

        return expression;
    }

    public String evaluateExpression (String expression) throws Exception {

        if(expression.equals("")){
            return "0";
        }else if(expression.contains("/0")){
            throw new Exception("Cant divide by zero");
        }

        Stack<Character> operatorStack = new Stack<>();
        Stack<Double> numericValuesStack = new Stack<>();

        if(!expression.isEmpty() && (expression.charAt(0) == '-' || expression.charAt(0) == '+')){
            while(numericValuesStack.isEmpty()){
                int x = 1;
                while (x < expression.length() && (Character.isDigit(expression.charAt(x)) || expression.charAt(x) == '.')) {
                    x++;
                }
                numericValuesStack.push(Double.parseDouble(expression.substring(0, x)));
                expression = expression.substring(x);
            }
        }

        int i = 0;
        while (i < expression.length()) {
            if (Character.isDigit(expression.charAt(i))) {
                int j = i;
                while (j < expression.length() && (Character.isDigit(expression.charAt(j)) || expression.charAt(j) == '.')) {
                    j++;
                }
                numericValuesStack.push(Double.parseDouble(expression.substring(i, j)));
                i = j;
            } else if (expression.charAt(i) == '+' || expression.charAt(i) == '-' ||
                    expression.charAt(i) == '*' || expression.charAt(i) == '/') {
                while (!operatorStack.isEmpty() && isCurrentOperatorHigherPrecedence(expression.charAt(i), operatorStack.peek())) {
                    numericValuesStack.push(applyOperation(operatorStack.pop(), numericValuesStack.pop(), numericValuesStack.pop()));
                }
                operatorStack.push(expression.charAt(i));
                i++;
            }
        }

        while (!operatorStack.isEmpty()) {
            numericValuesStack.push(applyOperation(operatorStack.pop(), numericValuesStack.pop(), numericValuesStack.pop()));
        }

        double roundedValue = roundDecimalValue(numericValuesStack.pop(), 6);
        return Double.toString(roundedValue);
    }

    private double applyOperation(char operator, double right, double left) {
        double returnValue = 0;
        switch (operator) {
            case '*':
                returnValue = left * right;
                break;
            case '/':
                returnValue = left / right;
                break;
            case '+':
                returnValue = left + right;
                break;
            case '-':
                returnValue = left - right;
                break;
        }
        return roundDecimalValue(returnValue, 10);
    }
    private static boolean isCurrentOperatorHigherPrecedence(char currentOperator, char topOfStackOperator) {
        return (currentOperator == '+' || currentOperator == '-') && (topOfStackOperator == '*' || topOfStackOperator == '/');
    }

    private double roundDecimalValue(double value, int places){
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
