package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// Adapter for RecyclerView that binds Item data to views that are displayed within a RecyclerView
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    // List to hold Item objects, which is the data source for the adapter.
    private List<Item> itemList; // The data source of the adapter

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    // Method to create new ViewHolder objects (invoked by the layout manager).
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(itemView);
    }

    // Method to bind data to the ViewHolder (invoked by the layout manager).
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.textViewId.setText(String.format("ID: %d", item.getId()));
        holder.textViewListId.setText(String.format("ListID: %d", item.getListId()));
        holder.textViewName.setText(String.format("Name: %s", item.getName()));
    }

    // Method to get the size of the data source (invoked by the layout manager).
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // ViewHolder class that holds references to all views within each item row.
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewListId, textViewName;

        // Constructor for the ViewHolder, with itemView as the inflated layout.
        public ItemViewHolder(View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewListId = itemView.findViewById(R.id.textViewListId);
            textViewName = itemView.findViewById(R.id.textViewName);
        }
    }
}
