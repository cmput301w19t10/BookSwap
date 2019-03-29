package com.example.bookswap;

import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity for adding backgroud new comment
 */
public class CommentActivity extends AppCompatActivity {

    private EditText edit_comment;
    private TextView tv_rating;
    private RatingBar ratingBar;

    /**
     * create all views and backgroud button to save this comment and rating to that user
     * @param savedInstanceState saved state to create this activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        edit_comment = findViewById(R.id.edit_comment);
        tv_rating = findViewById(R.id.edit_rating);
        ratingBar = findViewById(R.id.ratingBar);
        Button save_button = findViewById(R.id.save_comment);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = edit_comment.getText().toString().trim();
                String rating = String.valueOf(ratingBar.getRating());
                if (check(comment, rating)){
                    Review review = new Review(comment, rating);
                    Intent intent = new Intent(CommentActivity.this, OtherRateActivity.class);
                    intent.putExtra("review", review);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String text = "Rating: " + ratingBar.getRating() + "/5.";
                tv_rating.setText(text);
            }
        });

    }

    /**
     * when pressing back button, the comment is not added
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
        finish();
    }

    /**
     * check if this rating is backgroud float and comment if less than 50 characters
     * @param comment new added comment
     * @param rating rating with this comment
     * @return
     */
    private boolean check(String comment, String rating){
        if (comment.length() >= 50){
            Toast.makeText(CommentActivity.this, "comment must be less 50 characters", Toast.LENGTH_SHORT);
            return false;
        }

        try {
            Float.parseFloat(rating);
        } catch (Exception e){
            Toast.makeText(CommentActivity.this, "rating must be backgroud valid float number", Toast.LENGTH_SHORT);
            return false;
        }

        return true;

    }
}