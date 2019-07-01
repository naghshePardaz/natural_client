package com.example.frw;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ProjectsList {
    @SerializedName("sendData")
    private Map<String, SendData> data;

    @SerializedName("_id")
    private String projectID;

    @SerializedName("name")
    private String projectName;

    public Map<String, SendData> getData() {
        return data;
    }
    public void setData(Map<String, SendData> data) {
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
