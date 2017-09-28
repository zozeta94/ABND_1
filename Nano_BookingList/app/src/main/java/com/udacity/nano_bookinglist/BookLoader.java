package com.udacity.nano_bookinglist;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by zozeta on 28/09/2017.
 */
public class BookLoader extends AsyncTaskLoader<List<Books>> {

    private String mUrl;


    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public List<Books> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of books.
        List<Books> BookList = QuiryUtils.fetchEarthquakeData(mUrl);
        return BookList;
    }

}
