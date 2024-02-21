package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemRepository {

    private ApiService apiService;

    // The ItemRepository class is responsible for setting up the Retrofit instance
    // and providing a method to fetch items from the remote data source.
    public ItemRepository(){
        // Building the Retrofit instance using its Builder class
        // This instance will be used to send out network requests to the specified BASE URL
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    // Public method to initiate the fetching of items.
    // The method takes a Callback<List<Item>> to handle the results asynchronously.
    public void getItems(Callback<List<Item>> callback) {
        Call<List<Item>> call = apiService.getItems();
        call.enqueue(callback);
    }
}
