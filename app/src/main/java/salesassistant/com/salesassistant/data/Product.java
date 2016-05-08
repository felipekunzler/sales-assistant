package salesassistant.com.salesassistant.data;

/**
 * Simple Product POJO
 */
public class Product extends Item {

    private String name;
    private String company;

    public Product() {
        super(-1);
    }

    public Product(long id, String name, String company) {
        super(id);
        this.name = name;
        this.company = company;
    }

    public Product(String name, String company) {
        this(-1, name, company);
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

}
