package hust.soict.hedspi.aims.media;

public abstract class Media {
    // Các thuộc tính chung được yêu cầu [cite: 102]
    private int id;
    private String title;
    private String category;
    private float cost;

    // Constructor mặc định [cite: 97]
    public Media() {
    }

    // Getter và Setter (tạo tự động bằng Source -> Generate Getters and Setters) [cite: 103]
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public float getCost() { return cost; }
    public void setCost(float cost) { this.cost = cost; }
}