package com.orogersilva.bntm;

import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.*;
import com.crashlytics.android.core.BuildConfig;
import com.orogersilva.bntm.storage.persistence.AuthLocalDataSource;
import com.orogersilva.bntm.util.StringUtils;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by orogersilva on 12/15/2016.
 */

public class BntmApp extends Application {

    // region FIELDS

    private static BntmApp INSTANCE;

    private String mAuthToken;

    private final String USERNAME = "Roger Silva";
    private final String EMAIL = "orogersilva@gmail.com";

    // endregion

    // region APPLICATION LIFECYCLE METHODS

    @Override
    public void onCreate() {

        super.onCreate();

        // Inicializando Crashlytics (apenas para tipo de build DEBUG).
        CrashlyticsCore core = new CrashlyticsCore.Builder()
                .disabled(com.crashlytics.android.core.BuildConfig.DEBUG)
                .build();

        Fabric.with(this, new Crashlytics.Builder().core(core).build());

        // Configurando Timber.
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        Timber.plant(new CrashlyticsTree());

        INSTANCE = this;

        tryRetrieveAuthTokenFromStorage();
    }

    // endregion

    // region STATIC METHODS

    public static BntmApp getInstance() {

        return INSTANCE;
    }

    // endregion

    // region PUBLIC METHODS

    public String getAuthToken() {

        if (StringUtils.isNullOrEmpty(mAuthToken)) {
            tryRetrieveAuthTokenFromStorage();
        }

        return mAuthToken;
    }

    public void setAuthToken(String authToken) {

        mAuthToken = authToken;
    }

    public String getUsername() {

        return USERNAME;
    }

    public String getEmail() {

        return EMAIL;
    }

    public boolean hasLoggedIn() {

        return !StringUtils.isNullOrEmpty(mAuthToken);
    }

    // endregion

    // region UTILITY METHODS

    private void tryRetrieveAuthTokenFromStorage() {

        AuthLocalDataSource authLocalDataSource = AuthLocalDataSource.getInstance(this);

        authLocalDataSource.getAuthToken(USERNAME, EMAIL, new AuthDataSource.GetAuthTokenCallback() {

            @Override
            public void onAuthTokenLoaded(String authToken) {

                mAuthToken = authToken;
            }

            @Override
            public void onFailed() {

                mAuthToken = null;
            }
        });
    }

    // endregion

    // region UTILITY CLASSES

    private class CrashlyticsTree extends Timber.Tree {

        // region FIELDS

        private static final String CRASHLYTICS_PRIORITY_KEY = "priority";
        private static final String CRASHLYTICS_TAG_KEY = "tag";
        private static final String CRASHLYTICS_MESSAGE_KEY = "message";

        // endregion

        // region OVERRIDED METHODS

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {

            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) return;

            Crashlytics.setInt(CRASHLYTICS_PRIORITY_KEY, priority);
            Crashlytics.setString(CRASHLYTICS_TAG_KEY, tag);
            Crashlytics.setString(CRASHLYTICS_MESSAGE_KEY, message);

            if (t == null) {
                Crashlytics.logException(new Exception(message));
            } else {
                Crashlytics.logException(t);
            }
        }
    }

    // endregion
}
