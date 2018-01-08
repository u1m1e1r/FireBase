package com.firebase.ameerhamza.myapplication.interfaces;

import com.firebase.ameerhamza.myapplication.Donor;
import com.firebase.ameerhamza.myapplication.DonorList;
import com.firebase.ameerhamza.myapplication.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Ameer Hamza on 11/21/2017.
 */

public interface DonorInterface {

    public static final String BASE_URL = "http://10.0.2.2/blood_bank/public/api/";

    @POST("login")
    Call<Donor> login(@Body Login login);

    @POST("register")
    Call<Donor> register(@Body Donor donor);

    @GET("donor")
    Call<List<DonorList>> getDonors(@Header("Authorization") String authToken);
}
