package com.mehboob.hunzanews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mehboob.hunzanews.R;
import com.mehboob.hunzanews.models.allarticles.CategoryItem;

import java.util.List;

public class GbAdapter extends RecyclerView.Adapter<GbAdapter.CategoryHolder> {
    private List<CategoryItem> newsList;
    private Context context;
    private GbAdapter.OnItemClickListener onItemClickListener;
    int code;
    // Constructor and other necessary methods...

    public interface OnItemClickListener {
        void onItemClick(int position,CategoryItem newsItem);
    }

    public void setOnItemClickListener(GbAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    public GbAdapter(List<CategoryItem> newsList, Context context,int code) {
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

            // Add more as needed...
        }

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position,categoryItem);
            }
        });

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
