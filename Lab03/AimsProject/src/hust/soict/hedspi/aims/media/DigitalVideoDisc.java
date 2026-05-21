package hust.soict.hedspi.aims.media;

/**
 * Lớp DigitalVideoDisc hiện tại kế thừa từ Disc (theo Mục 5.1).
 * Disc đã kế thừa Media, nên DVD vẫn có đủ id, title, category, cost.
 */
public class DigitalVideoDisc extends Disc implements Playable {

    // 1. Không khai báo lại director và length vì đã có ở lớp Disc.
    // 2. Không khai báo lại id, title, category, cost vì đã có ở lớp Media.

    // 3. Các Constructor: Sử dụng super() để đẩy dữ liệu lên lớp cha (Disc)
    
    public DigitalVideoDisc(String title) {
        super(title);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(title, category, director, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, director, length, cost);
    }

    // Ghi đè phương thức toString để hiển thị thông tin chi tiết của DVD
    @Override
    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " 
                + getDirector() + " - " + getLength() + ": " + getCost() + " $";
    }

    // Phương thức kiểm tra tiêu đề khớp (phục vụ việc tìm kiếm)
    public boolean isMatch(String title) {
        if (this.getTitle() == null) return false;
        return this.getTitle().toLowerCase().contains(title.toLowerCase());
    }
    
    @Override
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }
}