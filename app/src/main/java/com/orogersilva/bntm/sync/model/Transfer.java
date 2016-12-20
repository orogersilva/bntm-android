package com.orogersilva.bntm.sync.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by orogersilva on 12/19/2016.
 */

public class Transfer {

    // region FIELDS

    @SerializedName("Id")
    long id;
    @SerializedName("ClienteId")
    long clientId;
    @SerializedName("Valor")
    double moneyValue;
    @SerializedName("Token")
    String authToken;
    @SerializedName("Data")
    String date;

    // endregion

    // region CONSTRUCTORS

    public Transfer(long id, long clientId, long moneyValue, String date) {

        this.id = id;
        this.clientId = clientId;
        this.moneyValue = (double) (moneyValue / 100);
        this.date = date;
    }

    // endregion

    // region PUBLIC METHODS

    public long getId() {

        return id;
    }

    public long getClientId() {

        return clientId;
    }

    public long getMoneyValue() {

        long centMoneyValue = (long) (moneyValue * 100);

        return centMoneyValue;
    }

    public String getAuthToken() {

        return authToken;
    }

    public String getDate() {

        return date;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transfer transfer = (Transfer) o;

        return id == transfer.getId() &&
                clientId == transfer.getClientId() &&
                getMoneyValue() == transfer.getMoneyValue() &&
                (authToken != null && transfer.getAuthToken() != null && authToken.equals(transfer.getAuthToken())) &&
                (date != null && transfer.getDate() != null && date.equals(transfer.getDate()));
    }

    // endregion
}
