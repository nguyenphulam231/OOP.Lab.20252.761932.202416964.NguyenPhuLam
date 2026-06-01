package hust.soict.hedspi.aims.test.polymorphism;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

import java.util.ArrayList;
import java.util.List;

public class PolymorphismTest {
	public static void main(String[] args) {
		List<Media> mediae = new ArrayList<Media>();

		Media dvd = new DigitalVideoDisc(2, "The Lion King", "Animation", 19.9f, "Roger Allers", 88);

		Media cd = new CompactDisc(1, "Greatest Hits", "Music", 15.5f, "Director A", 45, "Artist X");

		Media book = new Book(3, "Java Programming", "Education", 25.0f);
		
		mediae.add(cd);
		mediae.add(dvd);
		mediae.add(book);
		
		for(Media m: mediae)
		{
			System.out.println(m.toString());
		}
    }
	
}
