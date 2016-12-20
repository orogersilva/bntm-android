package com.orogersilva.bntm.data;

/**
 * Created by orogersilva on 12/16/2016.
 */

public interface AuthDataSource {

    // region INTERFACES

    interface GetAuthTokenCallback {

        void onAuthTokenLoaded(String authToken);
        void onFailed();
    }

    // endregion

    // region METHODS

    void getAuthToken(String name, String email, GetAuthTokenCallback callback);

    void saveAuthToken(String authToken);

    void deleteAuthToken();

    // endregion
}
