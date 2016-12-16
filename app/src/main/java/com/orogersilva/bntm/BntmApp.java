package com.orogersilva.bntm;

import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.*;
import com.crashlytics.android.core.BuildConfig;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by orogersilva on 12/15/2016.
 */

public class BntmApp extends Application {

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
