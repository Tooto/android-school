package ru.handh.lesson8.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Igor Glushkov on 12.07.18.
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertUsers(User... users);

    @Insert
    public void insertBothUsers(User user1, User user2);

    @Insert
    public void insertUsersAndFriends(User user, List<User> friends);

    @Update
    public void updateUsers(User... users);

    @Delete
    public void deleteUsers(User... users);

    @Delete
    void delete(User user);

    @Query("SELECT * from users where uid = :id LIMIT 1")
    public Flowable<User> loadUserById(int id);

    @Query("SELECT * FROM users")
    Flowable<List<User>> getAllObservable();
}
