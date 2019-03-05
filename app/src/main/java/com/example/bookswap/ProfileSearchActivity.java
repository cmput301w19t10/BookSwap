package com.example.bookswap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * activity for searching a person
 */
public class ProfileSearchActivity extends AppCompatActivity {

    private List<User> userList = new ArrayList<>();
    private UserAdapter adapter;

    /**
     * create views and create adapter for the search view
     * @param savedInstanceState the state saved t start the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_search);
        initUsers();
        RecyclerView recyclerView = findViewById(R.id.user_search);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new UserAdapter(userList);
        recyclerView.setAdapter(adapter);
    }

    /**
     * initialize users for testing
     */
    private void initUsers(){
        List<String> example_names = Arrays.asList("Andy", "Bob", "Vincent", "Catherine", "Sherlock", "George");
        for (int i=0; i<6; i++){
            User user = new User(example_names.get(i), "586921321", "595542478@qq.com", "xxx-xxx-xxxxx");
            userList.add(user);
        }
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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
