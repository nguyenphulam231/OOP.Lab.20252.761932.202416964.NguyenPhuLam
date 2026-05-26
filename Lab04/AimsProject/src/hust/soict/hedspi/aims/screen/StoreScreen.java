package hust.soict.hedspi.aims.screen; // Package chuẩn HEDSPI

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import hust.soict.hedspi.aims.store.Store; // Import lớp Store quản lý kho hàng của bạn
import hust.soict.hedspi.aims.cart.Cart;   // Import thêm lớp Cart để quản lý giỏ hàng
import hust.soict.hedspi.aims.media.Media;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart; // ĐÃ THÊM: Thuộc tính lưu trữ giỏ hàng toàn cục của ứng dụng

    // CẬP NHẬT: Constructor nhận thêm đối tượng Cart
    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart; // Lưu thực thể cart được truyền vào
        
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        // Ghép phần Thanh menu + Tiêu đề AIMS ở phía Bắc
        cp.add(createNorth(), BorderLayout.NORTH);
        // Ghép lưới danh sách sản phẩm ở vùng Trung tâm
        cp.add(createCenter(), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Store");
        setSize(1024, 768);
        setVisible(true);
    }

    // Tạo khu vực phía Bắc (Menu Bar + Header)
    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    // Tạo thanh Menu Options thả xuống
    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        smUpdateStore.add(new JMenuItem("Add Book"));
        smUpdateStore.add(new JMenuItem("Add CD"));
        smUpdateStore.add(new JMenuItem("Add DVD"));

        menu.add(smUpdateStore);
        menu.add(new JMenuItem("View store"));
        menu.add(new JMenuItem("View cart"));

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    // Tạo phần Header (Chữ AIMS màu xanh và nút View cart)
    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cartButton = new JButton("View cart");
        cartButton.setPreferredSize(new Dimension(100, 50));
        cartButton.setMaximumSize(new Dimension(100, 50));

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cartButton);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    // Tạo lưới ma trận 3x3 chứa tối đa 9 sản phẩm từ kho hàng
    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        // Lấy danh sách các mặt hàng đang có trong Store
        ArrayList<Media> mediaInStore = store.getItemsInStore();
        
        // Vòng lặp hiển thị tối đa 9 item lên màn hình
        int limit = Math.min(9, mediaInStore.size());
        for (int i = 0; i < limit; i++) {
            // CẬP NHẬT: Truyền thêm biến `this.cart` vào đây để MediaStore nhận diện được giỏ hàng
            MediaStore cell = new MediaStore(mediaInStore.get(i), this.cart);
            center.add(cell);
        }

        return center;
    }
}