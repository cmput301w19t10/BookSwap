package com.example.bookswap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * activity for showing others's info
 */
public class OtherProfileActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
        View other_include = findViewById(R.id.other_include);
        ImageView image = other_include.findViewById(R.id.self_image);
        TextView name = other_include.findViewById(R.id.name);
        TextView email = other_include.findViewById(R.id.email);
        TextView phoneNumber = other_include.findViewById(R.id.phoneNumber);
        TextView address = other_include.findViewById(R.id.address);

        /*
        Intent intent = getIntent();
        user = intent.getExtras().getParcelable("user");
        Bitmap bitmap = user.getImage();
        if (bitmap != null) {
            image.setImageBitmap(bitmap);
        } else {
            image.setImageResource(R.drawable.user_image);
        }
        name.setText(user.getName());
        email.setText(user.getEmail());
        address.setText(user.getAddress());
        phoneNumber.setText(user.getPhone_number());
        */

        Button other_review = findViewById(R.id.other_review);
        other_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtherProfileActivity.this, OtherRateActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);

            }
        });
    }
}
