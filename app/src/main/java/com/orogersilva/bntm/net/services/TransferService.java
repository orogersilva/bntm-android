package com.orogersilva.bntm.net.services;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by orogersilva on 12/19/2016.
 */

public interface TransferService {

    // region ENDPOINTS

    @FormUrlEncoded
    @POST("SendMoney")
    Call<Boolean> sendMoney(@Field("ClienteId") String id, @Field("token") String token,
                            @Field("valor") double valor);

    // endregion
}
