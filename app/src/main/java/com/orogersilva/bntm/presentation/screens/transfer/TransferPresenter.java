package com.orogersilva.bntm.presentation.screens.transfer;

import com.orogersilva.bntm.BntmApp;
import com.orogersilva.bntm.domain.executor.Executor;
import com.orogersilva.bntm.domain.executor.MainThread;
import com.orogersilva.bntm.domain.repository.AuthRepository;
import com.orogersilva.bntm.domain.repository.ContactRepository;
import com.orogersilva.bntm.domain.repository.TransferRepository;
import com.orogersilva.bntm.domain.usecase.GetAuthTokenUseCase;
import com.orogersilva.bntm.domain.usecase.GetContactsUseCase;
import com.orogersilva.bntm.domain.usecase.SendMoneyUseCase;
import com.orogersilva.bntm.domain.usecase.impl.GetAuthTokenUseCaseImpl;
import com.orogersilva.bntm.domain.usecase.impl.GetContactsUseCaseImpl;
import com.orogersilva.bntm.domain.usecase.impl.SendMoneyUseCaseImpl;
import com.orogersilva.bntm.presentation.converter.PresentationModelConverter;
import com.orogersilva.bntm.presentation.model.Contact;
import com.orogersilva.bntm.presentation.screens.AbstractPresenter;
import com.orogersilva.bntm.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by orogersilva on 12/17/2016.
 */

public class TransferPresenter extends AbstractPresenter implements TransferContract.Presenter {

    // region FIELDS

    private TransferContract.View mView;
    private AuthRepository mAuthRepository;
    private ContactRepository mContactRepository;
    private TransferRepository mTransferRepository;

    // endregion

    // region CONSTRUCTORS

    public TransferPresenter(TransferContract.View view, Executor executor, MainThread mainThread,
                             AuthRepository authRepository, ContactRepository contactRepository,
                             TransferRepository transferRepository) {

        super(executor, mainThread);

        mView = view;
        mAuthRepository = authRepository;
        mContactRepository = contactRepository;
        mTransferRepository = transferRepository;

        mView.setPresenter(this);
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void resume() {

        loadContacts();
    }

    @Override
    public void loadContacts() {

        mView.showLoadingIndicator(true);

        processContacts();
    }

    @Override
    public void sendMoney(final Contact contact, final double moneyValue) {

        String authToken = BntmApp.getInstance().getAuthToken();

        if (StringUtils.isNullOrEmpty(authToken)) {

            GetAuthTokenUseCase getAuthTokenUseCase = new GetAuthTokenUseCaseImpl("Roger Silva",
                    "orogersilva@gmail.com", mExecutor, mMainThread, new GetAuthTokenUseCase.Callback() {

                @Override
                public void onAuthTokenLoaded(String authToken) {

                    processTransfer(contact, moneyValue);
                }

                @Override
                public void onFailed() {

                    mView.showTransferStatusAlertMessage(false);
                }

            }, mAuthRepository);

            getAuthTokenUseCase.execute();

        } else {

            processTransfer(contact, moneyValue);
        }
    }

    // endregion

    // region UTILITY METHODS

    private void processContacts() {

        GetContactsUseCase contactsUseCase = new GetContactsUseCaseImpl(mExecutor, mMainThread,
                new GetContactsUseCase.Callback() {

                    @Override
                    public void onContactsLoaded(List<com.orogersilva.bntm.domain.model.Contact> contacts) {

                        mView.showLoadingIndicator(false);

                        Collections.sort(contacts);

                        mView.showContacts(PresentationModelConverter.convertContactListToPresentationModel(contacts));
                    }

                    @Override
                    public void onDataNotAvailable() {

                        mView.showLoadingIndicator(false);
                        // TODO: 12/17/2016 To show message of "Fail to load contacts."
                    }

                }, mContactRepository);

        contactsUseCase.execute();
    }

    private void processTransfer(Contact contact, double moneyValue) {

        SendMoneyUseCase sendMoneyUseCase = new SendMoneyUseCaseImpl(String.valueOf(contact.getId()),
                BntmApp.getInstance().getAuthToken(), moneyValue, mExecutor, mMainThread,
                new SendMoneyUseCase.Callback() {

                    @Override
                    public void onMoneySent(boolean status) {

                        mView.showTransferStatusAlertMessage(status);
                    }

                    @Override
                    public void onFailed() {

                        mView.showTransferStatusAlertMessage(false);
                    }

                }, mTransferRepository);

        sendMoneyUseCase.execute();
    }

    // endregion
}
