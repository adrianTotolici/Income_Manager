package eu.IncomeManager.GUI;

import eu.IncomeManager.GUI.AbstractComponents.*;
import eu.IncomeManager.GUI.customButtons.custom.component.button.ButtonType;
import eu.IncomeManager.GUI.customButtons.custom.component.button.GlossyButton;
import eu.IncomeManager.GUI.customButtons.util.Theme;
import eu.IncomeManager.GUI.customPanel.CurvedGradientPanel;
import eu.IncomeManager.GUI.customPanel.GradientPanel;
import eu.IncomeManager.GUI.customTabPane.PlasticTabbedPaneUI;
import eu.IncomeManager.Logic.Logic;
import eu.IncomeManager.Utils.*;
import eu.IncomeManager.Utils.Language.DefaultLang;
import eu.IncomeManager.dataBase.Bills;
import eu.IncomeManager.dataBase.Loans;
import eu.IncomeManager.dataBase.Luxuries;
import eu.IncomeManager.dataBase.Produse;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by adrian on 21.10.2014.
 */
public class DepozitPanel {

    private static DepozitPanel instance;
    private static JFrame mainFrame;
    public  String depozitName;

    private DefaultLang lang;
    private IconChoiser icon=new IconChoiser();

    //todo add product Panel
    private JTextField productName=new JTextField();
    private JTextField productCompany=new JTextField();
    private JTextField productPrice=new JTextField();
    private JTextField productQuantity=new JTextField();
    private JTextField productCashBack=new JTextField();
    private JXDatePicker productDatePicker= new JXDatePicker(new Date());

    JLabel productInfoLabel;

    private GlossyButton productCancelButton;
    private GlossyButton productCashBackButton;
    private GlossyButton productNewProductButton;

    // todo table products today
    private JXDatePicker tableShowDatePicker=new JXDatePicker(new Date());
    private TableModelProduse tableShowProduct=new TableModelProduse();
    private JTable productTable=new JTable();

    //todo tabIncome panel
    private JTextField monthIncomeTabIncome=new JTextField();
    private JTextField extraIncomeTabIncome=new JTextField();
    private JTextField lastIncomeTabIncome=new JTextField();
    private JTextField todaySavingsTabIncome=new JTextField();
    private JTextField weekSavingsTabIncome=new JTextField();
    private JTextField monthSavingsTabIncome=new JTextField();

    private GlossyButton saveNewIncomeButton;
    private GlossyButton addDepozitButton;

    //todo tabExpenses panel
    private JTextField billNameTabExpenses=new JTextField();
    private JTextField billValueTabExpenses=new JTextField();
    private JTextField todayExpensesTabExpenses=new JTextField();
    private JTextField weekExpensesTabExpenses=new JTextField();
    private JTextField monthExpensesTabExpenses=new JTextField();
    private JTextField totalBillTabExpenses=new JTextField();

    private JXDatePicker searchBillDatePicker=new JXDatePicker();
    private JXDatePicker addbillDatePicker=new JXDatePicker();

    private GlossyButton removeBillButtonExepensesTab;
    private GlossyButton addBillButtonExpensesTab;

    private TableModelBills billTableModel=new TableModelBills();
    private JTable billTable=new JTable();


    //todo tabLoan panel
    private TabelModelLoanMade tabelModelLoanMade=new TabelModelLoanMade();
    private JTable loanTable=new JTable();

    private GlossyButton reciveLoanTab;
    private GlossyButton giveLoanTab;

    //todo luxuriesTab panel
    private JTextField luxuriesTotalTab=new JTextField();

    private GlossyButton addLuxuriesButtonLuxuriesTab;
    private GlossyButton removeLuxuriesButtonLuxuriesTab;

    private TabelModelLuxuries tabelModelLuxuries=new TabelModelLuxuries();
    private JTable luxuriesTable=new JTable();


    //todo statisticsTab panel
    private JXDatePicker statisticsDatePickerTabSatistics= new JXDatePicker(new Date());
    private TableModelStatistics tableModelStatistics=new TableModelStatistics();



    public DepozitPanel(DefaultLang lang,String depozitName, JFrame jFrame){

        this.lang=lang;
        this.depozitName=depozitName;
        initEntity();

        mainFrame=jFrame;
        instance=this;

        iniPanel();
        keyListener();
    }

