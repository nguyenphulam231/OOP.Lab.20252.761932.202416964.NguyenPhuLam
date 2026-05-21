package hust.soict.hedspi.aims.media;

/**
 * Lớp Disc kế thừa từ Media, đại diện cho các loại phương tiện dạng đĩa.
 * Đây là lớp cha của DigitalVideoDisc và CompactDisc.
 */
public class Disc extends Media {

    private String director;
    private int length;

    // Getter cho đạo diễn
    public String getDirector() {
        return director;
    }

    // Getter cho độ dài đĩa
    public int getLength() {
        return length;
    }

    // Constructor mặc định
    public Disc() {
        super();
    }

    // Constructor hỗ trợ khởi tạo nhanh (thường dùng cho các lớp con)
    public Disc(String title) {
        super();
        this.setTitle(title);
    }

    public Disc(String title, String category, float cost) {
        super();
        this.setTitle(title);
        this.setCategory(category);
        this.setCost(cost);
    }

    public Disc(String title, String category, String director, float cost) {
        super();
        this.setTitle(title);
        this.setCategory(category);
        this.setCost(cost);
        this.director = director;
    }

    public Disc(String title, String category, String director, int length, float cost) {
        super();
        this.setTitle(title);
        this.setCategory(category);
        this.setCost(cost);
        this.director = director;
        this.length = length;
    }
}