
public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;

    //Thêm 1 đĩa (Phương thức gốc)
    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            itemsOrdered[qtyOrdered] = disc;
            qtyOrdered++;
            System.out.println("The disc \"" + disc.getTitle() + "\" has been added.");
        } else {
            System.out.println("The cart is almost full. Cannot add: " + disc.getTitle());
        }
    }

    // Nạp chồng Thêm một danh sách đĩa (Mảng)
    //public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
     //   for (DigitalVideoDisc disc : dvdList) {
     //       addDigitalVideoDisc(disc);
     //   }
    //}

    //Nạp chồng Thêm số lượng tham số tùy ý (Varargs)
    public void addDigitalVideoDisc(DigitalVideoDisc... dvds) {
        for (DigitalVideoDisc disc : dvds) {
            addDigitalVideoDisc(disc);
        }
    }

    //Nạp chồng Thêm đúng 2 đĩa cùng lúc
    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        addDigitalVideoDisc(dvd1);
        addDigitalVideoDisc(dvd2);
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        int indexFound = -1;
        
        // Tìm vị trí của đĩa cần xóa
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i] == disc) {
                indexFound = i;
                break;
            }
        }
        
        // Xóa và dồn mảng
        if (indexFound != -1) {
            for (int i = indexFound; i < qtyOrdered - 1; i++) {
                itemsOrdered[i] = itemsOrdered[i + 1]; 
            }
            itemsOrdered[qtyOrdered - 1] = null; 
            qtyOrdered--;
            System.out.println("The disc \"" + disc.getTitle() + "\" has been removed.");
        } else {
            System.out.println("The disc \"" + disc.getTitle() + "\" was not found in the cart.");
        }
    }

    public float totalCost() {
        float total = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i] != null) {
                total += itemsOrdered[i].getCost();
            }
        }
        return total;
    }
    
    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        
        for (int i = 0; i < qtyOrdered; i++) {
            System.out.println((i + 1) + ". " + itemsOrdered[i].toString());
        }
        
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }
    
 // Tìm kiếm theo ID
    public void search(int id) {
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].getId() == id) {
                System.out.println("Found match by ID: " + itemsOrdered[i].toString());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No match found for ID: " + id);
        }
    }

    // Tìm kiếm theo Title
    public void search(String title) {
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].isMatch(title)) {
                System.out.println("Found match by Title: " + itemsOrdered[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No match found for title: " + title);
        }
    }
    
}