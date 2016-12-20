package com.orogersilva.bntm.presentation.screens.home;

import com.orogersilva.bntm.BntmApp;

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

        mView.showHome(BntmApp.getInstance().getUsername(), BntmApp.getInstance().getEmail());
    }

    // endregion
}
