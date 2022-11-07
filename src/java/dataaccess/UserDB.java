package dataaccess;

/**
 *
 * @author Phi N
 */
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Role;
import models.User;

public class UserDB {
    

    
    public List<User> getAll() throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
        
        em.close();
        return users;
    }
    
    public User get(String email) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        User user = em.find(User.class, email);
        em.close();        
        return user;
    }

    public void insertUser(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            Role role = user.getRole();
            role.getUserList().add(user);
            trans.begin();
            em.persist(user);
            em.merge(role);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    

    public void update(User user) throws Exception {  
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void delete(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            Role role = user.getRole();
            role.getUserList().remove(user);
            trans.begin();
            em.remove(em.merge(user));
            em.merge(role);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
