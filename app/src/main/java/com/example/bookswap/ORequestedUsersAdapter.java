package com.example.bookswap;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 *  This is the ORequestedUserActivity adapter, can be using to display
 *  the user list for Owner book
 */
public class ORequestedUsersAdapter extends ArrayAdapter<String> {
    private Context context;
    private Book book;
    private ArrayList<String> userList;
    private String username;
    private DataBaseUtil u;

    /**
     * constructor
     * @param context
     * @param book
     * @param userList
     */
    public ORequestedUsersAdapter(Context context, Book book, ArrayList<String> userList) {
        super(context, 0, userList);
        this.context = context;
        this.book = book;
        this.userList = userList;
    }


    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        /**
         * about how to add a button into the listview item and how to using viewholder
         * i get the source from:https://blog.csdn.net/comeonyangzi/article/details/26858875
         */
        if (convertView == null){ // check if given view is null, if it is we inflate
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_orequesteduser, null);
            holder.Username = (TextView) convertView.findViewById(R.id.listUsername);
            holder.Bookname = (TextView) convertView.findViewById(R.id.listBookname);
            holder.bookcover = (ImageView)convertView.findViewById(R.id.bookCover);
            holder.button_accept = (Button)convertView.findViewById(R.id.oru_accept);
            holder.button_decline = (Button)convertView.findViewById(R.id.oru_decline);
            convertView.setTag(holder);
        }
        else {
            holder = (ORequestedUsersAdapter.ViewHolder)convertView.getTag();
        }


        holder.Username.setText(userList.get(position));
        holder.Bookname.setText((String)book.getTitle());

        /**
         * click the adapter to enter the user profile
         */
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = userList.get(position);
                Intent intent = new Intent(getContext(), OtherProfileActivity.class);
                intent.putExtra("userName", userList.get(position));
                intent.putExtra("review_type",0);
                getContext().startActivity(intent);
            }
        };
        convertView.setOnClickListener(listener);


        /**
         * get the userlist who want to Accept this book
         * from database
         */
        holder.button_accept.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                DataBaseUtil u = new DataBaseUtil("Bowen");
//                u.acceptAndDeleteOther(userList.get(position), book);
//                userList.clear();
                Intent goSwap = new Intent(getContext(),ORequestedSwapActivity.class);
                goSwap.putExtra("book",book);
                goSwap.putExtra("user", userList.get(position));
                notifyDataSetChanged();
                getContext().startActivity(goSwap);
            }
        });

        holder.button_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User myUser = MyUser.getInstance();
                u = new DataBaseUtil(myUser.getName());
                u.declineUser(userList.get(position), book);
                userList.remove(position);
                if(userList.size()==0);{
                    u.changeStatus(book,"Available");
                }
                notifyDataSetChanged();

            }
        });


        if (book.getImageUrl()!= null){
            Picasso.get()
                    .load(book.getImageUrl())
                    .into(holder.bookcover);
        }

        return convertView;

    }

    /**
     * build the ViewHolder
     */
    public final class ViewHolder {
        public TextView Username;
        public TextView Bookname;
        public ImageView bookcover;
        public Button button_accept;
        public Button button_decline;
    }




}
