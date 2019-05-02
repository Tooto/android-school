package ru.handh.mvp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Igor Glushkov on 19.08.18.
 */
public class DivorceInfo {

    @SerializedName("end")
    private Date end;

    @SerializedName("id")
    private int id;

    @SerializedName("start")
    private Date start;

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
}
