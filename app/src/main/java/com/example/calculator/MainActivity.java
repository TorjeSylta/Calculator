package com.example.calculator;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewInput;
    private TextView textViewResult;

    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();

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
    }

    private void setupButtonClick(int buttonId, final String value) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> onButtonClick(value));
    }

    private void onButtonClick(String value) {
        if (value.equals("EQUAL")) {
            double result = calculator.calculate(textViewInput.getText().toString());
            addResult(textViewInput.getText().toString()
                    + " = "
                    + result);
        } else if (value.equals("CLEAR")) {
            textViewInput.setText("");

        } else {
            // Append the button value to the TextView
            String currentText = textViewInput.getText().toString();
            textViewInput.append(value);
        }
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

}
