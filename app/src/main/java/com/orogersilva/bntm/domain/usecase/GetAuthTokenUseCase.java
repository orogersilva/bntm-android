package com.orogersilva.bntm.domain.usecase;

import com.orogersilva.bntm.domain.usecase.base.UseCase;

/**
 * Created by orogersilva on 12/16/2016.
 */

public interface GetAuthTokenUseCase extends UseCase {

    // region INTERFACES

    interface Callback {

        void onAuthTokenLoaded(String token);
        void onFailed();
    }

    // endregion
}
