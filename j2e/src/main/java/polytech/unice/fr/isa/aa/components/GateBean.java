package polytech.unice.fr.isa.aa.components;

import polytech.unice.fr.isa.aa.business.Gate;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;
import polytech.unice.fr.isa.aa.interfaces.GateFinder;
import polytech.unice.fr.isa.aa.utils.Database;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Lucas MARTINEZ
 * @version 19/03/16
 */

@Stateless(name="gate")
public class GateBean implements GateFinder {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private Database database;

    /**
     * Gets the gate associated to the gateId in parameter
     * @param gateId
     * @return
     */
    @Override
    public Optional<Gate> findById(int gateId){
        for(Gate g : database.getCacheGate()){
            if(g.getId() == gateId)
                return Optional.of(g);
        }
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Gate> criteria = builder.createQuery(Gate.class);
        Root<Gate> root =  criteria.from(Gate.class);
        Predicate restriction = builder.equal(root.get("id"), gateId);
        criteria.select(root).where(restriction);
        TypedQuery<Gate> query = entityManager.createQuery(criteria);
        try {
            Gate gate = query.getSingleResult();
            database.addGateIntoCache(gate);
            return Optional.of(gate);
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }

    /**
     * return the gates which are in the zone
     * @param zone
     * @return
     *
     */
    @Override
    public List<Gate> getGatesByZone(String zone) {
        ZonePass zonePass = null;
        for(ZonePass z : ZonePass.values())
            if(z.toString().equals(zone))
                zonePass = z;

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Gate> criteria = builder.createQuery(Gate.class);
        Root<Gate> root =  criteria.from(Gate.class);
        Predicate restriction = builder.equal(root.get("zone"), zonePass);
        criteria.select(root).where(restriction);
        TypedQuery<Gate> query = entityManager.createQuery(criteria);
        try {
            return query.getResultList();
        } catch (NoResultException nre){
            return new ArrayList<>();
        }

    }
}

