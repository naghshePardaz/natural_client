package com.example.frw;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.frw.request.ProjectResponse;
import com.example.frw.request.ProjectsList;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdaptor extends RecyclerView.Adapter<ProjectAdaptor.ViewHolder> {

    private List<String> pName;
    private List<String> pId;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public ProjectAdaptor(ProjectResponse data) {
        List<String> projectName = new ArrayList<>();
        List<String> projectId = new ArrayList<>();
        for (ProjectsList pList : data.getProjecList()) {
            projectName.add(pList.getProjectName());
            projectId.add(pList.getProjectID());
        }
        this.pName = projectName;
        this.pId = projectId;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_project, parent, false);

        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final String str = pName.get(position);
        final String id = pId.get(position);
        holder.mTextView.setText(str);
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), SendActivity.class);
                mIntent.putExtra("projectId", id);
                view.getContext().startActivity(mIntent);
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
<<<<<<< HEAD
        return mData.size();
=======
        return pName.size();
>>>>>>> d358e88ee8a29c1eab92aff85f8e06db0ecfa142
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextView;
        Button mButton;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tvProjectName);
            mButton = itemView.findViewById(R.id.btnSendImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    String getItem(int id) {
        return pName.get(id);
    }

    //allows click events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    //parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
