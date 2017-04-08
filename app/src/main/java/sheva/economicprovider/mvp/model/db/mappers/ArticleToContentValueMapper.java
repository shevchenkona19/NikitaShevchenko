package sheva.economicprovider.mvp.model.db.mappers;

import android.content.ContentValues;

import sheva.economicprovider.IConstants;
import sheva.economicprovider.mvp.model.entities.Article;

/**
 * Created by shevc on 06.04.2017.
 * l
 */

public class ArticleToContentValueMapper implements IMapper<Article, ContentValues> {

    @Override
    public ContentValues transform(Article object) throws RuntimeException {
        ContentValues values = new ContentValues();
        values.put(IConstants.DB.DATE, object.getDate());
        values.put(IConstants.DB.TIME, object.getTime());
        values.put(IConstants.DB.AUTHOR_NAME, object.getAuthor());
        values.put(IConstants.DB.TITLE, object.getTitle());
        values.put(IConstants.DB.DESCRIPTION, object.getDescription());
        values.put(IConstants.DB.URL, object.getUrl());
        values.put(IConstants.DB.URL_TO_IMAGE, object.getUrlToImage());
        values.put(IConstants.DB.PUBLISHED_AT, object.getPublishedAt());
        return values;
    }
}
