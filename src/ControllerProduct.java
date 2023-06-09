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


public class ControllerProduct implements Initializable {
    //table
    @FXML
    private TableView<Product> ProductTable;
    @FXML
    private TableColumn<Product, Integer> column1;
    @FXML
    private TableColumn<Product, String> column2;
    @FXML
    private TableColumn<Product, Integer> column3;
    @FXML
    private TableColumn<Product, String> column4;
    @FXML
    private TableColumn<Product, String> column5;
    @FXML
    private TableColumn<Product, String> column6;
    @FXML
    private TableColumn<Product, Integer> column7;

    //textfield
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;
    @FXML
    private TextField txt3;
    @FXML
    private TextField txt4;
    @FXML
    private TextField txt5;
    @FXML
    private TextField txt6;
    @FXML
    private TextField txt7;

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
        String ID,Name,Unit,Description,Condition,Expiry,Quantity;
            ID = txt1.getText();
            Name = txt2.getText();
            Unit = txt3.getText();
            Description = txt4.getText();
            Condition = txt5.getText();
            Expiry = txt6.getText();
            Quantity = txt7.getText();
        try
        {
            pst = con.prepareStatement("insert into product(Product_ID,Product_Name,Product_Unit,Product_Description,Product_Condition,Product_ExpiryDate,Product_Quantity)values(?,?,?,?,?,?,?)");
            pst.setString(1, ID);
            pst.setString(2, Name);
            pst.setString(3, Unit);
            pst.setString(4, Description);
            pst.setString(5, Condition);
            pst.setString(6, Expiry);
            pst.setString(7, Quantity);
            pst.executeUpdate();
          
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Product Registration");
 
            alert.setHeaderText("Product Registration");
            alert.setContentText("Product Added!");
 
            alert.showAndWait();
 
            ProductTable();
            
            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            txt5.setText("");
            txt6.setText("");
            txt7.setText("");
            txt1.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ControllerProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Fungsi buat menyambungkan table ke database
    public void ProductTable()
    {
        Connect();
        ObservableList<Product> product = FXCollections.observableArrayList();
     try
     {
         pst = con.prepareStatement("select Product_ID,Product_Name,Product_Unit,Product_Description,Product_Condition,Product_ExpiryDate,Product_Quantity from product");  
         ResultSet rs = pst.executeQuery();
    {
      while (rs.next())
      {
          Product st = new Product(0, null, 0, null, null,null,0);
          st.setId(rs.getInt("Product_ID"));
          st.setName(rs.getString("Product_Name"));
          st.setUnit(rs.getInt("Product_Unit"));
          st.setDescription(rs.getString("Product_Description"));
          st.setCondition(rs.getString("Product_Condition"));
          st.setExpiry(rs.getString("Product_ExpiryDate"));
          st.setQuantity(rs.getInt("Product_Quantity"));
          
          product.add(st);
     }
  }
            ProductTable.setItems(product);
            column1.setCellValueFactory(f -> f.getValue().idProperty().asObject());
            column2.setCellValueFactory(f -> f.getValue().nameProperty());
            column3.setCellValueFactory(f -> f.getValue().unitProperty().asObject());
            column4.setCellValueFactory(f -> f.getValue().descriptionProperty());
            column5.setCellValueFactory(f -> f.getValue().conditionProperty());
            column6.setCellValueFactory(f -> f.getValue().expiryProperty());
            column7.setCellValueFactory(f -> f.getValue().quantityProperty().asObject());


     }
    
     catch (SQLException ex)
     {
         Logger.getLogger(ControllerProduct.class.getName()).log(Level.SEVERE, null, ex);
     }

              ProductTable.setRowFactory( tv -> {
              TableRow<Product> myRow = new TableRow<>();
              myRow.setOnMouseClicked (event ->
              {
                 if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                     {
                      myIndex =  ProductTable.getSelectionModel().getSelectedIndex();
                      id = Integer.parseInt(String.valueOf(ProductTable.getItems().get(myIndex).getId()));
                      unit = Integer.parseInt(String.valueOf(ProductTable.getItems().get(myIndex).getUnit()));
                      quantity = Integer.parseInt(String.valueOf(ProductTable.getItems().get(myIndex).getQuantity()));

                      txt1.setText(String.valueOf(ProductTable.getItems().get(myIndex).getId()));
                      txt2.setText(ProductTable.getItems().get(myIndex).getName());
                      txt3.setText(String.valueOf(ProductTable.getItems().get(myIndex).getUnit()));
                      txt4.setText(ProductTable.getItems().get(myIndex).getDescription());
                      txt5.setText(ProductTable.getItems().get(myIndex).getCondition());  
                      txt6.setText(ProductTable.getItems().get(myIndex).getExpiry());
                      txt7.setText(String.valueOf(ProductTable.getItems().get(myIndex).getQuantity()));
                                 
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
        myIndex = ProductTable.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(ProductTable.getItems().get(myIndex).getId()));
                    
        try
        {
            pst = con.prepareStatement("delete from product where Product_ID = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Product Delete");
 
            alert.setHeaderText("Product Deleted");
            alert.setContentText("Deleted!");
 
            alert.showAndWait();
                  ProductTable();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ControllerProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Fungsi buat melakukan update di database
    @FXML
    void Update(ActionEvent event) {
        String ID,Name,Unit,Description,Condition,Expiry,Quantity;
        
         myIndex = ProductTable.getSelectionModel().getSelectedIndex();
         id = Integer.parseInt(String.valueOf(ProductTable.getItems().get(myIndex).getId()));

            ID = txt1.getText();
            Name = txt2.getText();
            Unit = txt3.getText();
            Description = txt4.getText();
            Condition = txt5.getText();
            Expiry = txt6.getText();
            Quantity = txt7.getText();
        try
        {
            pst = con.prepareStatement("update product SET Product_ID = ?,Product_Name = ? ,Product_Unit = ?,Product_Description = ?,Product_Condition = ?,Product_ExpiryDate = ?,Product_Quantity = ? WHERE Product_ID = ?");
            pst.setString(1, ID);
            pst.setString(2, Name);
            pst.setString(3, Unit);
            pst.setString(4, Description);
            pst.setString(5, Condition);
            pst.setString(6, Expiry);
            pst.setString(7, Quantity);
            pst.setInt(8, id); 

            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Product Update");
 
            alert.setHeaderText("Product Update");
            alert.setContentText("Updated!");
 
            alert.showAndWait();
            ProductTable();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ControllerProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    //private Statement statement;
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    int unit;
    int quantity;

    //Fungsi untuk melakukan inisialisasi
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        ProductTable();
    }    
}
//tes
//halooooooooooooooooooo