package com.orogersilva.bntm.domain.usecase;

import com.orogersilva.bntm.domain.usecase.base.UseCase;
import com.orogersilva.bntm.storage.model.ContactTransfer;

import java.util.List;

/**
 * Created by orogersilva on 12/19/2016.
 */

public interface GetTransfersUseCase extends UseCase {

    // region INTERFACES

    interface Callback {

        void onContactTransfersLoaded(List<ContactTransfer> contactTransfers);
        void onDataNotAvailable();
    }

    // endregion
}
