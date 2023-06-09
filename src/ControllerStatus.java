import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableRow;


public class ControllerStatus implements Initializable {
    //table
    @FXML
    private TableView<Status> statusTable;
    @FXML
    private TableColumn<Status, Integer> orderidColumn;
    @FXML
    private TableColumn<Status, String> orderdateColumn;
    @FXML
    private TableColumn<Status, String> estimateColumn;
    @FXML
    private TableColumn<Status, String> fareColumn;
    @FXML
    private TableColumn<Status, String> staffidColumn;

    //textfield
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;

    //button
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button switchToMain;
   

    //Fungsi buat pindah scene dari Staff scene ke Main scene
    @FXML
    void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Stage stage = (Stage) switchToMain.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    //Fungsi buat nambah data baru di database
    @FXML
    void Add(ActionEvent event) {
        String ID,Name,Address,Email,Phone;
            ID = txtID.getText();
            Name = txtName.getText();
            Address = txtAddress.getText();
            Email = txtEmail.getText();
            Phone = txtPhone.getText();
        try
        {
            pst = con.prepareStatement("insert into status_order(Order_ID,Order_Date,Order_Estimate_Date,Order_Fare,Staff_ID)values(?,?,?,?,?)");
            pst.setString(1, ID);
            pst.setString(2, Name);
            pst.setString(3, Address);
            pst.setString(4, Email);
            pst.setString(5, Phone);
            pst.executeUpdate();
          
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Status Order");
 
            alert.setHeaderText("Status Order Registration");
            alert.setContentText("Status Order Added!");
 
            alert.showAndWait();
 
            statusTable();
            
            txtID.setText("");
            txtName.setText("");
            txtAddress.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtID.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ControllerStatus.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Fungsi buat menyambungkan table ke database
    public void statusTable()
    {
        Connect();
        ObservableList<Status> status = FXCollections.observableArrayList();
     try
     {
         pst = con.prepareStatement("select Order_ID,Order_Date,Order_Estimate_Date,Order_Fare,Staff_ID from status_order");  
         ResultSet rs = pst.executeQuery();
    {
      while (rs.next())
      {
          Status st = new Status(0, null, null, null, null);
          st.setId(rs.getInt("Order_ID"));
          st.setDate(rs.getString("Order_Date"));
          st.setEstimate(rs.getString("Order_Estimate_Date"));
          st.setOrderFare(rs.getString("Order_Fare"));
          st.setStaffID(rs.getString("Staff_ID"));
          
          status.add(st);
     }
  }
              statusTable.setItems(status);
              orderidColumn.setCellValueFactory(f -> f.getValue().idProperty().asObject());
              orderdateColumn.setCellValueFactory(f -> f.getValue().dateProperty());
              estimateColumn.setCellValueFactory(f -> f.getValue().estimateProperty());
              fareColumn.setCellValueFactory(f -> f.getValue().orderfareProperty());
              staffidColumn.setCellValueFactory(f -> f.getValue().staffidProperty());

     }
    
     catch (SQLException ex)
     {
         Logger.getLogger(ControllerStatus.class.getName()).log(Level.SEVERE, null, ex);
     }

              statusTable.setRowFactory( tv -> {
              TableRow<Status> myRow = new TableRow<>();
              myRow.setOnMouseClicked (event ->
              {
                 if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                     {
                      myIndex =  statusTable.getSelectionModel().getSelectedIndex();
                      id = Integer.parseInt(String.valueOf(statusTable.getItems().get(myIndex).getId()));
                      txtID.setText(String.valueOf(statusTable.getItems().get(myIndex).getId()));
                      txtName.setText(statusTable.getItems().get(myIndex).getDate());
                      txtAddress.setText(statusTable.getItems().get(myIndex).getEstimate());
                      txtEmail.setText(statusTable.getItems().get(myIndex).getOrderFare());
                      txtPhone.setText(statusTable.getItems().get(myIndex).getStaffID());  
                                 
      }
   });
      return myRow;
                 });
    }

    //Fungsi buat menyambungkan ke database
    private void Connect() {
        try {
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyek_bd", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Fungsi buat menghapus data di database
    @FXML
    void Delete(ActionEvent event) {
        myIndex = statusTable.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(statusTable.getItems().get(myIndex).getId()));
                    
        try
        {
            pst = con.prepareStatement("delete from status_order where Order_ID = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Status Order");
 
            alert.setHeaderText("Status Order Deleted");
            alert.setContentText("Deleted!");
 
            alert.showAndWait();
                  statusTable();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ControllerStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Fungsi buat melakukan update di database
    @FXML
    void Update(ActionEvent event) {
        String ID,Date,Estimate_date,Order_fare,Staff_id;
        
         myIndex = statusTable.getSelectionModel().getSelectedIndex();
         id = Integer.parseInt(String.valueOf(statusTable.getItems().get(myIndex).getId()));

            ID = txtID.getText();
            Date = txtName.getText();
            Estimate_date = txtAddress.getText();
            Order_fare = txtEmail.getText();
            Staff_id = txtPhone.getText();
        try
        {
            pst = con.prepareStatement("update status_order SET Order_ID = ?,Order_Date = ? ,Order_Estimate_Date = ?,Order_Fare = ?,Staff_ID = ? WHERE Order_ID = ?");
            pst.setString(1, ID);
            pst.setString(2, Date);
            pst.setString(3, Estimate_date);
            pst.setString(4, Order_fare);
            pst.setString(5, Staff_id);
            pst.setInt(6, id); 

            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Status Order");
 
            alert.setHeaderText("Status Order Update");
            alert.setContentText("Updated!");
 
            alert.showAndWait();
            statusTable();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ControllerStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    //private Statement statement;
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;

    //Fungsi untuk melakukan inisialisasi
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        statusTable();
    }    
}