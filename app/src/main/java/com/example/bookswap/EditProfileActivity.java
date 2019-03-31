package com.example.bookswap;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * activity for editing self profile
 */
public class EditProfileActivity extends AppCompatActivity {

    EditText edit_phoneNumber;
    EditText edit_address;
    String name;
    /**
     * create all views and a save button to save this edited profile
     * @param savedInstanceState saved state to create this activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        edit_phoneNumber = findViewById(R.id.edit_phoneNumber);
        edit_address = findViewById(R.id.edit_address);


        Intent intent = getIntent();
        User user = intent.getExtras().getParcelable("user");
        edit_phoneNumber.setText(user.getPhone_number());
        edit_address.setText(user.getAddress());
        name = user.getName();

        Button save_button = findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = edit_phoneNumber.getText().toString().trim();
                String address = edit_address.getText().toString().trim();
                if (check(phoneNumber, address)){
                    User user = new User(name, phoneNumber, "", address, null);
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
     * @param phoneNumber a string
     * @return
     */
    private boolean check(String phoneNumber, String address){
        if (phoneNumber.length() == 0 || address.length() == 0){
            Toast.makeText(EditProfileActivity.this, "Don't leave fields empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            Integer.parseInt(phoneNumber);
        } catch (Exception e){
            Toast.makeText(EditProfileActivity.this, "phone number is not valid", Toast.LENGTH_SHORT).show();
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
