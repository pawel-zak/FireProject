package zakp.fireproject.data.model;

/**
 * K - id type
 */
public abstract class BaseModel<K> implements IContentValues {

    public static final String COL_ID = "id";

    protected K id;

    public K getId() {
        return id;
    }



}
