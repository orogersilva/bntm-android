package com.orogersilva.bntm.domain.executor;

/**
 * Created by orogersilva on 12/18/2016.
 */

public interface MainThread {

    // region METHODS

    void post(final Runnable runnable);

    // endregion
}
