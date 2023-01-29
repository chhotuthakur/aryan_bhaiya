package com.video.buzzy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.video.buzzy.R;
import com.video.buzzy.databinding.ItemLocationBinding;
import com.video.buzzy.modelRetrofit.LocationRoot;

import java.util.ArrayList;
import java.util.List;


public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
    OnLocationClickLisnter onLocationClickLisnter;
    private Context context;
    private List<LocationRoot.ResponseItem> locations = new ArrayList<>();

    public OnLocationClickLisnter getOnLocationClickLisnter() {
        return onLocationClickLisnter;
    }

    public void setOnLocationClickLisnter(OnLocationClickLisnter onLocationClickLisnter) {
        this.onLocationClickLisnter = onLocationClickLisnter;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new LocationViewHolder(LayoutInflater.from(context).inflate(R.layout.item_location, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public void addData(List<LocationRoot.ResponseItem> locations) {

        this.locations.addAll(locations);
        notifyItemRangeInserted(this.locations.size(), locations.size());
    }

    public void clear() {
        this.locations.clear();
        notifyDataSetChanged();
    }

    public interface OnLocationClickLisnter {
        void onLocationclick(LocationRoot.ResponseItem hashtag);
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        ItemLocationBinding binding;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemLocationBinding.bind(itemView);
        }

        public void setData(int position) {
            LocationRoot.ResponseItem location = locations.get(position);

            binding.tvLocation.setText(location.getCity() + ", " + location.getState() + "," + location.getCountryName());

            binding.getRoot().setOnClickListener(v -> onLocationClickLisnter.onLocationclick(locations.get(position)));
        }
    }
}
