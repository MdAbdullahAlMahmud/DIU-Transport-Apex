package com.socalledengineers.diutransportapex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.button.MaterialButton;
import com.socalledengineers.diutransportapex.model.Bus;

import java.util.ArrayList;

public class RoutesAdapter extends RecyclerView.Adapter<RoutesAdapter.RoutesViewHolder>{

    private Context context;
    private ArrayList<Bus> busList;

    public RoutesAdapter(Context context, ArrayList<Bus> busList) {
        this.context = context;
        this.busList = busList;
    }

    @NonNull
    @Override
    public RoutesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.routes_item,parent,false);
        return new RoutesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutesViewHolder holder, int position) {
        Bus bus = busList.get(position);
        holder.busNo.setText("Bus No. "+bus.getId());
        holder.busNo.setText("Departure "+bus.getStarting_time());
        holder.minAwayButton.setText("5 min away");
        holder.minAwayButton.setText("Live Location");
    }

    @Override
    public int getItemCount() {
        return busList.size();
    }

    class  RoutesViewHolder extends RecyclerView.ViewHolder {
    public TextView busNo,depuratureTV;
    public MaterialButton minAwayButton,liveLocationButton;

        public RoutesViewHolder(@NonNull View itemView) {
            super(itemView);
            busNo = itemView.findViewById(R.id.routesItemBusNo);
            depuratureTV = itemView.findViewById(R.id.routesItemDeouruteTimeTV);
            minAwayButton = itemView.findViewById(R.id.routesItemMinAwayButton);
            liveLocationButton = itemView.findViewById(R.id.routesItemLiveLocationButton);
        }
    }
}
