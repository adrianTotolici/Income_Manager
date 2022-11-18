package eu.IncomeManager.GUI;

import eu.IncomeManager.GUI.AbstractComponents.TabelModelLuxuries;
import eu.IncomeManager.GUI.customButtons.custom.component.button.ButtonType;
import eu.IncomeManager.GUI.customButtons.custom.component.button.GlossyButton;
import eu.IncomeManager.GUI.customButtons.util.Theme;
import eu.IncomeManager.GUI.customPanel.CurvedGradientPanel;
import eu.IncomeManager.Logic.Logic;
import eu.IncomeManager.Utils.Colors;
import eu.IncomeManager.Utils.IconChoiser;
import eu.IncomeManager.Utils.Language.DefaultLang;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by adrian on 5/19/14.
 */
public class AddNewLuxuriesPanel {

    private IconChoiser icon=new IconChoiser();
    private DefaultLang lang;
    private JFrame jFrame;
    private TabelModelLuxuries tabelModelLuxuries;
    private DepozitPanel instance;
    private String depozitName;

    private JLabel productNameLabel=new JLabel();
    private JLabel valueLabel=new JLabel();
    private JLabel cashBackLabel=new JLabel();
    private JLabel quantitylabel=new JLabel();
    private JLabel dateLabel=new JLabel();

    private JTextField textFieldProductName=new JTextField();
    private JTextField textFieldValue=new JTextField();
    private JTextField textFieldCashback=new JTextField();
    private JTextField textFieldQuantity=new JTextField();
    private JXDatePicker datePicker=new JXDatePicker();

    private GlossyButton addLuxuriesButton;
    private GlossyButton cancelLuxuriesButton;

    private JFrame addNewLuxuries=new JFrame();

    public AddNewLuxuriesPanel(DefaultLang lang, JFrame jFrame,TabelModelLuxuries tabelModelLuxuries, DepozitPanel instance,
                               String depozitName) {
        this.lang = lang;
        this.jFrame = jFrame;
        this.tabelModelLuxuries=tabelModelLuxuries;
        this.instance=instance;
        this.depozitName=depozitName;

        addLuxuriesButton=new GlossyButton(lang.add_luxuries, Theme.GLOSSY_GREEN_THEME, ButtonType.BUTTON_ROUNDED,icon.luxuries());
        cancelLuxuriesButton=new GlossyButton(lang.cancel_loan, Theme.GLOSSY_RED_THEME, ButtonType.BUTTON_ROUNDED,icon.remove());

        showPanel();
    }

    private void showPanel(){
        productNameLabel.setText(lang.product_name);
        valueLabel.setText(lang.value);
        cashBackLabel.setText(lang.cashback_value);
        quantitylabel.setText(lang.quantity);
        dateLabel.setText(lang.date);

        textFieldProductName.setColumns(15);
        textFieldValue.setColumns(15);
        textFieldCashback.setColumns(15);
        textFieldQuantity.setColumns(15);

        datePicker.setDate(new Date());

        MigLayout layout=new MigLayout("","","");
        CurvedGradientPanel panel=new CurvedGradientPanel(Colors.green1,Colors.white,layout);

        panel.add(productNameLabel);
        panel.add(textFieldProductName,"wrap");
        panel.add(valueLabel);
        panel.add(textFieldValue,"wrap");
        panel.add(dateLabel);
        panel.add(datePicker,"grow,wrap");
        panel.add(cashBackLabel);
        panel.add(textFieldCashback,"wrap");
        panel.add(quantitylabel);
        panel.add(textFieldQuantity,"wrap");
        panel.add(addLuxuriesButton,"span, split 2, center");
        panel.add(cancelLuxuriesButton);

        addNewLuxuries.add(panel);
        addNewLuxuries.setUndecorated(true);
        addNewLuxuries.pack();
        addNewLuxuries.setVisible(true);
        addNewLuxuries.setLocationRelativeTo(jFrame);

        addLuxuriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeProdus=textFieldProductName.getText();
                String value=textFieldValue.getText();
                Date data=datePicker.getDate();
                String casback=textFieldCashback.getText();
                String quantity=textFieldQuantity.getText();

                Logic.getInstance().addNewLuxuries(numeProdus,value,data,casback,quantity,tabelModelLuxuries,depozitName);

                instance.iniExpensesTab();
                instance.iniIncomeTab();
                instance.iniLuxuriesTab();
                addNewLuxuries.dispose();
            }
        });

        cancelLuxuriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLuxuries.dispose();
            }
        });
    }
}



