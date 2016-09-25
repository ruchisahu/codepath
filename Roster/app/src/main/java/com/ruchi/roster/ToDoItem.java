package com.ruchi.roster;

import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ruchi on 9/24/2016.
 */

public class ToDoItem {
    public static final String ITEM_SEP = System.getProperty("line.separator");


    public enum Status {
        NOTDONE, DONE
    };

    public final static String TITLE = "title";
    public final static String NOTE = "notes";
    //public final static String PRIORITY = "priority";
    public final static String STATUS = "status";
    public final static String DATE = "date";
    public final static String FILENAME = "filename";

    public final static SimpleDateFormat FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.US);

    private String mTitle = new String();
    private String mNotes = new String();
    //	private Priority mPriority = Priority.LOW;
    private Status mStatus = Status.NOTDONE;
    private Date mDate = new Date();

    ToDoItem(String title, String notes, Status status, Date date) {
        this.mTitle = title;
        this.mNotes = notes;
        this.mStatus = status;
        this.mDate = date;
    }

    // Create a new ToDoItem from data packaged in an Intent

    ToDoItem(Intent intent) {

        mTitle = intent.getStringExtra(ToDoItem.TITLE);
        mNotes = intent.getStringExtra(ToDoItem.NOTE);
        mStatus = Status.valueOf(intent.getStringExtra(ToDoItem.STATUS));

        try {
            mDate = ToDoItem.FORMAT.parse(intent.getStringExtra(ToDoItem.DATE));
        } catch (ParseException e) {
            mDate = new Date();
        }
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }


    public static void packageIntent(Intent intent, String title,
                                     String note, Status status, String date) {

        intent.putExtra(ToDoItem.TITLE, title);
        intent.putExtra(ToDoItem.NOTE, note);
        intent.putExtra(ToDoItem.STATUS, status.toString());
        intent.putExtra(ToDoItem.DATE, date);

    }

    public String toString() {
        return mTitle + ITEM_SEP + mNotes + ITEM_SEP + mStatus + ITEM_SEP
                + FORMAT.format(mDate);
    }

    public String toLog() {
        return "Title:" + mTitle + ITEM_SEP + "Notes:" + mNotes
                + ITEM_SEP + "Status:" + mStatus + ITEM_SEP + "Date:"
                + FORMAT.format(mDate) +"\n";

    }

}

