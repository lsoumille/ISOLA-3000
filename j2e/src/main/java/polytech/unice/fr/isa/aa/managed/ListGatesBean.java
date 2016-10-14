package polytech.unice.fr.isa.aa.managed;

import polytech.unice.fr.isa.aa.business.Gate;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;

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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucas MARTINEZ
 * @version 4/30/16
 */
@ManagedBean
public class ListGatesBean implements Serializable{

    @PersistenceContext
    private EntityManager entityManager;

    private List<Gate> listGates;

    public List<Gate> getListGates(){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Gate> crit = builder.createQuery(Gate.class);
        Root<Gate> root =  crit.from(Gate.class);

        crit.select(root);
        TypedQuery<Gate> queryCard = entityManager.createQuery(crit);
        try {
             listGates = queryCard.getResultList();
        } catch (NoResultException nre){
            listGates = new ArrayList<>();
        }

        return listGates;

    }
}
