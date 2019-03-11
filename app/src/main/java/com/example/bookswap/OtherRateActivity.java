package com.example.bookswap;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * activity for showing the rating for other users
 */
public class OtherRateActivity extends AppCompatActivity {

    private User user;
    SectionsPageAdapter adapter;

    /**
     * create all views and button to add a comment
     * @param savedInstanceState saved state to create this activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_rate);
        TextView comment = findViewById(R.id.comment);

        Intent intent = getIntent();
        user = intent.getExtras().getParcelable("user");

        Bundle bundle = new Bundle();
        bundle.putParcelable("user", user);
        adapter.getItem(0).setArguments(bundle);
        adapter.getItem(1).setArguments(bundle);

        ViewPager viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(OtherRateActivity.this, CommentActivity.class), 1);
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
                    /*
                    Review review = data.getExtras().getParcelable("review");
                    user.addOwner_reviews(review);
                    */
                    //TODO
                    //update this user in database since a review is added
                    break;
                }
            }default: break;
        }
    }

    private void setupViewPager(ViewPager viewPager){
        adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new CommentOwnerFragment(), "Owner");
        adapter.addFragment(new CommentBorrowerFragment(), "Borrower");
        viewPager.setAdapter(adapter);
    }


}
