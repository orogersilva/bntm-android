package com.orogersilva.bntm.net.services;

import com.orogersilva.bntm.sync.model.Transfer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by orogersilva on 12/19/2016.
 */

public interface TransferService {

    // region ENDPOINTS

    @FormUrlEncoded
    @POST("SendMoney")
    Call<Boolean> sendMoney(@Field("ClienteId") String id, @Field("token") String token,
                            @Field("valor") double valor);

    @GET("GetTransfers")
    Call<List<Transfer>> getTransfers(@Query("token") String authToken);

    // endregion
}
