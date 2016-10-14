package polytech.unice.fr.isa.aa.interfaces;

import polytech.unice.fr.isa.aa.business.Gate;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Lucas MARTINEZ
 * @version 19/03/16
 */
@Local
public interface GateFinder {

    Optional<Gate> findById(int gateId);

    List<Gate> getGatesByZone(String zone);
}
