package ru.handh.mvp.presentation.ui.bridgeslist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.handh.mvp.data.model.BridgeResponse;
import ru.handh.mvp.data.remote.ApiService;
import ru.handh.mvp.presentation.ui.base.BasePresenter;

/**
 * Created by Igor Glushkov on 19.08.18.
 */
public class BridgesListPresenter extends BasePresenter<BridgesListMvpView> {

    @NonNull
    private final ApiService apiService;

    @Nullable
    private Disposable disposable;

    public BridgesListPresenter(@NonNull ApiService apiService) {
        this.apiService = apiService;
    }

    public void onCreate() {
        checkViewAttached();
        getBridges();
    }

    public void getBridges() {
        checkViewAttached();
        getMvpView().showProgressView();
        disposable = apiService.getBridges()
                .map(BridgeResponse::getBridges)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bridges -> getMvpView().showBridges(bridges),
                        error -> {
                            error.printStackTrace();
                            getMvpView().showLoadingError();
                        });
    }

    @Override
    protected void doUnsubscribe() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
