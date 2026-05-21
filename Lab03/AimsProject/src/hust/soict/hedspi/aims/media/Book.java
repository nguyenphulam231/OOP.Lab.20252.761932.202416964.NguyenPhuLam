package hust.soict.hedspi.aims.media; 

import java.util.ArrayList;
import java.util.List;

public class Book extends Media { // Kế thừa từ Media 

    // Chỉ giữ lại thuộc tính riêng của Book 
    private List<String> authors = new ArrayList<String>();

    // Constructor mặc định 
    public Book() {
        super(); // Gọi constructor của lớp cha Media 
    }
    
    public Book(int id, String title, String category) {
        super(id, title, category); 
    }

    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost); 
    }
    

    // Phương thức thêm tác giả
    public void addAuthor(String authorName) {
        // Kiểm tra trùng lặp trước khi thêm
        if (!authors.contains(authorName)) {
            authors.add(authorName);
            System.out.println("Added author: " + authorName);
        } else {
            System.out.println("Author '" + authorName + "' is already in the list.");
        }
    }

    // Phương thức xóa tác giả
    public void removeAuthor(String authorName) {
        // Kiểm tra tồn tại trước khi xóa
        if (authors.contains(authorName)) {
            authors.remove(authorName);
            System.out.println("Removed author: " + authorName);
        } else {
            System.out.println("Author '" + authorName + "' not found in the list.");
        }
    }
}