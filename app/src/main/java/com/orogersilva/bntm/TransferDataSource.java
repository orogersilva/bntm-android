package com.orogersilva.bntm;

import com.orogersilva.bntm.storage.model.ContactTransfer;
import com.orogersilva.bntm.sync.model.Transfer;

import java.util.List;

/**
 * Created by orogersilva on 12/19/2016.
 */

public interface TransferDataSource {

    // region INTERFACES

    interface SendMoneyCallback {

        void onMoneySent(boolean status);
        void onFailed();
    }

    interface GetTransfersCallback {

        void onTransfersWereObtained(List<Transfer> transfers);
        void onContactTransfersLoaded(List<ContactTransfer> contactTransfers);
        void onDataNotAvailable();
    }

    // endregion

    // region METHODS

    void sendMoney(String id, String authToken, double moneyValue, SendMoneyCallback callback);

    void getTransfers(String authToken, GetTransfersCallback callback);

    int deleteAllTransfers();

    void saveTransfers(List<Transfer> transfers);

    // endregion
}
