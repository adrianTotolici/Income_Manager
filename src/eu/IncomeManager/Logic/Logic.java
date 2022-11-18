package eu.IncomeManager.Logic;

import eu.IncomeManager.GUI.AbstractComponents.*;
import eu.IncomeManager.GUI.MainPanel;
import eu.IncomeManager.Utils.*;
import eu.IncomeManager.Utils.Language.DefaultLang;
import eu.IncomeManager.Utils.Language.RomanianLang;
import eu.IncomeManager.communication.beans.ProductBeans;
import eu.IncomeManager.dataBase.*;


import javax.rmi.CORBA.Util;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * Created by adrian on 25.05.2014.
 */
public class Logic {

    private static Logic instance;
    private static DefaultLang lang;

    public static Logic getInstance(){
        if (instance==null){
            instance=new Logic();
        }

        return instance;
    }

    private Logic(){
        if (Constante.language.equals(Enumerari.Language.Romanian.getLang())) {
            lang = new RomanianLang();
        }else {
            lang=new DefaultLang();
        }
    }

    public Vector<String> getAllDepozitName(){
        List<Depozit> allDepozit = DataBaseLogic.getInstance().getAllDepozit();
        Vector listName=new Vector();
        if (allDepozit!=null) {
            for (Depozit depozit : allDepozit) {
                listName.add(depozit.getNume());
            }
        }else {
            DataBaseLogic.getInstance().addDepozit(lang.main_depozit);
            listName.add(lang.main_depozit);
        }
        return listName;
    }

    public void populateProductTable(TableModelProduse tableModelProduse,Date date,String depozitName){

        Depozit depozitByName = DataBaseLogic.getInstance().getDepozitByName(depozitName);
        int idDepozit = depozitByName.getIdDepozit();

        List<Produse> produseList = DataBaseLogic.getInstance().getAllProduseByDateAndDepozit(date,idDepozit);
        if (produseList !=null) {
            for (Produse produse : produseList) {
                tableModelProduse.addProduct(produse);
            }
        }
    }
    public void populateBillTable(TableModelBills tableModelBills, Date date, String numeDepozit){
        Depozit depozit= DataBaseLogic.getInstance().getDepozitByName(numeDepozit);
        int idDepozit = depozit.getIdDepozit();
        List<Bills> billsList=DataBaseLogic.getInstance().getAllBillsByDateAndDepozit(date,idDepozit);
        if (billsList !=null){
            for (Bills bills : billsList) {
                tableModelBills.addBill(bills);
            }
        }
    }
    public void populateLoanTable(TabelModelLoanMade tabelModelLoanMade, String numeDepozit){
        Depozit depozit=DataBaseLogic.getInstance().getDepozitByName(numeDepozit);
        int  idDepozit=depozit.getIdDepozit();
        Date simpleDate = Utils.getSimpleDate(new Date(), true, true, true, false, false, false, false);

        List<Loans> loansList=DataBaseLogic.getInstance().getAllMadeLoansEarlyOfDateByDate(simpleDate,idDepozit);
        if (loansList !=null){
            for (Loans loans : loansList) {
                tabelModelLoanMade.addLoan(loans);
            }
        }
    }
    public void populateLuxuriesTable(TabelModelLuxuries tabelModelLuxuries, String numeDespozit){
        Depozit depozit = DataBaseLogic.getInstance().getDepozitByName(numeDespozit);
        int idDepozit=depozit.getIdDepozit();
        Date simpleDate = Utils.getSimpleDate(new Date(), true, true, false, false, false, false, false);


        List<Luxuries> luxuriesList = DataBaseLogic.getInstance().getAllLuxuriesByDateAndDepozit(simpleDate,idDepozit);
        if (luxuriesList !=null){
            for (Luxuries luxuries : luxuriesList) {
                tabelModelLuxuries.addLuxuries(luxuries);
            }
        }
    }
    public void populateStatisticsTable(TableModelStatistics tableModelStatistics, Date date,String depozitName){
        List<Produse> produseList =makeProductStatistics(date, depozitName);
        for (Produse produse : produseList) {
            tableModelStatistics.addProduseStatistics(produse);
        }
    }

