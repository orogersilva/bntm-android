package com.orogersilva.bntm.sync;

import com.orogersilva.bntm.AuthDataSource;
import com.orogersilva.bntm.net.RestClient;
import com.orogersilva.bntm.net.services.AuthService;

import java.io.IOException;

import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by orogersilva on 12/16/2016.
 */

public class AuthRemoteDataSource implements AuthDataSource {

    // region FIELDS

    private static AuthRemoteDataSource INSTANCE;

    // endregion

    // region CONSTRUCTORS

    private AuthRemoteDataSource() {}

    // endregion

    // region STATIC METHODS

    public static AuthRemoteDataSource getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new AuthRemoteDataSource();
        }

        return INSTANCE;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void getAuthToken(String name, String email, GetAuthTokenCallback callback) {

        AuthService authService = RestClient.getService(AuthService.class);

        Response<String> response = null;

        try {

            response = authService.getToken(name, email).execute();

        } catch (IOException e) {

            Timber.e(e);
        }

        if (response != null && response.isSuccessful()) {
            callback.onAuthTokenLoaded(response.body().toString());
        } else {
            callback.onFailed();
        }
    }

    @Override
    public void saveAuthToken(String authToken) {

    }

    @Override
    public void deleteAuthToken() {

    }

    // endregion
}
