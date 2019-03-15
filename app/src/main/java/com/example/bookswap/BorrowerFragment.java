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
public class BorrowerFragment extends Fragment {

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
        Button requested = (Button) view.findViewById(R.id.Borrower_requested_btn);
        Button available = (Button) view.findViewById(R.id.Borrower_available_btn);
        requested.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), BRequestedBooksActivity.class);
                startActivity(intent);
            }
        });
        available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BAvailableActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
