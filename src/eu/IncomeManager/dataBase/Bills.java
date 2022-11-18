package eu.IncomeManager.dataBase;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Bills")
public class Bills {

    @Id
    @GeneratedValue
    @Column
    private int id_bill;

    @Column
    private String bill_name;

    @Column(name = "depozitId")
    private int idDepozit;

    @Column
    private double bill_value;

    @Column
    private Date date;


    public Bills(){}
    public Bills(String bill_name, double bill_value, Date date, int idDepozit){
        this.bill_name=bill_name;
        this.bill_value=bill_value;
        this.date=date;
        this.idDepozit = idDepozit;
    }

    public String getBill_name() {
        return bill_name;
    }
    public double getBill_value() {
        return bill_value;
    }
    public int getIdDepozit() {
        return idDepozit;
    }
    public Date getDate() {
        return date;
    }

    public void setBill_name(String bill_name) {
        this.bill_name = bill_name;
    }

    public void setIdDepozit(int idDepozit) {
        this.idDepozit = idDepozit;
    }

    public void setBill_value(double bill_value) {
        this.bill_value = bill_value;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
