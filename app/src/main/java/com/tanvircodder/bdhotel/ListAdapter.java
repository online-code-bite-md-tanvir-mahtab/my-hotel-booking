package com.tanvircodder.bdhotel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tanvircodder.bdhotel.util.Utility;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{
    private List<Utility> mData;
    private Context mContext;
    public ListAdapter(Context context){
        mContext = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Utility utility = mData.get(position);
        String name = utility.getLocation();
        String location = utility.getHotelName();
        String star = utility.getHotelStarCount();
        holder.mHotel_name.setText(name);
        holder.mHotel_location.setText(location);
        holder.mHotel_star.setText(star);
    }

    @Override
    public int getItemCount() {
        if (mData == null)return 0;
        return mData.size();
    }

    public void swapData(List data) {
        mData = data;
        if (data != null){
            notifyDataSetChanged();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mHotel_name;
        TextView mHotel_location;
        TextView mHotel_star;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mHotel_name = (TextView)itemView.findViewById(R.id.hotel_name);
            mHotel_location = (TextView)itemView.findViewById(R.id.hotel_location);
            mHotel_star = (TextView) itemView.findViewById(R.id.hotel_star);
        }
    }
}
