package com.example.bookswap;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BBorrowedAdapter extends ArrayAdapter<Book> {
    private ArrayList<Book> bro_booklist;
    private Boolean setBtnValue;
    private Boolean viewBtnVlaue;
    private BBorrowedAdapter.ViewHolder holder = null;
    private ArrayList<Boolean> swapList;





//    private Button setBtn;

    public BBorrowedAdapter(Context context, ArrayList<Book> bro_books, ArrayList<Boolean> swapList) {
        super(context,R.layout.element_bborrowed , bro_books);
        this.bro_booklist = bro_books;
        this.swapList = swapList;
    }
    /**
     * set the adapter to a list view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            holder = new BBorrowedAdapter.ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_bborrowed, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.BBB_title_textview);
            holder.author = (TextView) convertView.findViewById(R.id.BBB_author_textview);

            holder.setBtn = (Button)convertView.findViewById(R.id.BBB_setBtn);
            holder.viewBtn = (Button)convertView.findViewById(R.id.BBB_viewBtn);
            holder.bookcover = (ImageView) convertView.findViewById(R.id.BBB_bookCover_imageview);
            convertView.setTag(holder);
        }
        else {
            holder = (BBorrowedAdapter.ViewHolder) convertView.getTag();
        }
        final Book element = bro_booklist.get(position);






        if (position < swapList.size()){
            if (swapList.get(position)) {
                holder.setBtn.setVisibility(View.GONE);
            }
            else{
                holder.viewBtn.setVisibility(View.GONE);
            }
        }
        holder.title.setText("Title: "+(String)element.getTitle());
        holder.author.setText("Author: "+(String)element.getAuthor());
        holder.setBtn.setOnClickListener(new View.OnClickListener() {
            //when click the button will jump to the new activity that show all the user request for this book
            /**
             * how to get parcel for a book
             * resource from:https://www.youtube.com/watch?v=WBbsvqSu0is
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent returnBook = new Intent(getContext(), BReturnSet.class);
                returnBook.putExtra("book", element);
                getContext().startActivity(returnBook);
            }
        });
        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
            //when click the button will jump to the new activity that show all the user request for this book
            /**
             * how to get parcel for a book
             * resource from:https://www.youtube.com/watch?v=WBbsvqSu0is
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent returnBook = new Intent(getContext(), BReturnView.class);
                returnBook.putExtra("book", element);
                getContext().startActivity(returnBook);
            }
        });
        if (element.getImage() != null) {
            holder.bookcover.setImageBitmap(element.getImage());
        }


        return convertView;
    }


    /**
     * view holder for the  adapter
     */
    public static class ViewHolder {
        public TextView title;
        public TextView author;
        public Button setBtn;
        public Button viewBtn;
        public ImageView bookcover;
    }
}






