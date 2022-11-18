package eu.IncomeManager.dataBase;

import eu.IncomeManager.Utils.Enumerari.DobandaType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loans")
public class Loans {

    @Id
    @GeneratedValue
    @Column (name = "idLoans")
    private int id_loans;

    @Column (name= "depozit")
    private int idDepozit;

    @Column (name = "personName")
    private String person_name;

    @Column(name = "value")
    private double value;

    @Column(name = "dateMade")
    private Date date_made;

    @Column (name="dateLimit")
    private Date date_limit;

    @Column (name = "dobandaType")
    private DobandaType dobanda_type;

    @Column (name = "dobanda")
    private double dobanda;

    @Column(name = "incheiata")
    private boolean incheiat;

    @Column (name = "tipImprumut")
    private String tip_imprumut;

    public Loans(){}
    public Loans(String person_name, double value, Date date_made, Date date_limit, DobandaType dobanda_type, double dobanda,
                 boolean incheiat, String tip_imprumut, int idDepozit){
        this.person_name=person_name;
        this.value=value;
        this.date_made=date_made;
        this.date_limit=date_limit;
        this.dobanda=dobanda;
        this.incheiat=incheiat;
        this.tip_imprumut=tip_imprumut;
        this.dobanda_type=dobanda_type;
        this.idDepozit = idDepozit;
    }

    public String getPerson_name() {
        return person_name;
    }
    public double getValue() {
        return value;
    }
    public Date getDate_made() {
        return date_made;
    }
    public Date getDate_limit() {
        return date_limit;
    }
    public DobandaType getDobanda_type() {
        return dobanda_type;
    }
    public double getDobanda() {
        return dobanda;
    }
    public boolean isIncheiat() {
        return incheiat;
    }
    public String getTip_imprumut() {
        return tip_imprumut;
    }

    public void setDobanda(double dobanda) {
        this.dobanda = dobanda;
    }
    public void setIncheiat(boolean incheiat) {
        this.incheiat = incheiat;
    }
    public void setValue(double value) {
        this.value = value;
    }

    public int getIdDepozit() {
        return idDepozit;
    }
    public void setIdDepozit(int idDepozit) {
        this.idDepozit = idDepozit;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public void setDate_made(Date date_made) {
        this.date_made = date_made;
    }

    public void setDate_limit(Date date_limit) {
        this.date_limit = date_limit;
    }

    public void setDobanda_type(DobandaType dobanda_type) {
        this.dobanda_type = dobanda_type;
    }

    public void setTip_imprumut(String tip_imprumut) {
        this.tip_imprumut = tip_imprumut;
    }
}
