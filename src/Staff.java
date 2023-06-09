import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Staff {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty address;
    private final SimpleStringProperty email;
    private final SimpleStringProperty phone;

    public Staff(int id, String name, String address, String email, String phone) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
    }
    

    public int getId() {
        return (int) id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }
    public void setId(int newId) { 
        id.set(newId); 
    }
    public void setName(String newName) { 
        name.set(newName); 
    }

    public void setAddress(String newAddress) { 
        address.set(newAddress); 
    }
    
    public void setEmail(String newEmail) { 
        email.set(newEmail); 
    }
    
    public void setPhone(String newPhone) { 
        phone.set(newPhone); 
    }
    
}
