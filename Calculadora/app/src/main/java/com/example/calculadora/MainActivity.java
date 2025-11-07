package com.example.calculadora;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        setupClickListeners();
    }

    private void initializeComponents() {
        display = findViewById(R.id.display);
        calculator = new Calculator();
        updateDisplay();
    }

    private void setupClickListeners() {
        // Botões numéricos
        setNumberButtonListener(R.id.btn_0, "0");
        setNumberButtonListener(R.id.btn_1, "1");
        setNumberButtonListener(R.id.btn_2, "2");
        setNumberButtonListener(R.id.btn_3, "3");
        setNumberButtonListener(R.id.btn_4, "4");
        setNumberButtonListener(R.id.btn_5, "5");
        setNumberButtonListener(R.id.btn_6, "6");
        setNumberButtonListener(R.id.btn_7, "7");
        setNumberButtonListener(R.id.btn_8, "8");
        setNumberButtonListener(R.id.btn_9, "9");

        // Botões de operação
        setOperatorButtonListener(R.id.btn_add, "+");
        setOperatorButtonListener(R.id.btn_subtract, "-");
        setOperatorButtonListener(R.id.btn_multiply, "×");
        setOperatorButtonListener(R.id.btn_divide, "÷");

        // Botões especiais
        Button btnEquals = findViewById(R.id.btn_equals);
        Button btnClear = findViewById(R.id.btn_clear);
        Button btnDecimal = findViewById(R.id.btn_decimal);
        Button btnPercentage = findViewById(R.id.btn_percentage);
        Button btnParentheses = findViewById(R.id.btn_parentheses);

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = calculator.calculate();
                display.setText(result);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.reset();
                updateDisplay();
            }
        });

        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.setDecimal();
                updateDisplay();
            }
        });

        btnPercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.calculatePercentage();
                updateDisplay();
            }
        });

        btnParentheses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementação básica de parênteses
                calculator.appendNumber("(");
                updateDisplay();
            }
        });
    }

    private void setNumberButtonListener(int buttonId, final String number) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.appendNumber(number);
                updateDisplay();
            }
        });
    }

    private void setOperatorButtonListener(int buttonId, final String operator) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.setOperator(operator);
                updateDisplay();
            }
        });
    }

    private void updateDisplay() {
        display.setText(calculator.getDisplayText());
    }
}