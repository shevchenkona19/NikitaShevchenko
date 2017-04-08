package sheva.economicprovider.mvp.model.db.mappers;

/**
 * Created by shevc on 06.04.2017.
 * g
 */

public interface IMapper<A, B> {
    B transform(A object) throws RuntimeException;
}
