package com.orogersilva.bntm.domain.usecase.base;

import com.orogersilva.bntm.domain.executor.Executor;

/**
 * Created by orogersilva on 12/16/2016.
 */

public abstract class AbstractUseCase implements UseCase {

    // region FIELDS

    protected Executor mThreadExecutor;

    protected boolean mIsCanceled;
    protected boolean mIsRunning;

    // endregion

    // region CONSTRUCTORS

    public AbstractUseCase(Executor threadExecutor) {

        mThreadExecutor = threadExecutor;
    }

    // endregion

    // region PUBLIC METHODS

    public abstract void run();

    public void execute() {

        mIsRunning = true;

        mThreadExecutor.execute(this);
    }

    public boolean isRunning() {

        return mIsRunning;
    }

    public void cancel() {

        mIsCanceled = true;
        mIsRunning = false;
    }

    public void onFinished() {

        mIsCanceled = false;
        mIsRunning = false;
    }

    // endregion
}
