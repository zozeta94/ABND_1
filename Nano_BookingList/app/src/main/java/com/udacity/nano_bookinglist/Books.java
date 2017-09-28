package com.udacity.nano_bookinglist;

/**
 * Created by zozeta on 26/09/2017.
 */
public class Books {
    private String BookName;
    private String AuthorName;

    public Books(String bookNamec, String authorNamec) {
        BookName = bookNamec;
        AuthorName = authorNamec;
    }

    public String getBookName() {
        return BookName;
    }

    public String getAuthorName() {
        return AuthorName;
    }
}
