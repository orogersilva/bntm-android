package com.orogersilva.bntm;

import android.os.Handler;

import com.orogersilva.bntm.domain.executor.MainThread;

/**
 * Created by orogersilva on 12/18/2016.
 */

public class MainThreadImpl implements MainThread {

    // region FIELDS

    private static MainThread INSTANCE;
    private Handler mHandler;

    // endregion

    // region CONSTRUCTORS

    private MainThreadImpl() {

        mHandler = new Handler();
    }

    // endregion

    // region STATIC METHODS

    public static MainThread getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new MainThreadImpl();
        }

        return INSTANCE;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void post(Runnable runnable) {

        mHandler.post(runnable);
    }

    // endregion
}
