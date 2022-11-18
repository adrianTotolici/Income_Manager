package eu.IncomeManager.Logic;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import eu.IncomeManager.Utils.Constante;
import eu.IncomeManager.Utils.Enumerari;
import eu.IncomeManager.Utils.Utils;
import eu.IncomeManager.dataBase.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class XMLLogic {

    private static XMLLogic instance;

    String dataBaseRoot;


    private XMLLogic(String dataBaseRoot) {
        this.dataBaseRoot = dataBaseRoot;
    }

    public static synchronized XMLLogic getFirstInstance(String dataBaseRoot){
        if (instance==null){
            instance=new XMLLogic(dataBaseRoot);
        }
        return instance;
    }

    public static synchronized XMLLogic getInstance(){
        return instance;
    }

    public void uploadDataBase(){
        readXMLFile(dataBaseRoot+Constante.depozitFile, Constante.depozitXMLTAG);
        readXMLFile(dataBaseRoot+Constante.billsFile, Constante.billXMLTAG);
        readXMLFile(dataBaseRoot+Constante.incomeFile, Constante.incomeXMLTAG);
        readXMLFile(dataBaseRoot+Constante.produseFile, Constante.produsXMLTAG);
        readXMLFile(dataBaseRoot+Constante.luxuriesFile, Constante.luxuriesXMLTAG);
        readXMLFile(dataBaseRoot+Constante.loansFile, Constante.loansXMLTAG);
    }

    private void readXMLFile(String filePath, String nodeTag) {
        try {
            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(nodeTag);
            if (nodeTag.equals(Constante.depozitXMLTAG)) addDepozit(nList);
            if (nodeTag.equals(Constante.incomeXMLTAG)) addIncome(nList);
            if (nodeTag.equals(Constante.produsXMLTAG)) addProduse(nList);
            if (nodeTag.equals(Constante.billXMLTAG)) addBill(nList);
            if (nodeTag.equals(Constante.loansXMLTAG)) addLoans(nList);
            if (nodeTag.equals(Constante.luxuriesXMLTAG)) addLuxuries(nList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeXMLFile(Document doc, String xmlFilePath){
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlFilePath));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void addDepozit(NodeList nodeList){
        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node nNode = nodeList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Depozit depozit=new Depozit();
                depozit.setNume(eElement.getAttribute(Constante.nume));
                depozit.setTabIndex(Integer.parseInt(eElement.getAttribute(Constante.idDepozit)));

                // add depozit to DB
            }
        }
    }
    private void addIncome(NodeList nodeList){
        for (int temp= 0; temp<nodeList.getLength(); temp++){
            Node node=nodeList.item(temp);
            if (node.getNodeType()==Node.ELEMENT_NODE){
                Element element= (Element) node;
                Income income=new Income();
                income.setValue(Double.valueOf(element.getAttribute(Constante.value)));
                income.setExtraIncome(Double.valueOf(element.getAttribute(Constante.extra)));
                income.setLastMonthSavings(Double.valueOf(element.getAttribute(Constante.savings)));
                income.setDate(Utils.convertStringtoDate(element.getAttribute(Constante.data)));
                income.setIdDepozit(Integer.parseInt(element.getAttribute(Constante.idDepozit)));

                DataBaseLogic.getInstance().saveObject(income);
            }
        }
    }
    private void addProduse(NodeList nodeList){
        for (int temp=0; temp<nodeList.getLength(); temp++){
            Node node=nodeList.item(temp);
            if (node.getNodeType()==Node.ELEMENT_NODE){
                Element element= (Element) node;
                Produse produse=new Produse();
                produse.setNume(element.getAttribute(Constante.nume));
                produse.setCompany(element.getAttribute(Constante.company));
                produse.setIdDepozit(Integer.parseInt(element.getAttribute(Constante.idDepozit)));
                produse.setPret(Double.parseDouble(element.getAttribute(Constante.pret)));
                produse.setCantitate(Double.parseDouble(element.getAttribute(Constante.quantity)));
                produse.setReducere(Double.parseDouble(element.getAttribute(Constante.reducere)));
                produse.setDate(Utils.convertStringtoDate(element.getAttribute(Constante.data)));

                DataBaseLogic.getInstance().saveObject(produse);
            }
        }
    }
    private void addBill(NodeList nodeList){
        for (int temp=0; temp<nodeList.getLength(); temp++){
            Node node=nodeList.item(temp);
            if (node.getNodeType()==Node.ELEMENT_NODE){
                Element element= (Element) node;
                Bills bills=new Bills();
                bills.setBill_name(element.getAttribute(Constante.nume));
                bills.setBill_value(Double.parseDouble(element.getAttribute(Constante.value)));
                bills.setDate(Utils.convertStringtoDate(element.getAttribute(Constante.data)));
                bills.setIdDepozit(Integer.parseInt(element.getAttribute(Constante.idDepozit)));

                DataBaseLogic.getInstance().saveObject(bills);
            }
        }
    }
    private void addLoans(NodeList nodeList){
        for (int i=0;i<nodeList.getLength();i++){
            Node node=nodeList.item(i);
            if (node.getNodeType()==Node.ELEMENT_NODE){
                Element element=(Element) node;
                Loans loans=new Loans();
                loans.setIdDepozit(Integer.parseInt(element.getAttribute(Constante.idDepozit)));
                loans.setPerson_name(element.getAttribute(Constante.nume));
                String dateLimit = element.getAttribute(Constante.dateLimit);
                if (dateLimit.isEmpty()){
                    loans.setDate_limit(null);
                }else {
                    loans.setDate_limit(Utils.convertStringtoDate(dateLimit));
                }
                loans.setDate_made(Utils.convertStringtoDate(element.getAttribute(Constante.dateMade)));
                loans.setDobanda(Double.parseDouble(element.getAttribute(Constante.dobanda)));
                loans.setValue(Double.parseDouble(element.getAttribute(Constante.value)));
                String dobandaType = element.getAttribute(Constante.dobandaType);
                Enumerari.DobandaType type = Enumerari.DobandaType.valueOf(dobandaType);
                loans.setDobanda_type(type);
                loans.setIncheiat(Boolean.parseBoolean(element.getAttribute(Constante.incheiat)));
                loans.setTip_imprumut(element.getAttribute(Constante.tipImprumut));

                DataBaseLogic.getInstance().saveObject(loans);
            }
        }
    }
    private void addLuxuries(NodeList nodeList){
        for (int i=0;i<nodeList.getLength();i++){
            Node node=nodeList.item(i);
            if (node.getNodeType()==Node.ELEMENT_NODE){
                Element element= (Element) node;
                Luxuries luxuries=new Luxuries();
                luxuries.setIdDepozit(Integer.parseInt(element.getAttribute(Constante.idDepozit)));
                luxuries.setNume(element.getAttribute(Constante.nume));
                luxuries.setPret(Double.parseDouble(element.getAttribute(Constante.pret)));
                luxuries.setCantitate(Double.parseDouble(element.getAttribute(Constante.quantity)));
                luxuries.setDate(Utils.convertStringtoDate(element.getAttribute(Constante.data)));
                String reducere = element.getAttribute(Constante.reducere);
                if (reducere.isEmpty()){
                    luxuries.setReducere(0);
                }else {
                    luxuries.setReducere(Double.parseDouble(reducere));
                }
                DataBaseLogic.getInstance().saveObject(luxuries);
            }
        }
    }

}