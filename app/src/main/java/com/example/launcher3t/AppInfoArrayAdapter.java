package com.example.launcher3t;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppInfoArrayAdapter extends RecyclerView.Adapter<AppInfoArrayAdapter.MyViewHolder> {
    Context mContext;
    int mResource;
    public  ArrayList<AppInfo> mDataset;


    public AppInfoArrayAdapter(Context context, int resource, ArrayList<AppInfo> objects) {
        super();
        this.mContext=context;
        this.mResource=resource;
        mDataset=objects;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_app,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textViewAppName.setText(mDataset.get(position).appname);
        holder.imageView.setImageDrawable(mDataset.get(position).icon);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewAppName;
        TextView textViewPackageName;
        TextView textViewVersionName;
        TextView textViewDataDir;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewAppName= (TextView) itemView.findViewById(R.id.textViewAppName);
            imageView=(ImageView) itemView.findViewById(R.id.imageView);
        }

    }

};


