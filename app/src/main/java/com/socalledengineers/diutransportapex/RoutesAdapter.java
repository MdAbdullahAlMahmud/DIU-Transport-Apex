package com.socalledengineers.diutransportapex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.socalledengineers.diutransportapex.model.Bus;
import com.socalledengineers.diutransportapex.utils.Display;

import java.util.ArrayList;

public class RoutesAdapter extends RecyclerView.Adapter<RoutesAdapter.RoutesViewHolder>{

    private Context context;
    private ArrayList<Bus> busList;

    private  OnStartTripClickListener onStartTripClickListener;
    public RoutesAdapter(Context context, ArrayList<Bus> busList) {
        this.context = context;
        this.busList = busList;
    }

    @NonNull
    @Override
    public RoutesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.driver_trip_item,parent,false);
        return new RoutesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutesViewHolder holder, int position) {
        Bus bus = busList.get(position);
        holder.tripTimeTV.setText(bus.getStarting_time());
        holder.tripFromTV.setText(bus.getFrom());
        holder.tripToTV.setText(bus.getTo());
        holder.tripStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartTripClickListener.onTripClick(bus);
            }
        });


    }

    public void  setOnStartTripClickListener(OnStartTripClickListener onStartTripClickListener){
        this.onStartTripClickListener = onStartTripClickListener;
    }
    @Override
    public int getItemCount() {
        return busList.size();
    }

    class  RoutesViewHolder extends RecyclerView.ViewHolder {

    public TextView tripTimeTV,tripFromTV,tripToTV;
    public MaterialButton tripStartButton;

        public RoutesViewHolder(@NonNull View itemView) {
            super(itemView);
            tripTimeTV = itemView.findViewById(R.id.tripTimeTV);
            tripFromTV = itemView.findViewById(R.id.tripFromTV);
            tripToTV = itemView.findViewById(R.id.tripToTV);
            tripStartButton = itemView.findViewById(R.id.tripStartButton);
        }
    }
    interface OnStartTripClickListener{
        void onTripClick(Bus bus);
    }
}
