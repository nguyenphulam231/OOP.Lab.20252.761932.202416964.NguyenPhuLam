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
    public boolean equals(Object o) {
        // Nếu so sánh với chính nó
        if (this == o) return true;
        
        // Nếu đối tượng truyền vào là null hoặc không phải là một kiểu Media
        if (o == null || !(o instanceof Media)) return false;
        
        // Ép kiểu Object về Media để so sánh thuộc tính
        Media media = (Media) o;
        
        // Kiểm tra xem title có null không trước khi so sánh bằng hàm equals của String
        if (this.title == null || media.getTitle() == null) return false;
        
        return this.title.equalsIgnoreCase(media.getTitle());
    }
}