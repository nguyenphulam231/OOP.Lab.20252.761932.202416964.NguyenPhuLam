package hust.soict.hedspi.aims.media;

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

    // Các Constructor để khởi tạo CompactDisc
    public CompactDisc(String title) {
        super(title);
    }

    public CompactDisc(String title, String category, float cost) {
        super(title, category, cost);
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
    public void play() {
        // 1. In thông tin tổng quan của CD 
        System.out.println("Playing CD: " + this.getTitle() + " (Artist: " + this.artist + ")");
        System.out.println("CD total length: " + this.getLength());
        System.out.println("-------------------------");
        
        // 2. Vòng lặp duyệt qua từng track và gọi hàm play() của Track đó 
        for (Track track : tracks) {
            track.play(); // Gọi hàm play() của lớp Track 
        }
    }
}