package com.example.frw;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ProjectResponse {
    @SerializedName("_id")
    private String userID;

    @SerializedName("username")
    private String userName;

    @SerializedName("projects")
    private Map<String, ProjectsList> projecList;

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

    public Map<String, ProjectsList> getProjecList() {
        return projecList;
    }
    public void setProjecList(Map<String, ProjectsList> projecList) {
        this.projecList = projecList;
    }
}