    public void initEntity(){
        productInfoLabel=new JLabel(lang.must_be_completed);

        productCancelButton= new GlossyButton(lang.remove_last_product, Theme.GLOSSY_RED_THEME,
                ButtonType.BUTTON_ROUNDED, icon.remove());
        productCashBackButton= new GlossyButton(lang.add_buyback_product, Theme.GLOSSY_YELLOW_THEME,
                ButtonType.BUTTON_ROUNDED, icon.cashback());
        productNewProductButton=new GlossyButton(lang.add_new_product, Theme.GLOSSY_GREEN_THEME,
                ButtonType.BUTTON_ROUNDED, icon.addNew());

        saveNewIncomeButton=new GlossyButton(lang.save_new_income, Theme.GLOSSY_GREEN_THEME,
                ButtonType.BUTTON_ROUNDED, icon.debosit());
        addDepozitButton=new GlossyButton(lang.add_new_depozit, Theme.GLOSSY_BLUE_THEME,
                ButtonType.BUTTON_ROUNDED, icon.addNew());

        removeBillButtonExepensesTab=new GlossyButton(lang.remove_last_bill,Theme.GLOSSY_RED_THEME,
                ButtonType.BUTTON_ROUNDED,icon.remove());
        addBillButtonExpensesTab=new GlossyButton(lang.add_bill,Theme.GLOSSY_GREEN_THEME,
                ButtonType.BUTTON_ROUNDED, icon.addBill());

        reciveLoanTab=new GlossyButton(lang.pay_loan, Theme.GLOSSY_YELLOW_THEME,
                ButtonType.BUTTON_ROUNDED, icon.loanPayBack());
        giveLoanTab=new GlossyButton(lang.give_new_loan, Theme.GLOSSY_GREEN_THEME,
                ButtonType.BUTTON_ROUNDED, icon.giveLoan());

        addLuxuriesButtonLuxuriesTab=new GlossyButton(lang.add_luxuries, Theme.GLOSSY_GREEN_THEME,
                ButtonType.BUTTON_ROUNDED,icon.luxuries());
        removeLuxuriesButtonLuxuriesTab=new GlossyButton(lang.remove_luxuries, Theme.GLOSSY_RED_THEME,
                ButtonType.BUTTON_ROUNDED,icon.remove());
    }


    public JPanel showMainPanel(){
        MigLayout layout=new MigLayout("","[grow]","");
        CurvedGradientPanel panel=new CurvedGradientPanel(Colors.blue2,Colors.white,layout);

        JPanel addJPanel=showAddProductPanel();
        JPanel tableJPanel=showProductTablePanel();
        JTabbedPane tabPanel=showTabPane();

        panel.add(tableJPanel,"grow");
        panel.add(addJPanel, "grow,wrap");
        panel.add(tabPanel, "span,grow");

        return panel;
    }

    public void iniPanel(){
        iniProductTable();
        iniBillTable();

        iniIncomeTab();
        iniExpensesTab();

        iniLoansTable();
        buttonAction();

        iniLuxuriesTable();
        iniLuxuriesTab();

        iniStatisticsTable();
    }

    private JPanel showAddProductPanel(){
        JLabel newLine=new JLabel(" ");
        MigLayout layout=new MigLayout("","","");
        GradientPanel panel=new GradientPanel(layout);

        JLabel productNameLabel =new JLabel(lang.product_name+"*");
        JLabel productPriceLabel=new JLabel(lang.product_price+"*");
        JLabel productQuantityLabel= new JLabel(lang.quantity);
        JLabel productCashbackLabel= new JLabel(lang.cashback_value);
        JLabel productPurchaseDateLabel= new JLabel(lang.pruchase_date);
        JLabel separatorLabel=new JLabel("/");


        productName.setColumns(Constante.TEXT_INPUT_LENGTH);
        productCompany.setColumns(Constante.TEXT_INPUT_LENGTH);
        productPrice.setColumns(Constante.TEXT_INPUT_LENGTH);
        productQuantity.setColumns(Constante.TEXT_INPUT_LENGTH);
        productCashBack.setColumns(Constante.TEXT_INPUT_LENGTH);

        productInfoLabel.setFont(new Font("Verdana",Font.BOLD,12));
        productInfoLabel.setForeground(Colors.red1);
        productInfoLabel.setVisible(false);

        panel.add(newLine,"grow,wrap");
        panel.add(productNameLabel,"grow");
        panel.add(productName);
        panel.add(separatorLabel,"split 2");
        panel.add(productCompany,"wrap");
        panel.add(productPriceLabel,"grow");
        panel.add(productPrice,"wrap 2");
        panel.add(productQuantityLabel,"grow");
        panel.add(productQuantity,"wrap 2");
        panel.add(productCashbackLabel,"grow");
        panel.add(productCashBack,"wrap 2");
        panel.add(productPurchaseDateLabel,"grow");
        panel.add(productDatePicker,"grow,wrap");
        panel.add(productCancelButton,"grow");
        panel.add(productCashBackButton,"grow");
        panel.add(productNewProductButton,"grow,wrap");
        panel.add(productInfoLabel,"span 3, center");

        return panel;
    }

