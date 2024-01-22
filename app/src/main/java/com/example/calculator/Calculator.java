package com.example.calculator;

import java.util.Stack;

public class Calculator {
    public double calculate(String expression) {

        if (!isValidExpression(expression)) {
            System.out.println("Invalid expression");
            return Double.NaN; // Or handle the error in your preferred way
        }

        Stack<Character> operators = new Stack<>();
        Stack<Double> values = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            if (Character.isDigit(expression.charAt(i))) {
                int j = i;
                while (j < expression.length() && (Character.isDigit(expression.charAt(j)) || expression.charAt(j) == '.')) {
                    j++;
                }
                values.push(Double.parseDouble(expression.substring(i, j)));
                i = j;
            } else if (expression.charAt(i) == '+' || expression.charAt(i) == '-' ||
                    expression.charAt(i) == '*' || expression.charAt(i) == '/') {
                while (!operators.isEmpty() && isPrecedenceHigher(expression.charAt(i), operators.peek())) {
                    applyOperation(operators, values);
                }
                operators.push(expression.charAt(i));
                i++;
            } else if (expression.charAt(i) == '(') {
                operators.push(expression.charAt(i));
                i++;
            } else if (expression.charAt(i) == ')') {
                while (operators.peek() != '(') {
                    applyOperation(operators, values);
                }
                operators.pop(); // Discard the '('
                i++;
            } else {
                i++;
            }
        }

        while (!operators.isEmpty()) {
            applyOperation(operators, values);
        }

        return values.pop();
    }

    private static void applyOperation(Stack<Character> operators, Stack<Double> values) {
        char operator = operators.pop();
        double right = values.pop();
        double left = values.pop();

        switch (operator) {
            case '*':
                values.push(left * right);
                break;
            case '/':
                values.push(left / right);
                break;
            case '+':
                values.push(left + right);
                break;
            case '-':
                values.push(left - right);
                break;
        }
    }
    private static boolean isPrecedenceHigher(char op1, char op2) {
        if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) {
            return true;
        }
        return false;
    }

    private static boolean isValidExpression(String expression) {
        if (expression == null || expression.isEmpty()) {
            return false; // Empty expression
        }

        Stack<Character> parentheses = new Stack<>();
        boolean lastWasOperator = true; // To check consecutive operators

        for (char c : expression.toCharArray()) {
            if (c == '(') {
                parentheses.push(c);
                lastWasOperator = true;
            } else if (c == ')') {
                if (parentheses.isEmpty()) {
                    return false; // Unbalanced parentheses
                }
                parentheses.pop();
                lastWasOperator = false;
            } else if (isOperator(c)) {
                if (lastWasOperator) {
                    return false; // Consecutive operators or operator at the beginning
                }
                lastWasOperator = true;
            }else if(c =='.' && lastWasOperator){
                return false;
            }
            else if (Character.isDigit(c) || c == '.') {
                lastWasOperator = false;
            } else {
                return false; // Invalid character
            }
        }

        return parentheses.isEmpty() && !lastWasOperator;
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }


}
