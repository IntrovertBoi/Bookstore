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
public class Order {
    
    public String user;
    public String book;
    public int id;
    
    public Order(int id, String user, String book){
        this.user = user;
        this.book = book;
        this.id = id;
    }
}
