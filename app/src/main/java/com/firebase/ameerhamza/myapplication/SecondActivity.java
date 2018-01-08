package com.firebase.ameerhamza.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ameerhamza.myapplication.interfaces.DonorInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StringBuilder token = new StringBuilder("Bearer ") ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        token.append(intent.getStringExtra("token"));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DonorInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DonorInterface api = retrofit.create(DonorInterface.class);
        Call<List<DonorList>> call = api.getDonors(token.toString());
        call.enqueue(new Callback<List<DonorList>>() {
            @Override
            public void onResponse(Call<List<DonorList>> call, Response<List<DonorList>> response) {
                List<DonorList> lists = response.body();
                RecyclerView.Adapter adapter = new DonorAdapter(lists, SecondActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<DonorList>> call, Throwable t) {

            }
        });

    }
}
