package com.orogersilva.bntm.domain.model;

import android.support.annotation.NonNull;

import java.util.Arrays;

/**
 * Created by orogersilva on 12/18/2016.
 */

public class Contact implements Comparable<Contact> {

    // region FIELDS

    private long id;
    private String name;
    private String phone;
    private byte[] photo;

    // endregion

    // region CONSTRUCTORS

    public Contact(long id, String name, String phone, byte[] photo) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.photo = photo;
    }

    // endregion

    // region PUBLIC METHODS

    public long getId() {

        return id;
    }

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

        return id == contact.getId() &&
                (name != null && contact.getName() != null && name.equals(contact.getName())) &&
                (phone != null && contact.getPhone() != null && phone.equals(contact.getPhone())) &&
                Arrays.equals(photo, contact.getPhoto());
    }

    @Override
    public int compareTo(@NonNull Contact contact) {

        return name.compareTo(contact.getName());
    }

    // endregion
}
