package com.orogersilva.bntm.ui.home;

import android.support.annotation.MainThread;

import com.orogersilva.bntm.domain.executor.Executor;
import com.orogersilva.bntm.domain.repository.AuthRepository;
import com.orogersilva.bntm.domain.usecase.GetAuthTokenUseCase;
import com.orogersilva.bntm.domain.usecase.impl.GetAuthTokenUseCaseImpl;

/**
 * Created by orogersilva on 12/17/2016.
 */

public class HomePresenter implements HomeContract.Presenter {

    // region FIELDS

    private final HomeContract.View mView;

    // endregion

    // region CONSTRUCTORS

    public HomePresenter(HomeContract.View view) {

        mView = view;

        mView.setPresenter(this);
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void resume() {

        final String NAME = "Roger Silva";
        final String EMAIL = "orogersilva@gmail.com";

        mView.showHome(NAME, EMAIL);
    }

    // endregion
}
