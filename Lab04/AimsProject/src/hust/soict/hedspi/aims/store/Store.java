package hust.soict.hedspi.aims.store;

import java.util.ArrayList; // Import thư viện ArrayList theo yêu cầu [cite: 192, 201]
import hust.soict.hedspi.aims.media.Media; // Import lớp cha Media để áp dụng tính Đa hình 

public class Store {
    
    // Thay đổi thuộc tính từ mảng DigitalVideoDisc[] sang ArrayList<Media> 
    // Biến đếm qtyInStore không còn cần thiết vì ArrayList tự quản lý kích thước bằng phương thức .size()
    private ArrayList<Media> itemsInStore = new ArrayList<Media>(); 

    // Constructor của lớp Store
    public Store() {
    }

    // Thay thế addDVD() bằng addMedia() để có thể nhận vào cả Book, CD, DVD 
    public void addMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot add a null item to the store!");
            return;
        }
        
        // Kiểm tra xem mặt hàng này đã có sẵn trong cửa hàng hay chưa
        if (itemsInStore.contains(media)) {
            System.out.println("The media \"" + media.getTitle() + "\" is already in the store.");
        } else {
            itemsInStore.add(media);
            System.out.println("The media \"" + media.getTitle() + "\" has been successfully added to the store.");
        }
    }

    // Thay thế removeDVD() bằng removeMedia() sử dụng các cơ chế của ArrayList 
    public void removeMedia(Media media) {
        if (media == null) {
            System.out.println("Invalid item to remove from the store!");
            return;
        }
        
        // Hàm .remove(Object) của ArrayList sẽ tự động tìm kiếm mặt hàng dựa trên equals() và xóa đi
        if (itemsInStore.remove(media)) {
            System.out.println("The media \"" + media.getTitle() + "\" has been successfully removed from the store.");
        } else {
            System.out.println("The media \"" + media.getTitle() + "\" was not found in the store.");
        }
    }
    
    // Phương thức bổ sung giúp in ra toàn bộ kho hàng (Rất hữu ích cho việc hiển thị menu ở Mục 13) [cite: 295]
    public void printStore() {
        System.out.println("\n====================== STORE INVENTORY ======================");
        if (itemsInStore.isEmpty()) {
            System.out.println("The store is currently empty!");
        } else {
            int index = 1;
            for (Media media : itemsInStore) {
                // Áp dụng tính đa hình của toString() để hiển thị đúng định dạng tùy loại mặt hàng [cite: 45, 228]
                System.out.println(index + ". " + media.toString());
                index++;
            }
        }
        System.out.println("=============================================================");
    }

    // Phương thức bổ sung giúp tìm kiếm một Media trong kho theo Title (Dùng cho chức năng storeMenu) [cite: 309, 326, 328]
    public Media searchByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle() != null && media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null; // Trả về null nếu không có mặt hàng nào trùng khớp tiêu đề
    }

    // Getter lấy danh sách các mặt hàng trong cửa hàng nếu cần thiết
    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
}