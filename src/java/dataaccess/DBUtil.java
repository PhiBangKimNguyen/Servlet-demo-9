package dataaccess;

/**
 *
 * @author Phi N
 */
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("UserPU");
    
    public static EntityManagerFactory getEmFactory() {return EMF;}
}
