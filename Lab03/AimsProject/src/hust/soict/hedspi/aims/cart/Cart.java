package hust.soict.hedspi.aims.cart;

import java.util.ArrayList; // Import ArrayList theo yêu cầu [cite: 192]
import hust.soict.hedspi.aims.media.Media; // Import lớp cha Media

public class Cart {
    
    // Thay thế mảng cũ bằng ArrayList để quản lý mọi loại Media [cite: 184, 190]
    // Biến qtyOrdered không còn cần thiết vì ArrayList tự quản lý kích thước thông qua .size() [cite: 185]
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();

    // Constructor trống
    public Cart() {
    }

    // Thêm một mặt hàng (Media) bất kỳ vào giỏ hàng 
    public void addMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot add a null item to the cart!");
            return;
        }
        
        // Kiểm tra trùng lặp (Sẽ sử dụng hàm equals() được override ở mục 10) [cite: 211, 217]
        if (itemsOrdered.contains(media)) {
            System.out.println("The media \"" + media.getTitle() + "\" is already in the cart.");
        } else {
            itemsOrdered.add(media);
            System.out.println("The media \"" + media.getTitle() + "\" has been added to the cart.");
        }
    }

    // Xóa một mặt hàng (Media) khỏi giỏ hàng 
    public void removeMedia(Media media) {
        if (media == null) {
            System.out.println("Invalid item to remove!");
            return;
        }
        
        // Hàm remove() của ArrayList tự động tìm và xóa, trả về true nếu xóa thành công [cite: 217]
        if (itemsOrdered.remove(media)) {
            System.out.println("The media \"" + media.getTitle() + "\" has been removed from the cart.");
        } else {
            System.out.println("The media \"" + media.getTitle() + "\" was not found in the cart.");
        }
    }

    // Tính tổng tiền bằng cách duyệt ArrayList [cite: 198]
    public float totalCost() {
        float total = 0f;
        for (Media media : itemsOrdered) {
            if (media != null) {
                total += media.getCost(); // Gọi phương thức getCost() kế thừa từ lớp cha Media [cite: 89, 120]
            }
        }
        return total;
    }
    
    // Phương thức in thông tin giỏ hàng theo danh sách mới [cite: 49, 230]
    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        
        int index = 1;
        for (Media media : itemsOrdered) {
            // Sử dụng tính đa hình của toString(): tùy loại Media (CD/DVD/Book) mà in định dạng riêng [cite: 45, 228]
            System.out.println(index + ". " + media.toString());
            index++;
        }
        
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }
    
    // Tìm kiếm mặt hàng trong giỏ bằng ID (Yêu cầu lọc/tìm kiếm ở Menu Cart) [cite: 335]
    public void search(int id) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getId() == id) { // Lấy ID từ lớp cha Media [cite: 87, 89]
                System.out.println("Found match by ID: " + media.toString());
                found = true;
                break; // Vì ID là duy nhất nên tìm thấy là dừng vòng lặp
            }
        }
        if (!found) {
            System.out.println("No match found for ID: " + id);
        }
    }

    // Tìm kiếm mặt hàng trong giỏ bằng Title (Yêu cầu lọc/tìm kiếm ở Menu Cart) [cite: 335]
    public void search(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            // Sử dụng phương thức so khớp tiêu đề (giả định bạn đã chuyển phương thức isMatch() sang lớp Media)
            // Hoặc so sánh trực tiếp bằng: media.getTitle().equalsIgnoreCase(title)
            if (media.getTitle() != null && media.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found match by Title: " + media.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No match found for title: " + title);
        }
    }
    
    // Getter để hỗ trợ việc sắp xếp hoặc thao tác danh sách từ bên ngoài nếu cần ở các mục sau [cite: 246, 336]
    public ArrayList<Media> getItemsOrdered() {
        return itemsOrdered;
    }
}