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
import static android.content.Intent.getIntent;
import static android.support.v4.content.ContextCompat.startActivity;


/**
 *  This is the ORequestedActivity adapter, can be using to display
 *  the book for owner requested list
 */
public class ORequestedAdapter extends ArrayAdapter<Book> {
    private ArrayList<Book> requestedList;


    /**
     * constructor
     * @param context
     * @param resource
     * @param objects
     */
    public ORequestedAdapter(Context context, int resource, ArrayList<Book> objects) {
        super(context,resource,objects);
        this.requestedList = objects;
    }


    /**
     * Get backgroud View that displays the data at the specified position in the data set. You can either
     * create backgroud View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify backgroud root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create backgroud new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        /**
         * about how to add backgroud button into the listview item and how to using viewholder
         * i get the source from:https://blog.csdn.net/comeonyangzi/article/details/26858875
         */
        if (convertView == null){ // check if given view is null, if it is we inflate
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_orequested, null);
            holder.title = (TextView) convertView.findViewById(R.id.listUsername);
            holder.author = (TextView) convertView.findViewById(R.id.listBookname);
            holder.bookcover = (ImageView)convertView.findViewById(R.id.bookCover);
            holder.button_request = (Button)convertView.findViewById(R.id.or_view);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }


        Book element = requestedList.get(position);

        holder.title.setText((String)element.getTitle());
        holder.author.setText((String)element.getAuthor());
        holder.button_request.setTag(position);
        holder.button_request.setOnClickListener(new View.OnClickListener() {
            //when click the button will jump to the new activity that show all the user request for this book
            /**
             * how to get parcel for backgroud book
             * resource from:https://www.youtube.com/watch?v=WBbsvqSu0is
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent toORequestedUser = new Intent(getContext(), ORequestedUserActivity.class);
                Log.i("Bowen Test", " AAAAAA " + requestedList.get(position).getUnikey());
                toORequestedUser.putExtra("index", requestedList.get(position));
                getContext().startActivity(toORequestedUser);
            }
        });


        if (element.getImage() != null) {
            holder.bookcover.setImageBitmap(element.getImage());
        }

        return convertView;
    }

    /**
     * build ViewHolder
     */
    public final class ViewHolder {
        public TextView title;
        public TextView author;
        public ImageView bookcover;
        public Button button_request;
    }




}
