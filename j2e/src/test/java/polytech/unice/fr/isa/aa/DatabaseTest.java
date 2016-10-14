package polytech.unice.fr.isa.aa;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import polytech.unice.fr.isa.aa.business.Pass;

import javax.ejb.EJB;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author Lucas MARTINEZ
 * @version 07/03/16
 */
@RunWith(Arquillian.class)
public class DatabaseTest extends AbstractTest{

    protected Pass pass;

    private DateFormat formatter;
    private Date date;

    @Before
    public void setUp() throws ParseException {
        database.flush();
        formatter = new SimpleDateFormat("dd/MM/yy");
        pass = new Pass();
    }

    @Test
    public void updateDeleteTest() throws Exception{
        assertEquals(database.getPasses().size(), 0);
        date = formatter.parse("29/01/16");
        database.getPasses().put(pass, date);

        assertEquals(database.getPasses().size(), 1);
        database.updateDate();
        assertEquals(database.getPasses().size(), 0);
    }

    @Test
    public void updateKeepTest() throws Exception{
        assertEquals(database.getPasses().size(), 0);
        date = formatter.parse("29/06/16");
        database.getPasses().put(pass, date);

        assertEquals(database.getPasses().size(), 1);
        database.updateDate();
        assertEquals(database.getPasses().size(), 1);
    }

}

