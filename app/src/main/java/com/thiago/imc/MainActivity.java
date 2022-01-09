package com.thiago.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText weight;
    private EditText height;
    private TextView imc;
    private TextView result;
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        imc = findViewById(R.id.imc);
        result = findViewById(R.id.result);
        calculate = findViewById(R.id.calculate);

        weight.addTextChangedListener(inputTextWatcher);
        height.addTextChangedListener(inputTextWatcher);
    }

    private TextWatcher inputTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            boolean isWeightEmpty = weight.getText().toString().trim().isEmpty();
            boolean isHeightEmpty = height.getText().toString().trim().isEmpty();

            if(!isWeightEmpty && !isHeightEmpty) {
                calculate.setAlpha(1);
                calculate.setEnabled(true);
            } else {
                calculate.setAlpha(Float.valueOf("0.7"));
                calculate.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void calculateIMC(View view) {
        double finalWeight = Double.parseDouble(weight.getText().toString());
        double finalHeight = Double.parseDouble(height.getText().toString());

        double imcValue = finalWeight/(finalHeight * finalHeight);

        imc.setText(String.format("%.2f", imcValue));

        TextView label = findViewById(R.id.resultLabel);

        if(imcValue > 0) {
            label.setVisibility(View.VISIBLE);
        }

        if(imcValue < 18.5) {
            result.setText("Abaixo do peso");
        } else if(imcValue >= 18.5 && imcValue <= 24.99) {
            result.setText("Peso normal");
        } else if(imcValue >= 25 && imcValue <= 29.99) {
            result.setText("Acima do peso");
        } else if(imcValue >= 30 && imcValue <= 34.99) {
            result.setText("Obesidade 1");
        } else if(imcValue >= 35 && imcValue <= 39.99) {
            result.setText("Obesidade severa");
        }
    }
}