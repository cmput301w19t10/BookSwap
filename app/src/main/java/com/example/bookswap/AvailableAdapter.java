package com.example.bookswap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Custom adapter for the listview of available books
 * Handles the view of a singular item in the list
 *
 */
public class AvailableAdapter extends ArrayAdapter<Book> {
    private ArrayList<Book> availableList;


    /**
     * standard constructor from parent
     * @param context Android context
     * @param resource Android resource (used in super only)
     * @param objects ArrayList of our objects
     */
    public AvailableAdapter(Context context, int resource, ArrayList<Book> objects) {
        super(context, resource, objects);

        this.availableList = objects;
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_available, null);
        }
        // extract our recording from the list
        Book element = availableList.get(position);


        TextView title = (TextView) convertView.findViewById(R.id.listTitle);
        TextView author = (TextView) convertView.findViewById(R.id.listAuthor);
        //ImageView bookcover = (ImageView)convertView.findViewById(R.id.book_Cover);
        title.setText(element.getTitle());
        author.setText(element.getAuthor());


        return convertView;
    }

}
