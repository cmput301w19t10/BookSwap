package com.example.bookswap;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText user_name;
    private EditText user_password;
    private EditText user_confirmpassword;
    private ProgressBar progress_bar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button button_tologin = findViewById(R.id.button_toLogin);
        button_tologin.setOnClickListener(this);
        Button button_register = findViewById(R.id.button_register);
        button_register.setOnClickListener(this);
        user_name = findViewById(R.id.user_name);
        user_password = findViewById(R.id.user_password);
        user_confirmpassword = findViewById(R.id.user_confirmpassword);
        progress_bar = findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void registerUser(){
        String email = user_name.getText().toString();
        String password = user_password.getText().toString();
        String confirm_password = user_confirmpassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(RegisterActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
            return ;
        }

        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm_password)){
            Toast.makeText(RegisterActivity.this, "Please enter password or confirmpassword", Toast.LENGTH_SHORT).show();
            return ;
        }

        if (!password.equals(confirm_password)){
            Toast.makeText(RegisterActivity.this, "Two passwords are different", Toast.LENGTH_SHORT).show();
            return ;
        }

        progress_bar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "register successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }else{
                    Toast.makeText(RegisterActivity.this, "Could not register. Please try again", Toast.LENGTH_LONG).show();
                }
            }
        });
        progress_bar.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_toLogin:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
            case R.id.button_register:
                registerUser();
                break;
            default:
                break;
        }
    }
}
