package com.orogersilva.bntm.domain.executor.impl;

import com.orogersilva.bntm.domain.executor.Executor;
import com.orogersilva.bntm.domain.usecase.base.AbstractUseCase;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by orogersilva on 12/16/2016.
 */

public class ThreadExecutor implements Executor {

    // region FIELDS

    private static ThreadExecutor sThreadExecutor;

    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingDeque<Runnable> WORK_QUEUE = new LinkedBlockingDeque<>();

    private ThreadPoolExecutor mThreadPoolExecutor;

    // endregion

    // region CONSTRUCTORS

    private ThreadExecutor() {

        mThreadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT, WORK_QUEUE);
    }

    // endregion

    // region STATIC METHODS

    public static Executor getInstance() {

        if (sThreadExecutor == null) {
            sThreadExecutor = new ThreadExecutor();
        }

        return sThreadExecutor;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void execute(final AbstractUseCase useCase) {

        mThreadPoolExecutor.submit(new Runnable() {

            @Override
            public void run() {

                useCase.run();
                useCase.onFinished();
            }
        });
    }

    // endregion
}
