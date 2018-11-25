package com.heba.beboooooo.book;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter  extends ArrayAdapter<DataClass> {

    ImageView book_image;
    TextView  book_name ,book_author;

    public Adapter(Context context, int resource , ArrayList<DataClass> bookArray) {
        super ( context, resource ,bookArray);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        if (convertView == null){

            convertView = LayoutInflater.from ( getContext () ).inflate ( R.layout.list_item ,parent ,false );
        }

        DataClass dataClass = getItem(position );

        book_name = convertView.findViewById ( R.id.bookName_id );
        book_author = convertView.findViewById ( R.id.bookAuthor_id );
        book_image = convertView.findViewById ( R.id.bookImage_id );

        book_name.setText ( dataClass.getBookTitle () );
        book_author.setText ( dataClass.getAuthor () );

        Picasso
                .get()
                .load(dataClass.getImageUrl ())
                .placeholder(R.drawable.fantasy)
                .into(book_image);

        return  convertView ;
    }
}
