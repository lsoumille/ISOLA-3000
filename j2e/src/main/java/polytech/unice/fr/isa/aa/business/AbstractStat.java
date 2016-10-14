package polytech.unice.fr.isa.aa.business;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lucas on 29/04/16.
 */
@Entity
public class AbstractStat {

    @NotNull
    private Calendar date;

    @NotNull
    private int year;

    @NotNull
    private int day;

    @NotNull
    private int month;

    public AbstractStat() {
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractStat that = (AbstractStat) o;

        if (getYear() != that.getYear()) return false;
        if (getDay() != that.getDay()) return false;
        if (getMonth() != that.getMonth()) return false;
        return getDate() != null ? getDate().equals(that.getDate()) : that.getDate() == null;

    }

    @Override
    public int hashCode() {
        int result = getDate() != null ? getDate().hashCode() : 0;
        result = 31 * result + getYear();
        result = 31 * result + getDay();
        result = 31 * result + getMonth();
        return result;
    }
}
