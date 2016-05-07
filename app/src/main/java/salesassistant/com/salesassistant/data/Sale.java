package salesassistant.com.salesassistant.data;

/**
 * Simple Sale POJO
 */
public class Sale {

    private long id;
    private Client client;
    private Product product;

    public Sale(Client client, Product product) {
        this.client = client;
        this.product = product;
    }

    public Sale(long id, Client client, Product product) {
        this(client, product);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

}
