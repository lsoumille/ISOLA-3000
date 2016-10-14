package polytech.unice.fr.isa.aa;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import polytech.unice.fr.isa.aa.business.*;
import polytech.unice.fr.isa.aa.business.enums.AgePass;
import polytech.unice.fr.isa.aa.business.enums.TypePass;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;
import polytech.unice.fr.isa.aa.exceptions.PaymentException;
import polytech.unice.fr.isa.aa.exceptions.UnknownCardException;
import polytech.unice.fr.isa.aa.interfaces.Payment;
import polytech.unice.fr.isa.aa.utils.BankAPI;

import javax.ejb.EJB;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

/**
 * Created by lucas on 16/03/16.
 */

@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)

public class PurchaseTest extends AbstractTest {

    @EJB
    private Payment payment;

    private User lucas;
    private User nico;
    private Card card;
    private Pass pass;
    private Gate gate;
    private List<Gate> gates;
    private Set<Transaction> transactions;

    @Before
    public void setUpContext() throws Exception{
        //database.flush();

        card = new Card();
        pass = new Pass();
        gate = new Gate();
        gates = new ArrayList<>();
        transactions = new HashSet<>();
        lucas = new User();

        gate.setName("Nom");
        gate.setStation("Station");
        gate.setZone(ZonePass.AURON);

        gates.add(gate);

        pass.setZone(ZonePass.AURON);
        pass.setAge(AgePass.ADULT);
        pass.setType(TypePass.HALF_DAY);
        pass.setPrice(new Float(10.0));
        pass.setActivated(true);
        pass.setGateList(gates);

        card.setPass(pass);

        lucas.setName("Sousou");
        lucas.setFirstName("Lucas");
        lucas.setAge(21);
        lucas.setCard(card);
        lucas.setCreditCard("0000");
        lucas.setAllTransaction(transactions);

        nico = new User();
        nico.setName("Hory");
        nico.setFirstName("Nico");
        nico.setAge(12);
        nico.setCard(new Card());


        entityManager.persist(gate);
        entityManager.persist(pass);
        entityManager.persist(card);
        entityManager.persist(lucas);
        entityManager.persist(nico);
//        database.getCards().put(card,null);
//        database.getUsers().put(lucas, null);
//        database.getUsers().put(nico, null);
        // Mocking the external partner
        BankAPI mocked = mock(BankAPI.class);
        payment.useBankReference(mocked);
        when(mocked.performPayment(eq(lucas.getCreditCard()), anyDouble())).thenReturn(true);
        when(mocked.performPayment(eq(nico.getCreditCard()),  anyDouble())).thenReturn(false);
    }

    @After
    public void cleaningUp() {
        entityManager.remove(gate);
        entityManager.remove(pass);
        entityManager.remove(card);
        entityManager.remove(lucas);
        entityManager.remove(nico);
    }

    /**
     * check if the card is added after the payment
     * @throws Exception
     */
    @Test
    public void testPaymentCard() throws Exception {
        payment.payCardForUser(lucas, card);
        assertEquals(card, lucas.getCard());
    }

    /**
     * check if the payment is not correct
     */
    @Test(expected = PaymentException.class)
    public void testNoPaymentCard() throws Exception {
        payment.payCardForUser(nico, card);
    }

    /**
     * check if the pass is added after the payment
     */
    @Test
    public void testPaymentPass() throws Exception{
        payment.payPassForUser(lucas, pass);
        assertEquals(pass, lucas.getCard().getPass());
    }

    /**
     * check if the payment is not correct
     */
    @Test(expected = PaymentException.class)
    public void testNoPaymentPass() throws Exception {
        payment.payPassForUser(nico, pass);
    }
}
