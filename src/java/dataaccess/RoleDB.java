package dataaccess;

/**
 *
 * @author Phi N
 */
import javax.persistence.EntityManager;
import models.Role;

public class RoleDB {
    public Role getRole(int roleNo) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
     
        Role role = em.find(Role.class, roleNo);       
        em.close();
        return role;
    }
}
