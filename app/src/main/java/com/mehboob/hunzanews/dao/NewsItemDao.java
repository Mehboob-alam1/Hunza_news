package com.mehboob.hunzanews.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mehboob.hunzanews.models.allarticles.NewsItem;

import java.util.List;
@Dao

public interface NewsItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewsItems(List<NewsItem> newsItems);
    @Query("SELECT * FROM allarticle")
    LiveData<List<NewsItem>> getAllNewsItems();

    @Query("Delete from allarticle")
    void deleteAll();
}
