/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;
import java.util.*;
/**
 *
 * @author 72dabriks
 */
public class Bookstore {

    public static ArrayList<User> users = new ArrayList<User>(); // List of users
    public static ArrayList<Book> books = new ArrayList<Book>(); // List of books
    public static ArrayList<Order> orders = new ArrayList<Order>(); // List of orders
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
            DBOps.startDB();
            Log.getAdminPassword();
            
            java.awt.EventQueue.invokeLater(() -> {
                new Main().setVisible(true);
            });
            
           
        }
        

}
    

