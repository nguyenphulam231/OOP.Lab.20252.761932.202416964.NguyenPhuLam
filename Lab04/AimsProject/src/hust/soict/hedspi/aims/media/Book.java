package hust.soict.hedspi.aims.media; 

import java.util.ArrayList;
import java.util.List;

public class Book extends Media { 

    private List<String> authors = new ArrayList<String>();

    public Book() {
        super(); 
    }
    
    public Book(int id, String title, String category) {
        super(id, title, category); 
    }

    public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost); 
    }
    
    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
            System.out.println("Added author: " + authorName);
        } else {
            System.out.println("Author '" + authorName + "' is already in the list.");
        }
    }

    public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) {
            authors.remove(authorName);
            System.out.println("Removed author: " + authorName);
        } else {
            System.out.println("Author '" + authorName + "' not found in the list.");
        }
    }
}