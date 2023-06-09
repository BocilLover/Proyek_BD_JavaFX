import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Status {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty date;
    private final SimpleStringProperty estimate_date;
    private final SimpleStringProperty order_fare;
    private final SimpleStringProperty staff_id;

    public Status(int id, String date, String estimate_date, String order_fare, String staff_id) {
        this.id = new SimpleIntegerProperty(id);
        this.date = new SimpleStringProperty(date);
        this.estimate_date = new SimpleStringProperty(estimate_date);
        this.order_fare = new SimpleStringProperty(order_fare);
        this.staff_id = new SimpleStringProperty(staff_id);
    }
    

    public int getId() {
        return (int) id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public String getEstimate() {
        return estimate_date.get();
    }

    public SimpleStringProperty estimateProperty() {
        return estimate_date;
    }

    public String getOrderFare() {
        return order_fare.get();
    }

    public SimpleStringProperty orderfareProperty() {
        return order_fare;
    }

    public String getStaffID() {
        return staff_id.get();
    }

    public SimpleStringProperty staffidProperty() {
        return staff_id;
    }

    public void setId(int newId) { 
        id.set(newId); 
    }
    public void setDate(String newDate) { 
        date.set(newDate); 
    }
    public void setEstimate(String newEstimate) { 
        estimate_date.set(newEstimate); 
    }
    public void setOrderFare(String newOrder) { 
        order_fare.set(newOrder); 
    }
    
    public void setStaffID(String newStaffID) { 
        staff_id.set(newStaffID); 
    }
    
}
