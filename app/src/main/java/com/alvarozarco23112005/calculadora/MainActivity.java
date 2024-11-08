package com.alvarozarco23112005.calculadora;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentOperator;
    private Double firstValue, secondValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);


        setNumberButtonListeners();

        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberIds = { R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9 };

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String number = button.getText().toString();
                display.append(number);
            }
        };

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(numberClickListener);
        }
    }

    private void setOperatorButtonListeners() {
        findViewById(R.id.buttonAdd).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.buttonSubtract).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.buttonMultiply).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.buttonDivide).setOnClickListener(v -> setOperator("/"));

        findViewById(R.id.buttonEquals).setOnClickListener(v -> calculate());
        findViewById(R.id.buttonClear).setOnClickListener(v -> clear());
    }

    private void setOperator(String operator) {
        firstValue = Double.valueOf(display.getText().toString());
        currentOperator = operator;
        display.setText("");
    }

    private void calculate() {
        if (currentOperator != null) {
            secondValue = Double.valueOf(display.getText().toString());
            Double result = 0.0;

            switch (currentOperator) {
                case "+":
                    result = firstValue + secondValue;
                    break;
                case "-":
                    result = firstValue - secondValue;
                    break;
                case "*":
                    result = firstValue * secondValue;
                    break;
                case "/":
                    if (secondValue != 0) {
                        result = firstValue / secondValue;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            display.setText(result.toString());
            currentOperator = null;
        }
    }

    private void clear() {
        display.setText("");
        firstValue = null;
        secondValue = null;
        currentOperator = null;
    }
}
