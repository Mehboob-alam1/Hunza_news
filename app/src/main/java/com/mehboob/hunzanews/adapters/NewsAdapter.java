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

import com.mehboob.hunzanews.models.allarticles.NewsItem;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsItem> newsList;
    private Context context;

    public NewsAdapter(Context context, List<NewsItem> newsList) {
        this.context = context;
        this.newsList = newsList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsItem newsPost = newsList.get(position);

        // Set data to views
        holder.titleTextView.setText(newsPost.getPostTitle());
        holder.authorTextView.setText(newsPost.getPostAuthor());
        holder.txtContent.setText(newsPost.getPostContent());
        holder.txtTime.setText(newsPost.getPostDate());
        try {
            Glide.with(context)
                    .load(newsPost.getThumbnailUrl())
                    .into(holder.imgNews)
            ;
        } catch (Exception e) {

            // Add more as needed...
        }





    }
    @Override
    public int getItemCount () {
        return newsList != null ? newsList.size() : 0;
    }
    public void setNewsList(List<NewsItem> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView authorTextView;
        private final TextView txtContent;
        private final TextView txtTime;
        private ImageView imgNews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.txtMainHeading);
            authorTextView = itemView.findViewById(R.id.txtAuthor);
            txtContent = itemView.findViewById(R.id.txtContent);
            txtTime = itemView.findViewById(R.id.txtTime);
            imgNews = itemView.findViewById(R.id.imgNews);
            // Add more views as needed...
        }
    }
}
