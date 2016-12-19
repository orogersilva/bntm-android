package com.orogersilva.bntm.presentation.screens.transfer;

import com.orogersilva.bntm.BasePresenter;
import com.orogersilva.bntm.BaseView;
import com.orogersilva.bntm.domain.usecase.SendMoneyUseCase;
import com.orogersilva.bntm.presentation.model.Contact;

import java.util.List;

/**
 * Created by orogersilva on 12/17/2016.
 */

public interface TransferContract {

    // region INTERFACES

    interface View extends BaseView<Presenter> {

        // region METHODS

        void showLoadingIndicator(boolean isActive);

        void showContacts(List<Contact> contacts);

        void showTransferStatusAlertMessage(boolean isSuccessful);

        // endregion
    }

    interface Presenter extends BasePresenter {

        // region METHODS

        void loadContacts();

        void sendMoney(Contact contact, double moneyValue);

        // endregion
    }

    // endregion
}
