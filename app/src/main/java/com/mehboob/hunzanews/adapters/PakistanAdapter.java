package com.mehboob.hunzanews.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mehboob.hunzanews.R;
import com.mehboob.hunzanews.models.allarticles.CategoryItem;
import com.mehboob.hunzanews.ui.ArticleDetailActivity;

import java.util.List;

public class PakistanAdapter extends RecyclerView.Adapter<PakistanAdapter.CategoryHolder> {
    private List<CategoryItem> newsList;
    private Context context;
    private PakistanAdapter.OnItemClickListener onItemClickListener;
    int code;
    // Constructor and other necessary methods...




    public PakistanAdapter(List<CategoryItem> newsList, Context context,int code) {
        this.newsList = newsList;
        this.context = context;
        this.code=code;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        CategoryItem categoryItem = newsList.get(position);

        // Set data to views
        holder.txtPostTitle.setText(categoryItem.getPostTitle());
        holder.txtPostType.setText(categoryItem.getPostType());


        holder.txtTime.setText(categoryItem.getPostDate());

        try {
            Glide.with(context)
                    .load(categoryItem.getThumbnailUrl())
                    .into(holder.imgNews)
            ;
        } catch (Exception e) {
            holder.itemView.setOnClickListener(view -> {

                Intent i = new Intent(context, ArticleDetailActivity.class);
                Gson gson = new Gson();
                String jsonObj = gson.toJson(categoryItem);

                if (jsonObj != null) {
                    i.putExtra("obj", jsonObj);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                } else {
                    // Handle the case where jsonObj is null, perhaps log an error or show a message
                    Log.e("TAG", "jsonObj is null");
                    // You might want to show a Toast or log the error
                    Toast.makeText(context, "Error: Could not retrieve article details", Toast.LENGTH_SHORT).show();
                }


            });
            // Add more as needed...
        }

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position,categoryItem);
            }
        });


    }


    public interface OnItemClickListener {
        void onItemClick(int position,CategoryItem categoryItem);
    }

    public void setOnItemClickListener(PakistanAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        if (code==0)   // means from popular
            return Math.min(newsList.size(), 1);
        else
            return newsList != null ? newsList.size() : 0;
    }
    public void setNewsList(List<CategoryItem> newsList) {

        int startPosition = newsList.size();
        this.newsList.addAll(newsList);
        notifyItemRangeInserted(startPosition, newsList.size());


        notifyDataSetChanged();
    }

    public List<CategoryItem> getNewsList() {
        return newsList;
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{


        private ImageView imgNews;
        private TextView txtPostTitle;
        private TextView txtPostType;
        private TextView txtTime;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);


            imgNews=itemView.findViewById(R.id.imgCategoryNews);
            txtPostTitle=itemView.findViewById(R.id.txtTitle);
            txtPostType=itemView.findViewById(R.id.txtPostType);
            txtTime=itemView.findViewById(R.id.txtPostTime);
        }
    }
}
