package ru.handh.mvp.di;

import android.content.Context;

import ru.handh.mvp.data.remote.ApiService;
import ru.handh.mvp.presentation.ui.bridgeslist.BridgesAdapter;
import ru.handh.mvp.presentation.ui.bridgeslist.BridgesListPresenter;

/**
 * Created by Igor Glushkov on 19.08.18.
 */
public class ApplicationComponents {

    private static volatile ApplicationComponents instance;

    private ApiService apiService;
    private Context context;

    private ApplicationComponents(Context context) {
        this.context = context;
        this.apiService = ApiService.Creator.newApiService(context);
    }

    public static ApplicationComponents getInstance(Context context) {
        ApplicationComponents localInstance = instance;
        if (localInstance == null) {
            synchronized (ApplicationComponents.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ApplicationComponents(context);
                }
            }
        }
        return localInstance;
    }

    public ApiService provideApiService() {
        return apiService;
    }

    public Context provideContext() {
        return context;
    }

    public BridgesAdapter provideBridgesAdapter() {
        return new BridgesAdapter();
    }

    public BridgesListPresenter providePridgesListPresenter() {
        return new BridgesListPresenter(provideApiService());
    }


}
