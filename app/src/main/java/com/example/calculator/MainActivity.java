package com.example.calculator;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private TextView textViewInput;
    private TextView textViewResult;
    private Calculator calculator;
    private InputValidator inputValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        inputValidator = new InputValidator();

        textViewInput = findViewById(R.id.textViewInput);
        textViewResult = findViewById(R.id.textViewResult);
        textViewResult.setMovementMethod(new ScrollingMovementMethod());

        // Set up click listeners for number buttons
        setupButtonClick(R.id.buttonOne, "1");
        setupButtonClick(R.id.buttonTwo, "2");
        setupButtonClick(R.id.buttonThree, "3");
        setupButtonClick(R.id.buttonFour, "4");
        setupButtonClick(R.id.buttonFive, "5");
        setupButtonClick(R.id.buttonSix, "6");
        setupButtonClick(R.id.buttonSeven, "7");
        setupButtonClick(R.id.buttonEight, "8");
        setupButtonClick(R.id.buttonNine, "9");
        setupButtonClick(R.id.buttonZero, "0");

        // Set up click listeners for operation buttons
        setupButtonClick(R.id.buttonDecimal, ".");
        setupButtonClick(R.id.buttonPlus, "+");
        setupButtonClick(R.id.buttonMinus, "-");
        setupButtonClick(R.id.buttonMultiply, "*");
        setupButtonClick(R.id.buttonDivide, "/");
        setupButtonClick(R.id.buttonParenthesesLeft, "(");
        setupButtonClick(R.id.buttonParenthesesRight, ")");


        // Set up click listeners for equal and clear buttons
        setupButtonClick(R.id.buttonEqual, "EQUAL");
        setupButtonClick(R.id.buttonClear, "CLEAR");
        setupButtonClick(R.id.buttonClearOne, "CLEARONE");
    }

    private void setupButtonClick(int buttonId, final String value) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> onButtonClick(value));
    }

    private void onButtonClick(String value) {
        String userInput = textViewInput.getText().toString();
        switch (value) {
            case "EQUAL":
                if (inputValidator.isLastCharDigit(userInput) || inputValidator.isLastCharBackBracket(userInput)) {
                    String result = null;
                    try {
                        result = calculator.calculate(userInput);
                    } catch (Exception e) {
                        result = e.getMessage();
                    }
                    addResult(userInput + " = " + result);
                } else {
                    createToastMessage("Invalid format");
                }
                break;
            case "CLEAR":
                textViewInput.setText("");
                break;
            case "CLEARONE":
                textViewInput.setText(removeLastChar(userInput));
                break;
            case ".":
                if (inputValidator.isLastCharDigit(userInput)) {
                    addToInput(userInput, value);
                } else {
                    createToastMessage("Invalid decimal placement");
                }
                break;
            case "(":
                if (!inputValidator.isLastCharDigit(userInput) && !inputValidator.isLastCharDecimal(userInput)) {
                    addToInput(userInput, value);
                    inputValidator.addBracket();
                } else {
                    createToastMessage("Invalid bracket placement");
                }
                break;
            case ")":
                if (inputValidator.getBrackets() > 0 && inputValidator.isLastCharDigit(userInput)) {
                    addToInput(userInput, value);
                    inputValidator.removeBracket();
                } else {
                    createToastMessage("Invalid bracket placement");
                }
                break;
            case "*":
            case "/":
                if (inputValidator.isLastCharDigit(userInput) || inputValidator.isLastCharBackBracket(userInput)) {
                    addToInput(userInput, value);
                } else if (inputValidator.isLastCharMathSymbol(userInput) && inputValidator.isLastCharDigit(removeLastChar(userInput))) {
                    addToInput(changeLastChar(userInput, value), "");
                } else {
                    createToastMessage("Invalid " + value + " placement");
                }
                break;
            case "+":
            case "-":
                if (inputValidator.isLastCharDigit(userInput) || inputValidator.isLastCharBackBracket(userInput) || inputValidator.isLastCharFrontBracket(userInput)) {
                    addToInput(userInput, value);
                } else if (inputValidator.isLastCharMathSymbol(userInput)) {
                    addToInput(changeLastChar(userInput, value), "");
                } else {
                    createToastMessage("Invalid " + value + " placement");
                }
                break;
            default:
                if (inputValidator.isNumeric(value)) {
                    if (!inputValidator.isLastCharBackBracket(userInput)) {
                        addToInput(userInput, value);
                    } else {
                        addToInput(userInput, "*" + value);
                    }
                }
        }
    }

    private String changeLastChar(String userInput, String value){
        return userInput.substring(0, userInput.length() - 1) + value;
    }

    private void addToInput(String userInput, String value){
        userInput += value;
        textViewInput.setText(userInput);
    }

    private void addResult(String inputText){
        if(!inputText.equals("")){
            textViewResult.append("\n" + inputText);
            textViewInput.setText("");
            final int scrollAmount = textViewResult.getLayout().getLineTop(textViewResult.getLineCount()) - textViewResult.getHeight();
            // if there is no need to scroll, scrollAmount will be <=0
            if (scrollAmount > 0)
                textViewResult.scrollTo(0, scrollAmount);
            else
                textViewResult.scrollTo(0, 0);
        }

    }
    public String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length()-1);
    }

    public void createToastMessage(String msg){
        Toast.makeText(getApplicationContext(),
                msg,
                Toast.LENGTH_SHORT
        ).show();
    }



}
