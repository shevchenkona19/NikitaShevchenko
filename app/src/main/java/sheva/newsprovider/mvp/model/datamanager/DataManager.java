package sheva.newsprovider.mvp.model.datamanager;

import android.net.Uri;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sheva.newsprovider.App;
import sheva.newsprovider.IConstants;
import sheva.newsprovider.mvp.model.db.DBHelper;
import sheva.newsprovider.mvp.model.entities.Article;
import sheva.newsprovider.mvp.model.entities.ArticleDB;
import sheva.newsprovider.mvp.model.entities.Interest;
import sheva.newsprovider.mvp.model.entities.NewsEntity;
import sheva.newsprovider.mvp.model.entities.WeatherEntity;
import sheva.newsprovider.mvp.model.repositories.GeoRepository;
import sheva.newsprovider.mvp.model.repositories.NetworkRepository;
import sheva.newsprovider.mvp.model.repositories.NewsRepository;
import sheva.newsprovider.mvp.model.repositories.SharedPreferencesRepository;
import sheva.newsprovider.mvp.model.repositories.UserRepository;
import sheva.newsprovider.mvp.model.repositories.WeatherRepository;

public class DataManager {
    @Inject
    SharedPreferencesRepository sharedPreferencesRepository;
    @Inject
    UserRepository user;
    @Inject
    NewsRepository newsRepository;
    @Inject
    WeatherRepository weatherRepository;
    @Inject
    GeoRepository geoRepository;
    @Inject
    NetworkRepository networkRepository;
    @Inject
    DBHelper dbHelper;

    public DataManager() {
        App.get().getAppComponent().inject(this);
    }

    public boolean isNotify() {
        return sharedPreferencesRepository.isNotify();
    }

    public void setNotifications(boolean b) {
        sharedPreferencesRepository.setNotify(b);
    }

    public boolean isRegistered() {
        return sharedPreferencesRepository.isRegistered();
    }

    public void register(String name, String username, String password, String img) {
        user.registerMainUser(name, username, password, img);
        sharedPreferencesRepository.register(user);
    }

    public void putPhoto(Uri uri) {
        sharedPreferencesRepository.setPhoto(uri);
    }

    public void unregisterCurrentUser() {
        user.unregisterMainUser();
        sharedPreferencesRepository.unregisterCurrentUser();
        dbHelper.dropTable();
    }

    public boolean login(String username, String password) {
        return sharedPreferencesRepository.login(username, password);
    }

    public void inflateRegisteredUser() {
        String[] userInfo = sharedPreferencesRepository.getUserInfo();
        user.setListOfInterests(sharedPreferencesRepository.getSavedListOfInterests());
        user.registerMainUser(userInfo[0], userInfo[1], userInfo[2], userInfo[3]);
    }

    public String[] getUserInfo() {
        String[] userInfo = new String[2];
        userInfo[0] = user.getName();
        userInfo[1] = user.getUserImgString();
        return userInfo;
    }

    public List<Interest> getUserInterests() {
        return user.getListOfInterests();
    }

    public void deleteInterests() {
        sharedPreferencesRepository.deleteInterests();
        user.deleteInterests();
    }

    public void setUserInterests(List<Interest> list) {
        user.setListOfInterests(list);
        sharedPreferencesRepository.saveInterests(user);
    }

    public Observable<Article> getNewsObservable(String source, String sortBy) {
        return newsRepository.getNews(source, sortBy)
                .flatMap(new Func1<NewsEntity, Observable<Article>>() {
                    @Override
                    public Observable<Article> call(NewsEntity newsEntity) {
                        return Observable.from(newsEntity.getArticles());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void usePhoneBrowser(boolean b) {
        sharedPreferencesRepository.usePhoneBrowser(b);
    }

    public boolean isUsingPhoneBrowser() {
        return sharedPreferencesRepository.isUsingPhoneBrowser();
    }

    public void setIsUsingFarenheit(boolean b) {
        sharedPreferencesRepository.setIsUsingFarenheit(b);
    }

    public boolean isUsingFarenheit() {
        return sharedPreferencesRepository.isUsingFarenheit();
    }

    public Observable<WeatherEntity> getWeatherObservable() {
        if (sharedPreferencesRepository.isUsingFarenheit()) {
            return weatherRepository.getWeatherForCoordinates(geoRepository.getLon(),
                    geoRepository.getLat(),
                    IConstants.Weather.UNITS_IMPERIAL)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            return weatherRepository.getWeatherForCoordinates(31,
                    31,
                    IConstants.Weather.UNITS_METRIC)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

    public boolean isInternetAvailable() {
        return networkRepository.isInternetAvailable();
    }

    public void setVKAccessToken(String token) {
        sharedPreferencesRepository.setVKAccessToken(token);
    }

    public String getVKAccessToken() {
        return sharedPreferencesRepository.getVKAccessToken();
    }

    public void setIsUsingVk(boolean b) {
        sharedPreferencesRepository.setUsingVK(b);
    }

    public boolean isUsingVK() {
        return sharedPreferencesRepository.isUsingVk();
    }

    public Observable<List<ArticleDB>> getFavoriteArticles() {
        return dbHelper.getAllArticlesFromDB();
    }

    public void insertArticle(Article article) {
        dbHelper.insert(article);
    }

    public void deleteWithId(int id) {
        dbHelper.deleteWithId(id);
    }

    public void deleteLast() {
        dbHelper.deleteLast();
    }
}