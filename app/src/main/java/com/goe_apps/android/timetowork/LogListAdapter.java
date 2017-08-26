package com.goe_apps.android.timetowork;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alireza on 2017/08/26.
 */

public class LogListAdapter extends RecyclerView.Adapter<LogListAdapter.ViewHolder>{

    private ArrayList<Integer> logDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView logTextView;
        public ViewHolder(TextView v) {
            super(v);
            logTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public LogListAdapter(ArrayList<Integer> myDataset) {
        logDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LogListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.loglist, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.logTextView.setText("The number of seconds are: " +logDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return logDataset.size();
    }

}
