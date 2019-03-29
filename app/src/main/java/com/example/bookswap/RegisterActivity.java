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

/**
 * activity for user to register backgroud new account with email and password
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText user_name;
    private EditText user_password;
    private EditText user_confirmpassword;
    private ProgressBar progress_bar;
    private FirebaseAuth firebaseAuth;
    private String email;
    private String password;

    /**
     * set all kinds of views
     * set two buttons: login button to go to login activity, register button to register
     * set Auth
     * @param savedInstanceState saved state for creating activity
     */
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

    /**
     * register user with an valid email and password
     * when one of the fields is empty or password does not match password typed second time or email is not valid, register would failed
     */
    private void registerUser(){
        email = user_name.getText().toString();
        password = user_password.getText().toString();
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
                    User user = new User("","",email, "",password);
                    // TODO
                    // add this user into database
                    Toast.makeText(RegisterActivity.this, "register successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }else{
                    Toast.makeText(RegisterActivity.this, "Could not register. Please try again", Toast.LENGTH_LONG).show();
                }
            }
        });
        progress_bar.setVisibility(View.GONE);

    }

    /**
     * execute different operations depends on different views user clicked on
     * if user clicked on login button:  go to login activity
     * else if use clicked on register activity:   try registering user
     * @param v view the user clicked on
     */
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
