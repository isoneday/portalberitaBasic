package com.imastudio.portalberita.networking;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.imastudio.portalberita.helper.MyConstants.BASEURL;

/**
 * Created by Blackswan on 9/12/2017.
 */

public class MyRetrofitClient {
    private static Retrofit getRetrofit(){
        //insialisasi retrofit 2
        Retrofit r = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return r;
    }
    public static RestApi getInstaceRetrofit(){
        return getRetrofit().create(RestApi.class);
    }

}
