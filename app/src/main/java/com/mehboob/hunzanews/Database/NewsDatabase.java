package com.mehboob.hunzanews.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mehboob.hunzanews.dao.NewsItemDao;
import com.mehboob.hunzanews.models.allarticles.CommentConverter;
import com.mehboob.hunzanews.models.allarticles.NewsItem;

@Database(entities = {NewsItem.class}, version = 1, exportSchema = false)
@TypeConverters({CommentConverter.class})
public abstract class NewsDatabase extends RoomDatabase {


    private static final String DATABASE_NAME="NewsDatabase";


    public abstract NewsItemDao newsItemDao();


    private static volatile NewsDatabase INSTANCE;


    public static NewsDatabase getInstance(Context context){
        if (INSTANCE==null){

            synchronized (NewsDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context,NewsDatabase.class,DATABASE_NAME)
                            .addCallback(callback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }


  static   Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(INSTANCE);
        }
    };



    static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{
private NewsItemDao newsItemDao;

        public PopulateAsyncTask(NewsDatabase newsDatabase) {
       newsItemDao= newsDatabase.newsItemDao();;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            newsItemDao.deleteAll();
            return null;
        }
    }
}
