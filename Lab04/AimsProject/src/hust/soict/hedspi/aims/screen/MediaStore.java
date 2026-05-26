package hust.soict.hedspi.aims.screen;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart; // Khai báo thêm thuộc tính giỏ hàng để xử lý nút "Add to cart"

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart; // Nhận thực thể cart từ StoreScreen truyền vào
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Tạo nút "Add to cart" và gắn bộ lắng nghe sự kiện
        JButton addToCartButton = new JButton("Add to cart");
        addToCartButton.addActionListener(new ButtonListener());
        container.add(addToCartButton);

        // Nếu sản phẩm chơi được thì tạo nút "Play" và gắn bộ lắng nghe sự kiện
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

    // Lớp nội bộ xử lý sự kiện click chuột cho các nút bấm
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonText = e.getActionCommand();

            if (buttonText.equals("Add to cart")) {
                // Thực hiện thêm sản phẩm vào giỏ hàng chung
                cart.addMedia(media);
                // In ra console để kiểm tra trực quan số lượng phần tử hiện tại
                System.out.println("[GUI Message] Added \"" + media.getTitle() + "\" to cart.");
                System.out.println("Current items in cart: " + cart.getItemsOrdered().size());
                
            } else if (buttonText.equals("Play")) {
                // Tạo một cửa sổ hộp thoại JDialog để mô phỏng tính năng Play
                JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(MediaStore.this), "Playing Media", true);
                dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
                
                // Lấy chuỗi thông tin khi play (bạn có thể điều chỉnh lại chuỗi tùy thuộc vào hàm play() cũ)
                JLabel label = new JLabel("Playing: " + media.getTitle() + " (Simulating...)");
                label.setFont(new Font("Arial", Font.PLAIN, 14));
                
                dialog.add(label);
                dialog.setSize(300, 150);
                dialog.setLocationRelativeTo(MediaStore.this); // Hiển thị ở chính giữa ô sản phẩm
                dialog.setVisible(true);
            }
        }
    }
}