package persistence;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import polytech.unice.fr.isa.aa.AbstractTest;
import polytech.unice.fr.isa.aa.business.*;
import polytech.unice.fr.isa.aa.business.enums.AgePass;
import polytech.unice.fr.isa.aa.business.enums.TypePass;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by lucas on 15/04/16.
 */
@RunWith(Arquillian.class)
public class PersistenceTest extends AbstractTest {

    @PersistenceContext
    private EntityManager entityManager;

    private Gate g;
    private Pass p;

    @Before
    public void setUp(){
        g = new Gate();
        g.setName("PIGEON");
        g.setStation("PIGEONNIERE");
        g.setZone(ZonePass.AURON);
        p = new Pass();
        List<Gate> gates = new ArrayList<>();
        gates.add(g);
        p.setGateList(gates);
        p.setAge(AgePass.GOLDEN_AGE);
        p.setType(TypePass.EIGHT_DAYS);
        p.setZone(ZonePass.ALL);
        p.setNbDays(3);
        p.setPrice(0);
        //p.setActivated(true);
    }


    @Test
    @Transactional(TransactionMode.COMMIT)
    public void testGateStorage() throws Exception {
//        Gate g = new Gate();
//        g.setName("PIGEON");
//        g.setStation("PIGEONNIERE");
        assertEquals(0, g.getId());
        entityManager.persist(g);
        assertNotEquals(0,g.getId());
        Gate stored = (Gate) entityManager.find(Gate.class, g.getId());
        assertEquals(g,stored);
    }

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void testPassStorage() throws Exception {
        /*Pass p = new Pass();
        Gate g = new Gate();
        g.setName("PIGEON");
        g.setStation("PIGEONNIERE");
        List<Gate> gates = new ArrayList<>();
        gates.add(g);

        p.setGateList(gates);
        p.setAge(AgePass.GOLDEN_AGE);
        p.setType(TypePass.EIGHT_DAYS);
        p.setZone(ZonePass.ALL);
        p.setPrice(0); */
        p.setActivated(true);
        entityManager.persist(g);
        entityManager.persist(p);

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Pass> criteria = builder.createQuery(Pass.class);
        Root<Pass> root =  criteria.from(Pass.class);

        criteria.select(root).where(builder.equal(root.get("isActivated"), true));
        TypedQuery<Pass> query = entityManager.createQuery(criteria);
        //Pass stored = (Pass) entityManager.find(Pass.class, query);
        for(Pass pa : query.getResultList()){
            System.out.println(pa.getActivated());
        }
        assertEquals(p, query.getSingleResult());
    }

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void testCardStorage() throws Exception {
        //Pass p = new Pass();
        //Gate g = new Gate();
        //g.setName("PIGEON");
        //g.setStation("PIGEONNIERE");
        //List<Gate> gates = new ArrayList<>();
        //gates.add(g);

        /*p.setGateList(gates);
        p.setAge(AgePass.GOLDEN_AGE);
        p.setType(TypePass.EIGHT_DAYS);
        p.setZone(ZonePass.ALL);
        p.setPrice(0); */
        p.setActivated(false);
        entityManager.persist(g);
        entityManager.persist(p);
        /*
        boolean isActivated = true;
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Pass> criteria = builder.createQuery(Pass.class);
        Root<Pass> root =  criteria.from(Pass.class);

        criteria.select(root).where(builder.equal(root.get("isActivated"), isActivated));
        TypedQuery<Pass> query = entityManager.createQuery(criteria);
        //Pass stored = (Pass) entityManager.find(Pass.class, query);
        assertEquals(p, query.getSingleResult());*/

        Card c = new Card();
        c.setPass(p);
        //c.setId(UUID.randomUUID().toString());
        String id = c.getId();
        entityManager.persist(c);
        Card stored = (Card) entityManager.find(Card.class, id);
        assertEquals(c, stored);
    }

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void testUserStorage() throws Exception {
       /* Pass p = new Pass();
        Gate g = new Gate();
        g.setName("PIGEON");
        g.setStation("PIGEONNIERE");
        List<Gate> gates = new ArrayList<>();
        gates.add(g);

        p.setGateList(gates);
        p.setAge(AgePass.GOLDEN_AGE);
        p.setType(TypePass.EIGHT_DAYS);
        p.setZone(ZonePass.ALL);
        p.setPrice(0);
        p.setActivated(true);*/
        entityManager.persist(g);
        entityManager.persist(p);
        Card c = new Card();
        c.setPass(p);
        entityManager.persist(c);

        User user = new User();
        user.setFirstName("Nico");
        user.setName("Micro");
        user.setAge(12);
        user.setCreditCard("0000_0000");
        user.setCard(c);
        Set<Transaction> set = new HashSet<>();
        Transaction trans = new Transaction();
        trans.setUser(user);
        trans.setObjects(c);
        //entityManager.persist(trans);
        set.add(trans);
        user.setAllTransaction(set);
        entityManager.persist(user);
        User stored = (User) entityManager.find(User.class, user.getId());
        assertEquals(user, stored);
        assertEquals(stored.getAllTransaction().iterator().next(), trans);
    }
}
