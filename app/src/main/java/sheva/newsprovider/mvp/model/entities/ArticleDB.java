package sheva.newsprovider.mvp.model.entities;

public class ArticleDB extends Article {

    private int newsId;

    public ArticleDB(String author, String title, String description,
                     String url, String urlToImage, String publishedAt, int newsId) {
        super(author, title, description, url, urlToImage, publishedAt);
        this.newsId = newsId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }
}
