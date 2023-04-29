package com.example.sleeptrackerdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView signup;
    EditText inputEmail,inputPassword;
    Button login,google,facebook;
    private FirebaseAuth mAuth;
    ProgressDialog mlodingbar;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signup= findViewById(R.id.textView4);
        inputEmail = findViewById(R.id.Username);
        inputPassword = findViewById(R.id.Password);
        login = findViewById(R.id.button);
        google = findViewById(R.id.google);
        facebook = findViewById(R.id.facebook);
        mAuth = FirebaseAuth.getInstance();
        mlodingbar = new ProgressDialog(LoginActivity.this);
        //google signin
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this,gso);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Signin();
            }

        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();
            }
        });
    }

    private void Signin() {

        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
           try {
               task.getResult(ApiException.class);
                Activity();
           }catch(ApiException e){
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
public void Activity(){
        finish();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
}

    private void checkCredentials(){
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();

        if (email.isEmpty()) {
            showError(inputEmail, "Email is not valid");

        } else if (password.isEmpty() || password.length() < 7) {
            showError(inputPassword, "Password Length must be greater or equal to 7 characters ");

        }else{
            mlodingbar.setTitle("Login User");
            mlodingbar.setMessage("Please Wait");
            mlodingbar.setCanceledOnTouchOutside(false);
            mlodingbar.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        mlodingbar.dismiss();
                        Toast.makeText(LoginActivity.this,"Successfully Registration",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private void showError(EditText inputUsername, String s) {
        inputUsername.setError(s );
    }

}