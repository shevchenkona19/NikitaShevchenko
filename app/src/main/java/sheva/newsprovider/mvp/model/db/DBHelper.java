package sheva.newsprovider.mvp.model.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sheva.newsprovider.App;
import sheva.newsprovider.IConstants;
import sheva.newsprovider.mvp.model.db.mappers.ArticleToContentValueMapper;
import sheva.newsprovider.mvp.model.entities.Article;
import sheva.newsprovider.mvp.model.entities.ArticleDB;

public class DBHelper {
    private DB db1;
    private long lastId;
    @Inject
    ArticleToContentValueMapper mapper;

    @Inject
    public DBHelper() {
        db1 = new DB(App.get());
        createTable();
    }

    private void createTable() {
        SQLiteDatabase database = db1.getWritableDatabase();
        String createTable = "CREATE TABLE IF NOT EXISTS " + IConstants.Db.TABLE_NAME + " " +
                "(" +
                IConstants.Db.NEWS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                IConstants.Db.AUTHOR_NAME + " TEXT," +
                IConstants.Db.TITLE + " TEXT NOT NULL," +
                IConstants.Db.DESCRIPTION + " TEXT NOT NULL," +
                IConstants.Db.URL + " TEXT NOT NULL," +
                IConstants.Db.URL_TO_IMAGE + " TEXT NOT NULL," +
                IConstants.Db.PUBLISHED_AT + " TEXT," +
                " UNIQUE (" + IConstants.Db.NEWS_ID + ") ON CONFLICT REPLACE" +
                ");";
        database.execSQL(createTable);
    }

    public void dropTable() {
        SQLiteDatabase db = db1.getWritableDatabase();
        String QUERY = "DROP TABLE " + IConstants.Db.TABLE_NAME + ";";
        db.execSQL(QUERY);
    }

    public void deleteWithId(int id) {
        SQLiteDatabase database = db1.getWritableDatabase();
        String delete = "DELETE FROM " + IConstants.Db.TABLE_NAME +
                " WHERE " +
                IConstants.Db.NEWS_ID + " = " + id + ";";
        database.execSQL(delete);
    }

    public void deleteLast() {
        SQLiteDatabase database = db1.getWritableDatabase();
        String delete = "DELETE FROM " + IConstants.Db.TABLE_NAME +
                " WHERE " + IConstants.Db.NEWS_ID + " = " + lastId + ";";
        database.execSQL(delete);
        lastId -= 1;
    }

    public void insert(Article article) {
        SQLiteDatabase db = db1.getWritableDatabase();
        lastId = db.insert(IConstants.Db.TABLE_NAME, null, mapper.transform(article));
    }

    public Observable<List<ArticleDB>> getAllArticlesFromDB() {
        String query = "SELECT * FROM " + IConstants.Db.TABLE_NAME + ";";
        return select(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    private Observable<List<ArticleDB>> select(String query) {
        return Observable.create(subscriber -> {
            List<ArticleDB> list = syncGet(query);
            subscriber.onNext(list);
            subscriber.onCompleted();
        });
    }

    private List<ArticleDB> syncGet(String query) {
        List<ArticleDB> list = new ArrayList<>();
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

    private ArticleDB getArticle(Cursor cursor) {
        String author = DBCursorUtils.getString(cursor, IConstants.Db.AUTHOR_NAME);
        String title = DBCursorUtils.getString(cursor, IConstants.Db.TITLE);
        String description = DBCursorUtils.getString(cursor, IConstants.Db.DESCRIPTION);
        String url = DBCursorUtils.getString(cursor, IConstants.Db.URL);
        String urlToImage = DBCursorUtils.getString(cursor, IConstants.Db.URL_TO_IMAGE);
        String publishedAt = DBCursorUtils.getString(cursor, IConstants.Db.PUBLISHED_AT);
        int newsId = DBCursorUtils.getInt(cursor, IConstants.Db.NEWS_ID);

        return new ArticleDB(author, title, description, url, urlToImage, publishedAt, newsId);
    }
}
