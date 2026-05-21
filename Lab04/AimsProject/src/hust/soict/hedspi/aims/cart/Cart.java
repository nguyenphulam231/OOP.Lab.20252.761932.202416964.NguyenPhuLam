package hust.soict.hedspi.aims.cart;

import java.util.ArrayList; 
import hust.soict.hedspi.aims.media.Media; 

public class Cart {
    
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();


    public Cart() {
    }

    public void addMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot add a null item to the cart!");
            return;
        }
        
        if (itemsOrdered.contains(media)) {
            System.out.println("The media \"" + media.getTitle() + "\" is already in the cart.");
        } else {
            itemsOrdered.add(media);
            System.out.println("The media \"" + media.getTitle() + "\" has been added to the cart.");
        }
    }


    public void removeMedia(Media media) {
        if (media == null) {
            System.out.println("Invalid item to remove!");
            return;
        }

        if (itemsOrdered.remove(media)) {
            System.out.println("The media \"" + media.getTitle() + "\" has been removed from the cart.");
        } else {
            System.out.println("The media \"" + media.getTitle() + "\" was not found in the cart.");
        }
    }

    public float totalCost() {
        float total = 0f;
        for (Media media : itemsOrdered) {
            if (media != null) {
                total += media.getCost(); 
            }
        }
        return total;
    }
    
    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        
        int index = 1;
        for (Media media : itemsOrdered) {

            System.out.println(index + ". " + media.toString());
            index++;
        }
        
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }
    
    public void search(int id) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found match by ID: " + media.toString());
                found = true;
                break; 
            }
        }
        if (!found) {
            System.out.println("No match found for ID: " + id);
        }
    }


    public void search(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
    
            if (media.getTitle() != null && media.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found match by Title: " + media.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No match found for title: " + title);
        }
    }

    public ArrayList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

	public Media searchByTitle(String rTitle) {
		// TODO Auto-generated method stub
		return null;
	
	}
	
	
	public void clearCart() {

	    this.itemsOrdered.clear(); 
	    System.out.println("The cart has been emptied.");
	}
}