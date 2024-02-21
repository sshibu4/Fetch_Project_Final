package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ItemRepository itemRepository;
    private TextView textViewData; // TextView to display the data on screen
    private RecyclerView recyclerView; // RecyclerView to display the items

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewData = findViewById(R.id.textViewData); // Initialize the TextView
        recyclerView = findViewById(R.id.recyclerView); // Initialize the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set LayoutManager

        itemRepository = new ItemRepository();
        fetchData();
    }

    private void fetchData() {
        itemRepository.getItems(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Item> items = response.body();

                    // Filter and sort
                    List<Item> filteredSortedItems = filterAndSortItems(items);

                    // UI update on main thread
                    runOnUiThread(() -> {
                        // Directly call the setup method with the filtered and sorted list
                        setupRecyclerView(filteredSortedItems);
                    });
                } else {
                    Log.e("MainActivity", "Response not successful: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e("MainActivity", "Network call failed", t);
            }
        });
    }

    private List<Item> filterAndSortItems(List<Item> items) {
        // Filter out items with null or blank names
        List<Item> filteredItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getName() != null && !item.getName().trim().isEmpty()) {
                filteredItems.add(item);
            }
        }

        // Sort the filtered items first by "listId" and then by "name"
        Collections.sort(filteredItems, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                int listIdComparison = Integer.compare(o1.getListId(), o2.getListId());
                if (listIdComparison != 0) {
                    return listIdComparison;
                }

                //parsing
                Integer name1 = extractNumber(o1.getName());
                Integer name2 = extractNumber(o2.getName());
                return name1.compareTo(name2);
            }
        });

        return filteredItems;
    }

    private Integer extractNumber(String name) {
        try {
            return Integer.parseInt(name.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE; // Fallback for sorting
        }
    }

    private void setupRecyclerView(List<Item> items) {
        ItemAdapter adapter = new ItemAdapter(items);
        recyclerView.setAdapter(adapter);
    }
}

