package com.orogersilva.bntm.presentation.screens.transferhistory;

import com.orogersilva.bntm.BntmApp;
import com.orogersilva.bntm.domain.executor.Executor;
import com.orogersilva.bntm.domain.executor.MainThread;
import com.orogersilva.bntm.domain.repository.AuthRepository;
import com.orogersilva.bntm.domain.repository.TransferRepository;
import com.orogersilva.bntm.domain.usecase.GetAuthTokenUseCase;
import com.orogersilva.bntm.domain.usecase.GetTransfersUseCase;
import com.orogersilva.bntm.domain.usecase.impl.GetAuthTokenUseCaseImpl;
import com.orogersilva.bntm.domain.usecase.impl.GetTransfersUseCaseImpl;
import com.orogersilva.bntm.presentation.converter.PresentationModelConverter;
import com.orogersilva.bntm.presentation.screens.AbstractPresenter;
import com.orogersilva.bntm.storage.model.ContactTransfer;
import com.orogersilva.bntm.util.StringUtils;

import java.util.List;

/**
 * Created by orogersilva on 12/20/2016.
 */

public class TransferHistoryPresenter extends AbstractPresenter implements TransferHistoryContract.Presenter {

    // region FIELDS

    private TransferHistoryContract.View mView;
    private AuthRepository mAuthRepository;
    private TransferRepository mTransferRepository;

    // endregion

    // region CONSTRUCTORS

    public TransferHistoryPresenter(TransferHistoryContract.View view, Executor executor,
                                    MainThread mainThread, AuthRepository authRepository,
                                    TransferRepository transferRepository) {

        super(executor, mainThread);

        mView = view;
        mAuthRepository = authRepository;
        mTransferRepository = transferRepository;

        mView.setPresenter(this);
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void resume() {

        mView.showLoadingIndicator(true);

        loadContactTransfersHistory();
    }

    @Override
    public void loadContactTransfersHistory() {

        String authToken = BntmApp.getInstance().getAuthToken();

        if (StringUtils.isNullOrEmpty(authToken)) {

            GetAuthTokenUseCase getAuthTokenUseCase = new GetAuthTokenUseCaseImpl(
                    BntmApp.getInstance().getUsername(), BntmApp.getInstance().getEmail(),
                    mExecutor, mMainThread, new GetAuthTokenUseCase.Callback() {

                @Override
                public void onAuthTokenLoaded(String authToken) {

                    processContactTransfersHistory();
                }

                @Override
                public void onFailed() {

                    // TODO: 12/20/2016 TO SHOW ERROR MESSAGE.
                }

            }, mAuthRepository);

            getAuthTokenUseCase.execute();

        } else {

            processContactTransfersHistory();
        }
    }

    // endregion

    // region UTILITY METHODS

    private void processContactTransfersHistory() {

        GetTransfersUseCase getTransfersUseCase = new GetTransfersUseCaseImpl(
                BntmApp.getInstance().getAuthToken(), mExecutor, mMainThread, new GetTransfersUseCase.Callback() {

            @Override
            public void onContactTransfersLoaded(List<ContactTransfer> contactTransfers) {

                mView.showLoadingIndicator(false);

                mView.showContactTransfersHistory(
                        PresentationModelConverter.convertContactTransferListToPresentationModel(
                                contactTransfers));
            }

            @Override
            public void onDataNotAvailable() {

                mView.showLoadingIndicator(false);

                // TODO: 12/20/2016 SHOW ERROR MESSAGE.
            }

        }, mTransferRepository);

        getTransfersUseCase.execute();
    }

    // endregion
}
