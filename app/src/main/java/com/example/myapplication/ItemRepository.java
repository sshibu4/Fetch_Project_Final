package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemRepository {

    private ApiService apiService;

    public ItemRepository(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public void getItems(Callback<List<Item>> callback) {
        Call<List<Item>> call = apiService.getItems();
        call.enqueue(callback);
    }
}
