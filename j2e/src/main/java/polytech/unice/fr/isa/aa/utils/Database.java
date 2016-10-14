package polytech.unice.fr.isa.aa.utils;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import polytech.unice.fr.isa.aa.business.*;
import polytech.unice.fr.isa.aa.business.enums.AgePass;
import polytech.unice.fr.isa.aa.business.enums.TypePass;
import polytech.unice.fr.isa.aa.business.enums.ZonePass;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * Created by lucas on 29/02/16.
 */
@Singleton
@Startup
public class Database {

    private Map<Card, Pass> cards = new HashMap<>(); // Map linking cards and passes
    private Map<Pass, Date> passes = new HashMap<>(); // Map containing passes and their date
    private Map<User, Card> users = new HashMap<>(); // Map linking users and cards
    private Map<Integer, Gate> gates = new HashMap<>(); // Map containing gates
    private Map<AbstractPass, Double> prices = new HashMap<>(); // Price for an AbstractPass (zone, age, and type)
    private Map<ZonePass, List<Gate>> zoneGates= new HashMap<>(); // List of gates linked to each zone
    private float priceCard = new Float(1.50); // Price of a Cime Card
    private float priceSubFidelicime = new Float(15.0);
    private Map<Card, Date> timeoutCards = new HashMap<>();

    private List<Gate> cacheGate = new ArrayList<>();
    private Map<Card, Date> cacheCard = new HashMap<>();

