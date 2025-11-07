package com.example.calculadora;

public class Calculator {
    private double firstOperand;
    private double secondOperand;
    private String operator;
    private boolean waitingForSecondOperand;
    private StringBuilder currentInput;
    private boolean showResult;

    public Calculator() {
        reset();
    }

    public void reset() {
        firstOperand = 0;
        secondOperand = 0;
        operator = "";
        waitingForSecondOperand = false;
        currentInput = new StringBuilder();
        showResult = false;
    }

    public void appendNumber(String number) {
        if (showResult) {
            reset();
        }
        if (waitingForSecondOperand) {
            currentInput = new StringBuilder();
            waitingForSecondOperand = false;
        }
        currentInput.append(number);
        showResult = false;
    }

    public void setOperator(String op) {
        if (currentInput.length() > 0) {
            firstOperand = Double.parseDouble(currentInput.toString());
            operator = op;
            waitingForSecondOperand = true;
            showResult = false;
        }
    }

    public void setDecimal() {
        if (showResult) {
            reset();
        }
        if (waitingForSecondOperand) {
            currentInput = new StringBuilder("0");
            waitingForSecondOperand = false;
        }
        if (currentInput.toString().contains(".")) {
            return;
        }
        if (currentInput.length() == 0) {
            currentInput.append("0");
        }
        currentInput.append(".");
        showResult = false;
    }

    public void calculatePercentage() {
        if (currentInput.length() > 0) {
            double value = Double.parseDouble(currentInput.toString());
            value = value / 100;
            currentInput = new StringBuilder(String.valueOf(value));
        }
    }

    public String calculate() {
        if (currentInput.length() > 0 && !operator.isEmpty()) {
            secondOperand = Double.parseDouble(currentInput.toString());

            double result = 0;
            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "×":
                    result = firstOperand * secondOperand;
                    break;
                case "÷":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        return "Erro: Divisão por zero";
                    }
                    break;
            }

            String resultStr;
            if (result == (long) result) {
                resultStr = String.valueOf((long) result);
            } else {
                resultStr = String.valueOf(result);
            }

            showResult = true;
            return resultStr;
        }
        return currentInput.toString();
    }

    public String getDisplayText() {
        if (showResult) {
            // Mostra apenas o resultado
            if (currentInput.length() > 0) {
                double value = Double.parseDouble(currentInput.toString());
                if (value == (long) value) {
                    return String.valueOf((long) value);
                } else {
                    return String.valueOf(value);
                }
            }
            return "0";
        } else if (operator.isEmpty() || waitingForSecondOperand) {
            // Mostra a expressão completa: "15 + 3"
            if (operator.isEmpty()) {
                return currentInput.length() > 0 ? currentInput.toString() : "0";
            } else {
                return formatNumber(firstOperand) + " " + operator + " " + (currentInput.length() > 0 ? currentInput.toString() : "");
            }
        } else {
            // Mostra a expressão completa com segundo operando
            return formatNumber(firstOperand) + " " + operator + " " + currentInput.toString();
        }
    }

    private String formatNumber(double number) {
        if (number == (long) number) {
            return String.valueOf((long) number);
        } else {
            return String.valueOf(number);
        }
    }

    public String getCurrentInput() {
        if (currentInput.length() == 0) {
            return "0";
        }
        return currentInput.toString();
    }

    public void backspace() {
        if (currentInput.length() > 0 && !showResult) {
            currentInput.deleteCharAt(currentInput.length() - 1);
        }
    }
}