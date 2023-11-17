package com.mehboob.hunzanews.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mehboob.hunzanews.R;

import com.mehboob.hunzanews.models.allarticles.NewsItem;
import com.mehboob.hunzanews.ui.ArticleDetailActivity;
import com.mehboob.hunzanews.utils.HtmlParser;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsItem> newsList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    // Constructor and other necessary methods...

    public interface OnItemClickListener {
        void onItemClick(int position,NewsItem newsItem);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
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


        holder.txtContent.setText( HtmlParser.parseHtml(newsPost.getPostContent()));
        holder.txtTime.setText(newsPost.getPostDate());
        try {
            Glide.with(context)
                    .load(newsPost.getThumbnailUrl())
                    .into(holder.imgNews)
            ;
        } catch (Exception e) {

            // Add more as needed...
        }

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position,newsPost);
            }
        });

//        holder.itemView.setOnClickListener(view -> {
//
//            Intent i = new Intent(context, ArticleDetailActivity.class);
//            Gson gson= new Gson();
//           String jsonObj= gson.toJson(newsPost);
//           i.putExtra("obj",jsonObj);
//           context.startActivity(i);
//        });



    }

    public void addItems(List<NewsItem> newItems) {
        int startPosition = newsList.size();
        newsList.addAll(newItems);
        notifyItemRangeInserted(startPosition, newItems.size());
    }
    @Override
    public int getItemCount () {
        return newsList != null ? newsList.size() : 0;
    }
    public void setNewsList(List<NewsItem> newsList) {

        int startPosition = newsList.size();
        this.newsList.addAll(newsList);
        notifyItemRangeInserted(startPosition, newsList.size());


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
