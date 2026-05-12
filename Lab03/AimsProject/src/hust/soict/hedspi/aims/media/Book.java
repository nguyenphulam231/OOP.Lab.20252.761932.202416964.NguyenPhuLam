package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book {

    // 1. Khai báo các thuộc tính private [cite: 65, 66, 68, 69, 70, 71, 72]
    private int id;
    private String title;
    private String category;
    private float cost;
    private List<String> authors = new ArrayList<String>();

    // 2. Constructor không tham số [cite: 73]
    public Book() {
    }

    // 3. Getter và Setter cho các thuộc tính (ngoại trừ authors) [cite: 66, 77]
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    // 4. Phương thức thêm tác giả [cite: 80, 81]
    public void addAuthor(String authorName) {
        // Kiểm tra xem tên tác giả đã có trong danh sách chưa
        if (!authors.contains(authorName)) {
            authors.add(authorName);
            System.out.println("Added author: " + authorName);
        } else {
            System.out.println("Author '" + authorName + "' is already in the list.");
        }
    }

    // 5. Phương thức xóa tác giả [cite: 80, 82]
    public void removeAuthor(String authorName) {
        // Kiểm tra xem tên tác giả có tồn tại để xóa không
        if (authors.contains(authorName)) {
            authors.remove(authorName);
            System.out.println("Removed author: " + authorName);
        } else {
            System.out.println("Author '" + authorName + "' not found in the list.");
        }
    }
}