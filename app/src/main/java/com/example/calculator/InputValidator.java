package com.example.calculator;

import java.util.Arrays;

public class InputValidator {

    private final Character[] mathSymbols = {'*','/', '+', '-'};

    private int brackets = 0;

    public InputValidator(){
    }

    public boolean isLastCharMathSymbol(String s){
        if(s.isEmpty()){
            return false;
        }
        return Arrays.asList(mathSymbols).contains(s.charAt(s.length()-1));
    }

    public boolean isLastCharDigit(String s){
        if(s.isEmpty()){
            return false;
        }
        char lastChar = s.charAt(s.length() - 1);
        return Character.isDigit(lastChar);
    }

    public boolean isLastCharFrontBracket(String s){
        if(s.isEmpty()){
            return false;
        }
        char lastChar = s.charAt(s.length() - 1);
        return lastChar == '(';
    }

    public boolean isLastCharBackBracket(String s){
        if(s.isEmpty()){
            return false;
        }
        char lastChar = s.charAt(s.length() - 1);
        return lastChar == ')';
    }

    public boolean isLastCharDecimal(String s){
        if(s.isEmpty()){
            return false;
        }
        char lastChar = s.charAt(s.length() - 1);
        return lastChar == '.';
    }

    public void addBracket(){
        brackets++;
    }

    public void removeBracket(){
        brackets--;
    }

    public int getBrackets() {
        return brackets;
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
