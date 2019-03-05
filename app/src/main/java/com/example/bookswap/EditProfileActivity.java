package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * activity for editing self profile
 */
public class EditProfileActivity extends AppCompatActivity {

    EditText edit_name;
    EditText edit_email;
    EditText edit_phoneNumber;
    EditText edit_address;

    /**
     * create all views and a save button to save this edited profile
     * @param savedInstanceState saved state to create this activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        edit_name = findViewById(R.id.edit_name);
        edit_email = findViewById(R.id.edit_email);
        edit_phoneNumber = findViewById(R.id.edit_phoneNumber);
        edit_address = findViewById(R.id.edit_address);

        Button save_button = findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edit_name.getText().toString().trim();
                String email = edit_email.getText().toString().trim();
                String phoneNumber = edit_phoneNumber.getText().toString().trim();
                String address =edit_address.getText().toString().trim();
                if (check(email, phoneNumber)){
                    User user = new User(name, phoneNumber, email, address);
                    Intent intent = new Intent(EditProfileActivity.this, SelfProfileActivity.class);
                    intent.putExtra("user", user);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    /**
     * check if email is valid and phoneNumber is a integer
     * @param email a string
     * @param phoneNumber a string
     * @return
     */
    private boolean check(String email, String phoneNumber){
        try {
            Integer.parseInt(phoneNumber);
        } catch (Exception e){
            Toast.makeText(EditProfileActivity.this, "phone number is not valid", Toast.LENGTH_SHORT);
            return false;
        }

        return true;
    }

    /**
     * when pressing the back button, the edited profile is not saved
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
        finish();
    }
}
