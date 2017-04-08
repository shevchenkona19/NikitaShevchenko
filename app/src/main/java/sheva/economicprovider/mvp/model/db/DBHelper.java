package sheva.economicprovider.mvp.model.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sheva.economicprovider.App;
import sheva.economicprovider.IConstants;
import sheva.economicprovider.mvp.model.db.mappers.ArticleToContentValueMapper;
import sheva.economicprovider.mvp.model.entities.Article;

/**
 * Created by shevc on 06.04.2017.
 * s
 */

public class DBHelper {
    private static String TABLE_NAME = "News_table";
    @Inject
    DB db1;
    @Inject
    ArticleToContentValueMapper mapper;

    public DBHelper() {
        App.get().plusDBComponent().inject(this);
    }

    public void dropTable() {
        SQLiteDatabase db = db1.getWritableDatabase();
        String QUERY = "DROP TABLE " + TABLE_NAME + ";";
        db.execSQL(QUERY);
    }

    public long insert(Article article) {
        article.setTime(Integer.valueOf(new SimpleDateFormat("HHmm").format(new Date())));
        article.setDate(new SimpleDateFormat("dMY").format(new Date()));
        SQLiteDatabase db = db1.getWritableDatabase();
        long id = db.insert(TABLE_NAME, null, mapper.transform(article));
        Log.e("MY", "id in helper: " + id);
        return id;
    }

    public Observable<List<Article>> getAllArticlesFromDB() {
        String QUERY = "SELECT * FROM " + TABLE_NAME + ";";
        return select(QUERY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Article>> getLimitedAmountOfArticles(int limit) {
        String QUERY = "SELECT * FROM " + TABLE_NAME + " LIMIT " + String.valueOf(limit)
                + ";";
        return select(QUERY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Article>> getFilteredArticles(String date) {
        String QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + IConstants.DB.DATE + "=" + date
                + ";";
        return select(QUERY);
    }

    public Observable<List<Article>> getFilteredArticles(int time) {
        String QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                IConstants.DB.TIME + " BETWEEN " + String.valueOf(time) + " AND " +
                String.valueOf(time - 1) + ";";
        return select(QUERY);
    }

    @NonNull
    private Observable<List<Article>> select(String query) {
        return Observable.create(subscriber -> {
            List<Article> list = syncGet(query);
            subscriber.onNext(list);
            subscriber.onCompleted();
        });
    }

    private List<Article> syncGet(String query) {
        List<Article> list = new ArrayList<>();
        SQLiteDatabase db = db1.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                list.add(getArticle(cursor));
            }
            cursor.close();
        }
        return list;
    }

    private Article getArticle(Cursor cursor) {
        String date = DBCursorUtils.getString(cursor, IConstants.DB.DATE);
        int time = DBCursorUtils.getInt(cursor, IConstants.DB.TIME);
        String author = DBCursorUtils.getString(cursor, IConstants.DB.AUTHOR_NAME);
        String title = DBCursorUtils.getString(cursor, IConstants.DB.TITLE);
        String description = DBCursorUtils.getString(cursor, IConstants.DB.DESCRIPTION);
        String url = DBCursorUtils.getString(cursor, IConstants.DB.URL);
        String urlToImage = DBCursorUtils.getString(cursor, IConstants.DB.URL_TO_IMAGE);
        String publishedAt = DBCursorUtils.getString(cursor, IConstants.DB.PUBLISHED_AT);

        return new Article(author, title, description, url, urlToImage, publishedAt, date, time);
    }

}
