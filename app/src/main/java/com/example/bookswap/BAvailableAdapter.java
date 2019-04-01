package com.example.bookswap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Adapter for the list of requested books for borrower
 *
 * @author Chaoran
 * @version 1.0
 * @see BRequestedBooksActivity
 * @since 1.0
 */
public class BAvailableAdapter extends ArrayAdapter<Book> implements Filterable {
    private ArrayList<Book> ava_booklist;

    //private ArrayList<Book> avaListFull;


    public BAvailableAdapter(Context context, ArrayList<Book> ava_books) {
        super(context,R.layout.element_available , ava_books);
        this.ava_booklist = ava_books;

    }

    /**
     * set the adapter to a list view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BAvailableAdapter.ViewHolder holder = null;

        if (convertView == null) {
            holder = new BAvailableAdapter.ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_available, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.listTitle);
            holder.author = (TextView) convertView.findViewById(R.id.listAuthor);

            holder.bookcover = (ImageView) convertView.findViewById(R.id.bookCover);
            convertView.setTag(holder);
        }
        else {
            holder = (BAvailableAdapter.ViewHolder) convertView.getTag();
        }
        Book element = ava_booklist.get(position);

        holder.title.setText("Title: "+(String)element.getTitle());
        holder.author.setText("Author: "+(String)element.getAuthor());

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

        public ImageView bookcover;
    }


}

