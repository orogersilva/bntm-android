package com.orogersilva.bntm.domain.usecase;

import com.orogersilva.bntm.domain.usecase.base.UseCase;

/**
 * Created by orogersilva on 12/19/2016.
 */

public interface SendMoneyUseCase extends UseCase {

    // region INTERFACES

    interface Callback {

        void onMoneySent(boolean status);
        void onFailed();
    }

    // endregion
}
