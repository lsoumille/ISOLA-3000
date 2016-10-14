package integration;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import polytech.unice.fr.isa.aa.AbstractTest;
import polytech.unice.fr.isa.aa.business.Card;
import polytech.unice.fr.isa.aa.business.Transaction;
import polytech.unice.fr.isa.aa.business.User;
import polytech.unice.fr.isa.aa.interfaces.Payment;
import polytech.unice.fr.isa.aa.interfaces.UserFinder;
import polytech.unice.fr.isa.aa.interfaces.UserModifier;

import javax.ejb.EJB;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by lucas on 24/03/16.
 */
@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class PaymentIntegrationTest extends AbstractTest{

    private static final double DELTA = 1e-15;

    @EJB
    private UserFinder finderUser;

    @EJB
    private UserModifier userModifier;

    @EJB
    private Payment payment;

    private User user;
    private Card card;
    private Transaction trans;

    @Before
    public void setUpContext() {;
        user = new User("User", "WithoutCard",20,"0000", "mail@mail.com");
        card = new Card();
    }

    @Test
    public void integrationCustomerCard() throws Exception{
        userModifier.add(user);
        User retrieved1 = finderUser.getByNameAndFirstName("User", "WithoutCard").get();
        assertTrue(retrieved1.getAllTransaction().isEmpty());
        payment.payCardForUser(user,card);
        trans = user.getAllTransaction().iterator().next();
        User retrieved2 = finderUser.getByNameAndFirstName("User", "WithoutCard").get();
        assertFalse(retrieved2.getAllTransaction().isEmpty());
        Iterator i = retrieved2.getAllTransaction().iterator();
        Transaction t = (Transaction) i.next();
        double priceCard = 1.5;
        assertEquals(priceCard, t.getObjects().getPrice(), DELTA);
    }

    @After
    public void cleaningUp() throws Exception {
        Optional<User> toDispose = finderUser.getByNameAndFirstName(user.getName(), user.getFirstName());
        if(toDispose.isPresent()) {
            User c = entityManager.merge(toDispose.get());
            System.out.println(c.getAllTransaction().iterator().next());
            //entityManager.remove(c.getAllTransaction().iterator().next());
            entityManager.remove(c);
        }
    }
}
