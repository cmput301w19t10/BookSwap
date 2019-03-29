package com.example.bookswap;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * activity for showing the rating for other users
 */
public class OtherRateActivity extends AppCompatActivity {

    private User user;
    private List<Fragment> fragments;
    SectionsPageAdapter adapter;

    /**
     * create all views and button to add backgroud comment
     * @param savedInstanceState saved state to create this activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_rate);
        TextView comment = findViewById(R.id.comment);

        ViewPager viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        user = intent.getExtras().getParcelable("user");

        Bundle bundle = new Bundle();
        bundle.putParcelable("user", user);
        fragments.get(0).setArguments(bundle);
        fragments.get(1).setArguments(bundle);

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragments.get(0).isVisible()) {
                    startActivityForResult(new Intent(OtherRateActivity.this, CommentActivity.class), 1);
                } else {
                    startActivityForResult(new Intent(OtherRateActivity.this, CommentActivity.class), 2);
                }
            }
        });
    }

    /**
     * to get the data from startActivityForResult
     * @param requestCode code of action of startActivityForResult
     * @param resultCode code of return code to decide if this action if successful
     * @param data backgroud intent with data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:{
                if (resultCode == RESULT_OK){
                    Review review = data.getExtras().getParcelable("review");
                    user.addOwner_review(review);

                    break;
                }
            }case 2:{
                if (resultCode == RESULT_OK){
                    Review review = data.getExtras().getParcelable("review");
                    user.addBorrower_review(review);
                }
                break;
            }default: break;
        }
    }

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