    public Database() {
        flush();
        initValidatePassDemo(); //tmp pour ValidatePassDemo côté client
        addUser();//client init de base pour les tests
        initPassesPrices(); // Temporary to fill prices map for some AbstractPass objects
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    private void init(){
        CriteriaBuilder builderGate = entityManager.getCriteriaBuilder();
        CriteriaQuery<Gate> criteriaGate = builderGate.createQuery(Gate.class);
        Root<Gate> rootGate =  criteriaGate.from(Gate.class);
        criteriaGate.select(rootGate);
        TypedQuery<Gate> queryGate = entityManager.createQuery(criteriaGate);

        if (queryGate.getResultList().isEmpty()){
            initGates();
        }

        CriteriaBuilder builderAbstractPass = entityManager.getCriteriaBuilder();
        CriteriaQuery<AbstractPass> criteriaAbstractPass = builderAbstractPass.createQuery(AbstractPass.class);
        Root<AbstractPass> rootAbstractPass =  criteriaAbstractPass.from(AbstractPass.class);
        criteriaAbstractPass.select(rootAbstractPass);
        TypedQuery<AbstractPass> queryAbstractPass = entityManager.createQuery(criteriaAbstractPass);

        if (queryAbstractPass.getResultList().isEmpty()){
            initPrices();
        }

    }

    private void initGates(){
        Gate gate = new Gate("Station PLS", "RPLS");
        gate.setZone(ZonePass.ALL);
        entityManager.persist(gate);

        gate = new Gate("Station Aix", "RAix");
        gate.setZone(ZonePass.ISOLA2000);
        entityManager.persist(gate);

        gate = new Gate("Station Aix", "RAix Bis");
        gate.setZone(ZonePass.ISOLA2000);
        entityManager.persist(gate);

        gate = new Gate("Station Coq", "RCoq");
        gate.setZone(ZonePass.AURON);
        entityManager.persist(gate);

        gate = new Gate("Station Avenue", "RAvenue");
        gate.setZone(ZonePass.SNOWPARK);
        entityManager.persist(gate);

        gate = new Gate("Station Plateau", "RPlateau");
        gate.setZone(ZonePass.BEGINNER);
        entityManager.persist(gate);
    }

    private void initPrices(){
        AbstractPass pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.BEGINNER, AgePass.CHILDREN_OVER_FIVE);
        pass.setPrice(new Float(15.0));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.BEGINNER, AgePass.ADULT);
        pass.setPrice(new Float(20.20));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.BEGINNER, AgePass.GOLDEN_AGE);
        pass.setPrice(new Float(17.10));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.SNOWPARK, AgePass.STUDENT);
        pass.setPrice(new Float(20.0));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.ALL, AgePass.ADULT);
        pass.setPrice(new Float(33.50));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.HALF_DAY, ZonePass.ALL, AgePass.ADULT);
        pass.setPrice(new Float(26.80));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.ALL, AgePass.GOLDEN_AGE);
        pass.setPrice(new Float(28.20));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.HALF_DAY, ZonePass.ALL, AgePass.GOLDEN_AGE);
        pass.setPrice(new Float(22.50));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.ALL, AgePass.STUDENT);
        pass.setPrice(new Float(28.20));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.HALF_DAY, ZonePass.ALL, AgePass.STUDENT);
        pass.setPrice(new Float(22.50));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.ALL, AgePass.TEENAGER);
        pass.setPrice(new Float(28.20));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.HALF_DAY, ZonePass.ALL, AgePass.TEENAGER);
        pass.setPrice(new Float(22.50));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.ALL, AgePass.CHILDREN_OVER_FIVE);
        pass.setPrice(new Float(25.70));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.HALF_DAY, ZonePass.ALL, AgePass.CHILDREN_OVER_FIVE);
        pass.setPrice(new Float(19.30));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.ALL, AgePass.ELDER);
        pass.setPrice(new Float(0.0));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.HALF_DAY, ZonePass.ALL, AgePass.ELDER);
        pass.setPrice(new Float(0.0));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.ALL, AgePass.CHILDREN_UNDER_FIVE);
        pass.setPrice(new Float(0.0));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.HALF_DAY, ZonePass.ALL, AgePass.CHILDREN_UNDER_FIVE);
        pass.setPrice(new Float(0.0));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.ISOLA2000, AgePass.ADULT);
        pass.setPrice(new Float(33.50));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.ISOLA2000, AgePass.TEENAGER);
        pass.setPrice(new Float(28.20));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.ISOLA2000, AgePass.GOLDEN_AGE);
        pass.setPrice(new Float(28.20));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.ISOLA2000, AgePass.STUDENT);
        pass.setPrice(new Float(28.20));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.ONE_DAY, ZonePass.ISOLA2000, AgePass.CHILDREN_OVER_FIVE);
        pass.setPrice(new Float(25.70));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.TWO_DAYS, ZonePass.ISOLA2000, AgePass.ADULT);
        pass.setPrice(new Float(65.30));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.TWO_DAYS, ZonePass.ISOLA2000, AgePass.TEENAGER);
        pass.setPrice(new Float(55.0));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.TWO_DAYS, ZonePass.ISOLA2000, AgePass.GOLDEN_AGE);
        pass.setPrice(new Float(55.0));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.TWO_DAYS, ZonePass.ISOLA2000, AgePass.STUDENT);
        pass.setPrice(new Float(55.0));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.TWO_DAYS, ZonePass.ISOLA2000, AgePass.CHILDREN_OVER_FIVE);
        pass.setPrice(new Float(48.30));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.THREE_DAYS, ZonePass.ISOLA2000, AgePass.ADULT);
        pass.setPrice(new Float(91.50));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.THREE_DAYS, ZonePass.ISOLA2000, AgePass.TEENAGER);
        pass.setPrice(new Float(78.9));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.THREE_DAYS, ZonePass.ISOLA2000, AgePass.GOLDEN_AGE);
        pass.setPrice(new Float(78.9));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.THREE_DAYS, ZonePass.ISOLA2000, AgePass.STUDENT);
        pass.setPrice(new Float(78.9));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.THREE_DAYS, ZonePass.ISOLA2000, AgePass.CHILDREN_OVER_FIVE);
        pass.setPrice(new Float(70.10));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.FOUR_DAYS, ZonePass.ISOLA2000, AgePass.ADULT);
        pass.setPrice(new Float(114.50));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.FOUR_DAYS, ZonePass.ISOLA2000, AgePass.TEENAGER);
        pass.setPrice(new Float(98.7));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.FOUR_DAYS, ZonePass.ISOLA2000, AgePass.GOLDEN_AGE);
        pass.setPrice(new Float(98.7));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.FOUR_DAYS, ZonePass.ISOLA2000, AgePass.STUDENT);
        pass.setPrice(new Float(98.7));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.FOUR_DAYS, ZonePass.ISOLA2000, AgePass.CHILDREN_OVER_FIVE);
        pass.setPrice(new Float(87.7));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.FIVE_DAYS, ZonePass.ISOLA2000, AgePass.ADULT);
        pass.setPrice(new Float(135.30));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.FIVE_DAYS, ZonePass.ISOLA2000, AgePass.TEENAGER);
        pass.setPrice(new Float(117.40));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.FIVE_DAYS, ZonePass.ISOLA2000, AgePass.GOLDEN_AGE);
        pass.setPrice(new Float(117.40));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.FIVE_DAYS, ZonePass.ISOLA2000, AgePass.STUDENT);
        pass.setPrice(new Float(117.40));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.FIVE_DAYS, ZonePass.ISOLA2000, AgePass.CHILDREN_OVER_FIVE);
        pass.setPrice(new Float(105.5));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.SIX_DAYS, ZonePass.ISOLA2000, AgePass.ADULT);
        pass.setPrice(new Float(156.80));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.SIX_DAYS, ZonePass.ISOLA2000, AgePass.TEENAGER);
        pass.setPrice(new Float(133.0));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.SIX_DAYS, ZonePass.ISOLA2000, AgePass.GOLDEN_AGE);
        pass.setPrice(new Float(133.0));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.SIX_DAYS, ZonePass.ISOLA2000, AgePass.STUDENT);
        pass.setPrice(new Float(133.0));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.SIX_DAYS, ZonePass.ISOLA2000, AgePass.CHILDREN_OVER_FIVE);
        pass.setPrice(new Float(120.30));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.SEVEN_DAYS, ZonePass.ISOLA2000, AgePass.ADULT);
        pass.setPrice(new Float(174.80));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.SEVEN_DAYS, ZonePass.ISOLA2000, AgePass.TEENAGER);
        pass.setPrice(new Float(148.20));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.SEVEN_DAYS, ZonePass.ISOLA2000, AgePass.GOLDEN_AGE);
        pass.setPrice(new Float(148.20));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.SEVEN_DAYS, ZonePass.ISOLA2000, AgePass.STUDENT);
        pass.setPrice(new Float(148.20));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.SEVEN_DAYS, ZonePass.ISOLA2000, AgePass.CHILDREN_OVER_FIVE);
        pass.setPrice(new Float(135.30));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.EIGHT_DAYS, ZonePass.ISOLA2000, AgePass.ADULT);
        pass.setPrice(new Float(190.20));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.EIGHT_DAYS, ZonePass.ISOLA2000, AgePass.TEENAGER);
        pass.setPrice(new Float(162.40));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.EIGHT_DAYS, ZonePass.ISOLA2000, AgePass.GOLDEN_AGE);
        pass.setPrice(new Float(162.40));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.EIGHT_DAYS, ZonePass.ISOLA2000, AgePass.STUDENT);
        pass.setPrice(new Float(162.40));
        entityManager.persist(pass);

        pass = new AbstractPass(TypePass.EIGHT_DAYS, ZonePass.ISOLA2000, AgePass.CHILDREN_OVER_FIVE);
        pass.setPrice(new Float(147.4));
        entityManager.persist(pass);
    }

    public Map<Card, Date> getCacheCard() {
        return cacheCard;
    }

    public void addCardToCache(Card c){
        cacheCard.put(c, new Date());
    }

    public List<Gate> getCacheGate() {
        return cacheGate;
    }

    public void addGateIntoCache(Gate g){
        cacheGate.add(g);
    }

    public float getPriceSubFidelicime() {
        return priceSubFidelicime;
    }

    public Map<Card, Pass> getCards() {
        return cards;
    }

    public Map<Pass, Date> getPasses() {
        return passes;
    }

    public Map<Integer, Gate> getGates() {
        return gates;
    }

    /**
     * Update the database by removing passes which duration ended
     */
    public void updateDate() {
        for (Map.Entry<Pass, Date> entry : passes.entrySet()) {
            if (entry.getValue().before(new Date())){
                passes.remove(entry.getKey());
            }
        }

        Iterator it = cacheCard.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry item = (Map.Entry)it.next();
            if(((Date)item.getValue()).before(new Date())){
                it.remove();
            }
        }
        /*for (Map.Entry<Card, Date> entry : cacheCard.entrySet()) {
            if (entry.getValue().before(new Date())){
                cacheCard.remove(entry.getKey());
            }
        }*/
        //java.sql.Date currentDate = java.sql.Date.valueOf(java.time.LocalDate.now());
        //entityManager.createQuery("UPDATE Pass SET isActivated=false WHERE dateEnd < :currentDate AND isFidelicime=:isFidelicime").setParameter("currentDate", currentDate, TemporalType.DATE).setParameter("isFidelicime", true).executeUpdate();
    }

    public Map<AbstractPass, Double> getPrices() {
        return prices;
    }

    public float getPriceCard() {
        return priceCard;
    }

    public Map<ZonePass, List<Gate>> getZoneGates() {
        return zoneGates;
    }

    public void setZoneGates(Map<ZonePass, List<Gate>> zoneGates) {
        this.zoneGates = zoneGates;
    }

    public Map<User, Card> getUsers() {
        return users;
    }

    /**
     * Flush the database
     */
    public void flush() {
        users = new HashMap<>();
        cards = new HashMap<>();
        passes = new HashMap<>();
        gates = new HashMap<>();
        cacheGate = new ArrayList<>();
        cacheCard = new HashMap<>();
        timeoutCards = new HashMap<>();
    }

    private void initValidatePassDemo(){
        Card c = new Card();
        Pass p = new Pass();
        Gate g = new Gate();
        gates.put(1, g);
        List gatesList = new ArrayList<>(gates.values());

        p.setGateList(gatesList);
        c.setId("1");
        cards.put(c, p);

        zoneGates.put(ZonePass.SNOWPARK, gatesList);
    }

    private void addUser(){
        User lucas = new User("LUCAS","LUCAS",8,"0000","lucassoumille@yahoo.frz");
        users.put(lucas, null);
    }

    /**
     * Initializes the map with prices with some possible combinations of zone, type and age
     */
    private void initPassesPrices() {
        prices.put(new AbstractPass(TypePass.ONE_DAY, ZonePass.BEGINNER, AgePass.CHILDREN_OVER_FIVE), 15.0); // 15 euros for one day in begginer zone as a children over five
        prices.put(new AbstractPass(TypePass.ONE_DAY, ZonePass.BEGINNER, AgePass.ADULT), 20.20); // 20,20 euros for one day in begginer zone as an adult
        prices.put(new AbstractPass(TypePass.ONE_DAY, ZonePass.BEGINNER, AgePass.GOLDEN_AGE), 17.10); // 17,10 euros for one day in begginer zone as a golden age
        prices.put(new AbstractPass(TypePass.ONE_DAY, ZonePass.SNOWPARK, AgePass.STUDENT), 20.0); // 20 euros for one day in snowpark as student
        prices.put(new AbstractPass(TypePass.FOUR_DAYS, ZonePass.ISOLA2000, AgePass.GOLDEN_AGE), 98.70); // 98,70 euros for four days in ISOLA 2000 as a golden age
        prices.put(new AbstractPass(TypePass.FOUR_DAYS, ZonePass.ISOLA2000, AgePass.ADULT), 114.50); // 114,50 euros for four days in ISOLA 2000 as an adult
        prices.put(new AbstractPass(TypePass.FOUR_DAYS, ZonePass.ISOLA2000, AgePass.CHILDREN_OVER_FIVE), 87.70); // 87,70 euros for four days in ISOLA 2000 as a children over five
    }

    public Map<Card, Date> getTimeoutCards() {
        return timeoutCards;
    }

    public void setTimeoutCards(Map<Card, Date> timeoutPasses) {
        this.timeoutCards = timeoutPasses;
    }
}
