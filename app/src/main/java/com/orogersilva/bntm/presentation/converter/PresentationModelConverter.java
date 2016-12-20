package com.orogersilva.bntm.presentation.converter;

import com.orogersilva.bntm.domain.model.Contact;
import com.orogersilva.bntm.storage.model.ContactTransfer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by orogersilva on 12/18/2016.
 */

public class PresentationModelConverter {

    // region STATIC METHODS

    public static com.orogersilva.bntm.presentation.model.Contact convertContactToPresentationModel(Contact contact) {

        long id = contact.getId();
        String name = contact.getName();
        String phone = contact.getPhone();
        byte[] photo = contact.getPhoto();

        com.orogersilva.bntm.presentation.model.Contact convertedContact = new com.orogersilva.bntm.presentation.model.Contact(
                id,
                name,
                phone,
                photo
        );

        return convertedContact;
    }

    public static List<com.orogersilva.bntm.presentation.model.Contact> convertContactListToPresentationModel(List<Contact> contacts) {

        List<com.orogersilva.bntm.presentation.model.Contact> convertedContacts = new ArrayList<>();

        for (Contact contact : contacts) {
            convertedContacts.add(convertContactToPresentationModel(contact));
        }

        contacts.clear();
        contacts = null;

        return convertedContacts;
    }

    public static com.orogersilva.bntm.presentation.model.ContactTransfer convertContactTransferToPresentationModel(ContactTransfer contactTransfer) {

        long contactId = contactTransfer.getContactId();
        String contactName = contactTransfer.getContactName();
        String contactPhone = contactTransfer.getContactPhone();
        byte[] contactPhoto = contactTransfer.getContactPhoto();
        long transferredTotalMoney = contactTransfer.getTransferredTotalMoney();

        com.orogersilva.bntm.presentation.model.ContactTransfer convertedContactTransfer = new com.orogersilva.bntm.presentation.model.ContactTransfer(
                contactId,
                contactName,
                contactPhone,
                contactPhoto,
                transferredTotalMoney
        );

        return convertedContactTransfer;
    }

    public static List<com.orogersilva.bntm.presentation.model.ContactTransfer> convertContactTransferListToPresentationModel(List<ContactTransfer> contactTransfers) {

        List<com.orogersilva.bntm.presentation.model.ContactTransfer> convertedContactTransfers = new ArrayList<>();

        for (ContactTransfer contactTransfer : contactTransfers) {
            convertedContactTransfers.add(convertContactTransferToPresentationModel(contactTransfer));
        }

        contactTransfers.clear();
        contactTransfers = null;

        return convertedContactTransfers;
    }

    // endregion
}
