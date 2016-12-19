package com.orogersilva.bntm.presentation.converter;

import com.orogersilva.bntm.domain.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by orogersilva on 12/18/2016.
 */

public class PresentationModelConverters {

    // region STATIC METHODS

    public static com.orogersilva.bntm.presentation.model.Contact convertToPresentationModel(Contact contact) {

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

    public static List<com.orogersilva.bntm.presentation.model.Contact> convertListToPresentationModel(List<Contact> contacts) {

        List<com.orogersilva.bntm.presentation.model.Contact> convertedContacts = new ArrayList<>();

        for (Contact contact : contacts) {
            convertedContacts.add(convertToPresentationModel(contact));
        }

        contacts.clear();
        contacts = null;

        return convertedContacts;
    }

    // endregion
}
