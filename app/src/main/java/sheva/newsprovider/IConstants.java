package sheva.newsprovider;

/**
 * Created by shevc on 11.04.2017.
 * s
 */

public interface IConstants {
    int RESULT_PIC = 12034;

    interface Preferences {
        String APP_PREFERENCES = "PREFERENCES_NEWS";
        String PREFERENCES_USERNAME = "USERNAME";
        String PREFERENCES_PASSWORD = "PASSWORD";
        String PREFERENCES_NAME = "NAME";
        String PREFERENCES_IMG = "IMG";
        String PREFERENCES_ISREGISTRED = "IS_REGISTERED";
        String PREFERENCES_INTERESTS = "INTERESTS";
        String PREFERENCES_BROWSER = "BROWSER";
        String PREFERENCES_UNITS = "UNITS";
        String PREFERENCES_NOTIFICATIONS = "NOTIFICATIONS";
    }

    interface Weather{
        String BASE_IMG_URL = "http://openweathermap.org/img/w/";
        String PNG = ".png";
        String UNITS_IMPERIAL = "imperial";
        String UNITS_METRIC = "metric";
    }

    interface Sources {
        String[] news = {"associated-press"};
        String[] tech = {"techradar"};
        String[] sport = {"bbc-sport"};
        String[] business = {"business-insider"};
        String[] entertainment = {"buzzfeed"};
        String[] finance = {"financial-times"};
        String[] football = {"football-italia"};
        String[] games = {"ign"};
        String[] music = {"mtv-news"};
        String[] geography = {"national-geographic"};
        String[] science = {"new-scientist"};
        String[] politics = {"reuters"};
    }

    interface Feed {
        int TYPE_WEATHER = 0;
        int TYPE_HEADER = 1;
        int TYPE_NEWS = 2;
        int TYPE_MORE = 3;
    }

    interface ApiKeys {
        String newsAPIKey = "0a9a0488d7574a669cc08e8f02a84f93";
        String weatherAPIKey = "6505f0cb42fc4a562299a4e904190d9c";
    }

    interface Vk {
        String ACCESSTOKEN = "vkToken";
        String ISUSINGVK = "ISUSINGVK";
    }

    interface Db {
        String TABLE_NAME = "FavoriteArticles";
        String DB_NAME = "NewsProviderDB";
        int DB_VERSION = 1;
        String NEWS_ID = "NEWS_ID";
        String AUTHOR_NAME = "AUTHOR_NAME";
        String TITLE = "TITLE";
        String DESCRIPTION = "DESCRIPTION";
        String URL = "URL";
        String URL_TO_IMAGE = "URL_TO_IMAGE";
        String PUBLISHED_AT = "PUBLISHED_AT";
    }
}
