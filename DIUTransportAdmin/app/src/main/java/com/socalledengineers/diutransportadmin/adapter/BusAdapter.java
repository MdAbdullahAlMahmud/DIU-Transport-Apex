package com.socalledengineers.diutransportadmin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.socalledengineers.diutransportadmin.R;
import com.socalledengineers.diutransportadmin.model.BusItem;

import java.util.ArrayList;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusViewHolder>{
    private Context context;
    private ArrayList<BusItem> busList;

    public BusAdapter(Context context, ArrayList<BusItem> busList) {
        this.context = context;
        this.busList = busList;
    }
    @NonNull
    @Override
    public BusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bus_item,parent,false);
        return  new BusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusViewHolder holder, int position) {
        BusItem bus = busList.get(position);
        holder.deparutureTV.setText(bus.getStarting_time());
        holder.nameTV.setText(bus.getName());


    }

    @Override
    public int getItemCount() {
        return busList.size();
    }

    public  class BusViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTV,deparutureTV;
        public ConstraintLayout routes_Layout;
        public BusViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.routesBusName);
            deparutureTV = itemView.findViewById(R.id.routesBusDeparuture);
            routes_Layout = itemView.findViewById(R.id.routes_Layout);



        }
    }
}
