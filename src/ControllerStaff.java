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


public class ControllerStaff implements Initializable {
    //table
    @FXML
    private TableView<Staff> staffTable;
    @FXML
    private TableColumn<Staff, Integer> idColumn;
    @FXML
    private TableColumn<Staff, String> nameColumn;
    @FXML
    private TableColumn<Staff, String> addressColumn;
    @FXML
    private TableColumn<Staff, String> emailColumn;
    @FXML
    private TableColumn<Staff, String> phoneColumn;

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
    @FXML
    private Button switchToStaff;

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
        String ID;
        String Name,Address,Email,Phone;
            ID = txtID.getText();
            Name = txtName.getText();
            Address = txtAddress.getText();
            Email = txtEmail.getText();
            Phone = txtPhone.getText();
        try
        {
            pst = con.prepareStatement("insert into staff(Staff_ID,Staff_Name,Staff_Address,Staff_Email,Staff_Phone)values(?,?,?,?,?)");
            pst.setString(1, ID);
            pst.setString(2, Name);
            pst.setString(3, Address);
            pst.setString(4, Email);
            pst.setString(5, Phone);
            pst.executeUpdate();
          
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Staff Registration");
 
            alert.setHeaderText("Staff Registration");
            alert.setContentText("Staff Added!");
 
            alert.showAndWait();
 
            staffTable();
            
            txtID.setText("");
            txtName.setText("");
            txtAddress.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtID.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ControllerStaff.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Fungsi buat menyambungkan table ke database
    public void staffTable()
    {
        Connect();
        ObservableList<Staff> staff = FXCollections.observableArrayList();
     try
     {
         pst = con.prepareStatement("select Staff_ID,Staff_Name,Staff_Address,Staff_Email,Staff_Phone from staff");  
         ResultSet rs = pst.executeQuery();
    {
      while (rs.next())
      {
          Staff st = new Staff(0, null, null, null, null);
          st.setId(rs.getInt("Staff_ID"));
          st.setName(rs.getString("Staff_Name"));
          st.setAddress(rs.getString("Staff_Address"));
          st.setEmail(rs.getString("Staff_Email"));
          st.setPhone(rs.getString("Staff_Phone"));
          
          staff.add(st);
     }
  }
              staffTable.setItems(staff);
              idColumn.setCellValueFactory(f -> f.getValue().idProperty().asObject());
              nameColumn.setCellValueFactory(f -> f.getValue().nameProperty());
              addressColumn.setCellValueFactory(f -> f.getValue().addressProperty());
              emailColumn.setCellValueFactory(f -> f.getValue().emailProperty());
              phoneColumn.setCellValueFactory(f -> f.getValue().phoneProperty());

     }
    
     catch (SQLException ex)
     {
         Logger.getLogger(ControllerStaff.class.getName()).log(Level.SEVERE, null, ex);
     }

              staffTable.setRowFactory( tv -> {
              TableRow<Staff> myRow = new TableRow<>();
              myRow.setOnMouseClicked (event ->
              {
                 if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                     {
                      myIndex =  staffTable.getSelectionModel().getSelectedIndex();
                      id = Integer.parseInt(String.valueOf(staffTable.getItems().get(myIndex).getId()));
                      txtID.setText(String.valueOf(staffTable.getItems().get(myIndex).getId()));
                      txtName.setText(staffTable.getItems().get(myIndex).getName());
                      txtAddress.setText(staffTable.getItems().get(myIndex).getAddress());
                      txtEmail.setText(staffTable.getItems().get(myIndex).getEmail());
                      txtPhone.setText(staffTable.getItems().get(myIndex).getPhone());  
                                 
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
        myIndex = staffTable.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(staffTable.getItems().get(myIndex).getId()));
                    
        try
        {
            pst = con.prepareStatement("delete from staff where Staff_ID = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Staff Delete");
 
            alert.setHeaderText("Staff Deleted");
            alert.setContentText("Deleted!");
 
            alert.showAndWait();
                  staffTable();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ControllerStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Fungsi buat melakukan update di database
    @FXML
    void Update(ActionEvent event) {
        String ID,Name,Address,Email,Phone;
        
         myIndex = staffTable.getSelectionModel().getSelectedIndex();
         id = Integer.parseInt(String.valueOf(staffTable.getItems().get(myIndex).getId()));

            ID = txtID.getText();
            Name = txtName.getText();
            Address = txtAddress.getText();
            Email = txtEmail.getText();
            Phone = txtPhone.getText();
        try
        {
            pst = con.prepareStatement("update staff SET Staff_ID = ?,Staff_Name = ? ,Staff_Address = ?,Staff_Email = ?,Staff_Phone = ?WHERE Staff_ID = ?");
            pst.setString(1, ID);
            pst.setString(2, Name);
            pst.setString(3, Address);
            pst.setString(4, Email);
            pst.setString(5, Phone);
            pst.setInt(6, id); 

            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Staff Update");
 
            alert.setHeaderText("Staff Update");
            alert.setContentText("Updated!");
 
            alert.showAndWait();
            staffTable();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ControllerStaff.class.getName()).log(Level.SEVERE, null, ex);
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
        staffTable();
    }    


    //private ObservableList<Staff> staffList = FXCollections.observableArrayList();

    // ...

    /*private void populateStaffTable() throws SQLException {
    ResultSet resultSet = statement.executeQuery("SELECT * FROM staff");
    while (resultSet.next()) {
        Staff staff = new Staff(resultSet.getInt("Staff_ID"),
                                resultSet.getString("Staff_Name"),
                                resultSet.getString("Staff_Address"),
                                resultSet.getString("Staff_Email"),
                                resultSet.getString("Staff_Phone"));
        staffList.add(staff);
    }
    staffTable.setItems(staffList);
    }/* */

}










































/*import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Controller {
    @FXML
    private Label usernameLabel;

    public void connectButton(ActionEvent event) {
        Database connectNow = new Database();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT * from belajarajax";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput.next()) {
                usernameLabel.setText(queryOutput.getString("nama"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}/* */

