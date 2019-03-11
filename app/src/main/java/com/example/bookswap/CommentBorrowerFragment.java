package com.example.bookswap;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class CommentBorrowerFragment extends Fragment {

    private static final String TAG = "Borrower";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_borrower_comment, container, false);

        User user = getArguments().getParcelable("user");
        View include = view.findViewById(R.id.review);
        TextView name = include.findViewById(R.id.name);
        RatingBar rating = include.findViewById(R.id.ratingBar);
        TextView comment = include.findViewById(R.id.comment);
        List<Review> list_reviews = user.getBorrowerReviews();
        if (list_reviews.size() > 0){
            int len = list_reviews.size();
            comment.setText(list_reviews.get(len-1).getComment());
            rating.setRating(Float.parseFloat(list_reviews.get(len-1).getRating()));
        }
        name.setText(user.getName());

        return view;
    }
}
