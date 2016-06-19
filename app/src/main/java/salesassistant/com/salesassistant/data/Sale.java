package salesassistant.com.salesassistant.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Simple Sale POJO
 */
public class Sale extends Item {

    private Client client;
    private Product product;
    private Date date;

    public Sale() {
    }

    public Sale(Client client, Product product) {
        this.client = client;
        this.product = product;
    }

    public Sale(long id, Client client, Product product, Date date) {
        this(client, product);
        this.date = date;
        this.id = id;
    }

    public Sale(JSONObject json) throws JSONException {
        Client c = new Client();
        Product p = new Product();

        c.setId(json.getLong("client"));
        p.setId(json.getLong("product"));

        this.id = json.getLong("id");
        this.date = null; // need to parse date here
        this.client = c;
        this.product = p;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", id);
            json.put("date", date);
            json.put("client", client.getId());
            json.put("product", product.getId());

            return json;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
