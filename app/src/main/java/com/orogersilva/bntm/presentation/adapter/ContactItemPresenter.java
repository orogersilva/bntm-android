package com.orogersilva.bntm.presentation.adapter;

import com.orogersilva.bntm.presentation.model.Contact;

/**
 * Created by orogersilva on 12/18/2016.
 */

public class ContactItemPresenter {

    // region PUBLIC METHODS

    public void presentListItem(ContactAdapter.ItemViewHolder itemViewHolder, Contact contact) {

        itemViewHolder.setItem(contact);
    }

    // endregion
}
