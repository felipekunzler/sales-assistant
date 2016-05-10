package salesassistant.com.salesassistant.data;

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

}
