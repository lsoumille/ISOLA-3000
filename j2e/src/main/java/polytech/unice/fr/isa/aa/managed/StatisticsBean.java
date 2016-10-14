package polytech.unice.fr.isa.aa.managed;

import polytech.unice.fr.isa.aa.business.PurchaseStat;
import polytech.unice.fr.isa.aa.interceptors.PurchaseCounter;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Optional;

/**
 * @author Lucas MARTINEZ
 * @version 4/30/16
 */
@ManagedBean
public class StatisticsBean implements Serializable{

    @PersistenceContext
    private EntityManager entityManager;

    public int getProcessed() {
        Calendar date = Calendar.getInstance();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PurchaseStat> crit = builder.createQuery(PurchaseStat.class);
        Root<PurchaseStat> root =  crit.from(PurchaseStat.class);
        Predicate restriction = builder.and(builder.equal(root.get("year"), date.get(Calendar.YEAR)),
                builder.equal(root.get("month"), date.get(Calendar.MONTH)),
                builder.equal(root.get("day"), date.get(Calendar.DAY_OF_YEAR)));
        crit.select(root).where(restriction);
        TypedQuery<PurchaseStat> queryCard = entityManager.createQuery(crit);
        try {
            Optional<PurchaseStat> opt = Optional.of(queryCard.getSingleResult());
            return opt.get().getCounter();
        } catch (NoResultException nre){
            return 0;
        }
    }
}
