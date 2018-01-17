package com.example.dell.android_advance_ls3;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 16-Jan-18.
 */

public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.ViewHolder> {

    private List<Bitmap> mBitmapList = new ArrayList<>();
    private Context mConText;

    public RecylerAdapter(Context mConText, List<Bitmap> mBitmapList) {
        this.mConText = mConText;
        this.mBitmapList = mBitmapList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater    = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_item_gallery,viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return mBitmapList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageGallery;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageGallery = itemView.findViewById(R.id.image_gallery);
        }
        public void setData(int position){
            mImageGallery.setImageBitmap(mBitmapList.get(position));
        }
    }
}

