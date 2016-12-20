package com.orogersilva.bntm.storage.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.orogersilva.bntm.data.AuthDataSource;
import com.orogersilva.bntm.R;
import com.orogersilva.bntm.util.StringUtils;

/**
 * Created by orogersilva on 12/16/2016.
 */

public class AuthLocalDataSource implements AuthDataSource {

    // region FIELDS

    private static AuthLocalDataSource INSTANCE;

    private Context mContext;

    // endregion

    // region CONSTRUCTORS

    private AuthLocalDataSource(Context context) {

        mContext = context;
    }

    // endregion

    // region STATIC METHODS

    public static AuthLocalDataSource getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new AuthLocalDataSource(context);
        }

        return INSTANCE;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void getAuthToken(String name, String email, GetAuthTokenCallback callback) {

        SharedPreferences sharedPref = mContext.getSharedPreferences(
                mContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String authToken = sharedPref.getString(mContext.getString(R.string.auth_token_key), null);

        if (StringUtils.isNullOrEmpty(authToken)) {
            callback.onFailed();
        } else {
            callback.onAuthTokenLoaded(authToken);
        }
    }

    @Override
    public void saveAuthToken(final String authToken) {

        SharedPreferences sharedPref = mContext.getSharedPreferences(
                mContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(mContext.getString(R.string.auth_token_key), authToken);
        editor.commit();
    }

    @Override
    public void deleteAuthToken() {

        SharedPreferences sharedPref = mContext.getSharedPreferences(
                mContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.clear();
        editor.commit();
    }

    // endregion
}
