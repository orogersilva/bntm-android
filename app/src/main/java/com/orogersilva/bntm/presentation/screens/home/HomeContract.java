package com.orogersilva.bntm.presentation.screens.home;

import com.orogersilva.bntm.BasePresenter;
import com.orogersilva.bntm.BaseView;

/**
 * Created by orogersilva on 12/17/2016.
 */

public interface HomeContract {

    // region INTERFACES

    interface View extends BaseView<Presenter> {

        // region METHODS

        void showHome(String name, String email);

        // endregion
    }

    interface Presenter extends BasePresenter {

        // region METHODS



        // endregion
    }

    // endregion
}
