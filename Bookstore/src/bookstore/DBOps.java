/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import static bookstore.Bookstore.books;
import static bookstore.Bookstore.orders;
import static bookstore.Bookstore.users;
import java.sql.*;
import java.util.Random;

/**
 *
 * @author IntrovertBoy
 */
public class DBOps {
    
    public static Connection con;  // Declaring SQL connection
    public static Statement stmt;
    public static ResultSet rs;
    public static PreparedStatement pstmt;
    public static boolean u_number;
    
    public static void startDB(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore_briks","root","");  // Connecting to database
            stmt=con.createStatement();  
            rs=stmt.executeQuery("SELECT * FROM users");  
            char[] password;
            
                while(rs.next()){
                    
                    password = rs.getString("password").toCharArray();
                    users.add(new User(rs.getString("login"), password, rs.getBoolean("is_admin")));
                    
                } 
            rs=stmt.executeQuery("SELECT * FROM books");
            
                while(rs.next()){
                    
                    books.add(new Book(rs.getString("title"), rs.getString("author"), rs.getInt("pages"), rs.getInt("year"), rs.getString("genre"), rs.getDouble("price"), rs.getInt("qty"), rs.getString("description")));
                    
                } 
            rs=stmt.executeQuery("SELECT * FROM orders");
            
                while(rs.next()){
                    
                    orders.add(new Order(rs.getInt("id"), rs.getString("user_login"), rs.getString("book_title")));
                    
                }

        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Error in start: " + e);
        }
        finally {
                try { rs.close(); } catch (SQLException e) { /* ignored */ }
                try { stmt.close(); } catch (SQLException e) { /* ignored */ }
                try { con.close(); } catch (SQLException e) { /* ignored */ }
            }
        
    }
    
    public static void addUser(String login, char[] password, boolean is_admin){
        
        try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore_briks","root","");  // Connecting to database
                pstmt=con.prepareStatement("INSERT INTO users (login, password, is_admin)" + "VALUES (?, ?, ?)");
                pstmt.setString(1, login.toLowerCase());
                pstmt.setString(2, new String(password));
                pstmt.setBoolean(3, is_admin);
                pstmt.execute();
                Bookstore.users.add(new User(login, password, is_admin));
            }
            catch(SQLException e){
                System.out.println(e);
            }
            finally{
                try { rs.close(); } catch (SQLException e) { /* ignored */ }
                try { pstmt.close(); } catch (SQLException e) { /* ignored */ }
                try { con.close(); } catch (SQLException e) { /* ignored */ }
            }
    }
    
    public static void addBook(String title, String author, int pages, int year, String genre, double price, int qty, String description){
        
        try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore_briks","root","");  // Connecting to database
                pstmt=con.prepareStatement("INSERT INTO books (title, author, pages, year, genre, price, qty, description)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, title);
                pstmt.setString(2, author);
                pstmt.setInt(3, pages);
                pstmt.setInt(4, year);
                pstmt.setString(5, genre);
                pstmt.setDouble(6, price);
                pstmt.setInt(7, qty);
                pstmt.setString(8, description);
                pstmt.execute();  
                Bookstore.books.add(new Book(title, author, pages, year, genre, price, qty, description));
            }
            catch(SQLException e){
                System.out.println(e);
            }
            finally{
                try { rs.close(); } catch (SQLException e) { /* ignored */ }
                try { pstmt.close(); } catch (SQLException e) { /* ignored */ }
                try { con.close(); } catch (SQLException e) { /* ignored */ }
            }
    }
    
    public static void changePassword(char[] newpassword, String userlogin){
        
        try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore_briks","root","");  // Connecting to database
                pstmt=con.prepareStatement("UPDATE users set password = ? WHERE login = ?");
                pstmt.setString(1, new String(newpassword));
                pstmt.setString(2, userlogin);
                
                pstmt.executeUpdate();
            }
            catch(SQLException e){
                System.out.println(e);
            }
            finally{
                try { rs.close(); } catch (SQLException e) { /* ignored */ }
                try { pstmt.close(); } catch (SQLException e) { /* ignored */ }
                try { con.close(); } catch (SQLException e) { /* ignored */ }
            }
    }
    
    public static void orderBook(String user, String book){
        
        try{
                int id_number;
                Random randomGenerator = new Random();
                do{
                    u_number = true;
                    id_number = randomGenerator.nextInt(9999) + 1;
                    for(Order order : Bookstore.orders){
                        if(id_number == order.id)
                            u_number = false;
                    }
                }
                while(!u_number);
                
                con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore_briks","root","");  // Connecting to database
                pstmt=con.prepareStatement("INSERT INTO orders (id, user_login, book_title)" + "VALUES (?, ?, ?)");
                pstmt.setInt(1, id_number);
                pstmt.setString(2, user);
                pstmt.setString(3, book);
                pstmt.execute();
                Bookstore.orders.add(new Order(id_number, user, book));
                
                pstmt=con.prepareStatement("UPDATE books set qty = qty - 1 WHERE title = ?");
                pstmt.setString(1, book);
                
                pstmt.executeUpdate();
            }
            catch(SQLException e){
                System.out.println("Error ordering a book: ");
            }
            finally{
                try { rs.close(); } catch (SQLException e) { /* ignored */ }
                try { pstmt.close(); } catch (SQLException e) { /* ignored */ }
                try { con.close(); } catch (SQLException e) { /* ignored */ }
            }
    }
    
    public static void deleteOrder(int order_id, String book_title){
        
        try{
                    con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore_briks","root","");  // Connecting to database
                    pstmt=con.prepareStatement("DELETE FROM orders WHERE id = ?");
                    pstmt.setInt(1, order_id);
                    pstmt.execute();
                    
                    pstmt=con.prepareStatement("UPDATE books set qty = qty + 1 WHERE title = ?");
                    pstmt.setString(1, book_title);

                    pstmt.executeUpdate();
                }
                catch(SQLException e){
                    System.out.println(e);
                }
                finally{
                    try { rs.close(); } catch (SQLException e) { /* ignored */ }
                    try { pstmt.close(); } catch (SQLException e) { /* ignored */ }
                    try { con.close(); } catch (SQLException e) { /* ignored */ }
                }
    }
    
    public static void completeOrder(int order_id){
        
        try{
                    con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore_briks","root","");  // Connecting to database
                    pstmt=con.prepareStatement("DELETE FROM orders WHERE id = ?");
                    pstmt.setInt(1, order_id);
                    pstmt.execute();

                }
                catch(SQLException e){
                    System.out.println(e);
                }
                finally{
                    try { rs.close(); } catch (SQLException e) { /* ignored */ }
                    try { pstmt.close(); } catch (SQLException e) { /* ignored */ }
                    try { con.close(); } catch (SQLException e) { /* ignored */ }
                }
    }
    
    public static void updateDB(){
        
        try{
            users.clear();
            books.clear();
            orders.clear(); 
            con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore_briks","root","");  // Connecting to database
            stmt=con.createStatement();  
            rs=stmt.executeQuery("SELECT * FROM users");  
            char[] password;
            
                while(rs.next()){
                    
                    password = rs.getString("password").toCharArray();
                    users.add(new User(rs.getString("login"), password, rs.getBoolean("is_admin")));
                    
                } 
            rs=stmt.executeQuery("SELECT * FROM books");
            
                while(rs.next()){
                    
                    books.add(new Book(rs.getString("title"), rs.getString("author"), rs.getInt("pages"), rs.getInt("year"), rs.getString("genre"), rs.getDouble("price"), rs.getInt("qty"), rs.getString("description")));
                    
                } 
            rs=stmt.executeQuery("SELECT * FROM orders");
            
                while(rs.next()){
                    
                    orders.add(new Order(rs.getInt("id"), rs.getString("user_login"), rs.getString("book_title")));
                    
                }

        }
        catch(SQLException e){
            System.out.println("Error: " + e);
        }
        finally {
                try { rs.close(); } catch (SQLException e) { /* ignored */ }
                try { stmt.close(); } catch (SQLException e) { /* ignored */ }
                try { con.close(); } catch (SQLException e) { /* ignored */ }
            }
    }
    
    public static void deleteAccount(String username){
        
        try{
                    con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore_briks","root","");  // Connecting to database
                    pstmt=con.prepareStatement("DELETE FROM users WHERE login = ?");
                    pstmt.setString(1, username);
                    pstmt.execute();
                    
                    pstmt=con.prepareStatement("DELETE FROM orders WHERE user_login = ?");
                    pstmt.setString(1, username);

                    pstmt.executeUpdate();
                }
                catch(SQLException e){
                    System.out.println(e);
                }
                finally{
                    try { rs.close(); } catch (SQLException e) { /* ignored */ }
                    try { pstmt.close(); } catch (SQLException e) { /* ignored */ }
                    try { con.close(); } catch (SQLException e) { /* ignored */ }
                }
    }
    
    public static void deleteBook(String book_title){
        
        try{
                    con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore_briks","root","");  // Connecting to database
                    pstmt=con.prepareStatement("DELETE FROM books WHERE title = ?");
                    pstmt.setString(1, book_title);
                    pstmt.execute();
                    
                    pstmt=con.prepareStatement("DELETE FROM orders WHERE book_title = ?");
                    pstmt.setString(1, book_title);

                    pstmt.executeUpdate();
                }
                catch(SQLException e){
                    System.out.println(e);
                }
                finally{
                    try { rs.close(); } catch (SQLException e) { /* ignored */ }
                    try { pstmt.close(); } catch (SQLException e) { /* ignored */ }
                    try { con.close(); } catch (SQLException e) { /* ignored */ }
                }
    }
    
    public static void addBookStock(String book_title, int stock_ammount){
        
        try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore_briks","root","");  // Connecting to database
                pstmt=con.prepareStatement("UPDATE books SET qty = qty + ? WHERE title = ?");
                pstmt.setInt(1, stock_ammount);
                pstmt.setString(2, book_title);
                pstmt.execute();
            }
            catch(SQLException e){
                System.out.println(e);
            }
            finally{
                try { rs.close(); } catch (SQLException e) { /* ignored */ }
                try { pstmt.close(); } catch (SQLException e) { /* ignored */ }
                try { con.close(); } catch (SQLException e) { /* ignored */ }
            }
    }
    
    public static void removeBookStock(String book_title, int stock_ammount){
        
        try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore_briks","root","");  // Connecting to database
                pstmt=con.prepareStatement("UPDATE books SET qty = qty - ? WHERE title = ?");
                pstmt.setInt(1, stock_ammount);
                pstmt.setString(2, book_title);
                pstmt.execute();
            }
            catch(SQLException e){
                System.out.println(e);
            }
            finally{
                try { rs.close(); } catch (SQLException e) { /* ignored */ }
                try { pstmt.close(); } catch (SQLException e) { /* ignored */ }
                try { con.close(); } catch (SQLException e) { /* ignored */ }
            }
    }
}
