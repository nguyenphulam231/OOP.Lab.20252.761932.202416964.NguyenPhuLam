package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;

import java.util.Collections;
import java.util.Scanner;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Khởi tạo một vài dữ liệu mẫu cho Store để test
        store.addMedia(new DigitalVideoDisc(1, "The Lion King", "Animation", 19.95f, "Roger Allers", 88));
        store.addMedia(new Book(2, "Java Programming", "Education", 29.99f));
        store.addMedia(new CompactDisc(3, "Thriller", "Music", 15.50f, "Quincy Jones", 42, "Michael Jackson"));

        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc bỏ dòng trống

            switch (choice) {
                case 1:
                    handleViewStore();
                    break;
                case 2:
                    handleUpdateStore();
                    break;
                case 3:
                    handleViewCart();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } while (choice != 0);
    }

    // --- Hệ thống in Menu theo đúng mẫu tài liệu ---
    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3: ");
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4: ");
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2: ");
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4-5: ");
    }

    // --- Các hàm xử lý logic nghiệp vụ ---

    private static void handleViewStore() {
        // Hiển thị danh sách hàng trong kho
        store.printStore(); // Giả định bạn đã có hàm in hoặc hiển thị danh sách
        
        int choice;
        do {
            storeMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // See a media's details
                    System.out.print("Enter the title of the media: ");
                    String title = scanner.nextLine();
                    Media foundMedia = store.searchByTitle(title); // Giả định hàm tìm kiếm trong Store
                    if (foundMedia != null) {
                        System.out.println(foundMedia.toString());
                        handleMediaDetails(foundMedia);
                    } else {
                        System.out.println("Media not found.");
                    }
                    break;
                case 2: // Add a media to cart
                    System.out.print("Enter the title of the media: ");
                    String t = scanner.nextLine();
                    Media m = store.searchByTitle(t);
                    if (m != null) {
                        cart.addMedia(m);
                        // In ra số lượng DVD có trong giỏ theo yêu cầu mục 13
                        System.out.println("Items in cart: " + cart.getItemsOrdered().size()); 
                    } else {
                        System.out.println("Media not found.");
                    }
                    break;
                case 3: // Play a media
                    System.out.print("Enter the title of the media: ");
                    String playTitle = scanner.nextLine();
                    Media pm = store.searchByTitle(playTitle);
                    if (pm != null && pm instanceof Playable) {
                        ((Playable) pm).play();
                    } else {
                        System.out.println("This media cannot be played or not found.");
                    }
                    break;
                case 4: // See current cart
                    handleViewCart();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void handleMediaDetails(Media media) {
        int choice;
        do {
            mediaDetailsMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    break;
                case 2:
                    if (media instanceof Playable) {
                        ((Playable) media).play();
                    } else {
                        System.out.println("This media cannot be played.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void handleUpdateStore() {
        System.out.println("1. Add a media to store");
        System.out.println("2. Remove a media from store");
        System.out.print("Choose option: ");
        int opt = scanner.nextInt();
        scanner.nextLine();
        if (opt == 1) {
            // Viết logic thêm nhanh hoặc nhập tay tùy ý bạn
            System.out.println("Feature developing...");
        } else if (opt == 2) {
            System.out.print("Enter title to remove: ");
            String title = scanner.nextLine();
            Media m = store.searchByTitle(title);
            if (m != null) store.removeMedia(m);
        }
    }

    private static void handleViewCart() {
        cart.print(); 
        int choice;
        do {
            cartMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // Filter
                    System.out.println("1. Filter by ID / 2. Filter by Title");
                    int fOpt = scanner.nextInt();
                    scanner.nextLine();
                    break;
                case 2: // Sort (Áp dụng bộ Comparator ở Mục 12)
                    System.out.println("1. Sort by Title-Cost / 2. Sort by Cost-Title");
                    int sOpt = scanner.nextInt();
                    scanner.nextLine();
                    if (sOpt == 1) {
                        Collections.sort(cart.getItemsOrdered(), Media.COMPARE_BY_TITLE_COST);
                    } else if (sOpt == 2) {
                        Collections.sort(cart.getItemsOrdered(), Media.COMPARE_BY_COST_TITLE);
                    }
                    cart.print();
                    break;
                case 3: // Remove
                    System.out.print("Enter title to remove: ");
                    String rTitle = scanner.nextLine();
                    Media rm = cart.searchByTitle(rTitle);
                    if (rm != null) cart.removeMedia(rm);
                    break;
                case 4: // Play
                    System.out.print("Enter title to play: ");
                    String pTitle = scanner.nextLine();
                    Media pm = cart.searchByTitle(pTitle);
                    if (pm != null && pm instanceof Playable) {
                        ((Playable) pm).play();
                    }
                    break;
                case 5: // Place order
                    System.out.println("An order has been successfully created!");
                    cart.clearCart(); 
                    choice = 0; // Thoát về menu chính
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}