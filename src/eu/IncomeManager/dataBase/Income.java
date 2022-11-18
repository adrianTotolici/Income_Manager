package eu.IncomeManager.dataBase;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "income")
public class Income{
    @Id
    @GeneratedValue
    @Column(name = "idIncome")
    private int id_income;

    @Column(name = "depozitId")
    private int idDepozit;

    @Column (name = "date")
    private Date date;

    @Column(name = "value")
    private Double value;

    @Column (name = "extra_income")
    private Double extraIncome;

    @Column (name = "last_month_savings")
    private Double lastMonthSavings;

    public Income() {}

    public Income(Date date, int idDepozit, Double value, Double extraIncome, Double lastMonthSavings) {
        this.date = date;
        this.value = value;
        this.extraIncome = extraIncome;
        this.lastMonthSavings = lastMonthSavings;
        this.idDepozit =idDepozit;
    }

    public void setId_income(int id_income) {
        this.id_income = id_income;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setExtraIncome(Double extraIncome) {
        this.extraIncome = extraIncome;
    }

    public void setLastMonthSavings(Double lastMonthSavings) {
        this.lastMonthSavings = lastMonthSavings;
    }

    public int getId_income() {
        return id_income;
    }

    public Date getDate() {
        return date;
    }

    public Double getValue() {
        return value;
    }

    public Double getExtraIncome() {
        return extraIncome;
    }

    public Double getLastMonthSavings() {
        return lastMonthSavings;
    }

    public int getIdDepozit() {
        return idDepozit;
    }

    public void setIdDepozit(int idDepozit) {
        this.idDepozit = idDepozit;
    }
}
