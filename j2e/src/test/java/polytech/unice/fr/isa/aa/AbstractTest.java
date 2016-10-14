package polytech.unice.fr.isa.aa;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;
import polytech.unice.fr.isa.aa.components.CardBean;
import polytech.unice.fr.isa.aa.components.UserBean;
import polytech.unice.fr.isa.aa.exceptions.AlreadyExistingUserException;
import polytech.unice.fr.isa.aa.exceptions.UnknownCardException;
import polytech.unice.fr.isa.aa.exceptions.UnknownUserException;
import polytech.unice.fr.isa.aa.interfaces.CardModifier;
import polytech.unice.fr.isa.aa.interfaces.UserModifier;
import polytech.unice.fr.isa.aa.utils.Database;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Lucas MARTINEZ
 * @version 07/03/16
 */
@RunWith(Arquillian.class)
public abstract class AbstractTest {
    @EJB
    protected Database database;

    @PersistenceContext
    protected EntityManager entityManager;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addPackage(CardModifier.class.getPackage())
                .addPackage(UnknownCardException.class.getPackage())
                .addPackage(Database.class.getPackage())
                .addPackage(CardBean.class.getPackage())
                .addPackage(UserModifier.class.getPackage())
                .addPackage(UserBean.class.getPackage())
                .addPackage(AlreadyExistingUserException.class.getPackage())
                .addPackage(UnknownUserException.class.getPackage())
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }
}