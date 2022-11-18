package eu.IncomeManager.dataBase;


import javax.persistence.*;
import java.util.Date;
import eu.IncomeManager.dataBase.Depozit;

/**
 * Created by adrian on 2/18/14.
 */

@Entity
@Table(name = "produse")
public class Produse {
   @Id
   @GeneratedValue
   @Column
   private int id_produs;

    @Column(name = "depozitId")
    private int idDepozit;

   @Column
   private String nume;

   @Column
   private String company;

   @Column
   private double pret;

   @Column (nullable = true)
   private double cantitate;

   @Column (nullable = true)
   private double reducere;

    @Column
    private Date date;

   public Produse(){}
   public Produse(String nume, String company, double pret, double cantitate, double reducere, Date date, int idDepozit){
       this.nume=nume;
       this.company=company;
       this.pret=pret;
       this.cantitate=cantitate;
       this.reducere=reducere;
       this.date=date;
       this.idDepozit=idDepozit;
   }

    public String getNume() {
        return nume;
    }
    public String getCompany() {
        return company;
    }
    public double getPret() {
        return pret;
    }
    public double getCantitate() {
        return cantitate;
    }
    public double getReducere() {
        return reducere;
    }
    public int getId_produs() {
        return id_produs;
    }

    public int getIdDepozit() {
        return idDepozit;
    }

    public Date getDate() {
        return date;
    }

    public void setCantitate(double cantitate) {
        this.cantitate = cantitate;
    }
    public void setPret(double pret) {
        this.pret = pret;
    }

    public void setIdDepozit(int idDepozit) {
        this.idDepozit = idDepozit;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setReducere(double reducere) {
        this.reducere = reducere;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
