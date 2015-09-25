package api.common;

/**
 * Created by root on 20/09/15.
 */
public interface RepresentationBuilder<T,V>{

    public abstract T fromRepresentation(V dtoRepresentation);
    public abstract V toRepresentation(T modelRepresentation);

}
