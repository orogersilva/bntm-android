package com.orogersilva.bntm.presentation.screens.transferhistory;

import com.orogersilva.bntm.BasePresenter;
import com.orogersilva.bntm.BaseView;
import com.orogersilva.bntm.presentation.model.ContactTransfer;

import java.util.List;

/**
 * Created by orogersilva on 12/19/2016.
 */

public interface TransferHistoryContract {

    // region INTERFACES

    interface View extends BaseView<Presenter> {

        // region METHODS

        void showLoadingIndicator(boolean isActive);

        void showContactTransfersHistory(List<ContactTransfer> contactTransfers);

        // endregion
    }

    interface Presenter extends BasePresenter {

        // region METHODS

        void loadContactTransfersHistory();

        // endregion
    }

    // endregion
}
