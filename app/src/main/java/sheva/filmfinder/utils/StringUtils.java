package sheva.filmfinder.utils;

import android.util.Log;

/**
 * Created by shevc on 12.06.2017.
 * Let's GO!
 */

public class StringUtils {

    public static String getActors(String s) {
        StringBuilder builder = new StringBuilder();
        String[] ags = s.split(", ");
        for (int i = 0 ; i < ags.length; i ++) {
            try {
                builder.append(ags[i].substring(ags[i].indexOf(">") + 1, ags[i].lastIndexOf("<")));
            } catch (Exception e) {
                builder.append(ags[i]);
            }
            if (i != ags.length-1){
                builder.append(", ");
            }
        }
        return builder.toString();
    }
//"<a href=\"/persons/hoakim-ronning\">Хоаким Роннинг</a>, <a href=\"/persons/espen-sandberg\">Эспен Сандберг</a>"
    public static String getDirector(String s) {
        StringBuilder builder = new StringBuilder();
        String[] ags = s.split(", ");
        for(int i = 0 ; i < ags.length; i++){
            try {
                builder.append(ags[i].substring(ags[i].indexOf(">") + 1, ags[i].lastIndexOf("<")));
            } catch (Exception e) {
                builder.append(ags[i]);
            }
            if (i != ags.length-1){
                builder.append(", ");
            }
        }
        return builder.toString();
    }
    //<div class=\"timewrap nooffset\"><a onclick=\"ga('send','event','Время сеанса','vkino','Мумия');\" href=\"http://bilet.vkino.com.ua/showtime?show-name=%D0%9C%D1%83%D0%BC%D0%B8%D1%8F&datetime=2017-06-13+14%3A35&theater=boomer&hall-id=16&agent=kinoafisha\" class=\"activeEvent vkino-link\">14:35</a></div>
    // <div class=\"timewrap nooffset\"><a onclick=\"ga('send','event','Время сеанса','vkino','Мумия');\" href=\"http://bilet.vkino.com.ua/showtime?show-name=%D0%9C%D1%83%D0%BC%D0%B8%D1%8F&datetime=2017-06-13+21%3A30&theater=boomer&hall-id=16&agent=kinoafisha\" class=\"activeEvent vkino-link\">21:30</a></div>",

    public static String getFromatedSessionString(String s) {
        StringBuilder builder = new StringBuilder();
        String[] sessions = s.split("</div>");
        for (int i = 0; i < sessions.length; i++){
            sessions[i] = sessions[i].replaceAll("<div class=\"timewrap nooffset\">", "");
            sessions[i] = sessions[i].replaceAll("<div class=\"timewrap\">", "");
        }
        for(int i = 0; i < sessions.length; i++) {
            try {
                builder.append(sessions[i].substring(sessions[i].indexOf(">") + 1, sessions[i].indexOf("</a>")));
            } catch (Exception e) {
                builder.append(sessions[i].substring(sessions[i].indexOf(">") +1 , sessions[i].indexOf("</span>")));
            }
            if (i != sessions.length-1){
                builder.append("\t");
            }
        }
        return builder.toString();
    }
}
