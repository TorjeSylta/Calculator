package com.example.calculator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorUnitTests {

    @Test
    public void testEvaluateBasicExpression() {
        Calculator calculator = new Calculator();
        assertEquals("5.0", calculator.evaluateExpression("2+3"));
        assertEquals("6.0", calculator.evaluateExpression("8-2"));
        assertEquals("9.0", calculator.evaluateExpression("3*3"));
        assertEquals("1.0", calculator.evaluateExpression("3/3"));
    }

    @Test
    public void testEvaluateBasicExpressionWithDecimals() {
        Calculator calculator = new Calculator();
        assertEquals("5.0", calculator.evaluateExpression("2.0+3.0"));
        assertEquals("6.0", calculator.evaluateExpression("8.0-2.0"));
        assertEquals("9.0", calculator.evaluateExpression("3.0*3.0"));
        assertEquals("1.0", calculator.evaluateExpression("3.0/3.0"));
    }

    @Test
    public void testEvaluateBasicExpressionWithNegativeResult() {
        Calculator calculator = new Calculator();
        assertEquals("-5.5", calculator.evaluateExpression("-2.5-3.0"));
        assertEquals("-6.0", calculator.evaluateExpression("-8.0+2.0"));
        assertEquals("-9.9", calculator.evaluateExpression("-3.3*3.0"));
        assertEquals("-1.0", calculator.evaluateExpression("-3.0/3.0"));
    }

    @Test
    public void testEvaluateLongerExpressions() {
        Calculator calculator = new Calculator();
        assertEquals("9.0", calculator.evaluateExpression("3+5.0+10-9"));
        assertEquals("3.0", calculator.evaluateExpression("-3+5.0+10-9"));
        assertEquals("38.0", calculator.evaluateExpression("-3+5.0*10-9"));
        assertEquals("-11.5", calculator.evaluateExpression("-3+5.0/10-9"));
        assertEquals("1024.494737", calculator.evaluateExpression("352.3351*55.9476/19.241"));
    }

    @Test
    public void testCalculator() {
        Calculator calculator = new Calculator();
        /**
        assertEquals("6.0", calculator.calculate("3+3"));
        assertEquals("7.0", calculator.calculate("(3+4)"));
        assertEquals("8.0", calculator.calculate("(3+5"));
        assertEquals("9.0", calculator.calculate("((((3+6"));
        assertEquals("0.0", calculator.calculate("()"));
        assertEquals("0.0", calculator.calculate("("));
        assertEquals("10.0", calculator.calculate("(10)"));
         **/
        assertEquals("254.0", calculator.calculate("(10)+((10*2)-5)*(10+(2*3))+((1+1)+(1+1))"));
        assertEquals("310.0", calculator.calculate("(10)+(((10*2)-5)*((10+(2*3))+(((1+1)+(1+1))"));
    }
}
