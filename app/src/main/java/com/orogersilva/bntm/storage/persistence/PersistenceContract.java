package com.orogersilva.bntm.storage.persistence;

import android.provider.BaseColumns;

/**
 * Created by orogersilva on 12/18/2016.
 */

public class PersistenceContract {

    // region CONSTRUCTORS

    private PersistenceContract() {}

    // endregion

    // region INNER CLASSES

    public static abstract class ContactEntry implements BaseColumns {

        // region FIELDS

        public static final String TABLE_NAME = "contact";

        public static final String COLUMN_NAME_ID = TABLE_NAME + "_id";
        public static final String COLUMN_NAME_NAME = TABLE_NAME + "_name";
        public static final String COLUMN_NAME_PHONE = TABLE_NAME + "_phone";
        public static final String COLUMN_NAME_PHOTO = TABLE_NAME + "_photo";

        // endregion
    }

    public static abstract class TransferEntry implements BaseColumns {

        // region FIELDS

        public static final String TABLE_NAME = "transfer";

        public static final String COLUMN_NAME_ID = TABLE_NAME + "_id";
        public static final String COLUMN_NAME_CLIENT_ID = TABLE_NAME + "_client_id";
        public static final String COLUMN_NAME_MONEY_VALUE = TABLE_NAME + "_money_value";
        public static final String COLUMN_NAME_DATE = TABLE_NAME + "_date";

        // endregion
    }

    // endregion
}
