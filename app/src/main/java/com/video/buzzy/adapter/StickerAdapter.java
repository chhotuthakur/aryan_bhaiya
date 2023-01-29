package com.video.buzzy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.video.buzzy.BuildConfig;
import com.video.buzzy.R;
import com.video.buzzy.databinding.ItemStickerGridBinding;
import com.video.buzzy.modelRetrofit.StickerRoot;

import java.util.ArrayList;
import java.util.List;

public class StickerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW1 = 1;
    private static final int VIEW2 = 2;
    OnStickerClickListner onSongClickListner;
    Context context;
    private List<StickerRoot.StickerItem> stickerDummies = new ArrayList<>();

    public OnStickerClickListner getOnSongClickListner() {
        return onSongClickListner;
    }

    public void setOnSongClickListner(OnStickerClickListner onSongClickListner) {
        this.onSongClickListner = onSongClickListner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new StickerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sticker_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((StickerViewHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return stickerDummies.size();
    }

    public void addData(List<StickerRoot.StickerItem> stickerItems) {
        this.stickerDummies.addAll(stickerItems);
        notifyItemRangeInserted(this.stickerDummies.size(), stickerItems.size());
    }

    public interface OnStickerClickListner {
        void onStickerClick(StickerRoot.StickerItem song);
    }

    public class StickerViewHolder extends RecyclerView.ViewHolder {
        ItemStickerGridBinding binding;

        public StickerViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemStickerGridBinding.bind(itemView);

        }

        public void setData(int position) {
            StickerRoot.StickerItem sticker = stickerDummies.get(position);
            Glide.with(context).load(BuildConfig.BASE_URL + sticker.getSticker()).into(binding.image);
            binding.getRoot().setOnClickListener(v -> onSongClickListner.onStickerClick(sticker));
        }
    }
}

