package com.example.mddeloarhossain.bloodbank;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MD. DELOAR HOSSAIN on 30-Apr-19.
 */

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.MyViewHolder> {

    ArrayList<DonorR> list;
    public AdapterRecycler(ArrayList<DonorR> list){
        this.list=list;
    }

    @NonNull
    @Override
    public AdapterRecycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycler.MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.city.setText(list.get(position).getCity());
        holder.bloodgroup.setText(list.get(position).getBloodgroup());
        //holder.image.setImage(getApplicationContext(),list.getImage());
        //Picasso.with(ctx).load(image).into(image);
        //holder.image.setImageResource(list.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, city, bloodgroup;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.donorListNameItemTextViewId);
            city = itemView.findViewById(R.id.donorListCityItemTextViewId);
            bloodgroup = itemView.findViewById(R.id.donorListBloodItemTextViewId);
            image = itemView.findViewById(R.id.imageViewId);
        }
    }
}
