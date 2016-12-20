package com.orogersilva.bntm.presentation.model;

import java.util.Arrays;

/**
 * Created by orogersilva on 12/20/2016.
 */

public class ContactTransfer {

    // region FIELDS

    private long contactId;
    private String contactName;
    private String contactPhone;
    private byte[] contactPhoto;
    private long transferredTotalMoney;

    // endregion

    // region CONSTRUCTORS

    public ContactTransfer(long contactId, String contactName, String contactPhone,
                           byte[] contactPhoto, long transferredTotalMoney) {

        this.contactId = contactId;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactPhoto = contactPhoto;
        this.transferredTotalMoney = transferredTotalMoney;
    }

    // endregion

    // region PUBLIC METHODS

    public long getContactId() {

        return contactId;
    }

    public String getContactName() {

        return contactName;
    }

    public String getContactPhone() {

        return contactPhone;
    }

    public byte[] getContactPhoto() {

        return contactPhoto;
    }

    public long getTransferredTotalMoney() {

        return transferredTotalMoney;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactTransfer contactTransfer = (ContactTransfer) o;

        return contactId == contactTransfer.getContactId() &&
                (contactName != null && contactTransfer.getContactName() != null && contactName.equals(contactTransfer.getContactName())) &&
                (contactPhone != null && contactTransfer.getContactPhone() != null && contactPhone.equals(contactTransfer.getContactPhone())) &&
                Arrays.equals(contactPhoto, contactTransfer.getContactPhoto()) &&
                transferredTotalMoney == contactTransfer.getTransferredTotalMoney();
    }

    // endregion
}
