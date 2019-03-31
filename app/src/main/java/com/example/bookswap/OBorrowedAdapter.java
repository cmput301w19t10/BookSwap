package com.example.bookswap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OBorrowedAdapter extends ArrayAdapter<Book> {
    private ArrayList<Book> bro_booklist;
    private ArrayList<Boolean> swapList;
    private int red = Color.RED;
    private int green = Color.GREEN;
    private Swap swapclass;
    private OBorrowedAdapter.ViewHolder holder = null;


    public OBorrowedAdapter(Context context, ArrayList<Book> bro_books, ArrayList<Boolean> swapList) {
        super(context,R.layout.element_oborrowed , bro_books);
        this.bro_booklist = bro_books;
        this.swapList = swapList;
    }
    /**
     * set the adapter to a list view
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //OBorrowedAdapter.ViewHolder holder = null;

        if (convertView == null) {
            holder = new OBorrowedAdapter.ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_oborrowed, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.OBB_title_textview);
            holder.author = (TextView) convertView.findViewById(R.id.OBB_author_textview);
            holder.confirmBtn = (Button)convertView.findViewById(R.id.OBB_confirmBtn);
            holder.bookcover = (ImageView) convertView.findViewById(R.id.OBB_bookCover_imageview);
            convertView.setTag(holder);
        }
        else {
            holder = (OBorrowedAdapter.ViewHolder) convertView.getTag();
        }
        final Book element = bro_booklist.get(position);
//        DataBaseUtil u;
//        u = new DataBaseUtil("Bowen");
//        u.getSwap(element,new DataBaseUtil.getSwapInfo(){
//            @Override
//            public void getSwapInfo(Swap swap) {
//                swapclass =swap;
//                if (swap != null){
//                    swapclass = swap;
//                }
//                else{
//                    swapclass = null;
//                }
//                if(swapclass != null){
//                    holder.confirmBtn.setBackgroundColor(green);
//                }
//                if(swapclass==null){
//                    holder.confirmBtn.setBackgroundColor(red);
//                }
//
//            }
//        });
        if (swapList.get(position)) {
            holder.confirmBtn.setBackgroundColor(red);
        }
        else{
            holder.confirmBtn.setBackgroundColor(green);
        }
//        if(swapclass != null){
//            holder.confirmBtn.setBackgroundColor(green);
//        }else{
//            holder.confirmBtn.setBackgroundColor(red);
//        }



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
                if(swapclass == null){
                    Toast.makeText(getContext(),"Not premition to swap",Toast.LENGTH_SHORT).show();
                }else{
                    Intent returnBook = new Intent(getContext(), OBorrowedSwapActivity.class);
                    returnBook.putExtra("book", element);
                    getContext().startActivity(returnBook);
                }
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
