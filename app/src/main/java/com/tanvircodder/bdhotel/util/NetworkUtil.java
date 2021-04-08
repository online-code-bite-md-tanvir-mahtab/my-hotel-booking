package com.tanvircodder.bdhotel.util;

import android.net.Uri;
import android.renderscript.ScriptGroup;
import android.util.Log;

import androidx.loader.app.LoaderManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtil {
    private static final String LOG_TAG = NetworkUtil.class.getSimpleName();
    private static final String BASE_URI = "https://hotel-bokking-api.herokuapp.com/list";
    public static final URL buildUrl(){
        Uri buildUri = Uri.parse(BASE_URI).buildUpon()
                .build();
        URL url= null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return url;
    }
    public static final String URLHttpRequest(URL urlRequest) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection)urlRequest.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:33.0) Gecko/20100101 Firefox/33.0");
        urlConnection.setDoInput(true);
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hsCode = scanner.hasNext();
            if (hsCode){
                return scanner.next();
            }else {
                return null;
            }
        }catch (IOException e){
            Log.e(LOG_TAG,"There is some problem");
        }finally {
            urlConnection.disconnect();
        }
        return null;
    }
}
