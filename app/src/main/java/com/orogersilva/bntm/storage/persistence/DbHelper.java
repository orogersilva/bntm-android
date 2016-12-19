package com.orogersilva.bntm.storage.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.orogersilva.bntm.R;
import com.orogersilva.bntm.storage.model.Contact;
import com.orogersilva.bntm.storage.persistence.PersistenceContract.ContactEntry;
import com.orogersilva.bntm.util.BitmapUtils;

import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

/**
 * Created by orogersilva on 12/18/2016.
 */

public class DbHelper extends SQLiteOpenHelper {

    // region FIELDS

    public static final int DATABASE_VERSION = 1;
    public static final String DB_NAME = "neon.sqlite";

    private static final String BLOB_TYPE = " BLOB";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String TEXT_TYPE = " TEXT";

    private static final String COMMA_SEPARATOR = ",";

    private Context mContext;

    // endregion

    // region CONSTRUCTORS

    public DbHelper(Context context) {

        super(context, DB_NAME, null, DATABASE_VERSION);

        mContext = context;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_CONTACT_ENTRIES);

        prepareContacts(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_CONTACT_ENTRIES);

        onCreate(db);
    }

    // endregion

    // region UTILITY METHODS

    private void prepareContacts(SQLiteDatabase db) {

        Bitmap bitmapContact1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.barbara_matos);
        Bitmap bitmapContact2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.bruna_suarez);
        Bitmap bitmapContact3 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.carina_rodrigues);
        Bitmap bitmapContact4 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.douglas_matarazzo);
        Bitmap bitmapContact5 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.eduardo_bittencourt);
        Bitmap bitmapContact6 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.felicia_andres);
        Bitmap bitmapContact7 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.fernanda_silva);
        Bitmap bitmapContact8 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.guilhermina_bavaro);
        Bitmap bitmapContact9 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.helena_berna);
        Bitmap bitmapContact10 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.laura_otto);
        Bitmap bitmapContact11 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.luana_bievenutti);
        Bitmap bitmapContact12 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.olavo_cruz);
        Bitmap bitmapContact13 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.peter_crusoe);
        Bitmap bitmapContact14 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.shizu_osaka);
        Bitmap bitmapContact15 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.sula_almeida);

        Contact contact1 = new Contact(1, "Barbara Matos", "5194390233", BitmapUtils.getByteArrayFromBitmap(bitmapContact1));
        Contact contact2 = new Contact(2, "Bruna Suarez", "5192389322", BitmapUtils.getByteArrayFromBitmap(bitmapContact2));
        Contact contact3 = new Contact(3, "Carina Rodrigues", "5193822011", BitmapUtils.getByteArrayFromBitmap(bitmapContact3));
        Contact contact4 = new Contact(4, "Douglas Matarazzo", "5199389347", BitmapUtils.getByteArrayFromBitmap(bitmapContact4));
        Contact contact5 = new Contact(5, "Eduardo Bittencourt", "5192883888", BitmapUtils.getByteArrayFromBitmap(bitmapContact5));
        Contact contact6 = new Contact(6, "Felícia Andrès", "5192938294", BitmapUtils.getByteArrayFromBitmap(bitmapContact6));
        Contact contact7 = new Contact(7, "Fernanda Silva", "5194932193", BitmapUtils.getByteArrayFromBitmap(bitmapContact7));
        Contact contact8 = new Contact(8, "Guilhermina Bavaro", "5198300032", BitmapUtils.getByteArrayFromBitmap(bitmapContact8));
        Contact contact9 = new Contact(9, "Helena Berna", "5198930222", BitmapUtils.getByteArrayFromBitmap(bitmapContact9));
        Contact contact10 = new Contact(10, "Laura Otto", "5190222123", BitmapUtils.getByteArrayFromBitmap(bitmapContact10));
        Contact contact11 = new Contact(11, "Luana Bievenutti", "5192033499", BitmapUtils.getByteArrayFromBitmap(bitmapContact11));
        Contact contact12 = new Contact(12, "Olavo Cruz", "5195637744", BitmapUtils.getByteArrayFromBitmap(bitmapContact12));
        Contact contact13 = new Contact(13, "Peter Crusoe", "5191002999", BitmapUtils.getByteArrayFromBitmap(bitmapContact13));
        Contact contact14 = new Contact(14, "Shizu Osaka", "5194444444", BitmapUtils.getByteArrayFromBitmap(bitmapContact14));
        Contact contact15 = new Contact(15, "Sula Almeida", "5198882012", BitmapUtils.getByteArrayFromBitmap(bitmapContact15));

        List<Contact> contacts = Arrays.asList(contact1, contact2, contact3, contact4, contact5,
                contact6, contact7, contact8, contact9, contact10, contact11, contact12, contact13,
                contact14, contact15);


        String sql = "INSERT INTO " + ContactEntry.TABLE_NAME + " VALUES (?, ?, ?, ?);";

        try {

            SQLiteStatement statement = db.compileStatement(sql);

            db.beginTransaction();

            for (Contact contact : contacts) {

                statement.bindLong(1, contact.getId());
                statement.bindString(2, contact.getName());
                statement.bindString(3, contact.getPhone());
                statement.bindBlob(4, contact.getPhoto());

                statement.execute();
            }

            statement.close();

            db.setTransactionSuccessful();


        } catch (Exception e) {

            Timber.e(e);

        } finally {

            db.endTransaction();
        }

        // db.close();
    }

    // endregion

    // region SCRIPTS

    private static final String SQL_CREATE_CONTACT_ENTRIES =
            "CREATE TABLE " + ContactEntry.TABLE_NAME + " (" +
                    ContactEntry.COLUMN_NAME_ID + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEPARATOR +
                    ContactEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEPARATOR +
                    ContactEntry.COLUMN_NAME_PHONE + TEXT_TYPE + COMMA_SEPARATOR +
                    ContactEntry.COLUMN_NAME_PHOTO + BLOB_TYPE +
                    ")";

    private static final String SQL_DELETE_CONTACT_ENTRIES =
            "DROP TABLE IF EXISTS " + ContactEntry.TABLE_NAME;

    // endregion
}
