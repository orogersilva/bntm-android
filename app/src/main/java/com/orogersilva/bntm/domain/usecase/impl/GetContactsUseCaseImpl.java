package com.orogersilva.bntm.domain.usecase.impl;

import com.orogersilva.bntm.data.ContactDataSource;
import com.orogersilva.bntm.domain.executor.Executor;
import com.orogersilva.bntm.domain.executor.MainThread;
import com.orogersilva.bntm.domain.repository.ContactRepository;
import com.orogersilva.bntm.domain.usecase.GetContactsUseCase;
import com.orogersilva.bntm.domain.usecase.base.AbstractUseCase;
import com.orogersilva.bntm.storage.converter.StorageModelConverter;

import java.util.List;

/**
 * Created by orogersilva on 12/18/2016.
 */

public class GetContactsUseCaseImpl extends AbstractUseCase implements GetContactsUseCase {

    // region FIELDS

    private GetContactsUseCase.Callback mCallback;
    private ContactRepository mContactRepository;

    // endregion

    // region CONSTRUCTORS

    public GetContactsUseCaseImpl(Executor threadExecutor, MainThread mainThread,
                                  Callback callback, ContactRepository contactRepository) {

        super(threadExecutor, mainThread);

        mCallback = callback;
        mContactRepository = contactRepository;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void run() {

        mContactRepository.getContacts(new ContactDataSource.GetContactsCallback() {


            @Override
            public void onContactsLoaded(final List<com.orogersilva.bntm.storage.model.Contact> contacts) {

                mMainThread.post(new Runnable() {

                    // region OVERRIDED METHODS

                    @Override
                    public void run() {

                        mCallback.onContactsLoaded(StorageModelConverter.convertListToDomainModel(contacts));
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
