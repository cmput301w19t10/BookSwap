package com.example.bookswap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class BRequestedBooksAdapter extends ArrayAdapter<Book> {
    private ArrayList<Book> req_booklist;
    public BRequestedBooksAdapter(Context context, ArrayList<Book> req_books) {
        super(context,R.layout.requested_book_row , req_books);
        this.req_booklist = req_books;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.requested_book_row, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.BAB_title_textview);
            holder.author = (TextView) convertView.findViewById(R.id.BRB_author_textview);
            holder.owner = (TextView) convertView.findViewById(R.id.RBR_owner_textview);
            holder.bookcover = (ImageView) convertView.findViewById(R.id.BRB_bookCover_imageview);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Book element = req_booklist.get(position);

        holder.title.setText("Title: "+(String)element.getTitle());
        holder.author.setText("Author: "+(String)element.getAuthor());
        holder.owner.setText("Owner: "+ (String)element.getOwner());
        //holder.bookcover.setImageBitmap(element.getImage());
//        LayoutInflater inflater = LayoutInflater.from(getContext());
//        View customView = inflater.inflate(R.layout.requested_book_row, parent, false);
//
    return convertView;
    }

    public static class ViewHolder {
        public TextView title;
        public TextView author;
        public TextView owner;
        public ImageView bookcover;
    }
}
