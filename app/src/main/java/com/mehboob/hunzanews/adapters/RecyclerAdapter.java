package com.mehboob.hunzanews.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mehboob.hunzanews.R;
import com.mehboob.hunzanews.models.ModelClass;
import com.mehboob.hunzanews.ui.WebActivity;

import java.util.ArrayList;

import kotlin.jvm.internal.Lambda;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelClass> list;

    public RecyclerAdapter(Context context, ArrayList<ModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_news,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {

        holder.cardView.setOnClickListener(v -> {

            Intent i = new Intent(context, WebActivity.class);
            i.putExtra("url",list.get(position).getUrl());
            context.startActivity(i);
        });

        holder.mTime.setText("Published at :-" + list.get(position).getPublishedAt());
        holder.mAuthor.setText(list.get(position).getAuthor());
        holder.mHeading.setText(list.get(position).getTitle());
        holder.mContent.setText(list.get(position).getDescription());

        Glide.with(context).load(list.get(position).getUrlToImage()).into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mHeading, mContent,mTime,mAuthor;
        private CardView cardView;
        private ImageView newsImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mHeading=itemView.findViewById(R.id.txtMainHeading);
            mContent=itemView.findViewById(R.id.txtContent);
            mAuthor=itemView.findViewById(R.id.txtAuthor);
            mTime=itemView.findViewById(R.id.txtTime);
            cardView=itemView.findViewById(R.id.cardView);
            newsImage=itemView.findViewById(R.id.imgNews);
        }
    }
}
