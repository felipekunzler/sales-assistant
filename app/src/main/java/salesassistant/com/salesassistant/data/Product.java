package salesassistant.com.salesassistant.data;

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
}
