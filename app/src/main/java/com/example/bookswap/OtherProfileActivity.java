package com.example.bookswap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * activity for showing others's info
 */
public class OtherProfileActivity extends AppCompatActivity {

    DataBaseUtil u;
    private ImageView image;
    private TextView name;
    private TextView email;
    private TextView phoneNumber;
    private TextView address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
        View other_include = findViewById(R.id.other_include);
        image = other_include.findViewById(R.id.self_image);
        name = other_include.findViewById(R.id.name);
        email = other_include.findViewById(R.id.email);
        phoneNumber = other_include.findViewById(R.id.phoneNumber);
        address = other_include.findViewById(R.id.address);

        Intent intent = getIntent();
        u = new DataBaseUtil(intent.getStringExtra("userName"));
        image.setImageResource(R.drawable.user_image);

        u.getOwnerUser(new DataBaseUtil.getUserInfo() {
            @Override
            public void getNewUser(User user, List<Review> commentList) {
                name.setText(user.getName());
                email.setText(user.getEmail());
                address.setText(user.getAddress());
                phoneNumber.setText(user.getPhone_number());
            }
        });


        Button other_review = findViewById(R.id.other_review);
        other_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u.getOwnerUser(new DataBaseUtil.getUserInfo() {
                    @Override
                    public void getNewUser(User user, List<Review> commentList) {
                        Intent intent = new Intent(OtherProfileActivity.this, OtherRateActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
