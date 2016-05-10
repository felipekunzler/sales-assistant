package salesassistant.com.salesassistant.data;

/**
 * Simple Client POJO
 */
public class Client extends Item {

    private String name;
    private String phone;
    private String address;
    private String email;

    public Client() {
    }

    public Client(String name, String phone, String address, String email) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Client(long id, String name, String phone, String address, String email) {
        this(name, phone, address, email);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return name;
    }
}
