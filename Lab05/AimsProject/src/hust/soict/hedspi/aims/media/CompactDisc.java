package hust.soict.hedspi.aims.media;
import hust.soict.hedspi.aims.exception.PlayerException; // Thêm dòng này để import class lỗi vào
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp CompactDisc đại diện cho đĩa CD, kế thừa từ Disc.
 * Quản lý danh sách các bài hát (Track) và nghệ sĩ (artist).
 */
public class CompactDisc extends Disc implements Playable {

    private String artist;
    private List<Track> tracks = new ArrayList<Track>();

    // Constructor mặc định
    public CompactDisc() {
        super();
    }
    
    public CompactDisc(int id, String title) {
        super(id, title); 
    }

    public CompactDisc(int id, String title, String category, float cost) {
        super(id, title, category, cost); 
    }

    public CompactDisc(int id, String title, String category, float cost, String director, int length, String artist) {
        super(id, title, category, cost, director, length); 
        this.artist = artist;
    }
    public CompactDisc(String title, String category, String artist, float cost) {
        super(title, category, cost); 
        this.artist = artist;
    }
    // Getter cho artist
    public String getArtist() {
        return artist;
    }

    /**
     * Thêm một bài hát vào danh sách. 
     * Kiểm tra xem bài hát đã tồn tại chưa trước khi thêm.
     */
    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println("Track: " + track.getTitle() + " has been added.");
        } else {
            System.out.println("Track: " + track.getTitle() + " is already in the list.");
        }
    }

    /**
     * Xóa một bài hát khỏi danh sách.
     * Kiểm tra xem bài hát có tồn tại trong danh sách không.
     */
    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track: " + track.getTitle() + " has been removed.");
        } else {
            System.out.println("Track: " + track.getTitle() + " does not exist in the list.");
        }
    }

    /**
     * Tính tổng độ dài của CD bằng cách cộng tổng độ dài của tất cả các bài hát.
     * @return tổng độ dài (length)
     */
    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }
    
    @Override
    public void play() throws PlayerException {
        // Kiểm tra tổng độ dài của CD
        if (this.getLength() > 0) {
            System.out.println("Playing CD: " + this.getTitle() + " (Artist: " + this.getArtist() + ")");
            System.out.println("Total length: " + this.getLength());
            
            // Sử dụng Iterator duyệt qua danh sách các tracks giống hệt mẫu hình số 48
            java.util.Iterator iter = tracks.iterator();
            Track nextTrack;
            
            while (iter.hasNext()) {
                nextTrack = (Track) iter.next();
                try {
                    nextTrack.play(); // Tiến hành phát track
                } catch (PlayerException e) {
                    // Nếu bất kỳ track nào trong CD gặp lỗi không thể phát, ném tiếp ngoại lệ
                    throw e; 
                }
            }
        } else {
            System.err.println("ERROR: CD length is non-positive!");
            throw new PlayerException("ERROR: CD length is non-positive!");
        }
    }
}