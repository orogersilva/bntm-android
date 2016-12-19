package com.orogersilva.bntm.sync;

import com.orogersilva.bntm.TransferDataSource;
import com.orogersilva.bntm.net.RestClient;
import com.orogersilva.bntm.net.services.TransferService;

import java.io.IOException;

import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by orogersilva on 12/19/2016.
 */

public class TransferRemoteDataSource implements TransferDataSource {

    // region FIELDS

    private static TransferRemoteDataSource INSTANCE;

    // endregion

    // region CONSTRUCTORS

    private TransferRemoteDataSource() {}

    // endregion

    // region STATIC METHODS

    public static TransferRemoteDataSource getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new TransferRemoteDataSource();
        }

        return INSTANCE;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void sendMoney(String id, String token, double moneyValue, SendMoneyCallback callback) {

        TransferService transferService = RestClient.getService(TransferService.class);

        Response<Boolean> response = null;

        try {

            response = transferService.sendMoney(id, token, moneyValue).execute();

        } catch (IOException e) {

            Timber.e(e);
        }

        if (response != null && response.isSuccessful()) {
            callback.onMoneySent(response.body().booleanValue());
        } else {
            callback.onFailed();
        }
    }

    // endregion
}
