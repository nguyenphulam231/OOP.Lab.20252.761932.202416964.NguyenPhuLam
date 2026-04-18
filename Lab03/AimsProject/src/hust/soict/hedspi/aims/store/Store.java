package hust.soict.hedspi.aims.store;
import hust.soict.hedspi.aims.disc.DigitalVideoDisc;

public class Store {
    // Thuộc tính: Mảng chứa các DVD có trong cửa hàng
    private DigitalVideoDisc itemsInStore[] = new DigitalVideoDisc[100]; 
    private int qtyInStore = 0;

    //thêm DVD vào cửa hàng
    public void addDVD(DigitalVideoDisc dvd) {
        if (qtyInStore < 100) {
            itemsInStore[qtyInStore] = dvd;
            qtyInStore++;
            System.out.println("The DVD '" + dvd.getTitle() + "' has been added to the store.");
        } else {
            System.out.println("The store is almost full!");
        }
    }

    //xóa DVD khỏi cửa hàng
    public void removeDVD(DigitalVideoDisc dvd) {
        int foundIndex = -1;
        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i].equals(dvd)) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex != -1) {
            // Dồn các phần tử phía sau lên để lấp chỗ trống
            for (int i = foundIndex; i < qtyInStore - 1; i++) {
                itemsInStore[i] = itemsInStore[i + 1];
            }
            itemsInStore[qtyInStore - 1] = null;
            qtyInStore--;
            System.out.println("The DVD '" + dvd.getTitle() + "' has been removed from the store.");
        } else {
            System.out.println("The DVD '" + dvd.getTitle() + "' was not found in the store.");
        }
    }
}