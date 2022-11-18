package eu.IncomeManager.Logic;

import eu.IncomeManager.Utils.*;
import eu.IncomeManager.dataBase.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Date;
import java.util.List;

/**
 * Created by adrian on 9/15/2014.
 */
public class DataBaseLogic {
    private static DataBaseLogic instance=null;
    private String folderDatabase;
    private XMLLogic xmlLogic;
    private String rootPathIncome;

    private DataBaseLogic() {
        rootPathIncome =Utils.getPath()+"/IncomeManager2";
        this.folderDatabase="/DataBaseBackup";
        xmlLogic=XMLLogic.getFirstInstance(rootPathIncome + "/DataBaseBackup");
    }

    public static synchronized DataBaseLogic getInstance() {
        if (instance==null){
            instance=new DataBaseLogic();
        }
        return instance;
    }

    public void saveObject(Object object){
        Session session= HibernateUtil.getSessionFactroy().openSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        session.close();
    }
    public void updateObject(Object object){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        session.close();
    }
    public void deleteObject(Object object){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        session.close();
    }

    //  Depozit table
    public Depozit addDepozit(String name){
        Depozit depozit=new Depozit(name);
        saveObject(depozit);
        return depozit;
    }
    public Depozit getDepozitByName(String name){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("FROM Depozit WHERE nume=:name");
        query.setParameter("name",name);
        List<Depozit> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return null;
        return list.get(0);
    }
    public List<Depozit> getAllDepozit(){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("FROM Depozit");
        List<Depozit> list = query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return null;
        return list;
    }
    public void deleteAllDepozit(){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("delete from Depozit ");
        query.executeUpdate();
        session.close();
    }

    //  Income table
    public Income addIncome(Date currentDate, Double incomeValue, Double extraIncome, Double lastMonthSavings, int idDepozit){
        currentDate=Utils.getSimpleDate(currentDate,true,true,false,false,false,false,false);
        Income income=new Income(currentDate, idDepozit, incomeValue,extraIncome,lastMonthSavings);
        saveObject(income);
        return income;
    }
    public Income getIncomeFromDate(Date date, int idDepozit){
        date=Utils.getSimpleDate(date,true,true,false,false,false,false,false);
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("FROM Income WHERE date=:date AND idDepozit=:idDepozit");
        query.setParameter("date",date);
        query.setParameter("idDepozit",idDepozit);
        List<Income> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return null;
        return list.get(0);
    }
    public List<Income> getAllIcome(){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("from Income ");
        List<Income> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return null;
        return list;
    }
    public void deletAllIncome(){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("delete from Income ");
        query.executeUpdate();
        session.close();
    }

