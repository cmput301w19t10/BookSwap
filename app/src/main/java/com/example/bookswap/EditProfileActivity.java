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

import java.util.List;

/**
 * activity for editing self profile
 */
public class EditProfileActivity extends AppCompatActivity {

    EditText edit_phoneNumber;
    EditText edit_address;
    String name;
    DataBaseUtil u;
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

        u = new DataBaseUtil(MyUser.getInstance().getName());
        u.getOwnerUser("Owner", new DataBaseUtil.getUserInfo() {
            @Override
            public void getNewUser(User user, List<Review> commentList) {
                edit_phoneNumber.setText(user.getPhone_number());
                edit_address.setText(user.getAddress());
            }
        });

        Button save_button = findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = edit_phoneNumber.getText().toString().trim();
                String address = edit_address.getText().toString().trim();
                if (check(phoneNumber, address)){
                    User user = new User(name, phoneNumber, "", address, null);
                    DataBaseUtil u = new DataBaseUtil();
                    u.addNewUser(user);
                    Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
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
}
