package polytech.unice.fr.isa.aa;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import polytech.unice.fr.isa.aa.business.Card;
import polytech.unice.fr.isa.aa.business.User;
import polytech.unice.fr.isa.aa.exceptions.AlreadyExistingUserException;
import polytech.unice.fr.isa.aa.exceptions.UnknownCardException;
import polytech.unice.fr.isa.aa.exceptions.UnknownUserException;
import polytech.unice.fr.isa.aa.interfaces.UserFinder;
import polytech.unice.fr.isa.aa.interfaces.UserModifier;
import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Nicolas HORY
 * @version 14/03/16.
 */

@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class UserBeanTest extends AbstractTest {
    @EJB
    UserModifier userModifier;

    @EJB
    UserFinder userFinder;

    protected User user;
    protected Card card;

    @Before
    public void setUp() {
        database.flush();
        card = new Card();
        card.setId("1");
        user = new User();
        user.setName("Pierre");
        user.setFirstName("Jean");
        user.setAge(24);
       // user.setCard(card);
        user.setCreditCard("1234");
    }

    @After
    public void cleaningUp() throws Exception {
        Optional<User> toDispose = userFinder.getByNameAndFirstName(user.getName(), user.getFirstName());
        if(toDispose.isPresent()) {
            User c = entityManager.merge(toDispose.get());
            entityManager.remove(c);
        }
    }


    /**
     * Tests the fact of adding a user to the database
     * @throws Exception
     */
    @Test
    public void addUser() throws Exception{
        //assertEquals(0, database.getUsers().size()); // Checks the database is empty
        //userModifier.add(user); // Calls the bean method
        //assertEquals(1, database.getUsers().size()); // Checks there is only one user added
        //assertEquals(card, database.getUsers().get(user)); // Checks the card in database is the one associated to the added user
        userModifier.add(user);
        assertEquals(user, userFinder.getByNameAndFirstName(user.getName(), user.getFirstName()).get());
    }

    /**
     * Tests the fact of adding an already existing user to the database
     * @throws AlreadyExistingUserException
     */
    @Test(expected = AlreadyExistingUserException.class)
    public void addExistingUser() throws Exception {
        userModifier.add(user); // Adds the user to the database
        userModifier.add(user); // Tries to add the user again, has to throw AlreadyExistingUserException
    }

    /**
     * Tests the fact of updating a user in the database
     * @throws Exception
     */
    @Test
    public void updateUser() throws Exception{
        userModifier.add(user); // Adds the user
        userModifier.update(user, 70);
        assertEquals(70, userFinder.getByNameAndFirstName(user.getName(), user.getFirstName()).get().getAge());
    }

    /**
     * Tests the fact of updating an unknown user in the database
     * @throws UnknownUserException
     */
    @Test(expected = UnknownUserException.class)
    public void updateUnknownUser() throws Exception {
        assertEquals(0, database.getUsers().size()); // Checks the database is empty
        userModifier.update(user, 26); // Tries to update user, has to throw UnknownUserException
    }

    /**
     * Tests the fact of removing a user from the database
     * @throws Exception
     */
    @Test (expected = NoSuchElementException.class)
    public void removeUser() throws Exception {
        /*assertEquals(0, database.getUsers().size()); // Checks the database is empty
        userModifier.add(user); // Adds user to database
        assertEquals(1, database.getUsers().size()); // Checks the user was well added
        userModifier.remove(user); // Removes the user from the database
        assertEquals(0, database.getUsers().size());*/ // Checks the user was removed
        userModifier.add(user);
        userModifier.remove(user);
        userFinder.getByNameAndFirstName(user.getName(), user.getFirstName()).get();
    }

    /**
     * Tests the fact of adding a new card to a user in the database
     * @throws Exception
     */
    @Test
    public void addCardToUser() throws Exception {
        /*assertEquals(0, database.getUsers().size()); // Checks database is empty
        userModifier.add(user); // Adds the user
        assertEquals(card, database.getUsers().get(user)); // Checks the user was added
        Card newCard = new Card();
        newCard.setId("18"); // Create new card with id 18
        database.getCards().put(newCard, null); // Adds the card to the database
        userModifier.addCardForUser(user, newCard); // Adds the card to the user
        assertEquals(newCard, database.getUsers().get(user)); // Checks the card of user is the new one*/
        userModifier.add(user);
        Card newCard = new Card();
        userModifier.addCardForUser(user, newCard);
        assertEquals(newCard, userFinder.getByNameAndFirstName(user.getName(), user.getFirstName()).get().getCard());
    }

    /**
     * Tests the fact of adding a new card to an unknown user in the database
     * @throws UnknownUserException
     */
    @Test (expected = UnknownUserException.class)
    public void addCardToUnknownUser() throws Exception {
        userModifier.addCardForUser(user, card); // Tries to add card to user, has to throw UnknownUserException
    }

    @Test
    public void testGetByIdCard() throws Exception {
        userModifier.add(user);
        Card newCard = new Card();
        userModifier.addCardForUser(user, newCard);
        assertEquals(user, userFinder.getByIdCard(newCard.getId()).get());
    }

    @Test
    public void testGetByNameAndFirstName() throws Exception {
        userModifier.add(user);
        assertEquals(user, userFinder.getByNameAndFirstName(user.getName(), user.getFirstName()).get());
    }
}
