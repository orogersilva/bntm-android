package com.orogersilva.bntm.domain.usecase.base;

import com.orogersilva.bntm.domain.executor.Executor;
import com.orogersilva.bntm.domain.executor.MainThread;

/**
 * Created by orogersilva on 12/16/2016.
 */

public abstract class AbstractUseCase implements UseCase {

    // region FIELDS

    protected Executor mThreadExecutor;
    protected MainThread mMainThread;

    protected boolean mIsCanceled;
    protected boolean mIsRunning;

    // endregion

    // region CONSTRUCTORS

    public AbstractUseCase(Executor threadExecutor, MainThread mainThread) {

        mThreadExecutor = threadExecutor;
        mMainThread = mainThread;
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
