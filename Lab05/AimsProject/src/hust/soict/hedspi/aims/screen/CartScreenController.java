package hust.soict.hedspi.aims.screen;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType; 
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.exception.PlayerException; // Import lớp ngoại lệ từ Mục 13
import hust.soict.hedspi.aims.exception.NonExistingItemException; // ĐÃ THÊM: Import lớp ngoại lệ xóa sản phẩm

public class CartScreenController {

    private Cart cart;
    private FilteredList<Media> filteredList;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private Label lblTotalCost;

    public CartScreenController(Cart cart) {
        super();
        this.cart = cart;
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));
        
        filteredList = new FilteredList<>(this.cart.getItemsOrdered(), p -> true);
        tblMedia.setItems(filteredList);

        updateTotalCost();

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Media>() {
                @Override
                public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                    if (newValue != null) {
                        updateButtonBar(newValue);
                    }
                }
            }
        );

        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                showFilteredMedia(newValue);
            }
        });
    }

    private void updateTotalCost() {
        if (cart != null) {
            float total = cart.totalCost();
            lblTotalCost.setText(String.format("%.2f $", total));
        }
    }

    private void showFilteredMedia(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            filteredList.setPredicate(media -> true);
            return;
        }

        String searchKeyword = keyword.trim().toLowerCase();

        filteredList.setPredicate(media -> {
            if (radioBtnFilterId.isSelected()) {
                String idStr = String.valueOf(media.getId());
                return idStr.contains(searchKeyword);
            } else if (radioBtnFilterTitle.isSelected()) {
                if (media.getTitle() != null) {
                    return media.getTitle().toLowerCase().contains(searchKeyword);
                }
            }
            return false;
        });
    }

    private void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
        }
    }

    // ĐÃ CẬP NHẬT: Tích hợp try-catch bẫy lỗi NonExistingItemException từ core Cart ném ra
    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            // Trích xuất và lưu lại thông tin trước khi thực thể bị xóa khỏi danh sách
            int id = media.getId();
            String title = media.getTitle();
            String category = media.getCategory();
            float cost = media.getCost();

            try {
                // Gọi hàm removeMedia có nguy cơ ném Checked Exception
                cart.removeMedia(media);
                
                // Nếu xóa thành công từ lõi, cập nhật giao diện hiển thị tiền và bật Popup thông tin
                updateTotalCost(); 

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Media Removed");
                alert.setHeaderText("Xóa sản phẩm thành công!");
                
                String message = String.format(
                    "Đã xóa sản phẩm sau khỏi giỏ hàng:\n\n" +
                    "• ID: %d\n" +
                    "• Tiêu đề (Title): %s\n" +
                    "• Thể loại (Category): %s\n" +
                    "• Giá tiền (Cost): %.2f $",
                    id, title, category, cost
                );
                
                alert.setContentText(message);
                alert.showAndWait();

            } catch (NonExistingItemException e) {
                // ĐÓN BẮT NGOẠI LỆ: Bật hộp thoại báo lỗi nếu sản phẩm không tồn tại để xóa
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Removal Error");
                alert.setHeaderText("Lỗi thực thi xóa sản phẩm!");
                alert.setContentText(e.getMessage()); // Hiển thị chuỗi thông điệp lỗi chi tiết
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Selection Warning");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn một sản phẩm trong danh sách trước khi xóa!");
            alert.showAndWait();
        }
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
                
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Playing Media");
                alert.setHeaderText("Playing: " + media.getTitle());
                alert.setContentText("Hệ thống đang phát sản phẩm: " + media.getTitle() + " thành công!");
                alert.showAndWait();
                
            } catch (PlayerException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Illegal Media Length"); 
                alert.setHeaderText("Playback Error!");
                alert.setContentText(e.getMessage());   
                alert.showAndWait();
                
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Order Warning");
            alert.setHeaderText(null);
            alert.setContentText("Giỏ hàng của bạn đang trống! Vui lòng chọn sản phẩm trước.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Order Confirmation");
        alert.setHeaderText("Đặt hàng thành công!");
        alert.setContentText("Cảm ơn bạn đã mua sắm tại AIMS. Toàn bộ giỏ hàng sẽ được làm trống.");
        alert.showAndWait();

        cart.clearCart(); 
        updateTotalCost(); 
    }
}