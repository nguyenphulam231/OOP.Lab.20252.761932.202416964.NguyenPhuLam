public class CartTest {
    public static void main(String[] args) {
        //Khởi tạo giỏ hàng
        Cart cart = new Cart();

        //Tạo các đối tượng DVD mới và thêm vào giỏ hàng
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", 
                "Animation", "Roger Allers", 87, 19.95f);
        cart.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", 
                "Science Fiction", "George Lucas", 87, 24.95f);
        cart.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", 
                "Animation", 18.99f);
        cart.addDigitalVideoDisc(dvd3);

        cart.print();
        
     // Test tìm kiếm theo ID
        System.out.println("\n--- Searching by ID ---");
        cart.search(1); // Tìm ID có thật
        cart.search(5); // Tìm ID không có

        // Test tìm kiếm theo Title
        System.out.println("\n--- Searching by Title ---");
        cart.search("Lion"); // Tìm đúng/gần đúng
        cart.search("Avatar"); // Tìm tên không có
    }
}