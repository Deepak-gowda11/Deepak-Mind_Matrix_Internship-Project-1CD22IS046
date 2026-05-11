package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.R;
import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database.Harvest;

import java.util.ArrayList;
import java.util.List;

public class HarvestAdapter extends RecyclerView.Adapter<HarvestAdapter.HarvestViewHolder> {

    private List<Harvest> harvests = new ArrayList<>();

    @NonNull
    @Override
    public HarvestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_harvest, parent, false);
        return new HarvestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HarvestViewHolder holder, int position) {
        Harvest current = harvests.get(position);
        holder.textViewDate.setText(current.getDate());
        holder.textViewQuantity.setText(current.getQuantity() + " Kg");
    }

    @Override
    public int getItemCount() {
        return harvests.size();
    }

    public void setHarvests(List<Harvest> harvests) {
        this.harvests = harvests;
        notifyDataSetChanged();
    }

    class HarvestViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewDate, textViewQuantity;

        public HarvestViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDateHarvest);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantityHarvest);
        }
    }
}
