package salesassistant.com.salesassistant.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Simple Product POJO
 */
public class Product extends Item {

    private String name;
    private String company;

    public Product() {
    }

    public Product(String name, String company) {
        this.name = name;
        this.company = company;
    }

    public Product(long id, String name, String company) {
        this(name, company);
        this.id = id;
    }

    public Product(JSONObject json) throws JSONException {
        this.id = json.getLong("id");
        this.name = json.getString("name");
        this.company = json.getString("company");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return name;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", id);
            json.put("name", name);
            json.put("company", company);

            return json;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
