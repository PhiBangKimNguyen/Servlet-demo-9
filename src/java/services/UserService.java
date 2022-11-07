package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author Phi N
 */
public class UserService {
    UserDB userDB;

    public UserService() {
        this.userDB = new UserDB();
    }
    
    public List<User> getAll() throws Exception {
        
        List<User> users = userDB.getAll();
        return users;
    }
    
    public User get(String email) throws Exception {
         
        User user = userDB.get(email);
        return user;
    }
    
    public void insert(String email, String firstname, String lastname, String password, String roleName) throws Exception {       
        int roleID = (roleName.equals("system admin") ? 1 : 2);
        
 
        User user = new User(email, firstname, lastname, password);
        RoleDB roleDB = new RoleDB();
       
        Role role = roleDB.getRole(roleID);
        user.setRole(role);
        userDB.insertUser(user);
    }
    
    public void update(String email, String firstname, String lastname, String password, String roleName) throws Exception {
        int roleID = (roleName.equals("system admin") ? 1 : 2);
        
        RoleDB roleDB = new RoleDB();
        User user = userDB.get(email);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setPassword(password);
        Role role = roleDB.getRole(roleID);
        user.setRole(role);
        userDB.update(user);
    }
    
    public void delete(String email) throws Exception {
        User user = userDB.get(email);
        userDB.delete(user);
    }
}
