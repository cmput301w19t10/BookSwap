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

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * activity for enabling user to login by correct password and email
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText user_name;
    private EditText user_password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressBar progress_bar;
    private String name_or_email;
    private String password;
    private StringBuilder sb_email;
    private Button login_button;
    DataBaseUtil u;

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
        login_button = findViewById(R.id.button_register);
        login_button.setOnClickListener(this);
        Button toRegister_button = findViewById(R.id.button_toRegister);
        toRegister_button.setOnClickListener(this);
        progress_bar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
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
                login_button.setVisibility(View.GONE);
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
     * after judging if input are valid
     * if one of them is empty, app pops on a message saying "Fields are empty"
     * else if the input is incorrect, app pops on a message saying "No such Account"
     * else login successfully
     */
    private void startSignIn(){
        if (mAuth.getCurrentUser() != null){
            mAuth.getInstance().signOut();
        }
        name_or_email = user_name.getText().toString();
        password = user_password.getText().toString();

        sb_email = new StringBuilder();
        for (int i = 0; i < name_or_email.length(); i++) {
            char ch = name_or_email.charAt(i);
            if (ch != '@' && ch != '.') {
                sb_email.append(ch);
            }
        }


        u = new DataBaseUtil();
        progress_bar.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(name_or_email) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_LONG).show();
            return;
        }



        mAuth.signInWithEmailAndPassword(name_or_email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    u.getNameByEmail(sb_email.toString(), new DataBaseUtil.getName() {
                        @Override
                        public void getName(String name) {
                            MyUser.getInstance().setName(name);
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "No such Account", Toast.LENGTH_LONG).show();
                }
                progress_bar.setVisibility(View.GONE);
                login_button.setVisibility(View.VISIBLE);
            }
        });
        /*
        if (name_or_email.equals(sb_email.toString())) {
            Log.d("login", "getin");
            Log.d("login",name_or_email);
            DataBaseUtil u = new DataBaseUtil(name_or_email);
            u.getOwnerUser("Owner", new DataBaseUtil.getUserInfo() {
                @Override
                public void getNewUser(User user, List<Review> commentList) {
                    Log.d("login", user.getPassword() + "  " + password);

                    if (user.getPassword().equals(password)){
                        Log.d("login", "nameSuccess");
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("userName", name_or_email);
                        startActivity(intent);
                    } else {
                        Log.d("login", "name");
                        Toast.makeText(LoginActivity.this, "No such Account", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            });
        }
        */




    }

}
