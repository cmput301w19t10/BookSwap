package com.example.bookswap;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Home inteface after login
 */
public class HomeActivity extends AppCompatActivity {

    private String userName;
    private DataBaseUtil u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        String utilName = intent.getStringExtra("name");
        if (utilName == null){
            userName = "Bowen";
        } else{
            userName = utilName;
        }
        u = new DataBaseUtil(userName);
    }

    /**
     * set up adapter for fragments
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new OwnerFragment(), "Owner");
        adapter.addFragment(new BorrowerFragment(), "Borrower");
        adapter.addFragment(new SelfProfileFragment(), "Profile");
        viewPager.setAdapter(adapter);
    }

    public void GoToAvailable(View view){
        //Do something in response to button
        Intent intentAva = new Intent(this, OAvailableActivity.class);
        startActivity(intentAva);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout: {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                return true;
            } case R.id.profile: {
                return true;
            } case R.id.edit: {
                u.getOwnerUser("Owner", new DataBaseUtil.getUserInfo() {
                    @Override
                    public void getNewUser(User user, List<Review> commentList) {
                        Intent intent = new Intent(HomeActivity.this, EditProfileActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                    }
                });
                return true;
            } case R.id.review: {
                Intent intent = new Intent(HomeActivity.this, SelfRateActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
                return true;
            } case R.id.search: {
                Intent intent = new Intent(HomeActivity.this, ProfileSearchActivity.class);
                startActivity(intent);
                return true;
            }default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null && !userName.equals("Bowen")){
            finish();
        }
    }
}
