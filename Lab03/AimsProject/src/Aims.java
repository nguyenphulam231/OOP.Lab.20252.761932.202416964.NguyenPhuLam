public class Aims {
    public static void main(String[] args) {
        Cart anOrder = new Cart();

        // Tạo đĩa và in ID để kiểm tra Mục 5
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        System.out.println("DVD 1 ID: " + dvd1.getId()); // In ID để check
        anOrder.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        System.out.println("DVD 2 ID: " + dvd2.getId()); // In ID để check
        anOrder.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", 18.99f);
        System.out.println("DVD 3 ID: " + dvd3.getId()); // In ID để check
        anOrder.addDigitalVideoDisc(dvd3);

        System.out.print("Total Cost is: ");
        System.out.println(anOrder.totalCost());
        
        /* 
        System.out.println("\nRemoving dvd2 from the cart...");
        anOrder.removeDigitalVideoDisc(dvd2);
     
        System.out.print("Total Cost after removing dvd2: ");
        System.out.println(anOrder.totalCost());
   
        DigitalVideoDisc dvd4 = new DigitalVideoDisc("Avatar", "Sci-fi", 20.0f);
        System.out.println("\nTrying to remove dvd4 (not in cart)...");
        anOrder.removeDigitalVideoDisc(dvd4);
        */
    }
}