    //  Produse table
    public Produse addProduse(String nume, String company, double pret, double cantitate, double reducere, Date data, int idDepozit){
        data=Utils.getSimpleDate(data,true,true,true,false,false,false,false);


        Produse produse=new Produse(nume,company,pret,cantitate,reducere,data,idDepozit);
        saveObject(produse);
        return produse;
    }
    public List<Produse> getAllProduseByDateAndDepozit(Date date, int idDepozit){
        date=Utils.getSimpleDate(date,true,true,true,false,false,false,false);
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("FROM Produse WHERE date =:date AND idDepozit=:idDepozit");
        query.setParameter("date",date);
        query.setParameter("idDepozit",idDepozit);
        List<Produse> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return  null;
        return list;
    }
    public List<Produse> getAllProduseByDepozit(int idDepozit){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("FROM Produse WHERE  idDepozit=:idDepozit");
        query.setParameter("idDepozit",idDepozit);
        List<Produse> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return  null;
        return list;
    }
    public List<Produse> getAllProduse(){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("from Produse");
        List<Produse> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return null;
        return list;
    }
    public Produse getLastProduseForDate(Date date, int idDepozit){
        date= Utils.getSimpleDate(date,true,true,true,false,false,false,false);
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("FROM Produse WHERE date=:date AND idDepozit=:idDepozit");
        query.setParameter("date",date);
        query.setParameter("idDepozit",idDepozit);
        List<Produse> list=query.list();
        session.close();

        if (list.isEmpty() || list.size()<1) return null;
        return list.get(0);
    }
    public double getPriceForProduseOnDate(Date date, int idDepozit){
        double total_price=0;

        Date simpleDate = Utils.getSimpleDate(date, true, true, true, false, false, false, false);

        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("FROM Produse WHERE date=:simpleDate and  idDepozit=:idDepozit");
        query.setParameter("simpleDate",simpleDate);
        query.setParameter("idDepozit",idDepozit);
        List<Produse> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return total_price;

        for (Produse produs : list) {
            double cant=produs.getCantitate();
            double pret=produs.getPret();
            double cashback=produs.getReducere();

            double value=(cant*pret)-cashback;
            total_price=total_price+value;
        }
        return total_price;
    }
    public double getPriceForProduseBetweenDates(Date dateStart, Date dateStop, int idDepozit){
        double total_price=0;

        Date simpleDateStart = Utils.getSimpleDate(dateStart, true, true, true, false, false, false, false);
        Date simpleDateStop= Utils.getSimpleDate(dateStop, true,true,true,false,false,false,false);

        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("FROM Produse WHERE date>=:simpleDateStart and date<=:simpleDateStop  and  idDepozit=:idDepozit");
        query.setParameter("simpleDateStart",simpleDateStart);
        query.setParameter("simpleDateStop",simpleDateStop);
        query.setParameter("idDepozit",idDepozit);
        List<Produse> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return total_price;

        for (Produse produs : list) {
            double cant=produs.getCantitate();
            double pret=produs.getPret();
            double cashback=produs.getReducere();

            double value=(cant*pret)-cashback;
            total_price=total_price+value;
        }
        return total_price;
    }
    public void deleteAllProduse(){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("delete from Produse ");
        query.executeUpdate();
        session.close();
    }

    public double getValueofExpensesForDay(int day){
        Date date=new Date();
        date = Utils.setDay(day, date);
        double product_value=0;

        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("FROM Produse WHERE date=:date");
        query.setParameter("date",date);
        List<Produse> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return product_value;

        for (Produse produse : list) {
            double cant= produse.getCantitate();
            double pret= produse.getPret();
            double cashback= produse.getReducere();

            double val=(cant*pret)-cashback;
            product_value=product_value+val;
        }

        return product_value;
    }
    public double getValueofExpensesForMonth(int month){
        Date date=new Date();
        date = Utils.setMonth(month, date);
        double product_value=0;

        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("FROM Produse WHERE date=:date");
        query.setParameter("date",date);
        List<Produse> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return product_value;

        for (Produse produse : list) {
            double cant= produse.getCantitate();
            double pret= produse.getPret();
            double cashback= produse.getReducere();

            double val=(cant*pret)-cashback;
            product_value=product_value+val;
        }

        return product_value;
    }

    //  Bills table
    public Bills addBills(String billName, double billValue, Date date, int idDepozit){
        date=Utils.getSimpleDate(date,true,true,false,false,false,false,false);
        Bills bills=new Bills(billName,billValue,date,idDepozit);
        saveObject(bills);
        return bills;
    }
    public List<Bills> getAllBills(){
       Session session=HibernateUtil.getSessionFactroy().openSession();
       Query query=session.createQuery("from Bills ");
        List<Bills> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return null;
        return list;
    }
    public List<Bills> getAllBillsByDateAndDepozit(Date date,int idDepozit){
        date=Utils.getSimpleDate(date,true,true,false,false,false,false,false);

        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("FROM Bills WHERE date =:date AND idDepozit=:idDepozit");
        query.setParameter("date",date);
        query.setParameter("idDepozit",idDepozit);
        List<Bills> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return null;
        return list;
    }
    public Double getTotalBillsForDateAndDepozit(Date date, int depozit){
        date=Utils.getSimpleDate(date,true,true,false,false,false,false,false);
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("SELECT coalesce(sum(bill_value),0) FROM Bills WHERE date=:date AND idDepozit=:depozit");
        query.setParameter("date",date);
        query.setParameter("depozit",depozit);
        Object o = query.uniqueResult();
        session.close();

        if ((o!=null)) {
            Double sum = (Double) o;
            return sum;
        }
        return null;
    }
    public void deleteAllBills(){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("delete from Bills ");
        query.executeUpdate();
        session.close();
    }

