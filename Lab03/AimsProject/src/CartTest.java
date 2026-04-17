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
    }
}