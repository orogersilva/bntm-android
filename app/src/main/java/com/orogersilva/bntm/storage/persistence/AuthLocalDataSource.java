package com.orogersilva.bntm.storage.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.orogersilva.bntm.AuthDataSource;
import com.orogersilva.bntm.R;
import com.orogersilva.bntm.util.StringUtils;

/**
 * Created by orogersilva on 12/16/2016.
 */

public class AuthLocalDataSource implements AuthDataSource {

    // region FIELDS

    private Context mContext;

    // endregion

    // region CONSTRUCTORS

    public AuthLocalDataSource(Context context) {

        mContext = context;
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