    //    Luxuries table
    public Luxuries addLuxuries(String nume, double pret, double cantitate, double reducere, Date data, int idDepozit){

        data=Utils.getSimpleDate(data,true,true,false,false,false,false,false);
        Luxuries luxuries=new Luxuries(nume,pret,cantitate,reducere,data,idDepozit);
        saveObject(luxuries);
        return luxuries;
    }
    public List<Luxuries> getAllLuxuries(){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("from Luxuries ");
        List<Luxuries> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return null;
        return list;
    }
    public List<Luxuries> getAllLuxuriesByDateAndDepozit(Date date, int idDepozit){
        date=Utils.getSimpleDate(date,true,true,false,false,false,false,false);

        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("FROM Luxuries WHERE date=:date AND idDepozit=:idDepozit");
        query.setParameter("date",date);
        query.setParameter("idDepozit",idDepozit);
        List<Luxuries> list=query.list();
        session.close();

        if (list.isEmpty()||(list.size()<1)) return null;
        return list;
    }
    public Double getTotalLuxuriesForDateAndDepozit(Date date, int idDepozit){
        date=Utils.getSimpleDate(date,true,true,false,false,false,false,false);
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("SELECT coalesce(sum(pret),0) FROM Luxuries WHERE date=:date AND idDepozit=:depozit");
        query.setParameter("date",date);
        query.setParameter("depozit",idDepozit);
        Object o = query.uniqueResult();
        session.close();

        if ((o!=null)) {
            Double sum = (Double) o;
            return sum;
        }
        return null;
    }
    public void deleteAllLuxuries(){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("delete from Luxuries ");
        query.executeUpdate();
        session.close();
    }

    //    Loans table
    public Loans addLoans(String personName, double value, Date dateMade, Date dateLimit, Enumerari.DobandaType dobandaType, double dobanda,
                          boolean incheiata, String tipImprumut, int idDepozit ){

        dateMade=Utils.getSimpleDate(dateMade,true,true,true,false,false,false,false);
        if (dateLimit!=null) {
            dateLimit = Utils.getSimpleDate(dateLimit, true, true, true, false, false, false, false);
        }
        Loans loans=new Loans(personName, value, dateMade, dateLimit, dobandaType, dobanda, incheiata, tipImprumut, idDepozit);
        saveObject(loans);
        return loans;
    }
    public List<Loans> getAllLoans(){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("from Loans ");
        List<Loans> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return null;
        return list;
    }
    public List<Loans> getAllLoansByDateAndDepozitAndActive(int idDepozid){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("FROM Loans where incheiat=false AND idDepozit=:idDepozit");
        query.setParameter("idDepozit",idDepozid);
        List<Loans> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return null;
        return list;
    }
    public List<Loans> getAllMadeLoansEarlyOfDateByDate(Date date, int idDepozit){
        date=Utils.getSimpleDate(date,true,true,true,false,false,false,false);
        Session session=HibernateUtil.getSessionFactroy().openSession();

        Query query=session.createQuery("FROM Loans WHERE incheiat=false AND  date_made<=:date AND idDepozit=:idDepozit");
        query.setParameter("date",date);
        query.setParameter("idDepozit",idDepozit);
        List<Loans> list=query.list();
        session.close();

        if ((list.isEmpty())||(list.size()<1)) return null;
        return list;
    }
    public Double getTotalLoansMade(int idDepozit){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("SELECT coalesce(sum(value),0) FROM Loans WHERE incheiat=false AND idDepozit=:idDepozit");
        query.setParameter("idDepozit",idDepozit);
        Object o = query.uniqueResult();
        session.close();

        if ((o!=null)) {
            Double sum = (Double) o;
            return sum;
        }
        return null;
    }
    public void deleteAllLoans(){
        Session session=HibernateUtil.getSessionFactroy().openSession();
        Query query=session.createQuery("delete from Loans ");
        query.executeUpdate();
        session.close();
    }

