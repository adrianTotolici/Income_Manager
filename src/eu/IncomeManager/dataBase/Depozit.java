package eu.IncomeManager.dataBase;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by adrian on 9/16/2014.
 */
@Entity
@Table(name = "depozit")
public class Depozit {
    @Id
    @GeneratedValue
    @Column
    private int idDepozit;

    @Column (name = "nume")
    private String nume;

    @Column (name = "tabIndex")
    private int tabIndex;

    public Depozit(){};

    public Depozit(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getIdDepozit() {
        return idDepozit;
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }
}
