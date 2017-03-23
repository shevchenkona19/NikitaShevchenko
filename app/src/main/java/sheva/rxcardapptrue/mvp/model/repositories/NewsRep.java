package sheva.rxcardapptrue.mvp.model.repositories;


import java.util.ArrayList;
import java.util.List;

import sheva.rxcardapptrue.mvp.model.entities.NewsEntity;

/**
 * Created by shevc on 22.03.2017.
 */

public class NewsRep {
    private List<NewsEntity> list;

    public NewsRep(){
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            list.add(new NewsEntity("Title",
                    "THIS IS A NEW PIECE OF NEWS", (int) Math.random() * 2123));
        }
    }

    public List<NewsEntity> getList(){
        return list;
    }
}
