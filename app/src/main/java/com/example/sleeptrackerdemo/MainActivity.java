package com.example.sleeptrackerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button previous_Sleep,trouble;
    TextView username;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        previous_Sleep = findViewById(R.id.previous);
        trouble = findViewById(R.id.trouble);
        username = findViewById(R.id.username);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account!=null){
            String Name=account.getDisplayName();
            username.setText(Name);
        }

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