    private JPanel showProductTablePanel(){

        MigLayout layout=new MigLayout("","[grow]","");
        GradientPanel panel=new GradientPanel(layout);

        JLabel tableShowProductLabel=new JLabel(lang.buy_today);
        JLabel tableShowDateLabel=new JLabel(lang.products_from_date);


        panel.add(tableShowProductLabel,"wrap 2");
        panel.add(tableShowDateLabel,"split 2, center");
        panel.add(tableShowDatePicker,"wrap");
        panel.add(afisProdusTableComponent(), "grow");
        return panel;
    }
    private JScrollPane afisProdusTableComponent(){


        ArrayList<String> titleHeaders=new ArrayList<String>();
        titleHeaders.add(lang.NR);
        titleHeaders.add(lang.product_name);
        titleHeaders.add(lang.product_price);
        titleHeaders.add(lang.quantity);
        titleHeaders.add(lang.cashback);
        tableShowProduct.initTable(titleHeaders);
        productTable.setModel(tableShowProduct);

        JScrollPane scrollPane=new JScrollPane(productTable);
        scrollPane.setWheelScrollingEnabled(true);

        return scrollPane;
    }

    private JTabbedPane showTabPane(){
        JTabbedPane tabbedPane=new JTabbedPane();
        tabbedPane.add(lang.income,incomeTab());
        tabbedPane.add(lang.expenses,expensesTab());
        tabbedPane.add(lang.loans,loanTab());
        tabbedPane.add(lang.luxuries,luxuriesTab());
        tabbedPane.add(lang.statistics,statisticsTab());
        tabbedPane.setUI(new PlasticTabbedPaneUI());
        return tabbedPane;
    }

    private JPanel incomeTab(){
        MigLayout migLayout =new MigLayout("","","");
        CurvedGradientPanel incomePanel=new CurvedGradientPanel(Colors.blue4,Colors.white,migLayout);

        JLabel monthIncomeTabIncomeLabel=new JLabel(lang.month_income);
        JLabel extraIncomeTabIncomeLabel=new JLabel(lang.extra_income);
        JLabel lastMonthIncomeTabIncomeLabel=new JLabel(lang.last_month_income);
        JLabel todaySavingsTabIncomeLabel=new JLabel(lang.today_savings);
        JLabel weekSavingsTabIncomeLabel=new JLabel(lang.week_savings);
        JLabel monthSavingsTabIncomeLabel=new JLabel(lang.month_savings);

        monthIncomeTabIncome.setColumns(Constante.TEXT_INPUT_LENGTH);
        todaySavingsTabIncome.setColumns(Constante.TEXT_INPUT_LENGTH);
        extraIncomeTabIncome.setColumns(Constante.TEXT_INPUT_LENGTH);
        weekSavingsTabIncome.setColumns(Constante.TEXT_INPUT_LENGTH);
        lastIncomeTabIncome.setColumns(Constante.TEXT_INPUT_LENGTH);
        monthSavingsTabIncome.setColumns(Constante.TEXT_INPUT_LENGTH);

        todaySavingsTabIncome.setEditable(false);
        weekSavingsTabIncome.setEditable(false);
        lastIncomeTabIncome.setEditable(false);
        monthSavingsTabIncome.setEditable(false);

        incomePanel.add(monthIncomeTabIncomeLabel);
        incomePanel.add(monthIncomeTabIncome);
        incomePanel.add(todaySavingsTabIncomeLabel);
        incomePanel.add(todaySavingsTabIncome,"wrap");
        incomePanel.add(extraIncomeTabIncomeLabel);
        incomePanel.add(extraIncomeTabIncome);
        incomePanel.add(weekSavingsTabIncomeLabel);
        incomePanel.add(weekSavingsTabIncome,"wrap");
        incomePanel.add(lastMonthIncomeTabIncomeLabel);
        incomePanel.add(lastIncomeTabIncome);
        incomePanel.add(monthSavingsTabIncomeLabel);
        incomePanel.add(monthSavingsTabIncome,"wrap");
        incomePanel.add(saveNewIncomeButton,"span2, center");
        incomePanel.add(addDepozitButton,"span2, center");

        return incomePanel;
    }

