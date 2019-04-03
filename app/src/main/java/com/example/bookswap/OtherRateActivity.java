package com.example.bookswap;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * activity for showing the rating for other users
 */
public class OtherRateActivity extends AppCompatActivity {

    private String userName;
    private List<Fragment> fragments;
    SectionsPageAdapter adapter;
    DataBaseUtil u = new DataBaseUtil();
    Review review;
    ViewPager viewPager;
    private int reviewType;

    /**
     * create all views and button to add a comment
     * @param savedInstanceState saved state to create this activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_rate);

        viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        reviewType = intent.getIntExtra("review_type", 0);


        Bundle bundle = new Bundle();
        bundle.putString("userName", userName);
        fragments.get(0).setArguments(bundle);
        fragments.get(1).setArguments(bundle);

        Button comment = findViewById(R.id.comment);
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtherRateActivity.this, CommentActivity.class);
                if (fragments.get(0) == adapter.getCurrentFragment() ){
                    if (reviewType == 1) {
                        startActivityForResult(intent, 1);
                    } else {
                        Toast.makeText(OtherRateActivity.this, "Cannot be reviewed as Owner", Toast.LENGTH_SHORT).show();
                    }

                } else{
                    if (reviewType == 2) {
                        startActivityForResult(intent, 2);
                    } else {
                        Toast.makeText(OtherRateActivity.this, "Cannot be reviewed as Borrower", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    /**
     * to get the data from startActivityForResult
     * @param requestCode code of action of startActivityForResult
     * @param resultCode code of return code to decide if this action if successful
     * @param data a intent with data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:{
                if (resultCode == RESULT_OK){
                    review = data.getExtras().getParcelable("review");
                    u.addOwnerReview(userName, review);
                }
                break;
            }case 2:{
                if (resultCode == RESULT_OK){
                    review = data.getExtras().getParcelable("review");
                    u.addBorrowerReview(userName, review);
                }
                break;
            }default: break;
        }
    }

    /**
     * set up veiwpager for frgaments
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager){
        adapter = new SectionsPageAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();
        fragments.add(new CommentOwnerFragment());
        fragments.add(new CommentBorrowerFragment());
        adapter.addFragment(fragments.get(0), "Owner");
        adapter.addFragment(fragments.get(1), "Borrower");
        viewPager.setAdapter(adapter);
    }

}
