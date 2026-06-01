package hust.soict.hedspi.aims.screen;

import javax.swing.*;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store, "Add DVD to Store");
        
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu người dùng nhập từ các ô TextField của lớp cha và lớp con
                String title = tfTitle.getText();
                String category = tfCategory.getText();
                float cost = Float.parseFloat(tfCost.getText());
                String director = tfDirector.getText();
                int length = 0; // Mặc định độ dài bằng 0 (hoặc có thể bổ sung ô nhập nếu muốn)

                // Tự động sinh một mã id ngẫu nhiên dựa trên thời gian hệ thống hoặc hashCode để khớp với constructor của bạn
                int generatedId = (int) (System.currentTimeMillis() % 100000);

                // Khởi tạo đối tượng DVD bằng constructor khớp 100% với file DigitalVideoDisc của bạn:
                // Cấu trúc sử dụng: DigitalVideoDisc(int id, String title, String category, float cost, String director, int length)
                DigitalVideoDisc dvd = new DigitalVideoDisc(generatedId, title, category, cost, director, length);
                
                // Thêm sản phẩm vừa tạo vào kho hàng
                store.addMedia(dvd);
                
                // Hiển thị thông báo thành công và đóng cửa sổ form nhập liệu
                JOptionPane.showMessageDialog(null, "Thêm DVD vào cửa hàng thành công!");
                dispose(); 
            }
        });
        this.setVisible(true);
    }

    @Override
    protected void addCustomFields(JPanel panel) {
        panel.add(new JLabel("Director:"));
        tfDirector = new JTextField();
        panel.add(tfDirector);
    }
}