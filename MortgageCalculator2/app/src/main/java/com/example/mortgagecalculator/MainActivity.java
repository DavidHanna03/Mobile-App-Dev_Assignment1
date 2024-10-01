package com.example.mortgagecalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // UI elements
    private EditText mortgageAmount;
    private EditText tenure;
    private EditText interestRate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize the UI elements
        mortgageAmount = findViewById(R.id.mortgageAmount);
        tenure = findViewById(R.id.tenure);
        interestRate = findViewById(R.id.interestRate);

        // Button to calculate EMI
        Button move = findViewById(R.id.Move);

        // button to calculate an move to the end
        move.setOnClickListener(v -> calculateEMI());
    }

    // Method to calculate EMI
    private void calculateEMI() {
        try {
            // Validate input
            if (mortgageAmount.getText().toString().isEmpty() ||
                    tenure.getText().toString().isEmpty() ||
                    interestRate.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter all values", Toast.LENGTH_SHORT).show();
                return;
            }

            // Parse input values safely
            double principal = Double.parseDouble(mortgageAmount.getText().toString());
            double rateInMonths = Double.parseDouble(interestRate.getText().toString()) / 12 / 100;
            double tenureInMonths = Double.parseDouble(tenure.getText().toString());


            // Ensure values are valid
            if (principal <= 0 || tenureInMonths <= 0 || rateInMonths <= 0) {
                Toast.makeText(this, "Please enter valid positive numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            //EMI formula
            double emi = principal * (rateInMonths * Math.pow(1 + rateInMonths, tenureInMonths)) /
                    (Math.pow(1 + rateInMonths, tenureInMonths) - 1);

            // Create an intent to start EMIcalculator activity
            Intent intent = new Intent(this, EMIcalculator.class);
            intent.putExtra("emi", String.format("%.2f", emi));
            startActivity(intent);
        } catch (NumberFormatException e) {
            // Handle invalid input, like letters instead of numbers
            Toast.makeText(this, "Invalid input! only enter numbers.", Toast.LENGTH_SHORT).show();
        }
    }
}
