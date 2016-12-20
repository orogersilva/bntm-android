package com.orogersilva.bntm.storage.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.orogersilva.bntm.TransferDataSource;
import com.orogersilva.bntm.storage.model.ContactTransfer;
import com.orogersilva.bntm.storage.persistence.PersistenceContract.TransferEntry;
import com.orogersilva.bntm.sync.model.Transfer;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by orogersilva on 12/19/2016.
 */

public class TransferLocalDataSource implements TransferDataSource {

    // region FIELDS

    private static TransferLocalDataSource INSTANCE;

    private DbHelper mDbHelper;

    // endregion

    // region CONSTRUCTORS

    private TransferLocalDataSource(Context context) {

        mDbHelper = new DbHelper(context);
    }

    // endregion

    // region STATIC METHODS

    public static TransferLocalDataSource getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new TransferLocalDataSource(context);
        }

        return INSTANCE;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void sendMoney(String id, String authToken, double moneyValue, SendMoneyCallback callback) {

    }

    @Override
    public void getTransfers(String authToken, GetTransfersCallback callback) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String sql = "SELECT c.contact_id, c.contact_name, c.contact_phone, c.contact_photo, SUM(t.transfer_money_value) as \"total_transfer_value\" " +
                "FROM contact as c " +
                "INNER JOIN transfer as t " +
                "ON c.contact_id = t.transfer_client_id " +
                "GROUP BY c.contact_id";

        Cursor c = db.rawQuery(sql, null);

        List<ContactTransfer> contactTotalTransfers = new ArrayList<>();

        if (c != null && c.getCount() > 0) {

            while (c.moveToNext()) {

                long contactId = c.getLong(c.getColumnIndex(PersistenceContract.ContactEntry.COLUMN_NAME_ID));
                String contactName = c.getString(c.getColumnIndex(PersistenceContract.ContactEntry.COLUMN_NAME_NAME));
                String contactPhone = c.getString(c.getColumnIndex(PersistenceContract.ContactEntry.COLUMN_NAME_PHONE));
                byte[] contactPhoto = c.getBlob(c.getColumnIndex(PersistenceContract.ContactEntry.COLUMN_NAME_PHOTO));
                long transferredTotalMoney = c.getLong(c.getColumnIndex("total_transfer_value"));

                ContactTransfer contactTotalTransfer = new ContactTransfer(contactId,
                        contactName, contactPhone, contactPhoto, transferredTotalMoney);

                contactTotalTransfers.add(contactTotalTransfer);
            }
        }

        if (c != null) c.close();

        db.close();

        if (contactTotalTransfers.isEmpty()) {
            callback.onDataNotAvailable();
        } else {
            callback.onContactTransfersLoaded(contactTotalTransfers);
        }
    }

    @Override
    public int deleteAllTransfers() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        final int deletedRows = db.delete(TransferEntry.TABLE_NAME, null, null);

        db.close();

        return deletedRows;
    }

    @Override
    public void saveTransfers(List<Transfer> transfers) {

        if (transfers == null) throw new NullPointerException();

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String sql = "INSERT INTO " + TransferEntry.TABLE_NAME + " (" +
                TransferEntry.COLUMN_NAME_CLIENT_ID + ", " +
                TransferEntry.COLUMN_NAME_MONEY_VALUE + ", " +
                TransferEntry.COLUMN_NAME_DATE + ") VALUES (?, ?, ?);";

        try {

            SQLiteStatement statement = db.compileStatement(sql);

            db.beginTransaction();

            for (Transfer transfer : transfers) {

                // statement.bindLong(1, transfer.getId());
                statement.bindLong(1, transfer.getClientId());
                statement.bindLong(2, transfer.getMoneyValue());
                statement.bindString(3, transfer.getDate());

                statement.execute();
            }

            statement.close();

            db.setTransactionSuccessful();

        } catch (Exception e) {

            Timber.e(e);

        } finally {

            db.endTransaction();
        }

        db.close();
    }

    // endregion
}
