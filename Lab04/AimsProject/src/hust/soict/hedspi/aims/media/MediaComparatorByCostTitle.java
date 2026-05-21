package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        // So sánh theo Cost giảm dần trước
        int costCompare = Float.compare(m2.getCost(), m1.getCost());
        if (costCompare != 0) {
            return costCompare;
        }
        // Nếu trùng Cost, so sánh theo Title (Alphabet)
        return m1.getTitle().compareToIgnoreCase(m2.getTitle());
    }
}