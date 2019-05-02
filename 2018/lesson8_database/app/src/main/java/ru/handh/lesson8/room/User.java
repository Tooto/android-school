package ru.handh.lesson8.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

/**
 * Created by Igor Glushkov on 12.07.18.
 */
//TODO рассказать, что можно ссылаться на другие объекты
@Entity(tableName = "users") // имя таблицы не обязательно
public class User {
    // хоть 1 поле - primary key
    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @Embedded
    private Address address;

    @Ignore
    private Bitmap picture;

    public User(int uid, String firstName, String lastName, Address address) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
