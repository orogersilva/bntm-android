package com.orogersilva.bntm.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by orogersilva on 12/18/2016.
 */

public class BitmapUtils {

    // region STATIC METHODS

    public static byte[] getByteArrayFromBitmap(Bitmap bitmap) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);

        return outputStream.toByteArray();
    }

    public static Bitmap getBitmapFromByteArray(byte[] stream) {

        return BitmapFactory.decodeByteArray(stream, 0, stream.length);
    }

    // endregion
}
