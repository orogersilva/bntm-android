package com.orogersilva.bntm.presentation.screens;

import com.orogersilva.bntm.domain.executor.Executor;
import com.orogersilva.bntm.domain.executor.MainThread;

/**
 * Created by orogersilva on 12/18/2016.
 */

public abstract class AbstractPresenter {

    // region FIELDS

    protected Executor mExecutor;
    protected MainThread mMainThread;

    // endregion

    // region CONSTRUCTORS

    public AbstractPresenter(Executor executor, MainThread mainThread) {

        mExecutor = executor;
        mMainThread = mainThread;
    }

    // endregion
}
