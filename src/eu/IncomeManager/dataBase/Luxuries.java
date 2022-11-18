package eu.IncomeManager.dataBase;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "luxuries")
public class Luxuries {
    @Id
    @GeneratedValue
    @Column(name = "idLuxuries")
    private int id_luxuries;

    @Column(name = "idDepozit")
    private int idDepozit;

    @Column(name = "nume")
    private String nume;

    @Column(name = "pret")
    private double pret;

    @Column (nullable = true, name = "cantitate")
    private double cantitate;

    @Column (nullable = true, name = "reducere")
    private double reducere;

    @Column(name = "data")
    private Date date;

    public Luxuries() {}

    public Luxuries(String nume, double pret, double cantitate, double reducere, Date date, int iDdepozit){
        this.nume=nume;
        this.pret=pret;
        this.cantitate=cantitate;
        this.reducere=reducere;
        this.date=date;
        this.idDepozit =iDdepozit;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public double getCantitate() {
        return cantitate;
    }

    public void setCantitate(double cantitate) {
        this.cantitate = cantitate;
    }

    public double getReducere() {
        return reducere;
    }

    public void setReducere(double reducere) {
        this.reducere = reducere;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdDepozit(){

        return idDepozit;
    }

    public void setIdDepozit(int idDepozit) {
        this.idDepozit = idDepozit;
    }





}
