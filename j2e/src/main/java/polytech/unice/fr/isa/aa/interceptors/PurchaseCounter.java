package polytech.unice.fr.isa.aa.interceptors;

import polytech.unice.fr.isa.aa.business.Card;
import polytech.unice.fr.isa.aa.business.PurchaseStat;
import polytech.unice.fr.isa.aa.utils.Database;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
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
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * Created by lucas on 05/04/16.
 */
public class PurchaseCounter implements Serializable{

    @PersistenceContext
    private EntityManager entityManager;

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        Object result = ctx.proceed();  // do what you're supposed to do
        if(result.toString().equals("true")){
            incrementNbPurchase();
            System.out.println("Purchase processed.");
        }
        return result;
    }

    /**
     * Increment the nb of purchase for the date in param
     */
    private void incrementNbPurchase(){
        Calendar now = Calendar.getInstance();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PurchaseStat> crit = builder.createQuery(PurchaseStat.class);
        Root<PurchaseStat> root =  crit.from(PurchaseStat.class);
        Predicate restriction = builder.and(builder.equal(root.get("year"), now.get(Calendar.YEAR)),
                                            builder.equal(root.get("month"), now.get(Calendar.MONTH)),
                                            builder.equal(root.get("day"), now.get(Calendar.DAY_OF_YEAR)));
        crit.select(root).where(restriction);
        TypedQuery<PurchaseStat> queryCard = entityManager.createQuery(crit);
        try {
            PurchaseStat stat = queryCard.getSingleResult();
            stat.setCounter(stat.getCounter() + 1);
        } catch (NoResultException e){
            PurchaseStat stat = new PurchaseStat();
            stat.setCounter(1);
            stat.setDate(now);
            stat.setYear(now.get(Calendar.YEAR));
            stat.setMonth(now.get(Calendar.MONTH));
            stat.setDay(now.get(Calendar.DAY_OF_YEAR));
            entityManager.persist(stat);
        }

    }
}
