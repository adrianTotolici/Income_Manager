package eu.IncomeManager.GUI;

import eu.IncomeManager.GUI.AbstractComponents.TableModelProduse;
import eu.IncomeManager.GUI.customButtons.custom.component.button.ButtonType;
import eu.IncomeManager.GUI.customButtons.custom.component.button.GlossyButton;
import eu.IncomeManager.GUI.customButtons.util.Theme;
import eu.IncomeManager.GUI.customPanel.CurvedGradientPanel;
import eu.IncomeManager.Logic.Logic;
import eu.IncomeManager.Utils.Colors;
import eu.IncomeManager.Utils.Constante;
import eu.IncomeManager.Utils.IconChoiser;
import eu.IncomeManager.Utils.Language.DefaultLang;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class BuyBackPanel {

    private DefaultLang lang;
    private IconChoiser icon=new IconChoiser();
    private TableModelProduse tableModelProduse;
    private DepozitPanel instance;

    private JLabel buyBackNameLabel=new JLabel();
    private JLabel buyBackValueLabel=new JLabel();
    private JLabel buyBackDateLabel=new JLabel();

    private JTextField buybackName= new JTextField();
    private JTextField buyBackValue= new JTextField();
    private JXDatePicker buyBackDate=new JXDatePicker();

    private GlossyButton buyBackOk;
    private GlossyButton buyBackCancel;

    final JFrame jFrame=new JFrame();
    private JFrame mainFrame;
    private String depozitName;

    public BuyBackPanel(DefaultLang lang, TableModelProduse tableModelProduse, JFrame mainFrame, DepozitPanel instance,
                        String depozitName) {
        this.lang = lang;
        this.tableModelProduse = tableModelProduse;
        this.mainFrame=mainFrame;
        this.instance=instance;
        this.depozitName=depozitName;

        buyBackOk=new GlossyButton(lang.add_buyback_product, Theme.GLOSSY_GREEN_THEME,
                ButtonType.BUTTON_ROUNDED,icon.cashback());
        buyBackCancel=new GlossyButton(lang.cancel_loan, Theme.GLOSSY_RED_THEME,
                ButtonType.BUTTON_ROUNDED,icon.remove());

        showBuyBackPanel();
    }

    private void iniBuyBack(){
        buyBackNameLabel.setText(lang.enter_products_company_name);
        buyBackValueLabel.setText(lang.cashback_value);
        buyBackDateLabel.setText(lang.cashback_date);
        buybackName.setColumns(Constante.TEXT_INPUT_LENGTH);
        buyBackValue.setColumns(Constante.TEXT_INPUT_LENGTH);
        buyBackDate.setDate(new Date());

        actionBuyBack();
    }

    private void actionBuyBack(){
        buyBackCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });

        buyBackOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String company=buybackName.getText();
                String value_string=buyBackValue.getText();
                Date date=buyBackDate.getDate();
                Logic.getInstance().addCashBack(tableModelProduse,company,value_string,date,depozitName);
                buybackName.setText("");
                buyBackValue.setText("");

                instance.iniIncomeTab();
                instance.iniExpensesTab();

                jFrame.dispose();

            }
        });
    }

    private void showBuyBackPanel(){
        iniBuyBack();
        MigLayout layout=new MigLayout("","[grow]","[grow]");
        CurvedGradientPanel panel=new CurvedGradientPanel(Colors.green1,Colors.white,layout);

        panel.add(buyBackNameLabel);
        panel.add(buybackName,"wrap 2");
        panel.add(buyBackValueLabel);
        panel.add(buyBackValue,"wrap 2");
        panel.add(buyBackDateLabel);
        panel.add(buyBackDate,"grow,wrap");
        panel.add(buyBackOk,"span,split 2,center");
        panel.add(buyBackCancel);

        jFrame.add(panel);
        jFrame.setUndecorated(true);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(mainFrame);
    }
}
