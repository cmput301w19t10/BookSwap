package com.example.bookswap;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * activity for enabling user to login by correct password and email
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText user_name;
    private EditText user_password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressBar progress_bar;
    private String email;
    private String password;

    /**
     * create all views and set buttons for login and register
     * set Authentication for Auth listener
     * @param savedInstanceState state saved to start this activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        user_name = findViewById(R.id.user_name);
        user_password = findViewById(R.id.user_password);
        Button login_button = findViewById(R.id.button_register);
        login_button.setOnClickListener(this);
        Button toRegister_button = findViewById(R.id.button_toRegister);
        toRegister_button.setOnClickListener(this);
        progress_bar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null){
                    //startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                }
            }
        };
    }

    /**
     * start the whole activity
     * enable listener for mAuth
     */
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    /**
     * execute different operations depends on different view user clicked on
     * click on register -> go to register activity
     * click on login -> try logining user
     * @param view the view user clicked on
     */
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button_register:
                Log.d("wtf", "000");
                startSignIn();
                break;
            case R.id.button_toRegister:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * judge if user puts on correct email(or username, but only email for the first time) and password
     * if one of them is empty, app pops on a message saying "Fields are empty"
     * else if the input is incorrect, app pops on a message saying "No such Account"
     * else login successfully
     */
    private void startSignIn() {
        email = user_name.getText().toString();
        password = user_password.getText().toString();

        progress_bar.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_LONG).show();

        }else{
            //TODO
            // User user;
            // check if the username(variable email here but actually represents username) and password exists in database
            // if yes: user = getUser() (get this user);
            //         email = User.getEmail();
            Log.d("wtf", "1111");
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.d("wtf", "123");
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "No such Account", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, SelfProfileActivity.class);
                        //TODO
                        // if (User == null){
                        //     User = getUser() (get this user by email and password); }
                        // intent. putExtra("user", user);
                        startActivity(intent);
                    }
                }
            });
        }
        progress_bar.setVisibility(View.GONE);
    }

}
