package com.example.andreip.myapplication.feature1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] mDataset;

    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position]);

    }


    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }


    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_recyclerview, parent, false);
        v.setText(mDataset[0]);

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }


}
