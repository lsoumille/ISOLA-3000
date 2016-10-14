package polytech.unice.fr.isa.aa.components;

import polytech.unice.fr.isa.aa.business.AbstractPass;
import polytech.unice.fr.isa.aa.business.enums.AgePass;
import polytech.unice.fr.isa.aa.business.enums.TypePass;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;
import polytech.unice.fr.isa.aa.exceptions.PriceNotFoundException;
import polytech.unice.fr.isa.aa.interfaces.Catalog;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Nicolas HORY
 * @version 22/03/16.
 */
@Stateless
public class CatalogBean implements Catalog{

    @EJB
    private Database database;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Creates a list containing every combination possible of ZonePass, AgePass and TypePass
     * @return listAllPasses
     */
    @Override
    public List<AbstractPass> getAllTypesOfPass() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AbstractPass> criteria = builder.createQuery(AbstractPass.class);
        Root<AbstractPass> root =  criteria.from(AbstractPass.class);
        Predicate restriction = builder.and(builder.notEqual(root.get("price"), 0));
        criteria.select(root).where(restriction);
        TypedQuery<AbstractPass> query = entityManager.createQuery(criteria);
        try {
            return query.getResultList();
        } catch (NoResultException nre){
            return new ArrayList<>();
        }
    }

    /**
     * Gets a price for an AbstractPass
     * @param pass
     * @return price
     */
    @Override
    public float getPriceForPass(AbstractPass pass) throws PriceNotFoundException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AbstractPass> criteria = builder.createQuery(AbstractPass.class);
        Root<AbstractPass> root =  criteria.from(AbstractPass.class);
        Predicate restriction = builder.and(builder.equal(root.get("type"), pass.getType()),
                                            builder.equal(root.get("zone"), pass.getZone()),
                                            builder.equal(root.get("age"), pass.getAge()),
                                            builder.notEqual(root.get("price"), 0));
        criteria.select(root).where(restriction);
        TypedQuery<AbstractPass> query = entityManager.createQuery(criteria);
        try {
            return query.getSingleResult().getPrice();
        } catch (NoResultException nre){
            throw new PriceNotFoundException();
        }
    }

    /**
     * Gets the price of Cime Card
     * @return price
     */
    @Override
    public float getPriceCard() {
        return database.getPriceCard();
    }

    @Override
    public float getPriceSubFidelicime() {
        return database.getPriceSubFidelicime();
    }
}
