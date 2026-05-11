package com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.R;
import com.example.androidappdevelopmentusinggenai_madhu_margaagriculture.database.Inspection;

import java.util.ArrayList;
import java.util.List;

public class InspectionAdapter extends RecyclerView.Adapter<InspectionAdapter.InspectionViewHolder> {

    private List<Inspection> inspections = new ArrayList<>();

    @NonNull
    @Override
    public InspectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inspection, parent, false);
        return new InspectionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InspectionViewHolder holder, int position) {
        Inspection current = inspections.get(position);
        holder.textViewDate.setText("Date: " + current.getDate());
        holder.textViewDetails.setText(String.format("Queen: %s | Honey: %s | Activity: %s | Pests: %s",
                current.isQueenSeen() ? "Yes" : "No",
                current.getHoneyFlow(),
                current.getActivityLevel(),
                current.isPestDetected() ? "Yes" : "No"));
        
        if (current.getAlert() != null && !current.getAlert().isEmpty()) {
            holder.textViewAlert.setVisibility(View.VISIBLE);
            holder.textViewAlert.setText(current.getAlert());
            
            // Dynamic color based on severity
            if (current.getAlert().contains("CRITICAL") || current.getAlert().contains("🚨")) {
                holder.textViewAlert.setTextColor(Color.parseColor("#B00020")); // Deep Red
            } else if (current.getAlert().contains("WARNING") || current.getAlert().contains("⚠️")) {
                holder.textViewAlert.setTextColor(Color.parseColor("#E65100")); // Deep Orange
            } else if (current.getAlert().contains("ACTION") || current.getAlert().contains("✨")) {
                holder.textViewAlert.setTextColor(Color.parseColor("#1B5E20")); // Deep Green
            } else {
                holder.textViewAlert.setTextColor(Color.BLACK);
            }
        } else {
            holder.textViewAlert.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return inspections.size();
    }

    public void setInspections(List<Inspection> inspections) {
        this.inspections = inspections;
        notifyDataSetChanged();
    }

    class InspectionViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewDate, textViewDetails, textViewAlert;

        public InspectionViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDateLog);
            textViewDetails = itemView.findViewById(R.id.textViewDetailsLog);
            textViewAlert = itemView.findViewById(R.id.textViewAlertLog);
        }
    }
}
