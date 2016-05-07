package salesassistant.com.salesassistant.data;

/**
 * Simple POJO containing common attributes
 */
public abstract class Item {

    protected long id;

    public Item(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
