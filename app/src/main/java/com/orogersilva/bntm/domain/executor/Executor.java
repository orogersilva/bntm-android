package com.orogersilva.bntm.domain.executor;

import com.orogersilva.bntm.domain.usecase.base.AbstractUseCase;

/**
 * Created by orogersilva on 12/16/2016.
 */

public interface Executor {

    // region METHODS

    void execute(final AbstractUseCase useCase);

    // endregion
}
