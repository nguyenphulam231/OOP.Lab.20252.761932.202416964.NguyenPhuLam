package hust.soict.hedspi.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton penRadioButton; // Liên kết với nút Pen trên giao diện

    @FXML
    private RadioButton eraserRadioButton; // Liên kết với nút Eraser trên giao diện

    // Xử lý sự kiện kéo chuột vẽ tranh hoặc tẩy
    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        // Mặc định màu cọ vẽ ban đầu là màu Đen
        Color inkColor = Color.BLACK;
        
        // Nếu người dùng đang chọn nút Eraser, đổi màu mực sang màu Trắng (trùng nền canvas) để tạo hiệu ứng tẩy
        if (eraserRadioButton.isSelected()) {
            inkColor = Color.WHITE;
        }
        
        // Tạo hình tròn nhỏ có bán kính 4px với màu mực đã xác định tại vị trí con trỏ chuột
        Circle newCircle = new Circle(event.getX(), event.getY(), 4, inkColor);
        
        // Vẽ lên màn hình
        drawingAreaPane.getChildren().add(newCircle);
    }

    // Xử lý sự kiện bấm nút Clear để xoá sạch toàn bộ hình vẽ
    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }
}