package com.orogersilva.bntm.domain.repository;

import android.support.annotation.NonNull;

import com.orogersilva.bntm.AuthDataSource;

/**
 * Created by orogersilva on 12/16/2016.
 */

public class AuthRepository implements AuthDataSource {

    // region FIELDS

    private static AuthRepository INSTANCE = null;

    private final AuthDataSource mAuthLocalDataSource;
    private final AuthDataSource mAuthRemoteDataSource;

    // endregion

    // region CONSTRUCTORS

    private AuthRepository(@NonNull AuthDataSource authLocalDataSource,
                           @NonNull AuthDataSource authRemoteDataSource) {

        mAuthLocalDataSource = authLocalDataSource;
        mAuthRemoteDataSource = authRemoteDataSource;
    }

    // endregion

    // region STATIC METHODS

    public static AuthRepository getInstance(AuthDataSource authLocalDataSource,
                                             AuthDataSource authRemoteDataSource) {

        if (INSTANCE == null) {
            INSTANCE = new AuthRepository(authLocalDataSource, authRemoteDataSource);
        }

        return INSTANCE;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void getAuthToken(final String name, final String email, final GetAuthTokenCallback callback) {

        mAuthLocalDataSource.getAuthToken(name, email, new GetAuthTokenCallback() {

            @Override
            public void onAuthTokenLoaded(String authToken) {

                callback.onAuthTokenLoaded(authToken);
            }

            @Override
            public void onFailed() {

                getAuthTokenFromRemoteDataSource(name, email, callback);
            }
        });
    }

    @Override
    public void saveAuthToken(String authToken) {

    }

    @Override
    public void deleteAuthToken() {

    }

    // endregion

    // region UTILITY METHODS

    private void getAuthTokenFromRemoteDataSource(final String name, final String email,
                                                  final GetAuthTokenCallback callback) {

        mAuthRemoteDataSource.getAuthToken(name, email, new GetAuthTokenCallback() {

            @Override
            public void onAuthTokenLoaded(String authToken) {

                refreshLocalDataSource(authToken);

                callback.onAuthTokenLoaded(authToken);
            }

            @Override
            public void onFailed() {

                callback.onFailed();
            }
        });
    }

    private void refreshLocalDataSource(String authToken) {

        mAuthLocalDataSource.deleteAuthToken();
        mAuthLocalDataSource.saveAuthToken(authToken);
    }

    // endregion
}