    private JPanel expensesTab(){
        MigLayout migLayout =new MigLayout("","[grow]","");
        JPanel expensePanel=new JPanel(migLayout);

        JLabel showBillDateTabExpensesLabel=new JLabel(lang.show_bills);
        JLabel billNameTabExpensesLabel=new JLabel(lang.bill_name);
        JLabel billValueTabExpensesLabel=new JLabel(lang.bill_value);
        JLabel billDateTabExpensesLabel=new JLabel(lang.bill_date);
        JLabel todayExpensesTabExpensesLabel=new JLabel(lang.today_expenses);
        JLabel weekExpensesTabExpensesLabel=new JLabel(lang.week_expenses);
        JLabel monthExpensesTabExpensesLabel=new JLabel(lang.month_expenses);
        JLabel totalBilsTabExpensesLabel=new JLabel(lang.total_bills);

        MigLayout migLayoutBill=new MigLayout("","[grow]","");
        CurvedGradientPanel billPanel=new CurvedGradientPanel(Colors.blue4,Colors.white,migLayoutBill);

        searchBillDatePicker.setDate(new Date());
        addbillDatePicker.setDate(new Date());


        billNameTabExpenses.setColumns(Constante.TEXT_INPUT_LENGTH);
        billValueTabExpenses.setColumns(Constante.TEXT_INPUT_LENGTH);


        billPanel.add(showBillDateTabExpensesLabel,"split 3,center");
        billPanel.add(searchBillDatePicker,"wrap");
        billPanel.add(afisBillComponent(),"grow,wrap");
        billPanel.add(billNameTabExpensesLabel,"split 2,center");
        billPanel.add(billNameTabExpenses,"wrap");
        billPanel.add(billValueTabExpensesLabel,"split 2, center");
        billPanel.add(billValueTabExpenses,"wrap");
        billPanel.add(billDateTabExpensesLabel,"split 2, center");
        billPanel.add(addbillDatePicker,"wrap");
        billPanel.add(removeBillButtonExepensesTab,"split 2, center");
        billPanel.add(addBillButtonExpensesTab);

        MigLayout migLayoutExpenses=new MigLayout("","","");
        CurvedGradientPanel expensesPanel=new CurvedGradientPanel(Colors.red2,Colors.white,migLayoutExpenses);

        todayExpensesTabExpenses.setColumns(Constante.TEXT_INPUT_LENGTH);
        weekExpensesTabExpenses.setColumns(Constante.TEXT_INPUT_LENGTH);
        monthExpensesTabExpenses.setColumns(Constante.TEXT_INPUT_LENGTH);
        totalBillTabExpenses.setColumns(Constante.TEXT_INPUT_LENGTH);

        todayExpensesTabExpenses.setEditable(false);
        weekExpensesTabExpenses.setEditable(false);
        monthExpensesTabExpenses.setEditable(false);
        totalBillTabExpenses.setEditable(false);

        expensesPanel.add(todayExpensesTabExpensesLabel);
        expensesPanel.add(todayExpensesTabExpenses,"wrap");
        expensesPanel.add(weekExpensesTabExpensesLabel);
        expensesPanel.add(weekExpensesTabExpenses,"wrap");
        expensesPanel.add(monthExpensesTabExpensesLabel);
        expensesPanel.add(monthExpensesTabExpenses,"wrap");
        expensesPanel.add(totalBilsTabExpensesLabel);
        expensesPanel.add(totalBillTabExpenses);

        expensePanel.add(billPanel,"grow");
        expensePanel.add(expensesPanel,"grow");
        return expensePanel;
    }
    private JScrollPane afisBillComponent(){
        ArrayList<String> titleHeaders=new ArrayList<String>();
        titleHeaders.add(lang.NR);
        titleHeaders.add(lang.bill_name);
        titleHeaders.add(lang.bill_value);

        billTableModel.initTable(titleHeaders);
        billTable.setModel(billTableModel);

        JScrollPane scrollPane=new JScrollPane(billTable);
        scrollPane.setWheelScrollingEnabled(true);
        return new JScrollPane(billTable);
    }

