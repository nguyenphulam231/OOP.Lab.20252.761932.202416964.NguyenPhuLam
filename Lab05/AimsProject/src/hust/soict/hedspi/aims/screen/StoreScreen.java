package hust.soict.hedspi.aims.screen; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.cart.Cart; 
import hust.soict.hedspi.aims.media.Media;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart; 

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart; 
        
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        // Ghép phần Thanh menu + Tiêu đề AIMS ở phía Bắc
        cp.add(createNorth(), BorderLayout.NORTH);
        // Ghép lưới danh sách sản phẩm ở vùng Trung tâm
        cp.add(createCenter(), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null); // Hiển thị cửa sổ ở chính giữa màn hình
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

    // Tạo thanh Menu Options thả xuống và liên kết sự kiện điều hướng màn hình
    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem mItemAddBook = new JMenuItem("Add Book");
        JMenuItem mItemAddCD = new JMenuItem("Add CD");
        JMenuItem mItemAddDVD = new JMenuItem("Add DVD");
        
        smUpdateStore.add(mItemAddBook);
        smUpdateStore.add(mItemAddCD);
        smUpdateStore.add(mItemAddDVD);

        menu.add(smUpdateStore);
        
        JMenuItem mItemViewStore = new JMenuItem("View store");
        JMenuItem mItemViewCart = new JMenuItem("View cart");
        menu.add(mItemViewStore);
        menu.add(mItemViewCart);

        // BẮT SỰ KIỆN MỤC 11: Khi chọn tính năng thêm sản phẩm từ thanh Menu
        mItemAddBook.addActionListener(e -> new AddBookToStoreScreen(store));
        mItemAddCD.addActionListener(e -> new AddCompactDiscToStoreScreen(store));
        mItemAddDVD.addActionListener(e -> new AddDigitalVideoDiscToStoreScreen(store));

        // BẮT SỰ KIỆN MỤC 11: Khi chọn View cart từ thanh Menu -> Bật màn hình JavaFX CartScreen
        mItemViewCart.addActionListener(e -> new CartScreen(cart));

        // Nhấp vào View Store thì chỉ cần làm mới/hiển thị lại chính màn hình này (hoặc ẩn đi bật lại)
        mItemViewStore.addActionListener(e -> {
            this.setVisible(false);
            new StoreScreen(store, cart);
            this.dispose();
        });

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

        // BẮT SỰ KIỆN MỤC 11: Khi người dùng bấm nút "View cart" to màu xám ở Header
        cartButton.addActionListener(e -> new CartScreen(cart));

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
            MediaStore cell = new MediaStore(mediaInStore.get(i), this.cart);
            center.add(cell);
        }

        return center;
    }
}