    public void exportDataBaseToFile(){
        List<Depozit> allDepozit = getAllDepozit();
        List<Bills> allBills = getAllBills();
        List<Income> allIcome = getAllIcome();
        List<Produse> allProduse = getAllProduse();
        List<Luxuries> allLuxuries= getAllLuxuries();
        List<Loans> allLoans = getAllLoans();

        if (allDepozit!=null) {
            makeDepozitData(allDepozit);
        }else {
            Utils.deleteFile(rootPathIncome+folderDatabase+Constante.depozitFile);
        }

        if (allBills!=null){
            makeBillData(allBills);
        }else {
            Utils.deleteFile(rootPathIncome+folderDatabase+Constante.billsFile);
        }

        if (allIcome!=null){
            makeIncomeData(allIcome);
        }else {
            Utils.deleteFile(rootPathIncome + folderDatabase + Constante.incomeFile);
        }

        if (allProduse!=null){
            makeProduseData(allProduse);
        }else {
            Utils.deleteFile(rootPathIncome + folderDatabase + Constante.produseFile);
        }

        if (allLuxuries!=null){
            makeLuxuriesData(allLuxuries);
        }else {
            Utils.deleteFile(rootPathIncome + folderDatabase + Constante.luxuriesFile);
        }

        if (allLoans!=null){
            makeLoasData(allLoans);
        }else {
            Utils.deleteFile(rootPathIncome + folderDatabase + Constante.loansFile);
        }
    }
    public void importDataBaseFromFile(){
        emptyDatabase();
        xmlLogic.uploadDataBase();
    }

    public void emptyDatabase(){
//        deleteAllDepozit();
        deletAllIncome();
        deleteAllBills();
        deleteAllLoans();
        deleteAllLuxuries();
        deleteAllProduse();
    }

