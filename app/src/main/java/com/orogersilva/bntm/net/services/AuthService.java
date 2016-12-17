package com.orogersilva.bntm.net.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by orogersilva on 12/16/2016.
 */

public interface AuthService {

    // region ENDPOINTS

    @GET("GenerateToken")
    Call<String> getToken(@Query("nome") String name, @Query("email") String email);

    // endregion
}
