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
    public BBorrowedAdapter(Context context, ArrayList<Book> bro_books) {
        super(context,R.layout.element_bborrowed , bro_books);
        this.bro_booklist = bro_books;
    }
    /**
     * set the adapter to a list view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BBorrowedAdapter.ViewHolder holder = null;

        if (convertView == null) {
            holder = new BBorrowedAdapter.ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_bborrowed, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.BBB_title_textview);
            holder.author = (TextView) convertView.findViewById(R.id.BBB_author_textview);
            holder.returnBtn = (Button)convertView.findViewById(R.id.BBB_returnBtn);
            holder.bookcover = (ImageView) convertView.findViewById(R.id.BBB_bookCover_imageview);
            convertView.setTag(holder);
        }
        else {
            holder = (BBorrowedAdapter.ViewHolder) convertView.getTag();
        }
        final Book element = bro_booklist.get(position);

        holder.title.setText("Title: "+(String)element.getTitle());
        holder.author.setText("Author: "+(String)element.getAuthor());
        holder.returnBtn.setOnClickListener(new View.OnClickListener() {
            //when click the button will jump to the new activity that show all the user request for this book
            /**
             * how to get parcel for a book
             * resource from:https://www.youtube.com/watch?v=WBbsvqSu0is
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent returnBook = new Intent(getContext(), BReturn.class);
                //Log.i("Bowen Test", " AAAAAA " + bro_booklist.get(position).getUnikey());
                returnBook.putExtra("book", element);
                getContext().startActivity(returnBook);
            }
        });
        if (element.getImage() != null) {
            holder.bookcover.setImageBitmap(element.getImage());
        }

        //holder.bookcover.setImageBitmap(element.getImage());
//        LayoutInflater inflater = LayoutInflater.from(getContext());
//        View customView = inflater.inflate(R.layout.element_available2, parent, false);
//
        return convertView;
    }


    /**
     * view holder for the  adapter
     */
    public static class ViewHolder {
        public TextView title;
        public TextView author;
        public Button returnBtn;
        public ImageView bookcover;
    }
}
