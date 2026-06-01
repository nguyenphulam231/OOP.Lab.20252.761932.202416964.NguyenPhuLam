package hust.soict.hedspi.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Painter extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Sử dụng đường dẫn tuyệt đối chính xác theo cấu trúc package HEDSPI của bạn (Figure 34)
        Parent root = FXMLLoader.load(getClass().getResource("/hust/soict/hedspi/javafx/Painter.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Painter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Kích hoạt vòng đời của ứng dụng JavaFX
        launch(args);
    }
}