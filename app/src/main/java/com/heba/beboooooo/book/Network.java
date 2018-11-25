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
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Network {

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

        urlConn.setRequestMethod ( "GET" );

        urlConn.connect ();

        if (urlConn.getResponseCode ()==200)
        {
            inST = urlConn.getInputStream ();
            jasonResponse = readFromStreamm(inST);
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

    public static String readFromStreamm (InputStream inST) throws IOException {

        StringBuilder stBuild = new StringBuilder (  );


        if (inST != null)
        {
            InputStreamReader inSTreader = new InputStreamReader ( inST , Charset.defaultCharset () );

            BufferedReader bfRearer = new BufferedReader ( inSTreader );
            String line = bfRearer.readLine ();

            while (line != null)
            {
                stBuild.append ( line );
                line= bfRearer.readLine ();
            }
        }
        return stBuild.toString ();

    }
    public static ArrayList<DataClass> extractFromJason (String jasonResponse) throws JSONException {

        if (TextUtils.isEmpty ( jasonResponse ) && jasonResponse == null)
        {
            return null;
        }

        ArrayList<DataClass> bookInfo = new ArrayList<>();

        JSONObject jjj = new JSONObject ( jasonResponse);
        JSONArray jjjArr = jjj.getJSONArray( "item" );
         for (int i = 0 ;i < jjjArr.length () ;i++)
         {

             JSONObject item = jjjArr.getJSONObject ( i );
        }
        return bookInfo;
    }
}
