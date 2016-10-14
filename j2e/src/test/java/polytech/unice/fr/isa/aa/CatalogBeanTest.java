package polytech.unice.fr.isa.aa;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import polytech.unice.fr.isa.aa.business.AbstractPass;
import polytech.unice.fr.isa.aa.business.enums.AgePass;
import polytech.unice.fr.isa.aa.business.enums.TypePass;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;
import polytech.unice.fr.isa.aa.exceptions.PriceNotFoundException;
import polytech.unice.fr.isa.aa.interfaces.Catalog;

import javax.ejb.EJB;

import static org.junit.Assert.assertEquals;

/**
 * @author Lucas MARTINEZ
 * @version 4/26/16
 */
@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class CatalogBeanTest extends AbstractTest {

    @EJB
    Catalog catalog;

    protected AbstractPass abstractPass;

    @Before
    public void setUp() {
        abstractPass = new AbstractPass(TypePass.ONE_DAY, ZonePass.AURON, AgePass.ADULT);
        abstractPass.setPrice(20); //au hasard, ne respecte pas le vrai catalogue

        entityManager.persist(abstractPass);
    }

    @After
    public void cleaningUp() {
        entityManager.remove(abstractPass);
    }

    @Test
    public void getPriceForPassTest() throws PriceNotFoundException {
        double price = catalog.getPriceForPass(abstractPass);
        assertEquals(20, price, 0);
    }


}