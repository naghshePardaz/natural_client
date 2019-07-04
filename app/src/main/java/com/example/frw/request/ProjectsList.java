package com.example.frw.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectsList {
    @SerializedName("sendData")
    private SendData data;

    @SerializedName("_id")
    private String projectID;

    @SerializedName("name")
    private String projectName;

    public SendData getData() {
        return data;
    }

    public void setData(SendData data) {
        this.data = data;
    }

    public String getProjectID() {
        return projectID;
    }
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
