/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

/**
 *
 * @author IntrovertBoy
 */
public class Log {
    
    public static void getAdminPassword(){
        
        try {
            String a_password = "";
            FileReader reader = new FileReader("src\\bookstore\\admin_password.txt");
            int character;
 
            while ((character = reader.read()) != -1) {
                a_password = a_password + (char) character;
            }
            reader.close();
            
            User.a_pswrd = a_password.toCharArray();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void newAdminPassword(char[] new_apassword){
        
        try {
            FileWriter writer = new FileWriter("src\\bookstore\\admin_password.txt", false);
            writer.write(new String(new_apassword));
            writer.close();
            User.a_pswrd = new_apassword;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void newUserCreated(String username){
        try {
            FileWriter writer = new FileWriter("src\\bookstore\\log.txt", true);
            writer.write("User ");
            writer.write(username);
            writer.write(" was created.");
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void userOrdersBook(String username, String book_title){
        try {
            FileWriter writer = new FileWriter("src\\bookstore\\log.txt", true);
            writer.write("User ");
            writer.write(username);
            writer.write(" ordered a book titled '" + book_title + "'.");
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void userCancelsOrder(String username, int order_id){
        try {
            FileWriter writer = new FileWriter("src\\bookstore\\log.txt", true);
            writer.write("User ");
            writer.write(username);
            writer.write(" canceled an order with order id = " + order_id + ".");
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void userDeleted(String username){
        try {
            FileWriter writer = new FileWriter("src\\bookstore\\log.txt", true);
            writer.write("User ");
            writer.write(username);
            writer.write(" was deleted.");
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void adminAddedBook(String adname, String book_title){
        try {
            FileWriter writer = new FileWriter("src\\bookstore\\log.txt", true);
            writer.write("Admin ");
            writer.write(adname);
            writer.write(" added a book titled '" + book_title + "'.");
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void adminDeletedBook(String adname, String book_title){
        try {
            FileWriter writer = new FileWriter("src\\bookstore\\log.txt", true);
            writer.write("Admin ");
            writer.write(adname);
            writer.write(" deleted a book titled '" + book_title + "'.");
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void adminCanceledOrder(String adname, int order_id){
        try {
            FileWriter writer = new FileWriter("src\\bookstore\\log.txt", true);
            writer.write("Admin ");
            writer.write(adname);
            writer.write(" canceled order with order id = " + order_id + ".");
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void adminCompletedOrder(String adname, int order_id){
        try {
            FileWriter writer = new FileWriter("src\\bookstore\\log.txt", true);
            writer.write("Admin ");
            writer.write(adname);
            writer.write(" completed order with order id = " + order_id + ".");
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void adminAddsBookStock(String adname, String book_title, int am_of_stock){
        try {
            FileWriter writer = new FileWriter("src\\bookstore\\log.txt", true);
            writer.write("Admin ");
            writer.write(adname);
            writer.write(" added " + am_of_stock + " books titled '" + book_title + "'.");
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void adminRemovesBookStock(String adname, String book_title, int am_of_stock){
        try {
            FileWriter writer = new FileWriter("src\\bookstore\\log.txt", true);
            writer.write("Admin ");
            writer.write(adname);
            writer.write(" removed " + am_of_stock + " books titled '" + book_title + "'.");
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
