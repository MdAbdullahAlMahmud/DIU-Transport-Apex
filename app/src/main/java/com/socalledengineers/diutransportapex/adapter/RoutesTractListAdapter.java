package com.socalledengineers.diutransportapex.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.socalledengineers.diutransportapex.R;
import com.socalledengineers.diutransportapex.model.BusItem;
import com.socalledengineers.diutransportapex.ui.routes.BusRoutesWebView;
import com.socalledengineers.diutransportapex.utils.NodeName;

import java.util.ArrayList;

public class RoutesTractListAdapter extends RecyclerView.Adapter<RoutesTractListAdapter.RoutesTrackListViewHolder>{
    private Context context;
    private ArrayList<BusItem> busList;

    public RoutesTractListAdapter(Context context, ArrayList<BusItem> busList) {
        this.context = context;
        this.busList = busList;
    }
    @NonNull
    @Override
    public RoutesTrackListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.routes_item,parent,false);
        return  new RoutesTrackListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutesTrackListViewHolder holder, int position) {
        BusItem bus = busList.get(position);
        holder.deparutureTV.setText(bus.getStarting_time());
        holder.nameTV.setText(bus.getName());
        holder.routes_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BusRoutesWebView.class);
                intent.putExtra(NodeName.INTENT_BUS_ROUTE_URL,bus.getRoutes_url());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return busList.size();
    }

    public  class RoutesTrackListViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTV,deparutureTV;
        public ConstraintLayout routes_Layout;
        public RoutesTrackListViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.routesBusName);
            deparutureTV = itemView.findViewById(R.id.routesBusDeparuture);
            routes_Layout = itemView.findViewById(R.id.routes_Layout);



        }
    }
}
