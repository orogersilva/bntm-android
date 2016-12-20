package com.orogersilva.bntm.data;

import com.orogersilva.bntm.storage.model.Contact;

import java.util.List;

/**
 * Created by orogersilva on 12/18/2016.
 */

public interface ContactDataSource {

    // region INTERFACES

    interface GetContactsCallback {

        void onContactsLoaded(List<Contact> contacts);
        void onDataNotAvailable();
    }

    // endregion

    // region METHODS

    void getContacts(GetContactsCallback callback);

    // endregion
}
