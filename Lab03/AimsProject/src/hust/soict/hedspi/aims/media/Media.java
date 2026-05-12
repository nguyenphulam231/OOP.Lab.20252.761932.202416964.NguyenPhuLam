package hust.soict.hedspi.aims.media;

public abstract class Media {
    // Các thuộc tính chung được yêu cầu 
    private int id;
    private String title;
    private String category;
    private float cost;

    // Constructor mặc định
    public Media() {
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
}