package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compare(Media m1, Media m2) {
        // So sánh theo Title trước (Alphabet)
        int titleCompare = m1.getTitle().compareToIgnoreCase(m2.getTitle());
        if (titleCompare != 0) {
            return titleCompare;
        }
        // Nếu trùng Title, so sánh theo Cost giảm dần (đắt hơn đứng trước)
        return Float.compare(m2.getCost(), m1.getCost());
    }
}