    private JPanel loanTab(){
        MigLayout migLayout=new MigLayout("","[grow]","");
        CurvedGradientPanel loanPanel=new CurvedGradientPanel(Colors.blue4,Colors.white,migLayout);

        loanPanel.add(afisLoanMadeComponent(),"grow, wrap");
        loanPanel.add(reciveLoanTab,"split 2, center");
        loanPanel.add(giveLoanTab);

        return loanPanel;

    }
    private JScrollPane afisLoanMadeComponent(){
        java.util.List<String> titleHeaders=new ArrayList<String>();
        titleHeaders.add(lang.NR);
        titleHeaders.add(lang.person_name);
        titleHeaders.add(lang.value);
        titleHeaders.add(lang.date_made);
        titleHeaders.add(lang.date_limit);
        titleHeaders.add(lang.interest_type);
        titleHeaders.add(lang.interest_value);

        tabelModelLoanMade.initTable(titleHeaders);
        loanTable.setModel(tabelModelLoanMade);

        JScrollPane scrollPane=new JScrollPane(loanTable);
        scrollPane.setWheelScrollingEnabled(true);
        return new JScrollPane(loanTable);
    }

    private JPanel luxuriesTab(){

        JLabel luxuriesTotalLabel=new JLabel(lang.total_luxuries);

        MigLayout migLayout=new MigLayout("","[grow]","");
        CurvedGradientPanel luxuriesPanel=new CurvedGradientPanel(Colors.blue4,Colors.white,migLayout);

        luxuriesTotalTab.setColumns(Constante.TEXT_INPUT_LENGTH);
        luxuriesTotalTab.setEditable(false);

        luxuriesPanel.add(afisLuxuriesTable(),"grow,wrap");
        luxuriesPanel.add(luxuriesTotalLabel,"split 2, center");
        luxuriesPanel.add(luxuriesTotalTab, "wrap");
        luxuriesPanel.add(removeLuxuriesButtonLuxuriesTab, "split 2,center");
        luxuriesPanel.add(addLuxuriesButtonLuxuriesTab);

        return luxuriesPanel;
    }
    private JScrollPane afisLuxuriesTable(){
        ArrayList<String> titleHeaders=new ArrayList<String>();
        titleHeaders.add(lang.NR);
        titleHeaders.add(lang.luxuries);
        titleHeaders.add(lang.value);
        titleHeaders.add(lang.quantity);
        titleHeaders.add(lang.cashback);

        tabelModelLuxuries.initTable(titleHeaders);
        luxuriesTable.setModel(tabelModelLuxuries);

        JScrollPane scrollPane=new JScrollPane(luxuriesTable);
        scrollPane.setWheelScrollingEnabled(true);
        return new JScrollPane(luxuriesTable);
    }

    private JPanel statisticsTab(){
        MigLayout migLayout=new MigLayout("","[grow]","");
        CurvedGradientPanel statisticsPanel=new CurvedGradientPanel(Colors.blue4,Colors.white,migLayout);

        JLabel monthStatisticsLabel=new JLabel(lang.statistics_for_month);

        statisticsPanel.add(monthStatisticsLabel,"split 3, center");
        statisticsPanel.add(statisticsDatePickerTabSatistics,"wrap");
        statisticsPanel.add(afisStatisticsTable(),"grow");

        return statisticsPanel;
    }
    private JScrollPane afisStatisticsTable(){
        JTable table=new JTable();
        ArrayList<String> titleHeadres=new ArrayList<String>();
        titleHeadres.add(lang.NR);
        titleHeadres.add(lang.product_name);
        titleHeadres.add(lang.product_price);
        titleHeadres.add(lang.quantity);
        titleHeadres.add(lang.value);

        tableModelStatistics.initTable(titleHeadres);
        table.setModel(tableModelStatistics);

        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setWheelScrollingEnabled(true);
        return new JScrollPane(table);

    }



