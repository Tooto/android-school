package ru.handh.lesson8.room;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.handh.lesson8.R;

public class RoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database")
                .allowMainThreadQueries() // рассказать
                .build();

//        db.userDao().insertUsers(new User(1, "igor", "glushkov",
//                new Address("pushkina", "mordovia", "saransk", 430000)));
//
        User user = db.userDao().findByName("igor", "glushkov");
        db.userDao().delete(user);

        db.userDao().getAllObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> Log.d("LES", users.get(0).getFirstName()));
//
        Flowable.fromCallable(() -> {
            long[] ids = db.userDao().insertUsers(new User(1, "igor", "glushkov",
                    new Address("pushkina", "mordovia", "saransk", 430000)));
            return ids;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();


    }
}
