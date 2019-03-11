package com.example.bookswap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OwnerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_book_main, container, false);

        return view;
    }
    /**
     * called when the user tap the Available button
     *
     * @param view
     */

    public void GoToAvailable(View view){
        //Do something in response to button
        Intent intentAva = new Intent(getActivity(), OAvailableActivity.class);
        startActivity(intentAva);
    }


}
