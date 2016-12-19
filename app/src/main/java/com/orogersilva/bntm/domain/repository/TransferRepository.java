package com.orogersilva.bntm.domain.repository;

import android.support.annotation.NonNull;

import com.orogersilva.bntm.TransferDataSource;

/**
 * Created by orogersilva on 12/19/2016.
 */

public class TransferRepository implements TransferDataSource {

    // region FIELDS

    private static TransferRepository INSTANCE = null;

    private final TransferDataSource mTransferRemoteDataSource;

    // endregion

    // region CONSTRUCTORS

    private TransferRepository(@NonNull TransferDataSource transferRemoteDataSource) {

        mTransferRemoteDataSource = transferRemoteDataSource;
    }

    // endregion

    // region STATIC METHODS

    public static TransferRepository getInstance(TransferDataSource transferRemoteDataSource) {

        if (INSTANCE == null) {
            INSTANCE = new TransferRepository(transferRemoteDataSource);
        }

        return INSTANCE;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void sendMoney(String id, String token, double moneyValue, final SendMoneyCallback callback) {

        mTransferRemoteDataSource.sendMoney(id, token, moneyValue, new SendMoneyCallback() {

            @Override
            public void onMoneySent(boolean status) {

                callback.onMoneySent(status);
            }

            @Override
            public void onFailed() {

                callback.onFailed();
            }
        });
    }

    // endregion
}
