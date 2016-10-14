package polytech.unice.fr.isa.aa;

import junit.framework.Assert;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import polytech.unice.fr.isa.aa.business.Card;
import polytech.unice.fr.isa.aa.business.Pass;
import polytech.unice.fr.isa.aa.business.User;
import polytech.unice.fr.isa.aa.business.enums.AgePass;
import polytech.unice.fr.isa.aa.business.enums.TypePass;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;
import polytech.unice.fr.isa.aa.exceptions.UnknownCardException;
import polytech.unice.fr.isa.aa.interfaces.CardFinder;
import polytech.unice.fr.isa.aa.interfaces.CardModifier;

import javax.ejb.EJB;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;
/**
 * @author Nicolas HORY
 * @version 01/03/16.
 */

@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class CardBeanTest extends AbstractTest{

    @EJB
    CardModifier modifier;

    @EJB
    CardFinder finder;

    protected Card card;
    protected Pass pass;

    @Before
    public void setUp() {
        //database.flush();
        card = new Card();
        card.setId("1");
        //database.getCards().put(card, null);
        pass = new Pass();
        pass.setActivated(false);
        pass.setType(TypePass.EIGHT_DAYS);
        pass.setAge(AgePass.GOLDEN_AGE);
        pass.setZone(ZonePass.ALL);
    }

    @After
    public void cleaningUp() throws Exception {
        Optional<Card> toDispose = finder.findById(card.getId());
        if(toDispose.isPresent()) {
            Card c = entityManager.merge(toDispose.get());
            entityManager.remove(c);
        }
        entityManager.remove(pass);
    }

    /**
     * Tests the fact of adding a pass to an existing card
     * @throws Exception
     */
    @Test
    public void addPassToCard() throws Exception
    {
        /*assertEquals(null, database.getCards().get(card)); // On vérifie qu'il n'y a aucun pass associé à la carte
        Pass newPass = new Pass();
        newPass.setType(TypePass.EIGHT_DAYS); // New pass
        modifier.add(card, newPass); //Adds the pass to the card
        assertEquals(newPass, database.getCards().get(card)); // On vérifie que la carte a maintenant un pass associé*/
        modifier.create(card);
        modifier.add(card, pass);
        assertEquals(pass, finder.findById(card.getId()).get().getPass());
    }

    /**
     * Tests the fact of removing an existing pass from an existing card
     * @throws Exception
     */
    @Test
    public void removePassFromCard() throws Exception {
        /*Pass passToRemove = new Pass();
        passToRemove.setType(TypePass.MORE_THAN_NINE_DAYS); // New pass
        modifier.add(card, passToRemove); // Adds the pass to the card
        assertEquals(passToRemove, database.getCards().get(card)); // Checks the pass was added
        modifier.remove(card, passToRemove); // Removes the pass
        assertEquals(null, database.getCards().get(card)); // Checks the pass is no longer associated to the card in database*/
        modifier.create(card);
        modifier.add(card, pass);
        Pass founded = finder.findById(card.getId()).get().getPass();
        assertEquals(pass, founded);
        modifier.remove(card, founded);
        assertNull(finder.findById(card.getId()).get().getPass());
    }

    /**
     * Tests the fact of adding a pass to an unknown card: supposed to throw exception
     * @throws Exception
     */
    @Test (expected = UnknownCardException.class)
    public void addPassToUnknownCard() throws Exception {
        /*Card unknownCard = new Card(); // New card
        Pass newPass = new Pass(); // New pass
        newPass.setType(TypePass.FIDELICIMES);
        modifier.add(unknownCard, newPass); // Tries to add the pass, has to throw UnknownCardException*/
        Card unknownCard = new Card();
        modifier.add(unknownCard, pass);
    }

    /**
     * Tests the fact of removing a pass from an unknown card: supposed to throw exception
     * @throws Exception
     */
    @Test(expected = UnknownCardException.class)
    public void removePassFromUnknownCard() throws Exception {
        Card unknownCard = new Card(); //New card
        modifier.remove(unknownCard, pass); // Tries to remove the pass, has to throw UnknownCardException
    }

    /**
     * Check if the card returned has the right id
     */
    @Test
    public void getPassById() throws Exception{
        modifier.create(card);
        Card cardIdOne = finder.findById("1").get();
        assertEquals(card, cardIdOne);
    }

    @Test(expected = NoSuchElementException.class)
    public void falseGetPassById() throws Exception {
        modifier.create(card);
        finder.findById("2").get();
    }

}
