/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

import java.util.GregorianCalendar;

/**
 *
 * @author Alexandre
 */
@Named(value = "dateMB")
@SessionScoped
public class DateMB implements Serializable {

    private static final int NBYEARS = 82;
    private final GregorianCalendar now;
    /**
     * Creates a new instance of DateMB
     */
    public DateMB() {
        now = new GregorianCalendar();
    }
    
    @SuppressWarnings("empty-statement")
    public int[] getDays() {
        int[] days = new int[31];
        for(int i = 0; i < days.length; days[i]=++i);
        return days;
    }
    
    @SuppressWarnings("empty-statement")
    public int[] getYears() {
        int[]years = new int[NBYEARS];
        for(int i=0; i < years.length;years[i]=now.get(GregorianCalendar.YEAR)-18-i++);
        return years;
    }
}
