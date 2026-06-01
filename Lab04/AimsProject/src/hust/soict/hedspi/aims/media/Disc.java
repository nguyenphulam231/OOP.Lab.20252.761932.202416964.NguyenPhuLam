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

    public Disc() {
        super();
    }

    public Disc(int id, String title) {
        super(id, title);
    }

    public Disc(int id, String title, String category, float cost) {
        super(id, title, category, cost); 
    }

    public Disc(int id, String title, String director, int length, float cost) {
        super(id, title, null, cost); 
        this.director = director;     
        this.length = length;        
    }
    
    public Disc(int id, String title, String category, float cost, String director, int length) {
        super(id, title, category, cost); 
        this.director = director;         
        this.length = length;           
    }
    
 // Thêm constructor này vào trong lớp Disc.java
    public Disc(String title, String category, float cost) {
        super(title, category, cost); // Gọi lên constructor của Media mà bạn đã sửa ở bước trước
    }
    
}