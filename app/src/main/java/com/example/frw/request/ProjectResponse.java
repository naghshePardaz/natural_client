package com.example.frw.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectResponse {
    @SerializedName("_id")
    private String userID;

    @SerializedName("username")
    private String userName;

    @SerializedName("projects")
    private List<ProjectsList> projectList;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ProjectsList> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectsList> projectList) {
        this.projectList = projectList;
    }
}
