package com.example.bookswap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class OSearchAdapter extends ArrayAdapter<Book> implements Filterable{
    private ArrayList<Book> availableList;
    private ArrayList<Book> avaListFull;


    /**
     * standard constructor from parent
     * @param context Android context
     * @param resource Android resource (used in super only)
     * @param objects ArrayList of our objects
     */
    public OSearchAdapter(Context context, int resource, ArrayList<Book> objects) {
        super(context, resource, objects);

        this.availableList = objects;
        this.avaListFull = new ArrayList<>(availableList);
    }



    /**
     * Method used to create the view for each individual item
     * @param position index of the item in the list
     * @param convertView view object for method to convert, is later returned
     * @param parent parent activity
     * @return A view object used by Android to display our individual item in list view
     */

    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null){ // check if given view is null, if it is we inflate
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_osearch, null);
        }
        // extract our recording from the list
        Log.d("POSITION", Integer.toString(position));
        Book element = availableList.get(position);


        TextView title = (TextView) convertView.findViewById(R.id.sTitle);
        TextView author = (TextView) convertView.findViewById(R.id.sAuthor);
        ImageView bookcover = (ImageView)convertView.findViewById(R.id.sBookCover);
        title.setText(element.getTitle());
        author.setText(element.getAuthor());
        if (element.getImage() != null) {
            bookcover.setImageBitmap(element.getImage());
        }

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return BookFilter;
    }

    private Filter BookFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Book> filterdList = new ArrayList<>();
            if(constraint == null|| constraint.length() == 0) {
                filterdList.addAll(avaListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Book book: avaListFull){
                    if(book.getTitle().toLowerCase().contains(filterPattern)){
                        filterdList.add(book);

                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterdList;
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            availableList.clear();
            availableList.addAll(((ArrayList<Book>)results.values));
        }
    };


}
