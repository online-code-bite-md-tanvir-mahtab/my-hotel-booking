package com.tanvircodder.bdhotel.network;

import android.content.ContentValues;
import android.content.Context;

import com.tanvircodder.bdhotel.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParsing {
//    now i am going to crete the constant of the key variable..//
    private static final String HOTEL_KEY = "hotel";
    private static final String HOTEL_NAME = "location";
    private static final String HOTEL_STAR= "star";
//    to hendle the list we are going to create an list ..//
//    private static List<Utility> mStoreTheData  = null;
    private static List<Utility> mStoreTheData = null;
    public static final List<Utility> jsonParsing(Context context,String uriResponse) throws JSONException {
        JSONArray jsonArray =new JSONArray(uriResponse);
//        now i am going to create the instance of the list..//
        mStoreTheData = new ArrayList<>();
        for (int i= 0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            for the hotel_name..//
            String hotel_name = jsonObject.getString(HOTEL_KEY);
//            for the hotel_location..//
            String hotel_location = jsonObject.getString(HOTEL_NAME);
//            for the hotel_star..//
            String hotel_star = jsonObject.getString(HOTEL_STAR);
//            now i am going to add the data to the list..///
            mStoreTheData.add(new Utility(hotel_name,hotel_location,hotel_star));
//            mStoreTheData.add(i,values);
        }
        System.out.println(mStoreTheData);
        return mStoreTheData;
    }
}
