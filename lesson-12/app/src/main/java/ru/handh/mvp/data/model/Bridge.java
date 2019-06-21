package ru.handh.mvp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO реализовать все поля
 * Created by Igor Glushkov on 19.08.18.
 */
public class Bridge {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("divorces")
    private List<DivorceInfo> divorces = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DivorceInfo> getDivorces() {
        return divorces;
    }

    public void setDivorces(List<DivorceInfo> divorces) {
        this.divorces = divorces;
    }
}
