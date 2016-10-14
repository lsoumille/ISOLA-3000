package polytech.unice.fr.isa.aa;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import polytech.unice.fr.isa.aa.business.Gate;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;
import polytech.unice.fr.isa.aa.interfaces.GateFinder;

import javax.ejb.EJB;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Lucas MARTINEZ
 * @version 4/26/16
 */

@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class GateBeanTest extends AbstractTest{

    @EJB
    GateFinder gateFinder;

    protected Gate gate, gate2;
    protected List<Gate> gateList;

    @Before
    public void setUp() {
        //database.flush();

        gate = new Gate();
        gate.setName("Nom");
        gate.setStation("Station");
        gate.setZone(ZonePass.AURON);
        entityManager.persist(gate);

        gate2 = new Gate();
        gate2.setName("Nom2");
        gate2.setStation("Station2");
        gate2.setZone(ZonePass.AURON);
        entityManager.persist(gate2);

        //database.getGates().put(1, gate);
        gateList = new ArrayList<>();
        gateList.add(gate);
        gateList.add(gate2);
        //entityManager.persist(gateList);
        //database.getZoneGates().put(ZonePass.ALL, gateList);
    }

    @After
    public void cleaningUp() {
        Optional<Gate> toDispose = gateFinder.findById(gate.getId());
        Optional<Gate> toDispose2 = gateFinder.findById(gate2.getId());
        if(toDispose.isPresent()) {
            Gate g = entityManager.merge(toDispose.get());
            entityManager.remove(g);
        }

        if(toDispose2.isPresent()) {
            Gate g = entityManager.merge(toDispose2.get());
            entityManager.remove(g);
        }
    }

    /**
     * Tests the findGate method
     */
    @Test
    public void findGateTest() {
        Optional<Gate> gateOptional = gateFinder.findById(gate.getId());
        Gate found = new Gate();
        if (gateOptional.isPresent()){
            found = gateOptional.get();
        }

        assertEquals(gate, found);
        assertEquals(1, database.getCacheGate().size());

        found = null;
        Optional<Gate> gateOptionalNull = gateFinder.findById(1000); //id n'existe pas
        if (gateOptionalNull.isPresent()){
            found = gateOptionalNull.get();
        }
        assertEquals(null, found);
    }

    /**
     * Tests the getGatesByZone method
     */
    @Test
    public void getGateByZoneTest() {
        List<Gate> gates = gateFinder.getGatesByZone("AURON");
        for (Gate g : gates){
            assertTrue(entityManager.contains(g));
        }


        List<Gate> gatesEmpty = gateFinder.getGatesByZone("JE CRAQUE");
        assertEquals(new ArrayList<Gate>(), gatesEmpty);
    }
}

