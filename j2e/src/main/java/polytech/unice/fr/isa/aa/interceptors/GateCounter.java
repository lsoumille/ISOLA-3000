package polytech.unice.fr.isa.aa.interceptors;

import polytech.unice.fr.isa.aa.business.Gate;
import polytech.unice.fr.isa.aa.business.GateStat;
import polytech.unice.fr.isa.aa.business.PurchaseStat;
import polytech.unice.fr.isa.aa.utils.Database;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.*;

/**
 * Created by lucas on 15/04/16.
 */
public class GateCounter implements Serializable {
    @PersistenceContext
    private EntityManager entityManager;

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        Object[] params = ctx.getParameters();
        Object result = ctx.proceed();  // do what you're supposed to do
        if(result.toString().equals("OK")){
            incrementNbPassageForGate((Gate)params[1]);
            System.out.println("Passage");
        }
        return result;
    }

    private void incrementNbPassageForGate(Gate g){
        Calendar now = Calendar.getInstance();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GateStat> crit = builder.createQuery(GateStat.class);
        Root<GateStat> root =  crit.from(GateStat.class);
        Predicate restriction = builder.and(builder.equal(root.get("year"), now.get(Calendar.YEAR)),
                builder.equal(root.get("month"), now.get(Calendar.MONTH)),
                builder.equal(root.get("day"), now.get(Calendar.DAY_OF_YEAR)),
                builder.equal(root.get("hour"), now.get(Calendar.HOUR_OF_DAY)),
                builder.equal(root.get("minutes"), now.get(Calendar.MINUTE)),
                builder.equal(root.get("gate"), g));
        crit.select(root).where(restriction);
        TypedQuery<GateStat> queryCard = entityManager.createQuery(crit);
        try {
            GateStat stat = queryCard.getSingleResult();
            stat.setCounter(stat.getCounter() + 1);
        } catch (NoResultException e){
            GateStat stat = new GateStat();
            stat.setCounter(1);
            stat.setDate(now);
            stat.setYear(now.get(Calendar.YEAR));
            stat.setMonth(now.get(Calendar.MONTH));
            stat.setDay(now.get(Calendar.DAY_OF_YEAR));
            stat.setHour(now.get(Calendar.HOUR_OF_DAY));
            stat.setMinutes(now.get(Calendar.MINUTE));
            stat.setGate(g);
            entityManager.persist(stat);
        }
    }
}