    /**
     * buton Action method
     */
    private void buttonAction(){

        addDepozitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddDepozitPanel(lang,mainFrame,instance);
            }
        });

        productCashBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuyBackPanel(lang,tableShowProduct,mainFrame,instance,depozitName);
            }
        });

        reciveLoanTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = loanTable.getSelectedRows();
                for (int selectedRow : selectedRows) {
                    Loans loans = tabelModelLoanMade.getValueAtRow(selectedRow);
                    new PayBackLoanPanel(lang, mainFrame, loans, instance);

                }
            }
        });

        giveLoanTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GiveLoanPanel(lang,tabelModelLoanMade,mainFrame,instance,depozitName);
            }
        });

        addLuxuriesButtonLuxuriesTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddNewLuxuriesPanel(lang,mainFrame,tabelModelLuxuries,instance, depozitName);
            }
        });

        productNewProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewProduct();
                iniIncomeTab();
                iniExpensesTab();
                iniStatisticsTable();
            }
        });

        productCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProducts();
                iniProductTable();
                iniIncomeTab();
                iniExpensesTab();
                iniStatisticsTable();
            }
        });

        saveNewIncomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveNewIncome();
                iniIncomeTab();
                iniExpensesTab();
            }
        });

        searchBillDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniBillTable();
            }
        });

        addBillButtonExpensesTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewBill();
                iniExpensesTab();
                iniIncomeTab();
            }
        });

        removeBillButtonExepensesTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBill();
                iniBillTable();
                iniExpensesTab();
                iniIncomeTab();
            }
        });

        removeLuxuriesButtonLuxuriesTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeLuxuries();
                iniLuxuriesTab();
                iniExpensesTab();
                iniIncomeTab();
                iniLuxuriesTable();
            }
        });

        statisticsDatePickerTabSatistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniStatisticsTable();
            }
        });

        tableShowDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniProductTable();
            }
        });

    }
    public void keyListener(){

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }

            public void keyPressed(KeyEvent keyEvent) {
                printIt("Pressed", keyEvent);
            }

            private void printIt(String title, KeyEvent keyEvent) {
                int keyCode = keyEvent.getKeyCode();
                String keyText = KeyEvent.getKeyText(keyCode);
                if (keyCode== KeyEvent.VK_ENTER){
                    productNewProductButton.doClick();
                }
                if (keyCode==KeyEvent.VK_DOWN){
                    changeFocusDown();
                }
                if (keyCode==KeyEvent.VK_UP){
                    changeFocusUp();
                }
                if (keyCode==KeyEvent.VK_LEFT){
                    changeFocusLeft();
                }
                if (keyCode==KeyEvent.VK_RIGHT){
                    changeFocusRight();
                }
                if (keyCode==KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }
                System.out.println(title + " : " + keyText);
            }
        };

        productName.addKeyListener(keyListener);
        productPrice.addKeyListener(keyListener);
        productQuantity.addKeyListener(keyListener);
        productCashBack.addKeyListener(keyListener);
        productCompany.addKeyListener(keyListener);
    }

    private void changeFocusDown(){
        if (productName.hasFocus()){
            productPrice.requestFocus();
        }
        if (productPrice.hasFocus()){
            productQuantity.requestFocus();
        }
        if (productQuantity.hasFocus()){
            productCashBack.requestFocus();
        }
    }
    private void changeFocusUp(){
        if (productCashBack.hasFocus()){
            productQuantity.requestFocus();
        }
        if (productQuantity.hasFocus()){
            productPrice.requestFocus();
            return;
        }
        if (productPrice.hasFocus()){
            productName.requestFocus();
        }
    }
    private void changeFocusRight(){
        if (productName.hasFocus()){
            productCompany.requestFocus();
        }
    }
    private void changeFocusLeft(){
        if (productCompany.hasFocus()){
            productName.requestFocus();
        }
    }

    private void iniProductTable(){
        Date date=tableShowDatePicker.getDate();
        tableShowProduct.removeAllProducts();
        Logic.getInstance().populateProductTable(tableShowProduct, date, depozitName);
    }
    private void iniBillTable(){
     Date date=searchBillDatePicker.getDate();
        if (date==null) date=new Date();
        billTableModel.removeAllBill();
        Logic.getInstance().populateBillTable(billTableModel,date,depozitName);
    }
    public void iniLoansTable(){
        tabelModelLoanMade.removeAllLoans();
        Logic.getInstance().populateLoanTable(tabelModelLoanMade,depozitName);
    }
    private void iniLuxuriesTable(){
        tabelModelLuxuries.removeAllLuxuries();
        Logic.getInstance().populateLuxuriesTable(tabelModelLuxuries,depozitName);
    }
    private void iniStatisticsTable(){
        Date date=statisticsDatePickerTabSatistics.getDate();
        if (date==null) date=new Date();
        tableModelStatistics.removeAllProduseStatistics();
        Logic.getInstance().populateStatisticsTable(tableModelStatistics,date,depozitName);
    }

    public void iniIncomeTab(){
        Logic.getInstance().iniIncomeTab(monthIncomeTabIncome, extraIncomeTabIncome, lastIncomeTabIncome,
                todaySavingsTabIncome, weekSavingsTabIncome, monthSavingsTabIncome, depozitName);
    }
    public void iniExpensesTab(){
        Logic.getInstance().iniExpensesTab(todayExpensesTabExpenses,weekExpensesTabExpenses,monthExpensesTabExpenses,
                totalBillTabExpenses,depozitName);
        billNameTabExpenses.setText("");
        billValueTabExpenses.setText("");
    }
    public void iniLuxuriesTab(){
        Logic.getInstance().iniLuxuriesTab(luxuriesTotalTab,depozitName);

    }

    private void addNewProduct(){
        String name=productName.getText();
        String price=productPrice.getText();
        String company=productCompany.getText();
        String quantityString=productQuantity.getText();
        String cashBack=productCashBack.getText();
        Date date=productDatePicker.getDate();


        if ((name!=null)&&(name.length()>0)&&(price!=null)&&(price.length()>0)){
            Logic.getInstance().addNewProduct(tableShowProduct,name,company,price,quantityString,cashBack,date,depozitName);

            if (Utils.verify_is_curent_month(date)){
                Logic.getInstance().iniIncomeTab(monthIncomeTabIncome,extraIncomeTabIncome,lastIncomeTabIncome,
                        todaySavingsTabIncome, weekSavingsTabIncome,monthIncomeTabIncome,depozitName);
            }
        }else {
            productInfoLabel.setVisible(true);
        }

        SoundChoiser.getInstace().purchaseItem();
        productName.setText("");
        productPrice.setText("");
        productQuantity.setText("");
        productCashBack.setText("");
    }
    private void removeProducts(){
        int[] selectedRows = productTable.getSelectedRows();
        for (int selectedRow : selectedRows) {
            Produse produse= tableShowProduct.getRowValue(selectedRow);
            Logic.getInstance().removeProduct(produse);
        }
    }
    private void saveNewIncome(){
        String monthIncome=monthIncomeTabIncome.getText();
        String extraIncome=extraIncomeTabIncome.getText();
        String lastIncome=lastIncomeTabIncome.getText();

        Logic.getInstance().saveNewIncome(monthIncome, extraIncome, lastIncome, depozitName);
    }
    private void addNewBill(){
        String name=billNameTabExpenses.getText();
        String value=billValueTabExpenses.getText();
        Date date=addbillDatePicker.getDate();

        Logic.getInstance().addBill(billTableModel, name, value, date,depozitName);
    }
    private void removeBill(){
        int[] selectedRows = billTable.getSelectedRows();
        for (int selectedRow : selectedRows) {
            Bills bill = billTableModel.getValueAtRow(selectedRow);
            Logic.getInstance().removeBill(bill);
        }
    }
    private void removeLuxuries(){
        int[] selectedRows = luxuriesTable.getSelectedRows();
        for (int selectedRow : selectedRows) {
            Luxuries luxuries = tabelModelLuxuries.getValueAtRow(selectedRow);
            Logic.getInstance().removeLuxuries(luxuries);
        }
    }

}
