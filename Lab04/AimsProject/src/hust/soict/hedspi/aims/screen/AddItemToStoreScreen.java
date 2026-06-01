package hust.soict.hedspi.aims.screen;

import javax.swing.*;
import java.awt.*;
import hust.soict.hedspi.aims.store.Store;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected JTextField tfTitle, tfCategory, tfCost;
    protected JButton btnAdd;

    public AddItemToStoreScreen(Store store, String title) {
        this.store = store;
        
        // Thiết kế khung cửa sổ cơ bản
        setTitle(title);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        // Panel trung tâm chứa các ô nhập liệu chung
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 2, 5, 5));

        centerPanel.add(new JLabel("Title:"));
        tfTitle = new JTextField();
        centerPanel.add(tfTitle);

        centerPanel.add(new JLabel("Category:"));
        tfCategory = new JTextField();
        centerPanel.add(tfCategory);

        centerPanel.add(new JLabel("Cost:"));
        tfCost = new JTextField();
        centerPanel.add(tfCost);

        // Gọi hàm móc (hook method) để các lớp con tự bổ sung trường dữ liệu riêng của chúng
        addCustomFields(centerPanel);

        cp.add(centerPanel, BorderLayout.CENTER);

        // Nút bấm xác nhận thêm sản phẩm ở dưới cùng
        btnAdd = new JButton("Add Item");
        cp.add(btnAdd, BorderLayout.SOUTH);
    }

    // Phương thức trừu tượng buộc các lớp con (Book, DVD, CD) phải tự định nghĩa ô nhập liệu riêng
    protected abstract void addCustomFields(JPanel panel);
}