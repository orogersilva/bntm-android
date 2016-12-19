package com.orogersilva.bntm.domain.repository;

import android.support.annotation.NonNull;

import com.orogersilva.bntm.ContactDataSource;
import com.orogersilva.bntm.storage.model.Contact;

import java.util.List;

/**
 * Created by orogersilva on 12/18/2016.
 */

public class ContactRepository implements ContactDataSource {

    // region FIELDS

    private static ContactRepository INSTANCE = null;

    private final ContactDataSource mContactLocalDataSource;

    // endregion

    // region CONSTRUCTORS

    private ContactRepository(@NonNull ContactDataSource contactLocalDataSource) {

        mContactLocalDataSource = contactLocalDataSource;
    }

    // endregion

    // region STATIC METHODS

    public static ContactRepository getInstance(ContactDataSource contactLocalDataSource) {

        if (INSTANCE == null) {
            INSTANCE = new ContactRepository(contactLocalDataSource);
        }

        return INSTANCE;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void getContacts(final GetContactsCallback callback) {

        mContactLocalDataSource.getContacts(new GetContactsCallback() {

            @Override
            public void onContactsLoaded(List<Contact> contacts) {

                callback.onContactsLoaded(contacts);
            }

            @Override
            public void onDataNotAvailable() {

                callback.onDataNotAvailable();
            }
        });
    }

    // endregion
}
