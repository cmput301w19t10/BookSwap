package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelfProfileActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfprofile);
        Button edit_button = findViewById(R.id.edit_profile);
        edit_button.setOnClickListener(this);

        Button review_self = findViewById(R.id.review_self);
        review_self.setOnClickListener(this);

        TextView find_others = findViewById(R.id.find_others);
        find_others.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.edit_profile:
                startActivity(new Intent(SelfProfileActivity.this, EditProfileActivity.class));
                break;
            case R.id.review_self:
                startActivity(new Intent(SelfProfileActivity.this, SelfRateActivity.class));
                break;
            case R.id.find_others:
                startActivity(new Intent(SelfProfileActivity.this, ProfileSearchActivity.class));
                break;
            default:
                break;
        }
    }
}
