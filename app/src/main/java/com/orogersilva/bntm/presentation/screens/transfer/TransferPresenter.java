package com.orogersilva.bntm.presentation.screens.transfer;

import com.orogersilva.bntm.BntmApp;
import com.orogersilva.bntm.domain.executor.Executor;
import com.orogersilva.bntm.domain.executor.MainThread;
import com.orogersilva.bntm.domain.model.Contact;
import com.orogersilva.bntm.domain.repository.AuthRepository;
import com.orogersilva.bntm.domain.repository.ContactRepository;
import com.orogersilva.bntm.domain.usecase.GetAuthTokenUseCase;
import com.orogersilva.bntm.domain.usecase.GetContactsUseCase;
import com.orogersilva.bntm.domain.usecase.impl.GetAuthTokenUseCaseImpl;
import com.orogersilva.bntm.domain.usecase.impl.GetContactsUseCaseImpl;
import com.orogersilva.bntm.presentation.converter.PresentationModelConverters;
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
    private ContactRepository mContactRepository;

    // endregion

    // region CONSTRUCTORS

    public TransferPresenter(TransferContract.View view, Executor executor, MainThread mainThread,
                             ContactRepository contactRepository) {

        super(executor, mainThread);

        mView = view;
        mContactRepository = contactRepository;

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

    // endregion

    // region UTILITY METHODS

    private void processContacts() {

        GetContactsUseCase contactsUseCase = new GetContactsUseCaseImpl(mExecutor, mMainThread,
                new GetContactsUseCase.Callback() {

                    @Override
                    public void onContactsLoaded(List<Contact> contacts) {

                        mView.showLoadingIndicator(false);

                        Collections.sort(contacts);

                        mView.showContacts(PresentationModelConverters.convertListToPresentationModel(contacts));
                    }

                    @Override
                    public void onDataNotAvailable() {

                        mView.showLoadingIndicator(false);
                        // TODO: 12/17/2016 To show message of "Fail to load contacts."
                    }

                }, mContactRepository);

        contactsUseCase.execute();
    }

    // endregion
}
