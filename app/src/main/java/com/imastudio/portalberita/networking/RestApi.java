package com.imastudio.portalberita.networking;



import com.imastudio.portalberita.model.ResponseBerita;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {

    @GET("tampil_berita.php")
    Call<ResponseBerita> getdataBerita();
}
