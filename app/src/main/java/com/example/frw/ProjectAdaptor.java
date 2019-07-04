package com.example.frw;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frw.request.ProjectResponse;
import com.example.frw.request.ProjectsList;
import com.example.frw.request.SendData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectAdaptor extends RecyclerView.Adapter<ProjectAdaptor.ViewHolder> {

    private List<String> pName;
    private List<String> pId;
    private ItemClickListener mClickListener;
    private Map<Integer, SendData> pData;

    // data is passed into the constructor
    ProjectAdaptor(ProjectResponse data) {
        List<String> projectName = new ArrayList<>();
        List<String> projectId = new ArrayList<>();
        Map<Integer, SendData> projectData = new HashMap<>();

        for (ProjectsList pList : data.getProjectList()) {
            projectName.add(pList.getProjectName());
            projectId.add(pList.getProjectID());
            projectData.put(data.getProjectList().indexOf(pList), pList.getData());
        }

        this.pName = projectName;
        this.pId = projectId;
        this.pData = projectData;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_project, parent, false);

        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
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
        return pName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextView;
        Button mButton;

        private ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tvProjectName);
            mButton = itemView.findViewById(R.id.btnSendImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }


    SendData getProjectData(int id) {
        return pData.get(id);
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
