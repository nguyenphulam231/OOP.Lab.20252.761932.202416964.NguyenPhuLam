package hust.soict.hedspi.aims.screen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.Book;

public class AddBookToStoreScreen extends JFrame {
    private Store store;
    
    // Khai báo các ô nhập liệu
    private JTextField tfTitle;
    private JTextField tfCategory;
    private JTextField tfAuthors; // Nhập các tác giả cách nhau bằng dấu phẩy
    private JTextField tfCost;
    private JButton btnAdd;

    public AddBookToStoreScreen(Store store) {
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        // Tiêu đề của cửa sổ form
        JLabel lblHeader = new JLabel("ADD BOOK TO STORE", JLabel.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 24));
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cp.add(lblHeader, BorderLayout.NORTH);

        // Khu vực trung tâm chứa các ô Form nhập liệu
        JPanel panelCenter = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelCenter.add(new JLabel("Title:"));
        tfTitle = new JTextField();
        panelCenter.add(tfTitle);

        panelCenter.add(new JLabel("Category:"));
        tfCategory = new JTextField();
        panelCenter.add(tfCategory);

        panelCenter.add(new JLabel("Authors (separated by commas):"));
        tfAuthors = new JTextField();
        panelCenter.add(tfAuthors);

        panelCenter.add(new JLabel("Cost ($):"));
        tfCost = new JTextField();
        panelCenter.add(tfCost);

        cp.add(panelCenter, BorderLayout.CENTER);

        // Nút bấm xác nhận thêm ở phía dưới cùng
        JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAdd = new JButton("Add Book");
        panelSouth.add(btnAdd);
        cp.add(panelSouth, BorderLayout.SOUTH);

        // Xử lý sự kiện click nút Add Book
        btnAdd.addActionListener(e -> {
            try {
                String title = tfTitle.getText().trim();
                String category = tfCategory.getText().trim();
                String authorsRaw = tfAuthors.getText().trim();
                String costStr = tfCost.getText().trim();

                // Kiểm tra dữ liệu trống
                if (title.isEmpty() || category.isEmpty() || costStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ các trường bắt buộc!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                float cost = Float.parseFloat(costStr);
                if (cost < 0) {
                    JOptionPane.showMessageDialog(this, "Giá tiền không thể là số âm!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Khởi tạo đối tượng Book mới
                Book book = new Book(title, category, cost);

                // Xử lý chuỗi tác giả được phân tách bằng dấu phẩy ","
                if (!authorsRaw.isEmpty()) {
                    // Cắt chuỗi và loại bỏ khoảng trắng thừa của từng tác giả
                    String[] authorsArray = authorsRaw.split(",");
                    for (String author : authorsArray) {
                        if (!author.trim().isEmpty()) {
                            book.addAuthor(author.trim());
                        }
                    }
                }

                // Thêm vào kho hàng Store công khai
                store.addMedia(book);

                // Hiện Popup báo thành công trực quan
                JOptionPane.showMessageDialog(this, "Đã thêm cuốn sách \"" + title + "\" vào kho hàng thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                // Đóng cửa sổ nhập liệu sau khi thêm xong để quay về màn hình chính
                this.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Định dạng giá tiền không hợp lệ! Vui lòng nhập số thực (VD: 15.5).", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setTitle("Add Book");
        setSize(500, 350);
        setLocationRelativeTo(null); // Hiện ra giữa màn hình
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Chỉ đóng form này chứ không tắt toàn bộ App
        setVisible(true);
    }
}