package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.screen.StoreScreen;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.exception.CartFullException;         // ĐÃ THÊM
import hust.soict.hedspi.aims.exception.DuplicatedItemException;   // ĐÃ THÊM
import hust.soict.hedspi.aims.exception.NonExistingItemException; // ĐÃ THÊM

import java.util.Collections;
import java.util.Scanner;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Thêm dữ liệu mẫu vào Store (Nếu Store chưa ném exception thì giữ nguyên)
        store.addMedia(new DigitalVideoDisc(1, "The Lion King", "Animation", 19.95f, "Roger Allers", -88));
        store.addMedia(new Book(2, "Java Programming", "Education", 29.99f));
        store.addMedia(new CompactDisc(3, "Thriller", "Music", 15.50f, "Quincy Jones", 42, "Michael Jackson"));

        store.addMedia(new DigitalVideoDisc(4, "The Matrix", "Action", 24.95f, "Wachowskis", 136));
        store.addMedia(new Book(5, "Design Patterns", "Software Engineering", 59.99f));
        store.addMedia(new CompactDisc(6, "Abbey Road", "Rock", 18.50f, "The Beatles", 45, "George Martin"));
        store.addMedia(new DigitalVideoDisc(7, "Inception", "Sci-Fi", 29.95f, "Christopher Nolan", 143));
        store.addMedia(new CompactDisc(6, "DVD2", "abc", 18.50f, "The G", 45, "ABC"));
        store.addMedia(new DigitalVideoDisc(1, "Ranger", "MidsizeTruck", 29.95f, "Nguyen Phu Lam", 190));

        System.out.println("--- Launching AIMS Store Graphic User Interface (GUI) ---");
        new StoreScreen(store, cart);

        /* Tạm thời đóng menu dòng lệnh (Console) lại để tập trung tương tác trên giao diện đồ họa GUI.
         * Khi nào bạn muốn quay lại dùng Console, chỉ cần xóa cặp dấu cmt bọc khối này đi là được.
         *
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
        */
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
        store.printStore(); 
        
        int choice;
        do {
            storeMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: 
                    System.out.print("Enter the title of the media: ");
                    String title = scanner.nextLine();
                    Media foundMedia = store.searchByTitle(title); 
                    if (foundMedia != null) {
                        System.out.println(foundMedia.toString());
                        handleMediaDetails(foundMedia);
                    } else {
                        System.out.println("Media not found.");
                    }
                    break;
                case 2: 
                    System.out.print("Enter the title of the media: ");
                    String t = scanner.nextLine();
                    Media m = store.searchByTitle(t);
                    if (m != null) {
                        try {
                            // CẬP NHẬT: Bọc bẫy lỗi khi gọi addMedia ở giao diện Console
                            cart.addMedia(m);
                            System.out.println("Items in cart: " + cart.getItemsOrdered().size()); 
                        } catch (CartFullException | DuplicatedItemException e) {
                            System.err.println("Cannot add to cart: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Media not found.");
                    }
                    break;
                case 3: // Play a media
                    System.out.print("Enter the title of the media: ");
                    String playTitle = scanner.nextLine();
                    Media pm = store.searchByTitle(playTitle);
                    if (pm != null && pm instanceof Playable) {
                        try {
                            ((Playable) pm).play();
                        } catch (PlayerException e) {
                            System.err.println("Playback failed: " + e.getMessage());
                        }
                    } else {
                        System.out.println("This media cannot be played or not found.");
                    }
                    break;
                case 4: 
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
                    try {
                        // CẬP NHẬT: Bọc bẫy lỗi khi gọi addMedia tại menu xem chi tiết
                        cart.addMedia(media);
                    } catch (CartFullException | DuplicatedItemException e) {
                        System.err.println("Cannot add to cart: " + e.getMessage());
                    }
                    break;
                case 2:
                    if (media instanceof Playable) {
                        try {
                            ((Playable) media).play();
                        } catch (PlayerException e) {
                            System.err.println("Playback failed: " + e.getMessage());
                        }
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
                case 1: 
                    System.out.println("1. Filter by ID / 2. Filter by Title");
                    int fOpt = scanner.nextInt();
                    scanner.nextLine();
                    break;
                case 2: 
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
                case 3: 
                    System.out.print("Enter title to remove: ");
                    String rTitle = scanner.nextLine();
                    Media rm = cart.searchByTitle(rTitle);
                    if (rm != null) {
                        try {
                            // CẬP NHẬT: Bọc bẫy lỗi khi gọi removeMedia ở giao diện Console
                            cart.removeMedia(rm);
                        } catch (NonExistingItemException e) {
                            System.err.println("Cannot remove from cart: " + e.getMessage());
                        }
                    }
                    break;
                case 4: // Play
                    System.out.print("Enter title to play: ");
                    String pTitle = scanner.nextLine();
                    Media pm = cart.searchByTitle(pTitle);
                    if (pm != null && pm instanceof Playable) {
                        try {
                            ((Playable) pm).play();
                        } catch (PlayerException e) {
                            System.err.println("Playback failed: " + e.getMessage());
                        }
                    } else {
                        System.out.println("This media cannot be played or not found in cart.");
                    }
                    break;
                case 5: 
                    System.out.println("An order has been successfully created!");
                    cart.clearCart(); 
                    choice = 0; 
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}