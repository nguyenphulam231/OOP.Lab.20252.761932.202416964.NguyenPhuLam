package hust.soict.hedspi.aims.screen;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.exception.CartFullException;         // ĐÃ THÊM
import hust.soict.hedspi.aims.exception.DuplicatedItemException;   // ĐÃ THÊM

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart; 

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart; 
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addToCartButton = new JButton("Add to cart");
        addToCartButton.addActionListener(new ButtonListener());
        container.add(addToCartButton);

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(new ButtonListener());
            container.add(playButton);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonText = e.getActionCommand();

            if (buttonText.equals("Add to cart")) {
                try {
                    // 1. Thử thêm sản phẩm vào thực thể Giỏ hàng (Có nguy cơ ném lỗi)
                    cart.addMedia(media);
                    
                    // In log ra tab Console để phục vụ việc debug hệ thống
                    System.out.println("[GUI Message] Added \"" + media.getTitle() + "\" to cart.");
                    System.out.println("Current items in cart: " + cart.getItemsOrdered().size());
                    
                    // 2. Hiển thị hộp thoại thông báo thêm thành công nếu không có ngoại lệ nào bị ném ra
                    JOptionPane.showMessageDialog(
                        null, 
                        "Đã thêm sản phẩm \"" + media.getTitle() + "\" vào giỏ hàng thành công!\n" +
                        "Tổng số sản phẩm hiện tại trong giỏ: " + cart.getItemsOrdered().size(), 
                        "Add to Cart Success", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    
                } catch (CartFullException | DuplicatedItemException ex) {
                    // ĐÓN BẮT NGOẠI LỆ: Nếu giỏ đầy hoặc trùng lặp, hiển thị Popup lỗi màu đỏ
                    JOptionPane.showMessageDialog(
                        null, 
                        ex.getMessage(), 
                        "Add to Cart Error", 
                        JOptionPane.ERROR_MESSAGE // Biểu tượng dấu chấm than lỗi màu đỏ
                    );
                    
                    // In log vết lỗi ra Console để theo dõi
                    ex.printStackTrace();
                }
                
            } else if (buttonText.equals("Play")) {
                if (media instanceof Playable) {
                    try {
                        // CẬP NHẬT MỤC 14: Gọi hàm play() thực tế từ lõi Core Logic, hàm này có nguy cơ ném PlayerException
                        ((Playable) media).play();
                        
                        // Nếu đĩa chạy trơn tru (độ dài > 0), hiển thị hộp thoại JDialog mô phỏng như cũ
                        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(MediaStore.this), "Playing Media", true);
                        dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
                        
                        JLabel label = new JLabel("Playing: " + media.getTitle() + " (Simulating...)");
                        label.setFont(new Font("Arial", Font.PLAIN, 14));
                        
                        dialog.add(label);
                        dialog.setSize(300, 150);
                        dialog.setLocationRelativeTo(MediaStore.this);
                        dialog.setVisible(true);
                        
                    } catch (PlayerException ex) {
                        // ĐÓN BẮT NGOẠI LỆ MỤC 14: Bật ngay hộp thoại cảnh báo lỗi Swing với biểu tượng dấu chấm than đỏ
                        JOptionPane.showMessageDialog(
                            null, 
                            ex.getMessage(),               
                            "Illegal Media Length",        
                            JOptionPane.ERROR_MESSAGE      
                        );
                        
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}