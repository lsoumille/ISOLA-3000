package polytech.unice.fr.isa.aa.managed;

import polytech.unice.fr.isa.aa.business.Gate;
import polytech.unice.fr.isa.aa.business.GateStat;
import polytech.unice.fr.isa.aa.components.GateBean;
import polytech.unice.fr.isa.aa.interceptors.GateCounter;
import polytech.unice.fr.isa.aa.interfaces.GateFinder;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
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
public class StatPerGateBean implements Serializable{

    @PersistenceContext
    private EntityManager entityManager;

    @EJB private GateFinder gateBean;

    @ManagedProperty("#{param.gateId}")
    private String gateId;

    public String getGateId() {
        return gateId;
    }

    public void setGateId(String gateId) {
        this.gateId = gateId;
    }

    public String getGateName(){
        if(gateId == null) { return "No gateId given!"; }
        return gateBean.findById(Integer.parseInt(gateId)).get().getName();
    }

    public String getGateStation(){
        if(gateId == null) { return "No gateId given!"; }
        return gateBean.findById(Integer.parseInt(gateId)).get().getStation();
    }

    public String getGateZone(){
        if(gateId == null) { return "No gateId given!"; }
        return gateBean.findById(Integer.parseInt(gateId)).get().getZone().toString();
    }

    public int getGateCounter(){
        Calendar date = Calendar.getInstance();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GateStat> crit = builder.createQuery(GateStat.class);
        Root<GateStat> root =  crit.from(GateStat.class);
        Predicate restriction = builder.and(builder.equal(root.get("year"), date.get(Calendar.YEAR)),
                builder.equal(root.get("month"), date.get(Calendar.MONTH)),
                builder.equal(root.get("day"), date.get(Calendar.DAY_OF_YEAR)),
                /*builder.equal(root.get("hour"), date.get(Calendar.HOUR_OF_DAY)),
                builder.equal(root.get("minutes"), date.get(Calendar.MINUTE)),*/
                builder.equal(root.get("gate"), gateBean.findById(Integer.parseInt(gateId)).get()));
        crit.select(root).where(restriction);
        TypedQuery<GateStat> queryCard = entityManager.createQuery(crit);
        try {
            Optional<GateStat> opt = Optional.of(queryCard.getSingleResult());
            return opt.get().getCounter();
        } catch (NoResultException nre){
            return 0;
        }
    }


}
