package com.orogersilva.bntm.net;

import com.orogersilva.bntm.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by orogersilva on 12/16/2016.
 */

public class RestClient {

    // region FIELDS

    private static final String BASE_API_URL = BuildConfig.NEON_API_URL;

    private static Retrofit sRetrofit;

    // endregion

    // region STATIC METHODS

    public static <T> T getService(Class<T> serviceClass) {

        sRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return sRetrofit.create(serviceClass);
    }

    // endregion
}
