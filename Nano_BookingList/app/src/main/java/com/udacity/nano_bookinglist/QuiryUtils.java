package com.udacity.nano_bookinglist;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by zozeta on 27/09/2017.
 */
public class QuiryUtils {
    public static final String LOG_TAG = QuiryUtils.class.getSimpleName();
    private QuiryUtils(){}

    public static List<Books> fetchEarthquakeData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = MakeHTTPreq(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<Books> BookList = ExtractFeaturesFromJSON(jsonResponse);

        // Return the list of {@link Earthquake}s
        return BookList;
    }
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }
    private static String MakeHTTPreq(URL url) throws IOException{
        String JSONresponse="";
        if (url == null) {
            return JSONresponse;
        }
        HttpsURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if(urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                JSONresponse = readFromStream(inputStream);
            }
            else{
                Log.e(LOG_TAG,"ERROR response code :"+ urlConnection.getResponseCode());
            }
        }
        catch(IOException e){
            Log.e(LOG_TAG,"Problem retrieving result",e);

        }
        finally{
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }
        return JSONresponse;
    }
    private static String readFromStream(InputStream is) throws IOException{
        StringBuilder output = new StringBuilder();
        if (is != null) {
            InputStreamReader isReader = new InputStreamReader(is, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(isReader);
            String line = reader.readLine();
            while(line != null){
                output.append(line);
                line =reader.readLine();
            }
        }
        return output.toString();
    }
    private static List<Books> ExtractFeaturesFromJSON(String BJSON){
        if(TextUtils.isEmpty(BJSON)){
            return null;
        }
        List BookList = new ArrayList<>();
        try {
            JSONObject JSONResponse= new JSONObject(BJSON);
            JSONArray BookArray = JSONResponse.getJSONArray("items");
            for(int i=0;i<BookArray.length();i++){
                JSONObject JsonObject= BookArray.getJSONObject(i);
                String title =JsonObject.optString("title");
                String Author=JsonObject.optString("authors");
                Books b =new Books(title,Author);
                BookList.add(b);
            }



        } catch (JSONException e) {

            Log.e(LOG_TAG, "Problem parsing the Book JSON results", e);
        }
        return BookList;
    }
}
