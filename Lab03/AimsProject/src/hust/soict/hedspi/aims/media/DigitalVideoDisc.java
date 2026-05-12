package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Media { // Kế thừa trực tiếp Media 

    // Chỉ giữ lại các thuộc tính riêng của DVD mà Media không có 
    private String director;
    private int length;

    // Các Getter và Setter cho director và length
    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    // Các Constructor gọi super() để khởi tạo thuộc tính ở lớp cha Media
    public DigitalVideoDisc(String title) {
        super(); // Gọi constructor của Media
        this.setTitle(title);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super();
        this.setTitle(title);
        this.setCategory(category);
        this.setCost(cost);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super();
        this.setTitle(title);
        this.setCategory(category);
        this.director = director;
        this.setCost(cost);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super();
        this.setTitle(title);
        this.setCategory(category);
        this.director = director;
        this.length = length;
        this.setCost(cost);
    }

    @Override
    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " 
                + getDirector() + " - " + getLength() + ": " + getCost() + " $";
    }

    public boolean isMatch(String title) {
        return this.getTitle().toLowerCase().contains(title.toLowerCase());
    }
}