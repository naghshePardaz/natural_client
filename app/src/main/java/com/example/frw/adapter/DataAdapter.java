package com.example.frw.adapter;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frw.R;
import com.example.frw.request.SendData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<Date> dDate;
    private List<String> dImageUrl;

    public DataAdapter(ArrayList<SendData> data) {
        List<Date> dataDate = new ArrayList<>();
        List<String> dataImageUrl = new ArrayList<>();

        for (SendData sData : data) {
            dataDate.add(sData.getDataDate());
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
        final Date date = dDate.get(position);

        PersianDate persianDate = new PersianDate(date);
        PersianDateFormat persianDateFormat = new PersianDateFormat("j F y");
        PersianDateFormat persianTimeFormat = new PersianDateFormat("H:i:s");

        final String url = "https://natural.liara.run/api/file/image/" + dImageUrl.get(position);

        holder.dTextView.setText(persianDateFormat.format(persianDate));
        holder.tTextView.setText(persianTimeFormat.format(persianDate));

        Picasso.get().load(url).resize(130, 130).centerCrop().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return dDate.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView dTextView;
        private final TextView tTextView;
        private final ImageView mImageView;

        ViewHolder(View itemView) {
            super(itemView);
            dTextView = itemView.findViewById(R.id.tvDate);
            tTextView = itemView.findViewById(R.id.tvTime);
            mImageView = itemView.findViewById(R.id.imgData);
        }
    }
}
