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

public class OAcceptedAdapter extends ArrayAdapter<Book> {
    private ArrayList<Book> acp_booklist;
    private Book book;
    public OAcceptedAdapter(Context context, ArrayList<Book> acp_books) {
        super(context,R.layout.o_accepted_element , acp_books);
        this.acp_booklist = acp_books;
        //this.book = book;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        OAcceptedAdapter.ViewHolder holder = null;
        if (convertView == null) { // check if given view is null, if it is we inflate
            holder = new OAcceptedAdapter.ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.o_accepted_element, null);
            holder.title = (TextView) convertView.findViewById(R.id.OAB_title_textview);
            holder.author = (TextView) convertView.findViewById(R.id.OAB_author_textview);
            holder.bookcover = (ImageView) convertView.findViewById(R.id.OAB_bookCover_imageview);
            holder.button_swap = (Button) convertView.findViewById(R.id.OAB_swpBtn);
            convertView.setTag(holder);
        } else {
            holder = (OAcceptedAdapter.ViewHolder) convertView.getTag();
        }
        final Book element = acp_booklist.get(position);

        Log.d("viewbook0",element.getUnikey());

        holder.title.setText("Title: "+(String)element.getTitle());
        holder.author.setText("Author: "+(String)element.getAuthor());
        holder.button_swap.setOnClickListener(new View.OnClickListener() {
            //when click the button will jump to the new activity that show all the user request for this book
            /**
             * how to get parcel for a book
             * resource from:https://www.youtube.com/watch?v=WBbsvqSu0is
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent goSwap = new Intent(getContext(),OAcceptedSwapActivity.class);
                goSwap.putExtra("book",element);

                Log.d("viewbook1",element.getUnikey());
                getContext().startActivity(goSwap);
            }
        });
        if (element.getImage() != null) {
            holder.bookcover.setImageBitmap(element.getImage());
        }
        return convertView;
    }


    public final class ViewHolder {
        public TextView title;
        public TextView author;
        public ImageView bookcover;
        public Button button_swap;
    }
}

