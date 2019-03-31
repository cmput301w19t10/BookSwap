package com.example.bookswap;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder>{

    private List<Review> reviewList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        View reviewView;
        RatingBar ratingStar;
        TextView comment;

        public ViewHolder(View view){
            super(view);
            reviewView = view;
            ratingStar = view.findViewById(R.id.ratingbar);
            comment = view.findViewById(R.id.comment);
        }
    }

    public ReviewAdapter(List<Review> reviewList){
        this.reviewList = reviewList;
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.reviewView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.reviewView.getContext(), "review from motherfucker", Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Review review = reviewList.get(i);
        holder.ratingStar.setRating(Float.parseFloat(review.getRating()));
        holder.comment.setText(review.getComment());
    }
}
