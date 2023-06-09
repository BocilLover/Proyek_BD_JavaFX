import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
public void start(Stage primaryStage) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Staff Table");
        primaryStage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
}
public static void main(String[] args) {
    launch(args);
}

}

// buat cek udah nyambung database ato belom
// import java.sql.Connection;
// import javafx.application.Application;
// import javafx.stage.Stage;

// public class App extends Application {
//     public static void main(String[] args) {
//         Database db = new Database();
//         Connection connection = db.getConnection();

//         if (connection != null) {
//             System.out.println("Database connection established successfully.");
//             // Perform other database operations
//         } else {
//             System.out.println("Error establishing database connection.");
//             // Handle the error appropriately
//         }
//     }

//     @Override
//     public void start(Stage arg0) throws Exception {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'start'");
//     }
// }
