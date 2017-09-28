package com.udacity.nano_bookinglist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by zozeta on 27/09/2017.
 */
public class BookAdapter extends ArrayAdapter<Books> {


        public BookAdapter(Activity context, List<Books> BookList) {
            super(context, 0, BookList);
        }

        /**
         * Provides a view for an AdapterView (ListView, GridView, etc.)
         *
         * @param position    The position in the list of data that should be displayed in the
         *                    list item view.
         * @param convertView The recycled view to populate.
         * @param parent      The parent ViewGroup that is used for inflation.
         * @return The View for the position in the AdapterView.
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Check if the existing view is being reused, otherwise inflate the view
            View listItemView = convertView;
            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item, parent, false);
            }

            Books currentBook = getItem(position);

            TextView NameTextView = (TextView) listItemView.findViewById(R.id.BookName_tv);

            NameTextView.setText(currentBook.getBookName());

            TextView AuthorTextView = (TextView) listItemView.findViewById(R.id.AuthorName_tv);

            AuthorTextView.setText(currentBook.getAuthorName());

            return listItemView;
        }
    }

