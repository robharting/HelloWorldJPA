import environment.TransactionManagerTest;
import model.helloworld.Message;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by robendiane on 28-01-18.
 */
public class HelloWorldJPA extends TransactionManagerTest {

    @Test
    public void storeLoadMessage() throws Exception {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");

        try {
            {
                UserTransaction tx = TM.getUserTransaction();
                tx.begin();

                EntityManager em = emf.createEntityManager();

                Message message = new Message();
                message.setText("Hello World! from rob's first JPA Project");

                em.persist(message);

                tx.commit();
                // INSERT into MESSAGE (ID, TEXT) values (1, 'Hello World!')

                em.close();
            }
            {
                // display the stored message

                UserTransaction tx = TM.getUserTransaction();
                tx.begin();

                EntityManager em = emf.createEntityManager();

                List<Message> messages = em.createQuery("select m from Message m").getResultList();
                // SELECT * FROM MESSAGE

                assertEquals(messages.size(), 1);
                assertEquals(messages.get(0).getText(), "Hello World! from rob's first JPA Project");

                messages.get(0).setText("Hello World! aangepast door setText");
                assertEquals(messages.get(0).getText(), "Hello World! aangepast door setText");

                tx.commit();
                // UPDATE MESSAGE set TEXT = 'Hello World! aangepast door setText' where ID = 1

                em.close();
            }

        } finally {
            TM.rollback();
            emf.close();
        }
    }
}


