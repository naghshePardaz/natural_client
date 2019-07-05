package com.example.frw;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frw.request.SendData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<String> dDate;
    private List<String> dImageUrl;

    DataAdapter(ArrayList<SendData> data) {
        List<String> dataDate = new ArrayList<>();
        List<String> dataImageUrl = new ArrayList<>();

        for (SendData sData : data) {
            dataDate.add(sData.getDataDate().toString());
            dataImageUrl.add(sData.getDataURL());
        }
        this.dDate = dataDate;
        this.dImageUrl = dataImageUrl;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_show_data, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        final String date = dDate.get(position);
        final String url = "https://natural.liara.run/api/file/image/" + dImageUrl.get(position);
        holder.mTextView.setText(date);
        Picasso.get().load(url).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return dDate.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextView;

        private final ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tvDataDate);
            imageView = itemView.findViewById(R.id.imgData);
        }
    }
}
