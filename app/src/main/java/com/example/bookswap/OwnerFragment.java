package com.example.bookswap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * owner interface of home
 */

public class OwnerFragment extends Fragment implements View.OnClickListener{

    private ImageButton button_accept;
    private ImageButton button_request;
    private ImageButton button_available;
    private ImageButton button_borrow;

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

        button_accept = view.findViewById(R.id.accept);
        button_accept.setOnClickListener(this);
        button_request = view.findViewById(R.id.request);
        button_request.setOnClickListener(this);
        button_available = view.findViewById(R.id.available);
        button_available.setOnClickListener(this);
        button_borrow = view.findViewById(R.id.borrow);
        button_borrow.setOnClickListener(this);

        return view;

    }

    /**
     * actions for four buttons
     * @param v view of the clicked button
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.accept:{
                Intent intent = new Intent(getActivity(), OAcceptedActivity.class);
                startActivity(intent);
                break;
            }case R.id.request:{
                Intent intent = new Intent(getActivity(), ORequestedActivity.class);
                startActivity(intent);
                break;
            }case R.id.available:{
                Intent intent = new Intent(getActivity(), OAvailableActivity.class);
                startActivity(intent);
                break;
            }case R.id.borrow:{
                Intent intent = new Intent(getActivity(), OBorrowedActivity.class);
                startActivity(intent);
                break;
            }
            default: break;
        }
    }

}
