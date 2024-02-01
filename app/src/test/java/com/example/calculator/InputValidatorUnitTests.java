package com.example.calculator;
import org.junit.Test;
import static org.junit.Assert.*;

public class InputValidatorUnitTests {

    @Test
    public void testIsLastCharMathSymbol() {
        InputValidator validator = new InputValidator();
        assertTrue(validator.isLastCharMathSymbol("123*"));
        assertTrue(validator.isLastCharMathSymbol("123/"));
        assertTrue(validator.isLastCharMathSymbol("123+"));
        assertTrue(validator.isLastCharMathSymbol("123-"));
        assertFalse(validator.isLastCharMathSymbol("2"));
        assertFalse(validator.isLastCharMathSymbol(""));
    }

    @Test
    public void testIsLastCharDigit() {
        InputValidator validator = new InputValidator();
        assertTrue(validator.isLastCharDigit("2"));
        assertTrue(validator.isLastCharDigit("-2"));
        assertTrue(validator.isLastCharDigit("231245"));
        assertFalse(validator.isLastCharDigit("+"));
        assertFalse(validator.isLastCharDigit(""));
    }

    @Test
    public void testIsLastCharFrontBracket() {
        InputValidator validator = new InputValidator();
        assertTrue(validator.isLastCharFrontBracket("2*("));
        assertFalse(validator.isLastCharFrontBracket("2"));
        assertFalse(validator.isLastCharFrontBracket(""));
    }

    @Test
    public void testIsLastCharBackBracket() {
        InputValidator validator = new InputValidator();
        assertTrue(validator.isLastCharBackBracket("(2+3)"));
        assertFalse(validator.isLastCharBackBracket("(2+3)+3"));
        assertFalse(validator.isLastCharBackBracket(""));
    }

    @Test
    public void testIsLastCharDecimal() {
        InputValidator validator = new InputValidator();
        assertTrue(validator.isLastCharDecimal("3."));
        assertFalse(validator.isLastCharDecimal("3"));
        assertFalse(validator.isLastCharDecimal(".3"));
        assertFalse(validator.isLastCharDecimal(""));
    }

    @Test
    public void testAddAndRemoveBracket() {
        InputValidator validator = new InputValidator();
        validator.addBracket();
        validator.addBracket();
        assertEquals(2, validator.getBrackets());
        validator.removeBracket();
        assertEquals(1, validator.getBrackets());
    }

    @Test
    public void testIsNumeric() {
        InputValidator validator = new InputValidator();
        assertTrue(validator.isNumeric("123"));
        assertTrue(validator.isNumeric("-123"));
        assertTrue(validator.isNumeric("3.14"));
        assertFalse(validator.isNumeric("abc"));
        assertFalse(validator.isNumeric(""));
    }
}
