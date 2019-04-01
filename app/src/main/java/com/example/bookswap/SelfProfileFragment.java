package com.example.bookswap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
        u = new DataBaseUtil(MyUser.getInstance().getName());


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
    /*
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            u = new DataBaseUtil(util_name);
            u.getOwnerUser("Owner", new DataBaseUtil.getUserInfo() {
                @Override
                public void getNewUser(User user, List<Review> commentList) {
                    image.setImageResource(R.drawable.user_image);
                    address.setText(user.getAddress());
                    phoneNumber.setText(user.getPhone_number());
                }
            });
        }
    }
    */

    /**
     * refresh fragment
     */
    @Override
    public void onStart() {
        super.onStart();
        u = new DataBaseUtil(MyUser.getInstance().getName());
        u.getOwnerUser("Owner", new DataBaseUtil.getUserInfo() {
            @Override
            public void getNewUser(User user, List<Review> commentList) {
                image.setImageResource(R.drawable.user_image);
                address.setText(user.getAddress());
                phoneNumber.setText(user.getPhone_number());
            }
        });
    }
}
