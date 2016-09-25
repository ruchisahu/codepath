package com.ruchi.roster;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseAdapter {

    private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();

    private final Context mContext;

    private static final String TAG = "UserInterface";

    public MainActivity(Context context) {

        mContext = context;

    }

    public void add(ToDoItem item) {

        mItems.add(item);
        notifyDataSetChanged();

    }

    public void clear(){

        mItems.clear();
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {

        return mItems.size();

    }

    // Retrieve the number of ToDoItems

    @Override
    public Object getItem(int pos) {

        return mItems.get(pos);

    }

    // Get the ID for the ToDoItem
    // In this case it's just the position

    @Override
    public long getItemId(int pos) {

        return pos;

    }

    //Create a View to display the ToDoItem
    // at specified position in mItems

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the current ToDoItem
        final ToDoItem toDoItem = (ToDoItem) getItem(position);


        RelativeLayout itemLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.todo_item, null);

        final TextView titleView = (TextView) itemLayout.findViewById(R.id.titleView);
        titleView.setText(toDoItem.getTitle());

        final CheckBox statusView = (CheckBox) itemLayout.findViewById(R.id.statusCheckBox);
        statusView.setChecked(ToDoItem.Status.DONE.equals(toDoItem.getStatus()));

        statusView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                log("Entered onCheckedChanged()");

                // TODO - Set up and implement an OnCheckedChangeListener, which
                // is called when the user toggles the status checkbox
                toDoItem.setStatus(isChecked ? ToDoItem.Status.DONE : ToDoItem.Status.NOTDONE);


            }
        });

        //TODO - Display Notes in a TextView
        final TextView NoteView = (TextView) itemLayout.findViewById(R.id.additional);
        NoteView.setText(toDoItem.getNotes());

        final TextView dateView = (TextView) itemLayout.findViewById(R.id.dateView);
        dateView.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));

        return itemLayout;

    }

    private void log(String msg) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, msg);
    }

}

