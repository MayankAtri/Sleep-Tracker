package com.example.sleeptrackerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button previous_Sleep,trouble;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        previous_Sleep = findViewById(R.id.previous);
        trouble = findViewById(R.id.trouble);

        //Onclick Listener
        previous_Sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent history_activity = new Intent(MainActivity.this,PreviousSleep.class);
                startActivity(history_activity);
            }
        });
        trouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trouble_Sleeping = new Intent(MainActivity.this,TroubleSleeping.class);
                startActivity(trouble_Sleeping);
            }
        });
        
    }
}