    public void makeDepozitData(List<Depozit> depozitList){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(Constante.database);
            doc.appendChild(rootElement);

            Element staff;
            Attr attr;

            for (Depozit depozit : depozitList) {
                staff = doc.createElement(Constante.depozitXMLTAG);
                rootElement.appendChild(staff);

                attr = doc.createAttribute(Constante.nume);
                attr.setValue(depozit.getNume());
                staff.setAttributeNode(attr);

                attr= doc.createAttribute(Constante.idDepozit);
                attr.setValue(String.valueOf(depozit.getIdDepozit()));
                staff.setAttributeNode(attr);
            }

            xmlLogic.writeXMLFile(doc,rootPathIncome+folderDatabase+Constante.depozitFile);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    public void makeBillData(List<Bills> billsList){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(Constante.database);
            doc.appendChild(rootElement);

            Element staff;
            Attr attr;

            for (Bills bills : billsList) {
                staff = doc.createElement(Constante.billXMLTAG);
                rootElement.appendChild(staff);

                attr = doc.createAttribute(Constante.nume);
                attr.setValue(bills.getBill_name());
                staff.setAttributeNode(attr);

                attr= doc.createAttribute(Constante.value);
                attr.setValue(String.valueOf(bills.getBill_value()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.data);
                attr.setValue(Formatters.DATE_FORMAT_SIMPLE.format(bills.getDate()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.idDepozit);
                attr.setValue(String.valueOf(bills.getIdDepozit()));
                staff.setAttributeNode(attr);
            }
            xmlLogic.writeXMLFile(doc,rootPathIncome+folderDatabase+Constante.billsFile);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    public void makeIncomeData(List<Income> incomeList){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(Constante.database);
            doc.appendChild(rootElement);

            Element staff;
            Attr attr;

            for (Income income : incomeList) {

                staff = doc.createElement(Constante.incomeXMLTAG);
                rootElement.appendChild(staff);

                attr = doc.createAttribute(Constante.value);
                attr.setValue(String.valueOf(income.getValue()));
                staff.setAttributeNode(attr);

                attr= doc.createAttribute(Constante.extra);
                attr.setValue(String.valueOf(income.getExtraIncome()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.data);
                attr.setValue(Formatters.DATE_FORMAT_SIMPLE.format(income.getDate()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.idDepozit);
                attr.setValue(String.valueOf(income.getIdDepozit()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.savings);
                attr.setValue(String.valueOf(income.getLastMonthSavings()));
                staff.setAttributeNode(attr);
            }
            xmlLogic.writeXMLFile(doc,rootPathIncome+folderDatabase+Constante.incomeFile);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    public void makeProduseData(List<Produse> produseList){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(Constante.database);
            doc.appendChild(rootElement);

            Element staff;
            Attr attr;

            for (Produse produse : produseList) {

                staff = doc.createElement(Constante.produsXMLTAG);
                rootElement.appendChild(staff);

                attr = doc.createAttribute(Constante.nume);
                attr.setValue(produse.getNume());
                staff.setAttributeNode(attr);

                attr= doc.createAttribute(Constante.company);
                attr.setValue(produse.getCompany());
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.data);
                attr.setValue(Formatters.DATE_FORMAT_SIMPLE.format(produse.getDate()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.idDepozit);
                attr.setValue(String.valueOf(produse.getIdDepozit()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.quantity);
                attr.setValue(String.valueOf(produse.getCantitate()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.pret);
                attr.setValue(String.valueOf(produse.getPret()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.reducere);
                attr.setValue(String.valueOf(produse.getReducere()));
                staff.setAttributeNode(attr);
            }
            xmlLogic.writeXMLFile(doc,rootPathIncome+folderDatabase+Constante.produseFile);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    public void makeLuxuriesData(List<Luxuries> luxuriesList){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(Constante.database);
            doc.appendChild(rootElement);

            Element staff;
            Attr attr;

            for (Luxuries luxuries : luxuriesList) {

                staff = doc.createElement(Constante.luxuriesXMLTAG);
                rootElement.appendChild(staff);

                attr = doc.createAttribute(Constante.nume);
                attr.setValue(luxuries.getNume());
                staff.setAttributeNode(attr);

                attr= doc.createAttribute(Constante.pret);
                attr.setValue(String.valueOf(luxuries.getPret()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.data);
                attr.setValue(Formatters.DATE_FORMAT_SIMPLE.format(luxuries.getDate()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.idDepozit);
                attr.setValue(String.valueOf(luxuries.getIdDepozit()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.quantity);
                attr.setValue(String.valueOf(luxuries.getCantitate()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.reducere);
                attr.setValue(String.valueOf(luxuries.getReducere()));
                staff.setAttributeNode(attr);
            }
            xmlLogic.writeXMLFile(doc,rootPathIncome+folderDatabase+Constante.luxuriesFile);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    public void makeLoasData(List<Loans> loansList){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(Constante.database);
            doc.appendChild(rootElement);

            Element staff;
            Attr attr;

            for (Loans loans : loansList) {

                staff = doc.createElement(Constante.loansXMLTAG);
                rootElement.appendChild(staff);

                attr = doc.createAttribute(Constante.nume);
                attr.setValue(loans.getPerson_name());
                staff.setAttributeNode(attr);

                attr= doc.createAttribute(Constante.dateMade);
                attr.setValue(Formatters.DATE_FORMAT_SIMPLE.format(loans.getDate_made()));
                staff.setAttributeNode(attr);

                Date date_limit = loans.getDate_limit();
                if (date_limit!=null) {
                    attr=doc.createAttribute(Constante.dateLimit);
                    attr.setValue(Formatters.DATE_FORMAT_SIMPLE.format(date_limit));
                    staff.setAttributeNode(attr);
                }

                attr=doc.createAttribute(Constante.idDepozit);
                attr.setValue(String.valueOf(loans.getIdDepozit()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.value);
                attr.setValue(String.valueOf(loans.getValue()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.dobanda);
                attr.setValue(String.valueOf(loans.getDobanda()));
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.tipImprumut);
                attr.setValue(loans.getTip_imprumut());
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.dobandaType);
                attr.setValue(loans.getDobanda_type().name());
                staff.setAttributeNode(attr);

                attr=doc.createAttribute(Constante.incheiat);
                attr.setValue(String.valueOf(loans.isIncheiat()));
                staff.setAttributeNode(attr);
            }
            xmlLogic.writeXMLFile(doc,rootPathIncome+folderDatabase+Constante.loansFile);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
