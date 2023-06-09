import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerMain {
    @FXML
    private Button switchToStaff;
    @FXML
    private Button switchToStatus;
    @FXML
    private Button switchToProduct;

    //Fungsi buat pindah scene dari Main scene ke Staff scene
    @FXML
    void switchToStaff(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Staff.fxml"));
        Stage stage = (Stage) switchToStaff.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Fungsi buat pindah scene dari Main scene ke Status Order scene
    @FXML
    void switchToStatus(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Status.fxml"));
        Stage stage = (Stage) switchToStatus.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

     //Fungsi buat pindah scene dari Main scene ke Product scene
     @FXML
     void switchToProduct(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("product.fxml"));
         Stage stage = (Stage) switchToProduct.getScene().getWindow();
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
     }

    //Fungsi buat pindah scene dari Main scene ke Warehouse scene
    @FXML
    void switchToWarehouse(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Warehouse.fxml"));
        Stage stage = (Stage) switchToStaff.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Fungsi buat pindah scene dari Main scene ke Delivery Order scene
    @FXML
    void switchToDeliveryOrder(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Delivery_Order.fxml"));
        Stage stage = (Stage) switchToStaff.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Fungsi buat pindah scene dari Main scene ke Destination scene
    @FXML
    void switchToDestination(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Destination.fxml"));
        Stage stage = (Stage) switchToStaff.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Fungsi buat pindah scene dari Main scene ke Payment scene
    @FXML
    void switchToPayment(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        Stage stage = (Stage) switchToStaff.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

     //Fungsi buat pindah scene dari Main scene ke Customer scene
     @FXML
     void switchToCustomer(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
         Stage stage = (Stage) switchToStaff.getScene().getWindow();
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
     }

      //Fungsi buat pindah scene dari Main scene ke Driver scene
      @FXML
      void switchToDriver(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Driver.fxml"));
          Stage stage = (Stage) switchToStaff.getScene().getWindow();
          Scene scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
      }

      //Fungsi buat pindah scene dari Main scene ke Truck scene
      @FXML
      void switchToTruck(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Truck.fxml"));
          Stage stage = (Stage) switchToStaff.getScene().getWindow();
          Scene scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
      }
    
}
