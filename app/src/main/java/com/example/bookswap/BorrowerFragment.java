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
 * borrower interface in home
 */
public class BorrowerFragment extends Fragment implements View.OnClickListener{

    private Button button_request;
    private Button button_available;
    private Button button_accept;
    private Button button_borrow;
    /**
     *  create all views including two buttons
     * @param inflater inflater to inflate views to this fragment
     * @param container the view contains this fragment
     * @param savedInstanceState instance saved to start this fragment
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_borrower, container, false);
        Intent intent = getActivity().getIntent();
        button_request = view.findViewById(R.id.Borrower_requested_btn);
        button_request.setOnClickListener(this);
        button_available = view.findViewById(R.id.Borrower_available_btn);
        button_available.setOnClickListener(this);
        button_accept = view.findViewById(R.id.Borrower_accept_btn);
        button_accept.setOnClickListener(this);
        button_borrow = view.findViewById(R.id.Borrower_borrowed_btn);
        button_borrow.setOnClickListener(this);


        return view;
    }

    /**
     * actions for four buttons
     * @param v view of the clicked button
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Borrower_requested_btn:{
                Intent intent = new Intent(getActivity(), BRequestedBooksActivity.class);
                startActivity(intent);
                break;
            }case R.id.Borrower_available_btn:{
                Intent intent = new Intent(getActivity(), BAvailableActivity.class);
                startActivity(intent);
                break;
            }case R.id.Borrower_accept_btn:{
                Intent intent = new Intent(getActivity(), BAcceptActivity.class);
                startActivity(intent);
                break;
            }case R.id.Borrower_borrowed_btn:{
                //TODO
                break;
            }default: break;
        }
    }
}
