package com.example.bookswap;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * activity for showing the info of user itself
 */
public class SelfProfileActivity extends AppCompatActivity implements View.OnClickListener{


    /**
     * set all views
     * one text view for going to search activity
     * one button for editing profile
     * one button for seeing the reviews
     * @param savedInstanceState saved state for creating an activity
     */
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_profile);
        Button edit_button = findViewById(R.id.edit_profile);
        edit_button.setOnClickListener(this);

        Button review_self = findViewById(R.id.review_self);
        review_self.setOnClickListener(this);

        TextView find_others = findViewById(R.id.find_others);
        find_others.setOnClickListener(this);

        View self_include = findViewById(R.id.self_include);
        ImageView image = self_include.findViewById(R.id.self_image);
        TextView name = self_include.findViewById(R.id.name);
        TextView email = self_include.findViewById(R.id.email);
        TextView address = self_include.findViewById(R.id.address);
        TextView phoneNumber = self_include.findViewById(R.id.phoneNumber);

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

    }

    /**
     * execute different operations depends the view clicked( edit or review or find_others)
     * @param v view clicked by user
     */
    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.edit_profile:
                intent = new Intent(SelfProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("user", user);
                startActivityForResult(intent, 1);

                break;
            case R.id.review_self:
                intent = new Intent(SelfProfileActivity.this, SelfRateActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                break;
            case R.id.find_others:
                intent = new Intent(SelfProfileActivity.this, ProfileSearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * to get the data from startActivityForResult
     * @param requestCode code of action of startActivityForResult
     * @param resultCode code of return code to decide if this action if successful
     * @param data intent with returned data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1: {
                if (resultCode == RESULT_OK){
                    User user = data.getExtras().getParcelable("user");
                    break;
                }
            } default: break;
        }
    }
}
