package hust.soict.hedspi.aims.screen;

import java.io.IOException;
import javax.swing.JFrame;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import hust.soict.hedspi.aims.cart.Cart; // Nhập class Cart từ package quản lý của bạn

public class CartScreen extends JFrame {
    
    private Cart cart;

    public CartScreen(Cart cart) {
        super();
        this.cart = cart;
        
        // Bước 1: Khởi tạo cầu nối JFXPanel và add nó trực tiếp vào JFrame của Swing
        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);
        
        // Cấu hình các thuộc tính cơ bản cho cửa sổ Swing
        this.setTitle("Cart");
        this.setSize(1024, 768); // Đặt kích thước trùng với cấu trúc file FXML của bạn
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        // Bước 2: Chạy luồng JavaFX Thread an toàn bằng Platform.runLater
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Khởi tạo bộ nạp FXMLLoader chuyên dụng
                    FXMLLoader loader = new FXMLLoader(getClass()
                            .getResource("/hust/soict/hedspi/aims/screen/cart.fxml"));
                    
                    // Khởi tạo Controller cho giỏ hàng và truyền dữ liệu cart vào
                    CartScreenController controller = new CartScreenController(cart);
                    loader.setController(controller);
                    
                    // Nạp sơ đồ cây giao diện (Root Node) từ file FXML
                    Parent root = loader.load();
                    
                    // Bước 3: Tạo Scene JavaFX và nhúng thẳng vào khối JFXPanel của Swing
                    fxPanel.setScene(new Scene(root));
                    
                } catch (IOException e) {
                    System.err.println("Không thể nạp file fxml! Kiểm tra lại đường dẫn.");
                    e.printStackTrace();
                }
            }
        });
    }
}