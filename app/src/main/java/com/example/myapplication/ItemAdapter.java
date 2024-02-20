package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> itemList; // The data source of the adapter

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.textViewId.setText(String.format("ID: %d", item.getId()));
        holder.textViewListId.setText(String.format("ListID: %d", item.getListId()));
        holder.textViewName.setText(String.format("Name: %s", item.getName()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewListId, textViewName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewListId = itemView.findViewById(R.id.textViewListId);
            textViewName = itemView.findViewById(R.id.textViewName);
        }
    }
}
