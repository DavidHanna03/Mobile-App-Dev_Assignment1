package com.example.mortgagecalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class EMIcalculator extends AppCompatActivity {

    // UI elements
    private TextView emiResult;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_emicalculation);

        // Initialize the UI elements
        emiResult = findViewById(R.id.emiResult);
        backButton = findViewById(R.id.backButton);

        // get and display the EMI from intent
        String emi = getIntent().getStringExtra("emi");
        emiResult.setText("Your Monthly EMI Is:$" + emi);

        // allows user to return back to main page
        backButton.setOnClickListener(v -> finish());
    }
}
