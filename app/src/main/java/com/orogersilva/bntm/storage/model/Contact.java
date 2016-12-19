package com.orogersilva.bntm.storage.model;

import java.util.Arrays;

/**
 * Created by orogersilva on 12/18/2016.
 */

public class Contact {

    // region FIELDS

    private long id;
    private String name;
    private String phone;
    private byte[] photo;

    // endregion

    // region CONSTRUCTORS

    public Contact(long id, String name, String phone, byte[] photo) {

        setId(id);
        setName(name);
        setPhone(phone);
        setPhoto(photo);
    }

    // endregion

    // region PUBLIC METHODS

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public byte[] getPhoto() {

        return photo;
    }

    public void setPhoto(byte[] photo) {

        this.photo = photo;
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

    // endregion
}
