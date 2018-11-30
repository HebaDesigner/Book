package com.heba.beboooooo.book;

import android.app.Activity;
import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText book_title;
    Button search;
    ProgressBar progressBar;
    ListView listView;
    Adapter adapter;
    String Books_Api;
    String ooo;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        book_title = (EditText) findViewById ( R.id.book_nameEdit_id );
        search = (Button) findViewById ( R.id.search_id );
        progressBar = (ProgressBar) findViewById ( R.id.progeressBar_id );
        listView = (ListView) findViewById ( R.id.listViewMain_id );


        progressBar.setVisibility ( View.INVISIBLE );

        adapter = new Adapter ( getApplicationContext (), R.layout.list_item, new ArrayList<DataClass> () );

        listView.setAdapter ( adapter );



        listView.setOnItemLongClickListener ( new AdapterView.OnItemLongClickListener () {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            DataClass currentBook = adapter.getItem ( position );
            int pos = parent.getPositionForView(view);
            Toast.makeText(getApplicationContext(),pos+"",Toast.LENGTH_SHORT).show();

            PopupMenu popup = new PopupMenu(MainActivity.this, view);
            popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());
            //
            popup.show();

            popup.setOnMenuItemClickListener ( new PopupMenu.OnMenuItemClickListener () {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Adapter info = (Adapter) item.getMenuInfo();
                    switch (item.getItemId ()) {
                        case R.id.delet_id:
                            adapter.remove(adapter.getItem(info.position));
                            break;
                        case R.id.detail_id:
                            Toast.makeText(getApplicationContext(), "details clicked", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            } );

            String title = currentBook.getBookTitle ();
            String author = currentBook.getAuthor ();
            String publisher = currentBook.getPublisher ();
            String publishedDate = currentBook.getPublishDate ();
            String disc = currentBook.getDescripBook ();
            String imageU = currentBook.getImageUrl ();

            Intent intent = new Intent ( getApplicationContext (), BookInfo.class );

            intent.putExtra ( "title", title );
            intent.putExtra ( "author", author );
            intent.putExtra ( "publisher", publisher );
            intent.putExtra ( "publishedDate", publishedDate );
            intent.putExtra ( "description", disc );
            intent.putExtra ( "imageLinks", imageU );

            startActivity ( intent );
            return true;
        }
        });

                search.setOnClickListener ( new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        ooo = book_title.getText ().toString ();
                        if (ooo.length () == 0) {
                            Toast.makeText ( getApplicationContext (), "please Enter Book Name", Toast.LENGTH_SHORT ).show ();
                        } else {

                            Books_Api = "https://www.googleapis.com/books/v1/volumes?q=" + ooo;

                            AsyncTask<String, Void, ArrayList<DataClass>> nTask = new AssynkTask ();
                            nTask.execute ( Books_Api );

                            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService (
                                    Activity.INPUT_METHOD_SERVICE );
                            inputMethodManager.toggleSoftInput ( InputMethodManager.HIDE_IMPLICIT_ONLY, 0 );

                            book_title.setText ( "" );


                        }
                    }
                } );
    }



    public class AssynkTask extends AsyncTask<String, Void, ArrayList<DataClass>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute ();
            progressBar.setVisibility ( View.VISIBLE );
        }

        @Override
        protected ArrayList<DataClass> doInBackground(String... strings) {

            if (strings.length < 1 || strings[0] == null) {
                return null;
            } else {
                ArrayList<DataClass> books2 = Network.fetchBookData ( strings[0] );
                return books2;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<DataClass> dataClasses) {
            adapter.clear ();
            if (dataClasses != null && !dataClasses.isEmpty ()) {

                progressBar.setVisibility ( View.INVISIBLE );
                adapter.addAll ( dataClasses );

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater ();
        inflater.inflate ( R.menu.menu, menu );
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

       super.onCreateContextMenu ( menu, v, menuInfo );
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate ( R.menu.menu, menu );
    }

    }

