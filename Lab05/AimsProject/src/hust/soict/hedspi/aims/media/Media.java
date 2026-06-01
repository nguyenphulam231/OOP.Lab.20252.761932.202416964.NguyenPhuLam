package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public abstract class Media {
    // Các thuộc tính chung được yêu cầu 
    private int id;
    private String title;
    private String category;
    private float cost;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
    public Media() {
    }

    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
   
    }
    public Media(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Media(int id, String title, String category) {
        this.id = id;
        this.title = title;
        this.category = category;
    }

    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }
    
    
    // Getter và Setter (tạo tự động bằng Source -> Generate Getters and Setters)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public float getCost() { return cost; }
    public void setCost(float cost) { this.cost = cost; }
    
    @Override
    public boolean equals(Object obj) {
        // 1. Kiểm tra nếu so sánh chính nó với chính nó (Tối ưu hiệu năng)
        if (this == obj) {
            return true;
        }
        
        // 2. Phòng tránh NullPointerException: Nếu đối tượng truyền vào là null thì không bằng nhau
        if (obj == null) {
            return false;
        }
        
        // 3. Phòng tránh ClassCastException: Kiểm tra xem obj có thực sự thuộc lớp Media (hoặc con của nó) không
        // Sử dụng toán tử 'instanceof' theo đúng gợi ý của bài Lab
        if (!(obj instanceof Media)) {
            return false;
        }
        
        // 4. Ép kiểu an toàn sau khi đã qua bước kiểm tra instanceof
        Media other = (Media) obj;
        
        // 5. So sánh thuộc tính 'title' của hai đối tượng
        // Cần kiểm tra kịch bản title của một trong hai bên hoặc cả hai bên bị null để tránh NullPointerException
        if (this.title == null) {
            return other.title == null;
        }
        
        return this.title.equalsIgnoreCase(other.title); 
        // Hoặc dùng this.title.equals(other.title) tùy thuộc bạn muốn phân biệt hoa thường hay không.
        // Thường đối với tựa đề phim/sách, dùng equalsIgnoreCase sẽ giúp hệ thống tìm trùng lặp chính xác hơn.
    }
}