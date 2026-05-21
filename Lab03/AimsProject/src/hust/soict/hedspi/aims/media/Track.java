package hust.soict.hedspi.aims.media;

/**
 * Lớp Track đại diện cho một bài hát hoặc một phần trong CompactDisc.
 * Lớp này không kế thừa từ Media.
 */
public class Track implements Playable {

    private String title;
    private int length;

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
    public void play() {
        System.out.println("Playing track: " + this.getTitle());
        System.out.println("Track length: " + this.getLength());
    }
}