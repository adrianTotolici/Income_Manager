package eu.IncomeManager.communication.beans;

import java.util.Date;

/**
 * Created by adrian on 02.11.2014.
 */
public class ProductBeans {

    private Double cantitate;
    private String company;
    private Date date;
    private String depozitName;
    private String numeProdus;
    private Double pret;
    private Double reducere;

    public ProductBeans(Double cantitate, String company, Date date, String depozitName, String numeProdus,
                        Double pret, Double reducere) {
        this.cantitate = cantitate;
        this.company = company;
        this.date = date;
        this.depozitName = depozitName;
        this.numeProdus = numeProdus;
        this.pret = pret;
        this.reducere = reducere;
    }

    public Double getCantitate() {
        return cantitate;
    }

    public String getCompany() {
        return company;
    }

    public Date getDate() {
        return date;
    }

    public String getDepozitName() {
        return depozitName;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public Double getPret() {
        return pret;
    }

    public Double getReducere() {
        return reducere;
    }
}
