package com.example.android.lagosjavadevs.Network;

import com.example.android.lagosjavadevs.dataclasses.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by HARNY on 8/30/2017.
 */

public interface EndpointInterface {
    String API_KEY = "d00d29f855aaca5353971436ba353ef7a6ea8b40";
    @GET("users?q=+location:lagos+language:java?access_token="+API_KEY)
    Call<List<Item>> getJavaUsers();
}

