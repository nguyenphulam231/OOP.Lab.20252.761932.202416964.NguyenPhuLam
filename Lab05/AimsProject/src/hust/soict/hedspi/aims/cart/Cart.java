package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.exception.CartFullException;         // ĐÃ THÊM
import hust.soict.hedspi.aims.exception.DuplicatedItemException;   // ĐÃ THÊM
import hust.soict.hedspi.aims.exception.NonExistingItemException; // ĐÃ THÊM
import javafx.collections.FXCollections;
import javafx.collections.ObservableList; 

public class Cart {
    
    // Giới hạn số lượng sản phẩm tối đa của giỏ hàng theo đặc tả bài toán
    private static final int MAX_NUMBERS_ORDERED = 20;
    
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public Cart() {
    }

    // CẬP NHẬT: Thêm throws để đẩy trách nhiệm xử lý lỗi lên tầng giao diện
    public void addMedia(Media media) throws CartFullException, DuplicatedItemException {
        if (media == null) {
            System.out.println("Cannot add a null item to the cart!");
            return;
        }
        
        // 1. Kiểm tra nếu giỏ hàng đã đầy vượt ngưỡng
        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            throw new CartFullException("ERROR: The cart is full! Cannot add \"" + media.getTitle() + "\" (Maximum capacity: " + MAX_NUMBERS_ORDERED + ").");
        }
        
        // 2. Kiểm tra nếu sản phẩm bị trùng lặp tiêu đề dựa trên hàm equals() ở mục 15
        if (itemsOrdered.contains(media)) {
            throw new DuplicatedItemException("ERROR: The media \"" + media.getTitle() + "\" already exists in the cart! Cannot duplicate.");
        } 
        
        // Nếu vượt qua hết các chốt chặn, thực hiện thêm sản phẩm
        itemsOrdered.add(media);
        System.out.println("The media \"" + media.getTitle() + "\" has been added to the cart.");
    }

    // CẬP NHẬT: Thêm throws báo lỗi nếu sản phẩm muốn xóa không tìm thấy
    public void removeMedia(Media media) throws NonExistingItemException {
        if (media == null) {
            System.out.println("Invalid item to remove!");
            return;
        }

        // 3. Kiểm tra xem sản phẩm thực sự có trong giỏ hàng hay không
        if (!itemsOrdered.contains(media)) {
            throw new NonExistingItemException("ERROR: The media \"" + media.getTitle() + "\" does not exist in the cart!");
        }

        // Thực hiện xóa sản phẩm sau khi đã chắc chắn tìm thấy
        itemsOrdered.remove(media);
        System.out.println("The media \"" + media.getTitle() + "\" has been removed from the cart.");
    }

    public float totalCost() {
        float total = 0f;
        for (Media media : itemsOrdered) {
            if (media != null) {
                total += media.getCost(); 
            }
        }
        return total;
    }
    
    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        
        int index = 1;
        for (Media media : itemsOrdered) {
            System.out.println(index + ". " + media.toString());
            index++;
        }
        
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }
    
    public void search(int id) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found match by ID: " + media.toString());
                found = true;
                break; 
            }
        }
        if (!found) {
            System.out.println("No match found for ID: " + id);
        }
    }

    public void search(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getTitle() != null && media.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found match by Title: " + media.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No match found for title: " + title);
        }
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public Media searchByTitle(String rTitle) {
        for (Media media : itemsOrdered) {
            if (media.getTitle() != null && media.getTitle().equalsIgnoreCase(rTitle)) {
                return media;
            }
        }
        return null;
    }
    
    public void clearCart() {
        this.itemsOrdered.clear(); 
        System.out.println("The cart has been emptied.");
    }
}