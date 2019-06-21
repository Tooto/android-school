package ru.handh.mvp.presentation.ui.bridgeslist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.handh.mvp.domain.provider.BridgesRepository;
import ru.handh.mvp.presentation.ui.base.BasePresenter;

/**
 * Created by Igor Glushkov on 19.08.18.
 */
public class BridgesListPresenter extends BasePresenter<BridgesListMvpView> {

    @NonNull
    private final BridgesRepository bridgesRepository;

    @Nullable
    private Disposable disposable;

    public BridgesListPresenter(@NonNull BridgesRepository bridgesRepository) {
        this.bridgesRepository = bridgesRepository;
    }

    public void onCreate() {
        checkViewAttached();
        getBridges();
    }

    public void getBridges() {
        checkViewAttached();
        getMvpView().showProgressView();
        disposable = bridgesRepository.getBridges()
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
