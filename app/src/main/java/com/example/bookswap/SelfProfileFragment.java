package com.example.bookswap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * profile interface in home
 */
public class SelfProfileFragment extends Fragment{

    ImageView image;
    TextView name;
    TextView email;
    TextView address;
    TextView phoneNumber;
    DataBaseUtil u;
    Intent intent;

    /**
     *
     * @param inflater inflater to inflate views to this fragment
     * @param container the view contains this fragment
     * @param savedInstanceState instance saved to start this fragment
     * @return view of this fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_self_profile, container, false);
        Button edit_button = view.findViewById(R.id.edit_profile);
        Intent passed_intent = getActivity().getIntent();
        String util_name = passed_intent.getStringExtra("name");
        if (util_name == null){
            u = new DataBaseUtil("Bowen");
        } else {
            u = new DataBaseUtil(util_name);
        }
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u.getOwnerUser("Owner", new DataBaseUtil.getUserInfo() {
                    @Override
                    public void getNewUser(User user, List<Review> commentList) {
                        intent = new Intent(getActivity(), EditProfileActivity.class);
                        intent.putExtra("user", user);
                        startActivityForResult(intent, 1);
                    }
                });
            }
        });

        Button review_self = view.findViewById(R.id.review_self);
        review_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u.getOwnerUser("Owner", new DataBaseUtil.getUserInfo() {
                    @Override
                    public void getNewUser(User user, List<Review> commentList) {
                        intent = new Intent(getActivity(), SelfRateActivity.class);
                        intent.putExtra("userName", user.getName());
                        startActivity(intent);
                    }
                });
            }
        });

        Button find_others = view.findViewById(R.id.find_others);
        find_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u.getOwnerUser("Owner", new DataBaseUtil.getUserInfo() {
                    @Override
                    public void getNewUser(User user, List<Review> commentList) {
                        intent = new Intent(getActivity(), ProfileSearchActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
        /*
        Review borrower_review = new Review("nice borrower", "5.0");
        Review owner_review = new Review("nice owner", "4.0");
        user.addBorrower_review(borrower_review);
        user.addOwner_review(owner_review);
        user.setImageId(R.drawable.user_image);
        */

        View self_include = view.findViewById(R.id.self_include);
        image = self_include.findViewById(R.id.self_image);
        name = self_include.findViewById(R.id.name);
        email = self_include.findViewById(R.id.email);
        address = self_include.findViewById(R.id.address);
        phoneNumber = self_include.findViewById(R.id.phoneNumber);

        u.getOwnerUser("Owner", new DataBaseUtil.getUserInfo() {
            @Override
            public void getNewUser(User user, List<Review> commentList) {
                image.setImageResource(R.drawable.user_image);
                name.setText(user.getName());
                email.setText(user.getEmail());
                address.setText(user.getAddress());
                phoneNumber.setText(user.getPhone_number());
            }
        });



        return view;
    }

    /**
     * reactions to three buttons in this fragment
     * @param v the view of this corresponding button
     */
    /*
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.edit_profile:
                intent = new Intent(getActivity(), EditProfileActivity.class);
                intent.putExtra("user", user);
                startActivityForResult(intent, 1);
                break;
            case R.id.review_self:
                intent = new Intent(getActivity(), SelfRateActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                break;
            case R.id.find_others:
                intent = new Intent(getActivity(), ProfileSearchActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            default:
                break;
        }
    }
    */
    /**
     *get result of edited profile
     * @param requestCode a int of request in startActivityForResult
     * @param resultCode a int represents result
     * @param data returned intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1: {
                if (resultCode == RESULT_OK){
                    DataBaseUtil u = new DataBaseUtil();
                    User user = data.getExtras().getParcelable("user");
                    u.addNewUser(user);
                    image.setImageResource(R.drawable.user_image);
                    address.setText(user.getAddress());
                    phoneNumber.setText(user.getPhone_number());
                    break;
                }
            } default: break;
        }
    }

}
