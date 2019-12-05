/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp.Model;
import java.util.ArrayList;

/**
 *
 * @author Afonso
 */
public class User {
    
    private User_DAO userDAO;
    private String username;
    private String password;
    private ArrayList<String> playlist; 
   
    
    public User(String n, String p){
        username = n;
        password = p;
    }
    
    public User(){
        
    }
    
    public boolean login(String n, String p){
        User_DAO u = new User_DAO();
        User user = u.loginDAO(n);
        Boolean bool = false;
            if(user.getUsername().equals(n) && user.getPassword().equals(p)){
                bool = true;
            }
        
        return bool; 
       
    }
    
    public boolean verifyUser(String n){
        User_DAO u = new User_DAO();
        return u.verifyUserDAO(n);
    }
    
    public void register(){
        User_DAO u = new User_DAO();
        u.inserirUser(username, password);
    }
    
    public void logout(){
    
    
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
}
