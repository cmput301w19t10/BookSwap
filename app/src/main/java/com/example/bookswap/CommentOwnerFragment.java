package com.example.bookswap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * borrower comment intreface
 */
public class CommentOwnerFragment extends Fragment {

    private static final String TAG = "Owner";
    private TextView name;
    private RatingBar rating;
    private ImageView image;
    private String userName;
    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private DataBaseUtil u;
    private View include;
    private Float average_rating;

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
        View view = inflater.inflate(R.layout.fragment_owner_comment, container, false);

        userName = getArguments().getString("userName");
        include = view.findViewById(R.id.review);
        name = include.findViewById(R.id.name);
        rating = include.findViewById(R.id.ratingBar);
        image = include.findViewById(R.id.image);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView = view.findViewById(R.id.recycler_owner);
        recyclerView.setLayoutManager(manager);

        return view;
    }

    /**
     *
     */
    @Override
    public void onStart() {
        super.onStart();
        u = new DataBaseUtil(userName);
        u.getOwnerUser("Owner", new DataBaseUtil.getUserInfo(){
            @Override
            public void getNewUser(User user, List<Review> commentList){
                adapter = new ReviewAdapter(commentList);
                recyclerView.setAdapter(adapter);
                average_rating = 0f;
                for (int i=0; i<commentList.size(); i++){
                    Review review = commentList.get(i);
                    average_rating += Float.parseFloat(review.getRating());
                }
                average_rating /= commentList.size();
                rating.setRating(average_rating);
            }
        });

        name.setText(userName);
    }
}
