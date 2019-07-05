package com.example.frw.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class SendData implements Serializable {
    @SerializedName("_id")
    private String dataID;

    @SerializedName("data")
    private String dataURL;

    @SerializedName("feedBack")
    private String dataFeed;

    @SerializedName("date")
    private Date dataDate;

    public String getDataID() {
        return dataID;
    }
    public void setDataID(String dataID) {
        this.dataID = dataID;
    }

    public String getDataURL() {
        return dataURL;
    }
    public void setDataURL(String dataURL) {
        this.dataURL = dataURL;
    }

    public String getDataFeed() {
        return dataFeed;
    }
    public void setDataFeed(String dataFeed) {
        this.dataFeed = dataFeed;
    }

    public Date getDataDate() {
        return dataDate;
    }
    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }
}
