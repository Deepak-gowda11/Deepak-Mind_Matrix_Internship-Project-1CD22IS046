package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.R;
import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database.Hive;

import java.util.ArrayList;
import java.util.List;

public class HiveAdapter extends RecyclerView.Adapter<HiveAdapter.HiveViewHolder> {

    private List<Hive> hives = new ArrayList<>();
    private OnHiveClickListener listener;

    public interface OnHiveClickListener {
        void onInspectClick(Hive hive);
        void onHarvestClick(Hive hive);
    }

    public void setOnHiveClickListener(OnHiveClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public HiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hive, parent, false);
        return new HiveViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HiveViewHolder holder, int position) {
        Hive currentHive = hives.get(position);
        holder.textViewHiveId.setText(currentHive.getHiveId());
        holder.textViewLocation.setText(currentHive.getLocation());
        holder.textViewDate.setText(currentHive.getDateRegistered());

        holder.buttonInspect.setOnClickListener(v -> {
            if (listener != null) listener.onInspectClick(currentHive);
        });

        holder.buttonHarvest.setOnClickListener(v -> {
            if (listener != null) listener.onHarvestClick(currentHive);
        });
    }

    @Override
    public int getItemCount() {
        return hives.size();
    }

    public void setHives(List<Hive> hives) {
        this.hives = hives;
        notifyDataSetChanged();
    }

    class HiveViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewHiveId;
        private TextView textViewLocation;
        private TextView textViewDate;
        private View buttonInspect;
        private View buttonHarvest;

        public HiveViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHiveId = itemView.findViewById(R.id.textViewHiveId);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            buttonInspect = itemView.findViewById(R.id.buttonInspect);
            buttonHarvest = itemView.findViewById(R.id.buttonHarvest);
        }
    }
}
