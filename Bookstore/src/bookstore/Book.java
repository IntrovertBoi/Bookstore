/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

/**
 *
 * @author IntrovertBoy
 */
public class Book {
    
    public String title;
    public String author;
    public int pages;
    public int year;
    public String genre;
    public double price;
    public int qty;
    public String description;
    
    public Book(String title, String author, int pages, int year, String genre, double price, int qty, String description){
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.year = year;
        this.genre = genre;
        this.price = price;
        this.qty = qty;
        this.description = description;
    }
    
    public boolean contains(String smthng){
        if(smthng.contains(title) || smthng.contains(author) ||
                smthng.contains(Integer.toString(pages)) || smthng.contains(Integer.toString(year)) ||
                smthng.contains(genre) || smthng.contains(Double.toString(price)))
        {
            return true;
        }
        else{
            return false;
        }
    }
}
