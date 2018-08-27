package ru.handh.mvp.domain.provider;

import java.util.List;

import io.reactivex.Single;
import ru.handh.mvp.data.model.Bridge;
import ru.handh.mvp.data.model.BridgeResponse;
import ru.handh.mvp.data.remote.ApiService;

/**
 * Created by Igor Glushkov on 27.08.18.
 */
public class BridgesProvider {

    private final ApiService apiService;

    public BridgesProvider(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<List<Bridge>> getBridges() {
        return apiService.getBridges()
                .map(BridgeResponse::getBridges);
    }

    //TODO написать метод получения информации о мосте

}
