package hust.soict.hedspi.aims.media;
import hust.soict.hedspi.aims.exception.PlayerException; // Thêm dòng này để import class lỗi vào
/**
 * Lớp Track đại diện cho một bài hát hoặc một phần trong CompactDisc.
 * Lớp này không kế thừa từ Media.
 */
public class Track implements Playable {

    private String title;
    private int length;
    
    public Track(String title) {
        this.title = title;
    }
    // Constructor khởi tạo Track với tiêu đề và độ dài
    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    // Getter cho tiêu đề bài hát
    public String getTitle() {
        return title;
    }

    // Getter cho độ dài bài hát
    public int getLength() {
        return length;
    }
    
    @Override
    public void play() throws PlayerException {
        // Kiểm tra độ dài của Track
        if (this.getLength() > 0) {
            System.out.println("Playing track: " + this.getTitle());
            System.out.println("Track length: " + this.getLength());
        } else {
            // In thông báo lỗi ra luồng System.err và ném ngoại lệ
            System.err.println("ERROR: Track length is non-positive!");
            throw new PlayerException("ERROR: Track length is non-positive!");
        }
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Track)) return false;
        
        Track track = (Track) o;
        
        // So sánh cả title (không phân biệt hoa thường) và length
        if (this.title == null || track.getTitle() == null) return false;
        
        return this.length == track.getLength() && this.title.equalsIgnoreCase(track.getTitle());
    }
}