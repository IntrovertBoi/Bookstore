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

// Main User class
public class User {
    
    public String login;
    public char[] password;
    public boolean is_admin;
    public static char[] a_pswrd = {'a', 'd', 'm', 'i', 'n'};
    
    public User(String login, char[] password, boolean is_admin){
        this.login = login;
        this.password = password;
        this.is_admin = is_admin;
    }
    
    public String getLogin(){
        return login;
    }
    
    public char[] getPassword(){
        return password;
    }
    
    public void changeAPswrd(char[] a_pswrd){
        User.a_pswrd = a_pswrd;
    }
    
    
}
