package com.heba.beboooooo.book;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Network {

    public static  String title,author,publisher,date,description,thumbnail;

    public static ArrayList<DataClass> fetchBookData(String reqUrl)  {

        URL url1 = null;
        try {
            url1 = creatUrl ( reqUrl );
        } catch (MalformedURLException e) {
            e.printStackTrace ();
        }

        String jasonRes = null;

        try {
            jasonRes = MakeHttpRequest(url1);
        } catch (IOException e) {
            e.printStackTrace ();
        }

        ArrayList<DataClass> book1 = null;
        try {
            book1 = extractFromJason ( jasonRes );
        } catch (JSONException e) {
            e.printStackTrace ();
        }

        return book1;
    }

    public static URL creatUrl (String stringUrl) throws MalformedURLException {

        URL uuu = null;

        uuu = new URL ( stringUrl );

        return uuu ;

    }

    public static String MakeHttpRequest (URL uuu) throws IOException {

      String jasonResponse = "";

      if (uuu == null)
      {
          return jasonResponse;
      }

        HttpsURLConnection urlConn = null;
        InputStream inST = null;

        urlConn = (HttpsURLConnection) uuu.openConnection();

        urlConn.setRequestMethod ( "GET" );

        urlConn.connect ();

        if (urlConn.getResponseCode ()==200)
        {
            inST = urlConn.getInputStream ();
            jasonResponse = readFromStream(inST);
        }
        if (urlConn != null)
        {

            urlConn.disconnect ();
        }

        if (inST != null)
        {
            inST.close ();
        }

        return jasonResponse;
    }

    public static String readFromStream (InputStream inST) throws IOException {

        StringBuilder stBuild = new StringBuilder (  );


        if (inST != null)
        {
            InputStreamReader inSTreader = new InputStreamReader ( inST , Charset.defaultCharset () );

            BufferedReader bfReader = new BufferedReader ( inSTreader );
            String line = bfReader.readLine ();

            while (line != null)
            {
                stBuild.append ( line );
                line= bfReader.readLine ();
            }
        }
        return stBuild.toString ();

    }
    public static ArrayList<DataClass> extractFromJason (String jasonBooks) throws JSONException {

        if (TextUtils.isEmpty ( jasonBooks ) && jasonBooks == null)
        {
            return null;
        }

        ArrayList<DataClass> bookInfo = new ArrayList<>();

        JSONObject tobJson = new JSONObject (jasonBooks);
        JSONArray itemsArr = tobJson.getJSONArray( "items" );
         for (int i = 0 ;i < itemsArr.length () ;i++)
         {
             JSONObject firstItem = itemsArr.getJSONObject ( i );
             JSONObject info = firstItem.getJSONObject ( "volumeInfo" );

             if (info.has ( "title" ))
             {
                 title = info.getString ( "title" );
             }else
             {
                 title = "Title Not Found";
             }
             if (info.has ( "authors" ))
             {
                 author = info.getString ( "authors" );
             }else
             {
                 author = "Author Not Found";
             }
             if (info.has ( "publisher" ))
             {
                 publisher = info.getString ( "publisher" );
             }else
             {
                 publisher = "Publisher Not Found";
             }
             if (info.has ( "publishedDate" ))
             {
                 date = info.getString ( "publishedDate" );
             }else
             {
                 date = "Date Not Found";
             }
             if (info.has ( "description" ))
             {
                 description = info.getString ( "description");
             }else
             {
                 description = "Description Not Found";
             }
             if (info.has ( "imageLinks" ))
             {
                 JSONObject imageUrl = info.getJSONObject ( "imageLinks");
                 thumbnail = imageUrl.getString ("thumbnail"  );

             }else
             {
                 thumbnail = "";
             }

             bookInfo.add ( new DataClass ( title,author,publisher,date,description,thumbnail ) );

         }
        return bookInfo;
    }
}
