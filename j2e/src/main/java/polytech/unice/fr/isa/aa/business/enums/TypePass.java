package polytech.unice.fr.isa.aa.business.enums;

/**
 * @author lucas
 * @version 07/03/16.
 */
public enum TypePass {
    HALF_DAY(0.5), ONE_DAY(1), TWO_DAYS(2), THREE_DAYS(3), FOUR_DAYS(4), FIVE_DAYS(5),
    SIX_DAYS(6), SEVEN_DAYS(7), EIGHT_DAYS(8), MORE_THAN_NINE_DAYS(9);

    private double nbDays;

    TypePass(double nbDays) {
        this.nbDays = nbDays;
    }

    public double getNbDays() {
        return nbDays;
    }
}
