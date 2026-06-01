package hust.soict.hedspi.aims.media;
import hust.soict.hedspi.aims.exception.PlayerException; // Thêm dòng này để import class lỗi vào
/**
 * Lớp DigitalVideoDisc hiện tại kế thừa từ Disc (theo Mục 5.1).
 * Disc đã kế thừa Media, nên DVD vẫn có đủ id, title, category, cost.
 */
public class DigitalVideoDisc extends Disc implements Playable {

    // 1. Không khai báo lại director và length vì đã có ở lớp Disc.
    // 2. Không khai báo lại id, title, category, cost vì đã có ở lớp Media.

    
	public DigitalVideoDisc(int id, String title) {
        super(id, title); // Gọi constructor Disc(int id, String title)
    }

    public DigitalVideoDisc(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }


    public DigitalVideoDisc(int id, String title, String director, int length, float cost) {
        super(id, title, director, length, cost); 
    }

    public DigitalVideoDisc(int id, String title, String category, float cost, String director, int length) {
        super(id, title, category, cost, director, length); 
    }

    // Ghi đè phương thức toString để hiển thị thông tin chi tiết của DVD
    @Override
    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " 
                + getDirector() + " - " + getLength() + ": " + getCost() + " $";
    }

    public boolean isMatch(String title) {
        if (this.getTitle() == null) return false;
        return this.getTitle().toLowerCase().contains(title.toLowerCase());
    }
    
    @Override
    public void play() throws PlayerException {
        // Kiểm tra độ dài sản phẩm có lớn hơn 0 hay không
        if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength());
        } else {
            // In thông báo lỗi ra luồng System.err và ném ngoại lệ
            System.err.println("ERROR: DVD length is non-positive!");
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
    }
}