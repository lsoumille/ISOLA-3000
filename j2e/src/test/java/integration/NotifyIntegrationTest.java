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
import polytech.unice.fr.isa.aa.business.User;
import polytech.unice.fr.isa.aa.exceptions.AlreadyExistingUserException;
import polytech.unice.fr.isa.aa.interfaces.Notify;
import polytech.unice.fr.isa.aa.interfaces.UserFinder;
import polytech.unice.fr.isa.aa.interfaces.UserModifier;

import javax.ejb.EJB;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by lucas on 05/04/16.
 */
@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class NotifyIntegrationTest extends AbstractTest {

    private User user;

    @EJB
    private Notify notifyBean;

    @EJB
    private UserModifier userModifier;

    @EJB
    private UserFinder finder;

    @Before
    public void setUpContext() {
        user = new User("User", "WithoutCard",20,"0000","lucassoumille@yahoo.fr");
        try {
            userModifier.add(user);
        } catch (AlreadyExistingUserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void integrationNotify() throws Exception{
        assertTrue(notifyBean.notifyByAge("Venez faire du ski","Promo sur les forfaits", 10, 30));
    }

    @After
    public void cleaningUp() throws Exception {
        Optional<User> toDispose = finder.getByNameAndFirstName(user.getName(), user.getFirstName());
        if(toDispose.isPresent()) {
            User c = entityManager.merge(toDispose.get());
            entityManager.remove(c);
        }
    }

}
