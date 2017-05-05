package sheva.newsprovider.utils;

import sheva.newsprovider.IConstants;

/**
 * Created by shevc on 14.04.2017.
 * s
 */

public class SourcesUtils {
    public static String[] resolveSource(String name) {
        switch (name.toLowerCase()) {
            case "news":
                return IConstants.Sources.news;
            case "tech":
                return IConstants.Sources.tech;
            case "sport":
                return IConstants.Sources.sport;
            case "business":
                return IConstants.Sources.business;
            case "entertainment":
                return IConstants.Sources.entertainment;
            case "finance":
                return IConstants.Sources.finance;
            case "football":
                return IConstants.Sources.football;
            case "games":
                return IConstants.Sources.games;
            case "music":
                return IConstants.Sources.music;
            case "geography":
                return IConstants.Sources.geography;
            case "science":
                return IConstants.Sources.science;
            case "politics":
                return IConstants.Sources.politics;
            default:
                return null;
        }
    }
}
