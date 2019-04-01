package com.example.bookswap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class OBorrowedAdapter extends ArrayAdapter<Book> {
    private ArrayList<Book> bro_booklist;
    private ArrayList<Boolean> swapList;
    private int red = Color.GRAY;
    private int green = Color.WHITE;
    private Swap swapclass;
    private OBorrowedAdapter.ViewHolder holder = null;
    private DataBaseUtil u;


    public OBorrowedAdapter(Context context, ArrayList<Book> bro_books, ArrayList<Boolean> swapList) {
        super(context,R.layout.element_oborrowed,bro_books);
        this.bro_booklist = bro_books;
        this.swapList = swapList;
    }
    /**
     * set the adapter to a list view
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //OBorrowedAdapter.ViewHolder holder = null;

        if (convertView == null) {
            holder = new OBorrowedAdapter.ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_oborrowed, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.OBB_title_textview);
            holder.author = (TextView) convertView.findViewById(R.id.OBB_author_textview);
            holder.confirmBtn = (Button)convertView.findViewById(R.id.OBB_confirmBtn);
            holder.bookcover = (ImageView) convertView.findViewById(R.id.OBB_bookCover_imageview);
            convertView.setTag(holder);
        }
        else {
            holder = (OBorrowedAdapter.ViewHolder) convertView.getTag();
        }
        final Book element = bro_booklist.get(position);

        MyUser myUser = MyUser.getInstance();
        u = new DataBaseUtil(myUser.getName());
        u.getSwap(element,new DataBaseUtil.getSwapInfo(){
            @Override
            public void getSwapInfo(Swap swap) {
                swapclass = swap;

            }
        });


        if (position < swapList.size()) {
            if (swapList.get(position)) {
                holder.confirmBtn.setBackgroundColor(green);
            }
            else if (!swapList.get(position)) {
                //holder.confirmBtn.sethe;
                holder.confirmBtn.setBackgroundColor(red);
            }
        }
//        if(swapclass != null){
//            holder.confirmBtn.setBackgroundColor(green);
//        }else{
//            holder.confirmBtn.setBackgroundColor(red);
//        }



        holder.title.setText("Title: "+(String)element.getTitle());
        holder.author.setText("Author: "+(String)element.getAuthor());
        holder.confirmBtn.setOnClickListener(new View.OnClickListener() {
            //when click the button will jump to the new activity that show all the user request for this book
            /**
             * how to get parcel for a book
             * resource from:https://www.youtube.com/watch?v=WBbsvqSu0is
             * @param v
             */
            @Override
            public void onClick(View v) {
                if(!swapList.get(position)){
                    Toast.makeText(getContext(),"Waiting for Borrower to setup",Toast.LENGTH_SHORT).show();
                }
                if(swapList.get(position)){
                    Intent returnBook = new Intent(getContext(), OBorrowedSwapActivity.class);
                    returnBook.putExtra("book", element);
                    getContext().startActivity(returnBook);
                }
            }
        });
        //if (element.getImage() != null) {
            //holder.bookcover.setImageBitmap(element.getImage());
        //}
        if (element.getImageUrl()!= null){
            Picasso.get()
                .load(element.getImageUrl())
                .into(holder.bookcover);
        }
        return convertView;
    }


    /**
     * view holder for the  adapter
     */
    public static class ViewHolder {
        public TextView title;
        public TextView author;
        public Button confirmBtn;
        public ImageView bookcover;
    }
}
