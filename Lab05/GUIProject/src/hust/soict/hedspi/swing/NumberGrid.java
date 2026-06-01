package hust.soict.hedspi.swing; // Package chuẩn HEDSPI

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NumberGrid extends JFrame {
    private JButton[] btnNumbers = new JButton[10]; // Mảng chứa 10 nút số từ 0-9
    private JButton btnDelete, btnReset;
    private JTextField tfDisplay;

    public NumberGrid() {
        // 1. Khởi tạo ô hiển thị chữ và cấu hình nhập từ phải qua trái (như máy tính)
        tfDisplay = new JTextField();
        tfDisplay.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        // 2. Khởi tạo JPanel phụ dùng bố cục lưới 4 hàng, 3 cột để chứa các nút bấm
        JPanel panelButtons = new JPanel(new GridLayout(4, 3));
        addButtons(panelButtons); // Gọi hàm add các nút vào panel

        // 3. Lấy Content-Pane và sử dụng BorderLayout để phân chia khu vực chính
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(tfDisplay, BorderLayout.NORTH);     // Ô hiển thị nằm phía BẮC (trên cùng)
        cp.add(panelButtons, BorderLayout.CENTER); // Cụm nút bấm nằm ở TRUNG TÂM

        // 4. Cấu hình thuộc tính cho cửa sổ JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Number Grid");
        setSize(200, 200);
        setVisible(true);
    }

    // Hàm tạo nút và add vào JPanel (Theo hình 7)
    void addButtons(JPanel panelButtons) {
        ButtonListener btnListener = new ButtonListener();
        
        // Vòng lặp tạo các nút từ 1 đến 9 và add vào panel
        for(int i = 1; i <= 9; i++) {
            btnNumbers[i] = new JButton("" + i);
            panelButtons.add(btnNumbers[i]);
            btnNumbers[i].addActionListener(btnListener);
        }

        // Tạo nút DEL (Xóa 1 số)
        btnDelete = new JButton("DEL");
        panelButtons.add(btnDelete);
        btnDelete.addActionListener(btnListener);

        // Tạo nút số 0
        btnNumbers[0] = new JButton("0");
        panelButtons.add(btnNumbers[0]);
        btnNumbers[0].addActionListener(btnListener);

        // Tạo nút C (Xóa hết)
        btnReset = new JButton("C");
        panelButtons.add(btnReset);
        btnReset.addActionListener(btnListener);
    }

    public static void main(String[] args) {
        new NumberGrid();
    }

    // Lớp nội xử lý sự kiện (Bao gồm phần bạn cần tự code ở hình 8)
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String button = e.getActionCommand();
            
            // Trường hợp 1: Nếu bấm vào các nút số từ '0' đến '9' -> Nối thêm số vào màn hình
            if(button.charAt(0) >= '0' && button.charAt(0) <= '9') {
                tfDisplay.setText(tfDisplay.getText() + button);
            } 
            // Trường hợp 2: Nếu bấm nút "DEL" -> Xóa đi ký tự cuối cùng
            else if (button.equals("DEL")) {
                String currentText = tfDisplay.getText();
                if (currentText.length() > 0) {
                    // Cắt bỏ ký tự cuối cùng bằng hàm substring
                    tfDisplay.setText(currentText.substring(0, currentText.length() - 1));
                }
            } 
            // Trường hợp 3: Nếu bấm nút "C" (Clear) -> Xóa sạch màn hình về chuỗi rỗng
            else {
                tfDisplay.setText("");
            }
        }
    }
}