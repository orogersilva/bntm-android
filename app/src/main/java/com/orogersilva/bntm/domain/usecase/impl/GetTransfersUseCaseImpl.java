package com.orogersilva.bntm.domain.usecase.impl;

import com.orogersilva.bntm.data.TransferDataSource;
import com.orogersilva.bntm.domain.executor.Executor;
import com.orogersilva.bntm.domain.executor.MainThread;
import com.orogersilva.bntm.domain.repository.TransferRepository;
import com.orogersilva.bntm.domain.usecase.GetTransfersUseCase;
import com.orogersilva.bntm.domain.usecase.base.AbstractUseCase;
import com.orogersilva.bntm.storage.model.ContactTransfer;
import com.orogersilva.bntm.sync.model.Transfer;

import java.util.List;

/**
 * Created by orogersilva on 12/19/2016.
 */

public class GetTransfersUseCaseImpl extends AbstractUseCase implements GetTransfersUseCase {

    // region FIELDS

    private String mAuthToken;

    private GetTransfersUseCase.Callback mCallback;
    private TransferRepository mTransferRepository;

    // endregion

    // region CONSTRUCTORS

    public GetTransfersUseCaseImpl(String authToken, Executor threadExecutor, MainThread mainThread,
                                   Callback callback, TransferRepository transferRepository) {

        super(threadExecutor, mainThread);

        mAuthToken = authToken;
        mCallback = callback;
        mTransferRepository = transferRepository;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void run() {

        mTransferRepository.getTransfers(mAuthToken, new TransferDataSource.GetTransfersCallback() {

            @Override
            public void onTransfersWereObtained(final List<Transfer> transfers) {
            }

            @Override
            public void onContactTransfersLoaded(final List<ContactTransfer> contactTransfers) {

                mMainThread.post(new Runnable() {

                    // region OVERRIDED METHODS

                    @Override
                    public void run() {

                        mCallback.onContactTransfersLoaded(contactTransfers);
                    }

                    // endregion
                });
            }

            @Override
            public void onDataNotAvailable() {

                mMainThread.post(new Runnable() {

                    // region OVERRIDED METHODS

                    @Override
                    public void run() {

                        mCallback.onDataNotAvailable();
                    }

                    // endregion
                });
            }
        });
    }

    // endregion
}
