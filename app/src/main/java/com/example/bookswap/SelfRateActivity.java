package com.example.bookswap;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * activity for seeing the rating for self
 */
public class SelfRateActivity extends AppCompatActivity {

    private User user;
    private List<Fragment> fragments = new ArrayList<>();
    SectionsPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_rate);

        ViewPager viewPager = findViewById(R.id.container);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

        Intent intent = getIntent();
        user = intent.getExtras().getParcelable("user");
        Bundle bundle = new Bundle();
        bundle.putParcelable("user", user);
        fragments.get(0).setArguments(bundle);
        fragments.get(1).setArguments(bundle);

    }

    /**
     * set up adapter
     * @param viewPager viewPager
     */
    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        fragments.add(new CommentOwnerFragment());
        fragments.add(new CommentBorrowerFragment());
        adapter.addFragment(fragments.get(0), "Owner");
        adapter.addFragment(fragments.get(1), "Borrower");
        viewPager.setAdapter(adapter);
    }
}
