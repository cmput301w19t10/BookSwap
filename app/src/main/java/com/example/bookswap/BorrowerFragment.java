package com.example.bookswap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * borrower interface in home
 */

public class BorrowerFragment extends Fragment implements View.OnClickListener{

    private ImageButton button_request;
    private ImageButton button_available;
    private ImageButton button_accept;
    private ImageButton button_borrow;
    private TextView reddot;
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
        View view = inflater.inflate(R.layout.activity_book_main, container, false);
        Intent intent = getActivity().getIntent();

        button_request = view.findViewById(R.id.request);
        button_request.setOnClickListener(this);
        button_available = view.findViewById(R.id.available);
        button_available.setOnClickListener(this);
        button_accept = view.findViewById(R.id.accept);
        button_accept.setOnClickListener(this);
        button_borrow = view.findViewById(R.id.borrow);
        button_borrow.setOnClickListener(this);
        //notificationManager = NotificationManagerCompat.from(this);

        /*reddot =  view.findViewById(R.id.reddot);
        reddot.setVisibility(View.INVISIBLE);
        final DataBaseUtil u;
        u = new DataBaseUtil("Bowen");
        u.checkNotification("Borrow",new DataBaseUtil.getStatus(){
            @Override
            public void getStatus(String value){
                if(value.equals("True")){
                    reddot.setVisibility(View.VISIBLE);
                }
            }

        });*/

        return view;
    }


    /**
     * actions for four buttons
     * @param v view of the clicked button
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.request:{
                Intent intent = new Intent(getActivity(), BRequestedBooksActivity.class);
                /*final DataBaseUtil u;
                u = new DataBaseUtil("Bowen");
                u.checkNotification("Borrow",new DataBaseUtil.getStatus(){
                    @Override
                    public void getStatus(String value){
                        if(value.equals("False")){
                            reddot.setVisibility(View.INVISIBLE);
                            //notificationcall();
                            u.changeNotificationStatus("Request","False");

                        }
                    }

                });*/
                startActivity(intent);
                break;
            }case R.id.available:{
                Intent intent = new Intent(getActivity(), BAvailableActivity.class);
                startActivity(intent);
                break;
            }case R.id.accept:{
                Intent intent = new Intent(getActivity(), BAcceptActivity.class);
                startActivity(intent);
                break;
            }case R.id.borrow:{
                //TODO
                Intent intent = new Intent(getActivity(), BBorrowedActivity.class);
                startActivity(intent);
                break;
            }default: break;
        }
    }
}
