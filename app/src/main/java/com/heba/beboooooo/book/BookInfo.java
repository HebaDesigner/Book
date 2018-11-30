package com.heba.beboooooo.book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BookInfo extends AppCompatActivity {

    ImageView image;
    TextView title,author,publisher,date,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_book_info );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image = (ImageView) findViewById ( R.id.bookImage_id );
        title = (TextView) findViewById ( R.id.bookName_id );
        author = (TextView) findViewById ( R.id.bookAuthor_id );
        publisher = (TextView) findViewById ( R.id.publisher_id);
        date = (TextView) findViewById ( R.id.date_id );
        description = (TextView) findViewById ( R.id.description_id );

        String title2 = getIntent ().getStringExtra ( "title" );
        String author2 = getIntent ().getStringExtra ( "authors" );
        String publisher2 = getIntent ().getStringExtra ( "publisher" );
        String date2 = getIntent ().getStringExtra ( "publishedDate" );
        String description2 = getIntent ().getStringExtra ( "description" );
        String imageUrl2 = getIntent ().getStringExtra ( "imageLinks" );

        title.setText ( title2 );
        author.setText ( author2 );
        publisher.setText ( publisher2 );
        date.setText ( date2 );
        description.setText ( description2 );
//
        if (imageUrl2.length () != 0 )
        {
            Picasso.get ()
                    .load ( imageUrl2 )
                    .placeholder ( R.drawable.fantasy )
                    .error (R.drawable.fantasy)
                    .resize ( 200,200 )
                    .into ( image );
        }else
        {
            image.setImageResource ( R.drawable.fantasy );
        }

    }
}
