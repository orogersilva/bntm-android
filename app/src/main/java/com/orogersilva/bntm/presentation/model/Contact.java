package com.orogersilva.bntm.presentation.model;

import android.support.annotation.NonNull;

import java.util.Arrays;

/**
 * Created by orogersilva on 12/17/2016.
 */

public class Contact {

    // region FIELDS

    private String name;
    private String phone;
    private byte[] photo;

    // endregion

    // region CONSTRUCTORS

    public Contact(String name, String phone, byte[] photo) {

        this.name = name;
        this.phone = phone;
        this.photo = photo;
    }

    // endregion

    // region PUBLIC METHODS

    public String getName() {

        return name;
    }

    public String getPhone() {

        return phone;
    }

    public byte[] getPhoto() {

        return photo;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return (name != null && contact.getName() != null && name.equals(contact.getName())) &&
                (phone != null && contact.getPhone() != null && phone.equals(contact.getPhone())) &&
                Arrays.equals(photo, contact.getPhoto());
    }

    // endregion
}
