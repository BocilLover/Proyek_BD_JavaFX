import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Product {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty product_unit;
    private final SimpleStringProperty product_description;
    private final SimpleStringProperty product_condition;
    private final SimpleStringProperty expiry_date;
    private final SimpleIntegerProperty product_quantity;

    public Product(int id, String name, int product_unit, String product_description, String product_condition,String expiry_date,int product_quantity) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.product_unit = new SimpleIntegerProperty(product_unit);
        this.product_description = new SimpleStringProperty(product_description);
        this.product_condition = new SimpleStringProperty(product_condition);
        this.expiry_date = new SimpleStringProperty(expiry_date);
        this.product_quantity = new SimpleIntegerProperty(product_quantity);
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

    public int getUnit() {
        return product_unit.get();
    }

    public SimpleIntegerProperty unitProperty() {
        return product_unit;
    }

    public String getDescription() {
        return product_description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return product_description;
    }

    public String getCondition() {
        return product_condition.get();
    }

    public SimpleStringProperty conditionProperty() {
        return product_condition;
    }

    public String getExpiry() {
        return expiry_date.get();
    }

    public SimpleStringProperty expiryProperty() {
        return expiry_date;
    }

    public int getQuantity() {
        return product_quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return product_quantity;
    }

    public void setId(int newId) { 
        id.set(newId); 
    }
    public void setName(String newName) { 
        name.set(newName); 
    }
    public void setUnit(int newUnit) { 
        product_unit.set(newUnit); 
    }
    public void setDescription(String newDescription) { 
        product_description.set(newDescription); 
    }
    public void setCondition(String newCondition) { 
        product_condition.set(newCondition); 
    }
    public void setExpiry(String newExpiry) { 
        expiry_date.set(newExpiry); 
    }
    public void setQuantity(int newQuantity) { 
        product_quantity.set(newQuantity); 
    }
    
}
