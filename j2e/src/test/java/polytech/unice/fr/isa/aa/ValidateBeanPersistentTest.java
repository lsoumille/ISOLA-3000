package polytech.unice.fr.isa.aa;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import polytech.unice.fr.isa.aa.business.Card;
import polytech.unice.fr.isa.aa.business.FideliCimePass;
import polytech.unice.fr.isa.aa.business.Gate;
import polytech.unice.fr.isa.aa.business.Pass;
import polytech.unice.fr.isa.aa.business.enums.AgePass;
import polytech.unice.fr.isa.aa.business.enums.TypePass;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;
import polytech.unice.fr.isa.aa.interfaces.GateFinder;
import polytech.unice.fr.isa.aa.interfaces.ValidatePass;

import javax.ejb.EJB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Lucas MARTINEZ
 * @version 4/26/16
 */
@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class ValidateBeanPersistentTest extends AbstractTest{

    @EJB
    ValidatePass validator;

    @EJB
    GateFinder finder;

    private Card card;
    private Gate gate;
    private Pass pass;
    private Gate gateAll;

    @Before
    public void setUp() {
        card = new Card();
        gate = new Gate("station", "nom");
        pass = new Pass();
        gateAll = new Gate("allStation", "name");
        gateAll.setZone(ZonePass.ALL);
        gate.setZone(ZonePass.AURON);
        List<Gate> gates = new ArrayList<>();
        gates.add(gate);

        pass.setType(TypePass.ONE_DAY);
        pass.setZone(ZonePass.AURON);
        pass.setAge(AgePass.ADULT);
        pass.setNbDays(3);
        pass.setPrice(new Float(20.0)); //au hasard, ne respecte pas le vrai catalogue
        pass.setGateList(gates);

        card.setPass(pass);

        entityManager.persist(pass);
        entityManager.persist(card);
        entityManager.persist(gate);
        entityManager.persist(gateAll);
    }

    @After
    public void cleaningUp() {
        entityManager.remove(pass);
        entityManager.remove(gate);
        entityManager.remove(card);
        entityManager.remove(gateAll);
    }

    @Test
    public void validateTest(){
        assertEquals("OK", validator.validate(card, gate)); // Validate card at the gate
    }

    @Test
    public void validateFailEmptyCard(){
        Card emptyCard = new Card();
        entityManager.persist(emptyCard);
        assertEquals("KO", validator.validate(emptyCard, gate)); // Validate card at the gate
        entityManager.remove(emptyCard);
    }

    /**
     * Test the decrementation of nb days on a pass when activating it
     */
    @Test
    public void decrementDaysPass() {
        assertEquals("OK", validator.validate(card, gate)); // Validate the card at the gate
        assertEquals(2, pass.getNbDays(), 0.0); // Checks nbDays decremented well when activated pass

        database.getTimeoutCards().put(card, new Date(new Date().getTime() - 70000)); // Updates the date associated to the card in timeout map (to allow a second passage here)
        assertEquals("OK", validator.validate(card, gate)); // Validate again
        assertNotEquals(1, pass.getNbDays(), 0.0); // Checks that it didn't decrement again since there is an activated pass
    }

    /**
     * Test the timeout on a card when validating several times
     */
    @Test
    public void timeoutCard() {
        assertEquals("OK", validator.validate(card, gate)); // Validate the card at the gate
        assertEquals("KO", validator.validate(card, gate)); // Tries to validate again, but timeout problem
        database.getTimeoutCards().put(card, new Date(new Date().getTime() - 50000)); // Updates the date associated to the card in timeout map with date of less than three minutes ago
        assertEquals("KO", validator.validate(card, gate)); // Validate the card at the gate
        database.getTimeoutCards().put(card, new Date(new Date().getTime() - 60000)); // Updates the date associated to the card in timeout map with date of three minutes ago
        assertEquals("OK", validator.validate(card, gate)); // Validate the card at the gate
    }

    /**
     * Validation with Fidelicime
     */
    @Test
    public void fidelicimeValidate() {
        FideliCimePass fideliCimePass = new FideliCimePass(AgePass.ADULT);
        fideliCimePass.setGateList(finder.getGatesByZone(ZonePass.ALL.toString()));
        //entityManager.persist(fideliCimePass);
        card.setPass(fideliCimePass);
        //entityManager.merge(card);
        assertEquals("OK", validator.validate(card, gateAll));
    }

    /**
     * Validate at a gate and checks that the timeout and passes maps contain the pass after
     */
    @Test
    public void validateInMap() {
        System.out.println(database);
        database.flush();
        assertTrue(database.getPasses().size() == 0); // Empty map of passes
        assertTrue(database.getTimeoutCards().size() == 0); // Empty map of timeout cards

        validator.validate(card, gate); // Validate at the gate
        assertTrue(database.getPasses().size() == 1); // Checks the pass is stocked in activated ones
        assertTrue(database.getTimeoutCards().size() == 1); // Checks the card is in timeout map
        assertEquals("KO",validator.validate(card,gate)); // Tries to validate again but timeout
    }
}