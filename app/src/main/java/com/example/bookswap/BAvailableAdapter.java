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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_available2, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.BAB_title_textview);
            holder.author = (TextView) convertView.findViewById(R.id.BAB_author_textview);

            holder.bookcover = (ImageView) convertView.findViewById(R.id.BAB_bookCover_imageview);
            convertView.setTag(holder);
        }
        else {
            holder = (BAvailableAdapter.ViewHolder) convertView.getTag();
        }
        Book element = ava_booklist.get(position);

        holder.title.setText("Title: "+(String)element.getTitle());
        holder.author.setText("Author: "+(String)element.getAuthor());
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

        public ImageView bookcover;
    }

//    @Override
//    public Filter getFilter() {
//        return BookFilter;
//    }
//
//    private Filter BookFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            ArrayList<Book> filterdList = new ArrayList<>();
//            if(constraint == null|| constraint.length() == 0) {
//                // No filter implemented we return all the list
//                filterdList.addAll();
//            } else {
//                // We perform filtering operation
//                String filterPattern = constraint.toString().toLowerCase().trim();
//                for (Book book: ){
//                    if(book.getTitle().toLowerCase().contains(filterPattern)){
//                        filterdList.add(book);
//
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//            results.values = filterdList;
//            return results;
//        }
//
//        @SuppressWarnings("unchecked")
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            ava_booklist.clear();
//            ArrayList<Book> myArr = (ArrayList<Book>)results.values;
//            ava_booklist.addAll(myArr);
//        }
//    };
}

