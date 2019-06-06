package ru.handh.lesson7;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;

public class RxActivity extends AppCompatActivity {

    public static Intent createStartIntent(Context context) {
        return new Intent(context, RxActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);


        //Flowable.just("Hello");
        //Flowable.just("Hello", "World");

//        Observable.just("Hello");
//        Observable.just("Hello", "World");
//
//        Maybe.just("Hello");
//        Single.just("Hello");

        //Flowable.just("hello world")
        //       .subscribe(str -> Log.d("LES", str));


//        Disposable disposable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("first");
//                emitter.onNext("second");
//
//                String str = null;
//                str.length();
//
//                emitter.onComplete();
//            }
//        })
//                .subscribe(
//                        str -> Log.d("LES", str),
//                        error -> error.printStackTrace(),
//                        () -> {
//                            Log.d("LES", "complete");
//                        });


//
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("first");
//                emitter.onNext("second");
//                emitter.onComplete();
//            }
//        }).subscribe(str -> Log.d("LES", str));
//

        //Рассказать про три терминальных остояния
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("first");
//                emitter.onNext("second");
//                emitter.onComplete();
//            }
//        }).subscribe(str -> Log.d("LES", str));


//        Disposable disposable = Flowable.fromCallable(() -> {
//            Thread.sleep(5000); //  imitate expensive computation
//            return "Done";
//        })
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(str -> Toast.makeText(this, "done", Toast.LENGTH_SHORT).show());


//        Disposable disposable = Observable.fromCallable(() -> {
//            Thread.sleep(5000); //  imitate expensive computation
//            return "Done";
//        })
//                .map(str -> str + "123")
//                .flatMap((Function<String, ObservableSource<List<String>>>) str -> getArrayList(str))
//                .flatMap(new Function<List<String>, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(List<String> strings) throws Exception {
//                        return Observable.fromIterable(strings);
//                    }
//                })
//                .map(str -> str + " one more")
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(str -> Log.d("LES", "finish: " + str), error -> error.printStackTrace());

//        Disposable disposable = Observable.fromCallable(() -> {
//            Thread.sleep(5000); //  imitate expensive computation
//            return "Done";
//        })
//                .map(str -> str + "123")
//                .flatMap(new Function<String, ObservableSource<List<String>>>() {
//                    @Override
//                    public ObservableSource<List<String>> apply(String str) throws Exception {
//                        return getArrayList(str);
//                    }
//                })
//                .flatMap(new Function<List<String>, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(List<String> strings) throws Exception {
//                        return Observable.fromIterable(strings);
//                    }
//                })
//                .map(str -> str + " one more")
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(str -> Log.d("LES", "finish: " + str));
    }

    private Observable<List<String>> getArrayList(String str) {
        List<String> result = new ArrayList<>();
        result.add(str);
        result.add("first");
        result.add("second");
        result.add("third");
        return Observable.just(result);
    }
}
