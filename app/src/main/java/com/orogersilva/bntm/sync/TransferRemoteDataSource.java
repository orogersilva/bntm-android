package com.orogersilva.bntm.sync;

import com.orogersilva.bntm.TransferDataSource;
import com.orogersilva.bntm.net.RestClient;
import com.orogersilva.bntm.net.services.TransferService;
import com.orogersilva.bntm.sync.model.Transfer;

import java.io.IOException;
import java.util.List;

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
    public void sendMoney(String id, String authToken, double moneyValue, SendMoneyCallback callback) {

        TransferService transferService = RestClient.getService(TransferService.class);

        Response<Boolean> response = null;

        try {

            response = transferService.sendMoney(id, authToken, moneyValue).execute();

        } catch (IOException e) {

            Timber.e(e);
        }

        if (response != null && response.isSuccessful()) {
            callback.onMoneySent(response.body().booleanValue());
        } else {
            callback.onFailed();
        }
    }

    @Override
    public void getTransfers(String authToken, GetTransfersCallback callback) {

        TransferService transferService = RestClient.getService(TransferService.class);

        Response<List<Transfer>> response = null;

        try {

            response = transferService.getTransfers(authToken).execute();

        } catch (IOException e) {

            Timber.e(e);
        }

        if (response != null && response.isSuccessful()) {

            List<Transfer> transfers = response.body();

            callback.onTransfersWereObtained(transfers);

        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public int deleteAllTransfers() {
        return 0;
    }

    @Override
    public void saveTransfers(List<Transfer> transfers) {
    }

    // endregion
}
