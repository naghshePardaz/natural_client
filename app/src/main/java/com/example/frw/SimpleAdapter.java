package com.example.frw;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SampleHolder> {

    @NonNull
    @Override
    public SampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SampleHolder o = null;
        return o;
    }

    @Override
    public void onBindViewHolder(@NonNull SampleHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SampleHolder extends RecyclerView.ViewHolder {
        public SampleHolder(View itemView) {
            super(itemView);
        }
    }
}