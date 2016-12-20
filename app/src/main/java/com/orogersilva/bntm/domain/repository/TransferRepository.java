package com.orogersilva.bntm.domain.repository;

import android.support.annotation.NonNull;

import com.orogersilva.bntm.TransferDataSource;
import com.orogersilva.bntm.storage.model.ContactTransfer;
import com.orogersilva.bntm.storage.persistence.TransferLocalDataSource;
import com.orogersilva.bntm.sync.model.Transfer;

import java.util.List;

/**
 * Created by orogersilva on 12/19/2016.
 */

public class TransferRepository implements TransferDataSource {

    // region FIELDS

    private static TransferRepository INSTANCE = null;

    private final TransferDataSource mTransferLocalDataSource;
    private final TransferDataSource mTransferRemoteDataSource;

    // endregion

    // region CONSTRUCTORS

    private TransferRepository(@NonNull TransferDataSource transferLocalDataSource,
                               @NonNull TransferDataSource transferRemoteDataSource) {

        mTransferLocalDataSource = transferLocalDataSource;
        mTransferRemoteDataSource = transferRemoteDataSource;
    }

    // endregion

    // region STATIC METHODS

    public static TransferRepository getInstance(TransferDataSource transferLocalDataSource,
                                                 TransferDataSource transferRemoteDataSource) {

        if (INSTANCE == null) {
            INSTANCE = new TransferRepository(transferLocalDataSource, transferRemoteDataSource);
        }

        return INSTANCE;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void sendMoney(String id, String authToken, double moneyValue, final SendMoneyCallback callback) {

        mTransferRemoteDataSource.sendMoney(id, authToken, moneyValue, new SendMoneyCallback() {

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

    @Override
    public void getTransfers(final String authToken, final GetTransfersCallback callback) {

        mTransferRemoteDataSource.getTransfers(authToken, new GetTransfersCallback() {

            @Override
            public void onTransfersWereObtained(List<Transfer> transfers) {

                refreshLocalDataSource(transfers);

                mTransferLocalDataSource.getTransfers(authToken, new GetTransfersCallback() {

                    @Override
                    public void onTransfersWereObtained(List<Transfer> transfers) {
                    }

                    @Override
                    public void onContactTransfersLoaded(List<ContactTransfer> contactTransfers) {

                        callback.onContactTransfersLoaded(contactTransfers);
                    }

                    @Override
                    public void onDataNotAvailable() {

                        callback.onDataNotAvailable();
                    }
                });
            }

            @Override
            public void onContactTransfersLoaded(List<ContactTransfer> contactTransfers) {
            }

            @Override
            public void onDataNotAvailable() {

                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public int deleteAllTransfers() {
        return 0;
    }

    @Override
    public void saveTransfers(List<Transfer> transfers) {
    }

    // endregion

    // region UTILITY METHODS

    private void refreshLocalDataSource(List<Transfer> transfers) {

        mTransferLocalDataSource.deleteAllTransfers();
        mTransferLocalDataSource.saveTransfers(transfers);
    }

    // endregion
}
