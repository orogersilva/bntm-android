package com.orogersilva.bntm.storage.converter;

import com.orogersilva.bntm.storage.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by orogersilva on 12/18/2016.
 */

public class StorageModelConverter {

    // region STATIC METHODS

    public static com.orogersilva.bntm.domain.model.Contact convertToDomainModel(Contact contact) {

        long id = contact.getId();
        String name = contact.getName();
        String phone = contact.getPhone();
        byte[] photo = contact.getPhoto();

        com.orogersilva.bntm.domain.model.Contact convertedContact = new com.orogersilva.bntm.domain.model.Contact(
                id,
                name,
                phone,
                photo
        );

        return convertedContact;
    }

    public static List<com.orogersilva.bntm.domain.model.Contact> convertListToDomainModel(List<Contact> contacts) {

        List<com.orogersilva.bntm.domain.model.Contact> convertedContacts = new ArrayList<>();

        for (Contact contact : contacts) {
            convertedContacts.add(convertToDomainModel(contact));
        }

        contacts.clear();
        contacts = null;

        return convertedContacts;
    }

    // endregion
}
