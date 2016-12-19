package com.orogersilva.bntm.storage.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.orogersilva.bntm.ContactDataSource;
import com.orogersilva.bntm.storage.model.Contact;
import com.orogersilva.bntm.storage.persistence.PersistenceContract.ContactEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by orogersilva on 12/18/2016.
 */

public class ContactLocalDataSource implements ContactDataSource {

    // region FIELDS

    private static ContactLocalDataSource INSTANCE;

    private DbHelper mDbHelper;

    // endregion

    // region CONSTRUCTORS

    private ContactLocalDataSource(Context context) {

        mDbHelper = new DbHelper(context);
    }

    // endregion

    // region STATIC METHODS

    public static ContactLocalDataSource getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new ContactLocalDataSource(context);
        }

        return INSTANCE;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void getContacts(GetContactsCallback callback) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                ContactEntry.COLUMN_NAME_ID,
                ContactEntry.COLUMN_NAME_NAME,
                ContactEntry.COLUMN_NAME_PHONE,
                ContactEntry.COLUMN_NAME_PHOTO
        };

        Cursor c = db.query(ContactEntry.TABLE_NAME, projection, null, null, null, null, null);

        List<Contact> contacts = new ArrayList<>();

        if (c != null && c.getCount() > 0) {

            while (c.moveToNext()) {

                long id = c.getLong(c.getColumnIndex(ContactEntry.COLUMN_NAME_ID));
                String name = c.getString(c.getColumnIndex(ContactEntry.COLUMN_NAME_NAME));
                String phone = c.getString(c.getColumnIndex(ContactEntry.COLUMN_NAME_PHONE));
                byte[] photo = c.getBlob(c.getColumnIndex(ContactEntry.COLUMN_NAME_PHOTO));

                Contact contact = new Contact(id, name, phone, photo);

                contacts.add(contact);
            }
        }

        if (c != null) c.close();

        db.close();

        if (contacts.isEmpty()) {
            callback.onDataNotAvailable();
        } else {
            callback.onContactsLoaded(contacts);
        }
    }

    // endregion
}
