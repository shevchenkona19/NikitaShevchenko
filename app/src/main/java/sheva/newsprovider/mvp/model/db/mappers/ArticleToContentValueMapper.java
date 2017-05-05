package sheva.newsprovider.mvp.model.db.mappers;

import android.content.ContentValues;

import javax.inject.Inject;

import sheva.newsprovider.IConstants;
import sheva.newsprovider.mvp.model.entities.Article;

public class ArticleToContentValueMapper implements IMapper<Article, ContentValues> {
    @Inject
    public ArticleToContentValueMapper() {
    }

    @Override
    public ContentValues transform(Article object) throws RuntimeException {
        ContentValues values = new ContentValues();
        values.put(IConstants.Db.AUTHOR_NAME, object.getAuthor());
        values.put(IConstants.Db.DESCRIPTION, object.getDescription());
        values.put(IConstants.Db.PUBLISHED_AT, object.getPublishedAt());
        values.put(IConstants.Db.TITLE, object.getTitle());
        values.put(IConstants.Db.URL, object.getUrl());
        values.put(IConstants.Db.URL_TO_IMAGE, object.getUrlToImage());
        return values;
    }
}
