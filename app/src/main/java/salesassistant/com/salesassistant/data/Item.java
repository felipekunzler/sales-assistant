package salesassistant.com.salesassistant.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Simple POJO containing common attributes
 */
public abstract class Item {

    protected long id = -1;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public abstract JSONObject toJSON();

}
