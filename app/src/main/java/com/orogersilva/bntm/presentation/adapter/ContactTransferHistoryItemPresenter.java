package com.orogersilva.bntm.presentation.adapter;

import com.orogersilva.bntm.presentation.model.ContactTransfer;

/**
 * Created by orogersilva on 12/20/2016.
 */

public class ContactTransferHistoryItemPresenter {

    // region PUBLIC METHODS

    public void presentListItem(ContactTransferHistoryAdapter.ItemViewHolder itemViewHolder,
                                ContactTransfer contactTransfer) {

        itemViewHolder.setItem(contactTransfer);
    }

    // endregion
}