    public void addNewProduct(TableModelProduse tableModelProduse, String nume, String company, String pret_s,
                              String cantitate_s, String reducere_s, Date date, String depozitName){

        double pret= Double.parseDouble(pret_s);
        double cantitate=1;
        double reducere=0;


        if ((cantitate_s!=null)&&(cantitate_s.length()>0)){
            cantitate= Double.parseDouble(cantitate_s);
        }

        if ((reducere_s!=null)&&(reducere_s.length()>0)){
            reducere= Double.parseDouble(reducere_s);
        }

        Depozit depozit = DataBaseLogic.getInstance().getDepozitByName(depozitName);
        Produse produse=DataBaseLogic.getInstance().addProduse(nume,company,pret,cantitate,reducere,date,depozit.getIdDepozit());

        if (Utils.verify_is_curent_day(date)) {
            tableModelProduse.addProduct(produse);
        }
    }
    public void addMadeLoans(Double value,Date date_made, Date date_limit, int dobanda_type_int,
                             String numePersoana, TabelModelLoanMade tabelModelLoanMade, String depozitName){
        double dobanda_value=0;
        Enumerari.DobandaType type = null;
        Depozit depozit=DataBaseLogic.getInstance().getDepozitByName(depozitName);
        int idDepozit = depozit.getIdDepozit();

        switch (dobanda_type_int){
            case (0):
                type= Enumerari.DobandaType.FaraDobanda;
                break;
            case (1):
                type= Enumerari.DobandaType.DobandaPeZi;
                dobanda_value= (int) Utils.getDateDiff(date_limit,date_made, TimeUnit.DAYS);
                break;
            case (2):
                type= Enumerari.DobandaType.DobandaPeSuma;
                dobanda_value=(value*10)/100;
                break;
        }

        Loans loan=DataBaseLogic.getInstance().addLoans(numePersoana,value,date_made,date_limit,type,dobanda_value,false,
                Constante.IMPRUMUT_DAT,idDepozit);
        tabelModelLoanMade.addLoan(loan);
    }
    public void addCashBack(TableModelProduse tableModelProduse, String company, String reducere_s,Date date,String numeDepozit){
        if ((reducere_s!=null)&&(reducere_s.length()>0)&&(company!=null)&&(company.length()>0)) {
            double reducere= Double.parseDouble(reducere_s);

            Depozit depozit=DataBaseLogic.getInstance().getDepozitByName(numeDepozit);
            Produse produse=new Produse(Constante.DEFAULT_NAME,company,0,1,reducere,date,depozit.getIdDepozit());
            DataBaseLogic.getInstance().saveObject(produse);
            tableModelProduse.addProduct(produse);
        }
    }
    public void addBill(TableModelBills tableModelBills,String name,String value_s,Date date, String depozitName){

        if ((name!=null)&&(name.length()>0)&&(value_s!=null)&&(value_s.length()>0)) {
            double value= Double.parseDouble(value_s);
            Depozit depozit=DataBaseLogic.getInstance().getDepozitByName(depozitName);
            int idDepozit=depozit.getIdDepozit();
            Bills bill=DataBaseLogic.getInstance().addBills(name,value,date,idDepozit);
            tableModelBills.addBill(bill);
        }
    }
    public void addNewLuxuries(String nume, String value_s, Date date, String cashback_s, String quantity_s, TabelModelLuxuries tabelModelLuxuries, String depozitName){

        if ((nume!=null)&&(nume.length()>0)&&(value_s!=null)&&(value_s.length()>0)){

            Depozit depozit=DataBaseLogic.getInstance().getDepozitByName(depozitName);
            int idDepozit = depozit.getIdDepozit();

            double pret= Double.parseDouble(value_s);
            double cantitate=1;
            if ((quantity_s!=null)&&(quantity_s.length()>0)){
                cantitate= Double.parseDouble(quantity_s);
            }
            double cashback=0;
            if ((cashback_s!=null)&&(cashback_s.length()>0)){
                cashback= Double.parseDouble(cashback_s);
            }
            Luxuries luxuries = DataBaseLogic.getInstance().addLuxuries(nume, pret, cantitate, cashback, date, idDepozit);
            tabelModelLuxuries.addLuxuries(luxuries);
        }
    }
    public void addNewDepozit(String nume){
        DataBaseLogic.getInstance().addDepozit(nume);
    }

