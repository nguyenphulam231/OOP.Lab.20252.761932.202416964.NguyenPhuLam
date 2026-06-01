package hust.soict.hedspi.swing;

import java.awt.*;
import java.awt.event.*;

public class AWTAccumulator extends Frame {
    private TextField tfInput;
    private TextField tfOutput;
    private int sum = 0; // Biến tích lũy tổng

    // Constructor để thiết lập GUI
    public AWTAccumulator() {
        // Thiết lập Layout dạng lưới: 2 hàng, 2 cột
        setLayout(new GridLayout(2, 2));

        // Hàng 1: Label và TextField để nhập dữ liệu
        add(new Label("Enter an Integer: "));
        tfInput = new TextField(10);
        add(tfInput);

        // Đăng ký sự kiện khi nhấn Enter trên tfInput
        tfInput.addActionListener(new TFInputListener());

        // Hàng 2: Label và TextField để hiển thị tổng số
        add(new Label("The Accumulated Sum is: "));
        tfOutput = new TextField(10);
        tfOutput.setEditable(false); // Không cho người dùng sửa ô này
        add(tfOutput);

        setTitle("AWT Accumulator");
        setSize(350, 120);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AWTAccumulator();
    }

    // Lớp xử lý sự kiện (Inner Class)
    private class TFInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Lấy chuỗi nhập vào, chuyển sang kiểu int
            int numberIn = Integer.parseInt(tfInput.getText());
            sum += numberIn;      // Cộng dồn vào biến sum
            tfInput.setText("");  // Xóa trống ô nhập liệu
            tfOutput.setText(sum + ""); // Hiển thị tổng mới lên ô Output
        }
    }
}