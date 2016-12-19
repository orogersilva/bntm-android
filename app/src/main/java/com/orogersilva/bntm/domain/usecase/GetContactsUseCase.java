package com.orogersilva.bntm.domain.usecase;

import com.orogersilva.bntm.domain.model.Contact;
import com.orogersilva.bntm.domain.usecase.base.UseCase;

import java.util.List;

/**
 * Created by orogersilva on 12/18/2016.
 */

public interface GetContactsUseCase extends UseCase {

    // region INTERFACES

    interface Callback {

        void onContactsLoaded(List<Contact> contacts);
        void onDataNotAvailable();
    }

    // endregion
}
