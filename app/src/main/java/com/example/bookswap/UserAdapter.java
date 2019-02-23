package com.example.bookswap;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> implements Filterable {

    private List<User> userList;
    private List<User> userListFull;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View userView;
        ImageView userImage;
        TextView userName;
        TextView userPhoneNumber;
        TextView userAddress;
        TextView userEmail;

        public ViewHolder(View view){
            super(view);
            userView = view;
            userImage = view.findViewById(R.id.user_image);
            userName = view.findViewById(R.id.user_name);
            userPhoneNumber = view.findViewById(R.id.user_phoneNumber);
            userAddress = view.findViewById(R.id.user_address);
            userEmail = view.findViewById(R.id.user_email);
        }
    }

    public UserAdapter(List<User> userList){
        this.userList = userList;
        this.userListFull = new ArrayList<>(userList);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @NonNull
    @Override
    public ViewHolder UserAdapter(List<User> userList) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                User user = userList.get(position);
                Context context = holder.userView.getContext();
                context.startActivity(new Intent(context, OtherProfileActivity.class));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        User user = userList.get(i);
        holder.userImage.setImageResource(user.getImageId());
        holder.userName.setText(user.getName());
        holder.userPhoneNumber.setText(user.getPhone_number());
        holder.userEmail.setText(user.getEmail());
        holder.userAddress.setText(user.getAddress());
    }

    @Override
    public Filter getFilter() {
        return UserFilter;
    }

    private Filter UserFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<User> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(userListFull);
            } else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (User user: userListFull){
                    if(user.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(user);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            userList.clear();
            userList.addAll(((List<User>)results.values));
            notifyDataSetChanged();
        }
    };
}
