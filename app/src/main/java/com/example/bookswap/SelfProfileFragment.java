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

public class SelfProfileFragment extends Fragment implements View.OnClickListener{

    private User user;
    ImageView image;
    TextView name;
    TextView email;
    TextView address;
    TextView phoneNumber;
    DataBaseUtil u;
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_selfprofile, container, false);
        Button edit_button = view.findViewById(R.id.edit_profile);
        edit_button.setOnClickListener(this);

        Button review_self = view.findViewById(R.id.review_self);
        review_self.setOnClickListener(this);

        TextView find_others = view.findViewById(R.id.find_others);
        find_others.setOnClickListener(this);

        View self_include = view.findViewById(R.id.self_include);
        image = self_include.findViewById(R.id.self_image);
        name = self_include.findViewById(R.id.name);
        email = self_include.findViewById(R.id.email);
        address = self_include.findViewById(R.id.address);
        phoneNumber = self_include.findViewById(R.id.phoneNumber);
        u = new DataBaseUtil("Bowen");

        u.getOwnerUser(new DataBaseUtil.getUserInfo() {
            @Override
            public void getNewUser(User user, List<Review> commentList) {
                Log.d("wtf","12www"+user.getPhone_number());
                image.setImageResource(R.drawable.user_image);
                name.setText(user.getName());
                email.setText(user.getEmail());
                address.setText(user.getAddress());
                phoneNumber.setText(user.getPhone_number());
                Log.d("wtf","123"+user.getPhone_number());
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.edit_profile:
                intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.review_self:
                u.getOwnerUser(new DataBaseUtil.getUserInfo() {
                    @Override
                    public void getNewUser(User user, List<Review> commentList) {
                        Log.d("wtf","2222222222"+user.getPhone_number());
                        intent = new Intent(getActivity(), SelfRateActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                    }
                });
                break;
            case R.id.find_others:
                u.getOwnerUser(new DataBaseUtil.getUserInfo() {
                    @Override
                    public void getNewUser(User user, List<Review> commentList) {
                        Log.d("wtf","333333333333"+user.getPhone_number());
                        intent = new Intent(getActivity(), ProfileSearchActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1: {
                if (resultCode == RESULT_OK){
                    User user = data.getExtras().getParcelable("user");
                    u.addNewUser(user);
                    break;
                }
            } default: break;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);

    }
}