    public void saveNewIncome(String month_income_string, String extra_income_string, String last_month_savings_string, String depozitName){
        try {

            Depozit depozit=DataBaseLogic.getInstance().getDepozitByName(depozitName);
            int idDepozit = depozit.getIdDepozit();

            if(!month_income_string.isEmpty()){
                Income income=DataBaseLogic.getInstance().getIncomeFromDate(new Date(),idDepozit);



                double monthIncome=Double.parseDouble(month_income_string);
                double monthIncomeExtra;
                double lastMonthSavings;


                if (extra_income_string.isEmpty()){
                    monthIncomeExtra=0;
                }else {
                    monthIncomeExtra=Double.parseDouble(extra_income_string)+income.getExtraIncome();
                }

                Date lastMonth = Utils.getLastMonth(new Date());
                if (last_month_savings_string.isEmpty()){
                    lastMonthSavings=calculMonthSavings(lastMonth,idDepozit);
                }else {
                    lastMonthSavings=income.getLastMonthSavings();
                }

                income.setLastMonthSavings(lastMonthSavings);
                income.setExtraIncome(monthIncomeExtra);
                income.setValue(monthIncome);

                DataBaseLogic.getInstance().updateObject(income);
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    public void iniLuxuriesTab(JTextField luxuriesTotalTab, String depozitName){

        Depozit depozit=DataBaseLogic.getInstance().getDepozitByName(depozitName);
        int idDepozit = depozit.getIdDepozit();

        double total= DataBaseLogic.getInstance().getTotalLuxuriesForDateAndDepozit(new Date(),idDepozit);

        luxuriesTotalTab.setText(Formatters.NUMBER_2DECIMALS.format(total));
    }
    public void iniIncomeTab(JTextField monthIncomeTabIncome,JTextField extraIncomeTabIncome,JTextField lastIncomeTabIncome,
                             JTextField todaySavingsTabIncome, JTextField weekSavingsTabIncome, JTextField monthSavingsTabIncome,
                             String depozitName){

        Depozit depozit=DataBaseLogic.getInstance().getDepozitByName(depozitName);
        int idDepozit = depozit.getIdDepozit();

        Date date = Utils.getSimpleDate(new Date(), true, true, false, false, false, false, false);
        Income income = DataBaseLogic.getInstance().getIncomeFromDate(date, idDepozit);

        if (income ==null){
            Date lastMonth = Utils.getLastMonth(date);
            double lastMonthSavings=calculMonthSavings(lastMonth, idDepozit);
            income =new Income(date,idDepozit,0d,0d,lastMonthSavings);

            DataBaseLogic.getInstance().saveObject(income);
        }

        double thisMonthIncome= income.getValue();
        double thisMonthExtraIncome= income.getExtraIncome();
        double thisMonthSavings=income.getLastMonthSavings();

        double todaySavings=calculTodaySavings(date,idDepozit);
        double weekSavings=calculWeekSavings(date,idDepozit);
        double monthSavings=calculMonthSavings(date,idDepozit);

        monthIncomeTabIncome.setText(Formatters.NUMBER_2DECIMALS.format(thisMonthIncome));
        extraIncomeTabIncome.setText(Formatters.NUMBER_2DECIMALS.format(thisMonthExtraIncome));
        lastIncomeTabIncome.setText(Formatters.NUMBER_2DECIMALS.format(thisMonthSavings));

        todaySavingsTabIncome.setText(Formatters.NUMBER_2DECIMALS.format(todaySavings));
        weekSavingsTabIncome.setText(Formatters.NUMBER_2DECIMALS.format(weekSavings));
        monthSavingsTabIncome.setText(Formatters.NUMBER_2DECIMALS.format(monthSavings));
    }
    public void iniExpensesTab(JTextField todayExpensesTabexpenses, JTextField weekExpensesTabExpenses,
                               JTextField monthExpensesTabExpenses, JTextField totalBillTabExpenses, String depozitName){

        Depozit depozit=DataBaseLogic.getInstance().getDepozitByName(depozitName);

        double todayExpenses=calculTodayExpenses(depozit.getIdDepozit());
        double weekExpenses=calculProduseValueForThisWeek(depozit.getIdDepozit());
        double monthExpenses=calculMonthExpenses(new Date(),depozit.getIdDepozit());
        double totalBills=calculTotalBills(new Date(), depozit.getIdDepozit());

        todayExpensesTabexpenses.setText(Formatters.NUMBER_2DECIMALS.format(todayExpenses));
        weekExpensesTabExpenses.setText(Formatters.NUMBER_2DECIMALS.format(weekExpenses));
        monthExpensesTabExpenses.setText(Formatters.NUMBER_2DECIMALS.format(monthExpenses));
        totalBillTabExpenses.setText(Formatters.NUMBER_2DECIMALS.format(totalBills));
    }

    public void removeProduct(Produse produse){
        DataBaseLogic.getInstance().deleteObject(produse);
    }
    public void removeBill(Bills bills){
        DataBaseLogic.getInstance().deleteObject(bills);
    }
    public void removeLuxuries(Luxuries luxuries){
        DataBaseLogic.getInstance().deleteObject(luxuries);
    }

    public void payUpLoan(Loans loan){
            loan.setIncheiat(true);
            loan.setValue(0);
            DataBaseLogic.getInstance().updateObject(loan);
    }
    public void payUpPartialLoan(Loans loan, String value_string){
        if (value_string.length()>0){
            double value_new= Double.parseDouble(value_string);
            double value_old= loan.getValue();
            double value=value_old-value_new;
            if (value<0) {
                value=0;
                loan.setIncheiat(true);
                loan.setValue(value);
            }else{
                loan.setValue(value);
            }
            DataBaseLogic.getInstance().updateObject(loan);
        }
    }

    public double calculMonthSavings(Date date, int idDepozit){
        Date simpleDate = Utils.getSimpleDate(date, true, true, false, false, false, false, false);
        Income income = DataBaseLogic.getInstance().getIncomeFromDate(simpleDate, idDepozit);
        double totalIncome=0;

        if (income !=null){
            totalIncome= income.getValue() + income.getExtraIncome()+income.getLastMonthSavings();
        }

        double totalExpenses=0;
        Double totalProduse=calculProduseValueForThisMonth(idDepozit);
        Double totalBillsForDateAndDepozit = DataBaseLogic.getInstance().getTotalBillsForDateAndDepozit(simpleDate, idDepozit);
        Double totalLuxuriesForDateAndDepozit = DataBaseLogic.getInstance().getTotalLuxuriesForDateAndDepozit(simpleDate, idDepozit);
        Double totalLoansMade = DataBaseLogic.getInstance().getTotalLoansMade(idDepozit);

        totalExpenses=totalExpenses+totalBillsForDateAndDepozit+totalLuxuriesForDateAndDepozit+totalLoansMade+totalProduse;

        double lastMonthSavings=totalIncome-totalExpenses;
        return lastMonthSavings;
    }
    public double calculTodaySavings(Date date, int idDepozit){
        double dailySaving=0;

        Income income = DataBaseLogic.getInstance().getIncomeFromDate(date, idDepozit);
        double billsValue= DataBaseLogic.getInstance().getTotalBillsForDateAndDepozit(date,idDepozit);
        double luxuriesValue= DataBaseLogic.getInstance().getTotalLuxuriesForDateAndDepozit(date,idDepozit);
        double produse=calculTotalProduseExpensesUntilNow(idDepozit);

        if (income !=null) {
            double totalIncome = income.getValue() + income.getExtraIncome()+income.getLastMonthSavings();
            totalIncome -= billsValue;
            totalIncome -= luxuriesValue;
            totalIncome -= produse;


            int daysFromMonth = Utils.getDaysFromMonth(date);
            int daysRemaining =daysFromMonth-(Utils.getDay(new Date()))+1;

            dailySaving=totalIncome/daysRemaining;
            double produseValue=DataBaseLogic.getInstance().getPriceForProduseBetweenDates(new Date(),new Date(),idDepozit);
            dailySaving -=produseValue;
        }

        return dailySaving;
    }
    public double calculWeekSavings(Date date, int idDepozit){
        double weekSavings=0;
        Date simpleDate = Utils.getSimpleDate(date, true, true, true, false, false, false, false);

        Income income = DataBaseLogic.getInstance().getIncomeFromDate(simpleDate,idDepozit);
        double billsValue= DataBaseLogic.getInstance().getTotalBillsForDateAndDepozit(simpleDate,idDepozit);
        double luxurieValue= DataBaseLogic.getInstance().getTotalLuxuriesForDateAndDepozit(simpleDate,idDepozit);

        if (income !=null){
            double total_income= income.getValue()+ income.getExtraIncome()+ income.getLastMonthSavings();
            total_income -=billsValue;
            total_income -=luxurieValue;
            int nr_zile_maxim=Utils.getDaysFromMonth(date);

            double venit_pe_zi=total_income/nr_zile_maxim;
            int nr_zile_pe_saptamana=Utils.getNrDaysOfCurrentWeek(new Date());
            double economi_saptamana=venit_pe_zi*nr_zile_pe_saptamana;

            double week_expenenses=calculProduseValueForThisWeek(idDepozit);
            weekSavings= economi_saptamana-week_expenenses;
        }
        return weekSavings;
    }

    public double calculTodayExpenses(int idDepozit){
        double todayExpeneses= DataBaseLogic.getInstance().getPriceForProduseOnDate(new Date(),idDepozit);
        if (todayExpeneses<0){
            todayExpeneses=0;
        }
        return todayExpeneses;
    }
    public double calculMonthExpenses(Date date,int idDezpozit){
        double monthExpenses;

        double billsValue= DataBaseLogic.getInstance().getTotalBillsForDateAndDepozit(date, idDezpozit);
        double luxuriesValue= DataBaseLogic.getInstance().getTotalLuxuriesForDateAndDepozit(date,idDezpozit);
        double produseValue=0;

        int firstDayOfMonth=1;
        int lastDayOfMonth=Utils.getDaysFromMonth(new Date());

        for (int i=firstDayOfMonth; i<lastDayOfMonth+1; i++){
            Date curentDate=Utils.setDay(i,date);
            double dayExpenses=DataBaseLogic.getInstance().getPriceForProduseOnDate(curentDate,idDezpozit);
            produseValue+=dayExpenses;
        }

        double loanValue=calculImprumuturiPePerioada(idDezpozit);

        monthExpenses=billsValue+luxuriesValue+produseValue+loanValue;

        if (monthExpenses<0){
            monthExpenses=0;
        }

        return monthExpenses;
    }
    public double calculImprumuturiPePerioada(int idDepozit){
        double sumaImprumut=0;
        List<Loans> loansList = DataBaseLogic.getInstance().getAllLoansByDateAndDepozitAndActive(idDepozit);
        if (loansList !=null) {
            for (Loans loans : loansList) {
                Date dateLimit = loans.getDate_limit();
                Date today = new Date();
                if (dateLimit!=null) {
                    Long datediff = Utils.getDateDiff(dateLimit, today, TimeUnit.DAYS);
                    if (datediff < 0) {
                        sumaImprumut = sumaImprumut + loans.getValue();
                    }
                }
            }
        }
        return sumaImprumut;
    }
    public double calculTotalBills(Date date, int idDepozit){
        double billValues= DataBaseLogic.getInstance().getTotalBillsForDateAndDepozit(date, idDepozit);
        return billValues;
    }

    public boolean verifyIfProductInList (String name, List<Produse> produseList){
        boolean exist=false;
        for (Produse produse : produseList) {
            if (name.equalsIgnoreCase(produse.getNume())) {
                exist = true;
                break;
            }
        }
        return exist;
    }
    public boolean verifyIfProduseFromMonth(Date monthDesired, Produse produse){
        boolean exist=false;
        Date date = produse.getDate();
        int monthCurrent = Utils.getMonth(date);
        int monthD = Utils.getMonth(monthDesired);

        if (monthCurrent==monthD){
            exist =true;
        }

        return exist;
    }

    public List<Produse> makeProductStatistics(Date date, String depozitName) {
        Date simpleDate = Utils.getSimpleDate(date, true, true, false, false, false, false, false);
        Depozit depozit=DataBaseLogic.getInstance().getDepozitByName(depozitName);
        int idDepozit = depozit.getIdDepozit();
        List<Produse> produseList = DataBaseLogic.getInstance().getAllProduseByDepozit(idDepozit);

        List<Produse> produsStatistics = new ArrayList<Produse>();
        if (produseList != null) {
            for (Produse produse : produseList) {
                String name = produse.getNume();
                double price = produse.getPret();
                if (price > 0) {
                    if (verifyIfProduseFromMonth(simpleDate,produse)) {
                        if (verifyIfProductInList(name, produsStatistics)) {

                            double quantity = produse.getCantitate();

                            for (Produse produsStatistic : produsStatistics) {
                                String nume = produsStatistic.getNume();
                                if (nume.equalsIgnoreCase(name)) {
                                    quantity = quantity + produsStatistic.getCantitate();
                                    price = (price + produsStatistic.getPret()) / 2;
                                    produsStatistics.remove(produsStatistic);
                                    break;
                                }
                            }
                            produse.setCantitate(quantity);
                            produse.setPret(price);
                            produsStatistics.add(produse);


                        } else {
                            produsStatistics.add(produse);
                        }
                    }
                }

            }
        }
        return produsStatistics;
    }

    public Boolean addProducts(List<ProductBeans> list){
        for (ProductBeans productBeans : list) {
            Date date=Utils.getSimpleDate(productBeans.getDate(),true,true,true,false,false,false,false);
            Depozit depozit=DataBaseLogic.getInstance().getDepozitByName(productBeans.getDepozitName());
            Produse produse=new Produse(productBeans.getNumeProdus(),productBeans.getCompany(),productBeans.getPret(),
                    productBeans.getCantitate(),productBeans.getReducere(),date,depozit.getIdDepozit());
            DataBaseLogic.getInstance().saveObject(produse);
        }
        MainPanel.getInstance().refreshMainPanel();
        return true;
    }

    public double calculTotalProduseExpensesUntilNow(int idDepozit){
        Double produseExpenses=0d;
        Date today =new Date();
        for (int i=1; i<Utils.getDay(today); i++) {
            Date date = Utils.setDay(i, today);
            List<Produse> produseList = DataBaseLogic.getInstance().getAllProduseByDateAndDepozit(date, idDepozit);

            if (produseList != null) {
                for (Produse produse : produseList) {
                    double cantitate = produse.getCantitate();
                    double pret = produse.getPret();
                    double discount = produse.getReducere();
                    produseExpenses = produseExpenses + ((cantitate * pret) - discount);
                }
            }
        }
        return produseExpenses;
    }
    private double calculProduseValueForThisMonth(int idDepozit){
        Double produseExpenses=0d;
        Date today =new Date();
        for (int i=1; i<=Utils.getDay(today); i++) {
            Date date = Utils.setDay(i, today);
            List<Produse> produseList = DataBaseLogic.getInstance().getAllProduseByDateAndDepozit(date, idDepozit);

            if (produseList != null) {
                for (Produse produse : produseList) {
                    double cantitate = produse.getCantitate();
                    double pret = produse.getPret();
                    double discount = produse.getReducere();
                    produseExpenses = produseExpenses + ((cantitate * pret) - discount);
                }
            }
        }
        return produseExpenses;
    }
    private double calculProduseValueForThisWeek(int idDepozit){
        Double produseExpenses=0d;
        Date today= new Date();
        int firstDayOfTheWeek = Utils.getFirstDayOfTheWeek(today);
        for (int i=firstDayOfTheWeek; i<= Utils.getDay(today); i++){
            Date date = Utils.setDay(i, today);
            List<Produse> produseList = DataBaseLogic.getInstance().getAllProduseByDateAndDepozit(date, idDepozit);

            if (produseList != null) {
                for (Produse produse : produseList) {
                    double cantitate = produse.getCantitate();
                    double pret = produse.getPret();
                    double discount = produse.getReducere();
                    produseExpenses = produseExpenses + ((cantitate * pret) - discount);
                }
            }
        }
        return produseExpenses;
    }
}
