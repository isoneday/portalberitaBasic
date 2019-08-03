package com.imastudio.portalberita;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.imastudio.portalberita.adapter.PortalBeritaAdapter;
import com.imastudio.portalberita.model.BeritaItem;
import com.imastudio.portalberita.model.ResponseBerita;
import com.imastudio.portalberita.networking.MyRetrofitClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listBerita)
    RecyclerView listBerita;
    private boolean status;
    private List<BeritaItem> dataBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //get data from db using retrofit
        getDataBerita();


    }

    private void getDataBerita() {
        //tampilkan loading
        final ProgressDialog loading = ProgressDialog.show(this, "Proses get data",
                "loading...");

        MyRetrofitClient.getInstaceRetrofit().getdataBerita().enqueue(new Callback<ResponseBerita>() {
            //ketika berhasil menampilkan data json
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                if (response.isSuccessful()) {
                    //menghilangkan loading
                    loading.dismiss();
                    status = response.body().isStatus();
                    if (status==true){
                        dataBerita = response.body().getBerita();

                        //set adapter
                        PortalBeritaAdapter adapter = new PortalBeritaAdapter(MainActivity.this,dataBerita);
                        listBerita.setAdapter(adapter);
                        listBerita.setLayoutManager(new
                                LinearLayoutManager(MainActivity.this));
                    }else{
                        Toast.makeText(MainActivity.this, "gagal menampilkan data", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "masalah format JSON", Toast.LENGTH_SHORT).show();
                }
            }

            //ketika gagal menampilkan data json
            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                Toast.makeText(MainActivity.this, "masalah koneksi:"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
