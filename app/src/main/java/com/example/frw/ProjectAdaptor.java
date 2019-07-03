package com.example.frw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProjectAdaptor extends RecyclerView.Adapter<ProjectAdaptor.ViewHolder> {

    private List<String> mData;

    // data is passed into the constructor
    public ProjectAdaptor(List<String> data) {
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.content_profile, parent, false);

        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String str = mData.get(position);
        holder.mTextView.setText(str);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData == null ? 0 :mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tvProjectName);
        }
    }
}
