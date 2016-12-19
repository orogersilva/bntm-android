package com.orogersilva.bntm.domain.usecase.impl;

import com.orogersilva.bntm.TransferDataSource;
import com.orogersilva.bntm.domain.executor.Executor;
import com.orogersilva.bntm.domain.executor.MainThread;
import com.orogersilva.bntm.domain.repository.TransferRepository;
import com.orogersilva.bntm.domain.usecase.SendMoneyUseCase;
import com.orogersilva.bntm.domain.usecase.base.AbstractUseCase;

/**
 * Created by orogersilva on 12/19/2016.
 */

public class SendMoneyUseCaseImpl extends AbstractUseCase implements SendMoneyUseCase {

    // region FIELDS

    private String mClientId;
    private String mAuthToken;
    private double mMoneyValue;

    private SendMoneyUseCase.Callback mCallback;
    private TransferRepository mTransferRepository;

    // endregion

    // region CONSTRUCTORS

    public SendMoneyUseCaseImpl(String clientId, String authToken, double moneyValue,
                                Executor threadExecutor, MainThread mainThread, Callback callback,
                                TransferRepository transferRepository) {

        super(threadExecutor, mainThread);

        mClientId = clientId;
        mAuthToken = authToken;
        mMoneyValue = moneyValue;
        mCallback = callback;
        mTransferRepository = transferRepository;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void run() {

        mTransferRepository.sendMoney(mClientId, mAuthToken, mMoneyValue,
                new TransferDataSource.SendMoneyCallback() {

            @Override
            public void onMoneySent(final boolean status) {

                mMainThread.post(new Runnable() {

                    // region OVERRIDED METHODS

                    @Override
                    public void run() {

                        mCallback.onMoneySent(status);
                    }

                    // endregion
                });
            }

            @Override
            public void onFailed() {

                mMainThread.post(new Runnable() {

                    // region OVERRIDED METHODS

                    @Override
                    public void run() {

                        mCallback.onFailed();
                    }

                    // endregion
                });
            }
        });
    }

    // endregion
}
