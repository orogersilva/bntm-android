package com.orogersilva.bntm;

/**
 * Created by orogersilva on 12/19/2016.
 */

public interface TransferDataSource {

    // region INTERFACES

    interface SendMoneyCallback {

        void onMoneySent(boolean status);
        void onFailed();
    }

    // endregion

    // region METHODS

    void sendMoney(String id, String token, double moneyValue, SendMoneyCallback callback);

    // endregion
}
