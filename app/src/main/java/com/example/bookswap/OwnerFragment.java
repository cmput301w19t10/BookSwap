package com.example.bookswap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * owner interface of home
 */
public class OwnerFragment extends Fragment {

    private Button button_accept;

    /**
     * create all views
     * @param inflater inflater to inflate views to this fragment
     * @param container the view contains this fragment
     * @param savedInstanceState instance saved to start this fragment
     * @return view of this fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_book_main, container, false);

        button_accept = (Button)view.findViewById(R.id.button3);
        button_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BAcceptActivity.class);
                startActivity(intent);
            }
        });
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

    public void GoToRequestActivity(View view){
        //Do something in response to button
        Intent intent = new Intent(getActivity(), ORequestedActivity.class);
        startActivity(intent);
    }

//    public void GoToBacceptActivity(View view){
//        //Do something in response to button
//        Intent intent = new Intent(this, BAcceptActivity.class);
//        startActivity(intent);
//    }



}
