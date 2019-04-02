package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * activity for searching a person
 */
public class ProfileSearchActivity extends AppCompatActivity {

    private List<User> userList;
    private UserAdapter adapter;
    private DataBaseUtil u = new DataBaseUtil();
    RecyclerView recyclerView;

    /**
     * create views and create adapter for the search view
     * @param savedInstanceState the state saved t start the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_search);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.user_search);
        recyclerView.setLayoutManager(manager);
    }

    /**
     * create the search menu Ui
     * @param menu the menu for search person
     * @return always true to enable creating menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search_person);
        SearchView searchView = (SearchView)searchItem.getActionView();

        //adapter = new UserAdapter(userList);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userList = new ArrayList<>();
                Log.d("wtf", newText);
                u.searchUser(newText, new DataBaseUtil.getMatchedUser() {
                    @Override
                    public void getMatchedUser(User user) {
                        userList.add(user);
                        adapter = new UserAdapter(userList);
                        recyclerView.setAdapter(adapter);
                    }
                });
                return false;
            }
        });
        return true;
    }

    /**
     * for refresh
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (userList != null) {
            adapter = new UserAdapter(userList);
            recyclerView.setAdapter(adapter);
        }
    }

}
