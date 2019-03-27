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

public class OBorrowedAdapter extends ArrayAdapter<Book> {
    private ArrayList<Book> bro_booklist;
    public OBorrowedAdapter(Context context, ArrayList<Book> bro_books) {
        super(context,R.layout.element_oborrowed , bro_books);
        this.bro_booklist = bro_books;
    }
    /**
     * set the adapter to a list view
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        OBorrowedAdapter.ViewHolder holder = null;

        if (convertView == null) {
            holder = new OBorrowedAdapter.ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_bborrowed, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.OBB_title_textview);
            holder.author = (TextView) convertView.findViewById(R.id.OBB_author_textview);
            holder.confirmBtn = (Button)convertView.findViewById(R.id.OBB_confirmBtn);
            holder.bookcover = (ImageView) convertView.findViewById(R.id.OBB_bookCover_imageview);
            convertView.setTag(holder);
        }
        else {
            holder = (OBorrowedAdapter.ViewHolder) convertView.getTag();
        }
        Book element = bro_booklist.get(position);

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
                Intent returnBook = new Intent(getContext(), BReturn.class);
                Log.i("Bowen Test", " AAAAAA " + bro_booklist.get(position).getUnikey());
                returnBook.putExtra("index", bro_booklist.get(position));
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
        public Button confirmBtn;
        public ImageView bookcover;
    }
}