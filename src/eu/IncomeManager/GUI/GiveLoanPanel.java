package eu.IncomeManager.GUI;

import eu.IncomeManager.GUI.AbstractComponents.TabelModelLoanMade;
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
public class GiveLoanPanel {

    private IconChoiser icon=new IconChoiser();
    private DefaultLang lang;
    private TabelModelLoanMade tabelModelLoanMade;
    private JFrame mainFrame;
    private DepozitPanel instance;
    private String depozitName;

    private JLabel personNameLabel=new JLabel();
    private JLabel valueLabel=new JLabel();
    private JLabel dateMadeLabel=new JLabel();
    private JLabel dateLimitLabel=new JLabel();

    private JComboBox<String> dobanda;

    private JTextField textFieldPersonName=new JTextField();
    private JTextField textFieldValue=new JTextField();
    private JXDatePicker dateLoanMade=new JXDatePicker();
    private JXDatePicker dateLoanLimit=new JXDatePicker();

    private GlossyButton addLoanButton;
    private GlossyButton cancelLoanButton;

    private JFrame addNewLoan=new JFrame();

    public GiveLoanPanel(DefaultLang lang, TabelModelLoanMade tabelModelLoanMade, JFrame mainFrame, DepozitPanel instance,
                         String depozitName) {
        this.lang=lang;
        this.tabelModelLoanMade=tabelModelLoanMade;
        this.mainFrame=mainFrame;
        this.instance=instance;
        this.depozitName=depozitName;
        dobanda=new JComboBox<String>(lang.combolist);

        addLoanButton=new GlossyButton(lang.add_loan, Theme.GLOSSY_GREEN_THEME,
                ButtonType.BUTTON_ROUNDED,icon.giveLoan());

        cancelLoanButton=new GlossyButton(lang.cancel_loan, Theme.GLOSSY_RED_THEME,
                ButtonType.BUTTON_ROUNDED,icon.remove());

        showPanel();
    }

    private void showPanel(){
        personNameLabel.setText(lang.person_name);
        valueLabel.setText(lang.value);
        dateMadeLabel.setText(lang.date_made);
        dateLimitLabel.setText(lang.date_limit);

        textFieldPersonName.setColumns(15);
        textFieldValue.setColumns(15);

        dateLoanMade.setDate(new Date());

        MigLayout layout=new MigLayout("","","");
        CurvedGradientPanel panel=new CurvedGradientPanel(Colors.green1,Colors.white,layout);

        panel.add(personNameLabel);
        panel.add(textFieldPersonName,"wrap");
        panel.add(valueLabel);
        panel.add(textFieldValue,"wrap");
        panel.add(dateMadeLabel);
        panel.add(dateLoanMade,"grow,wrap");
        panel.add(dateLimitLabel);
        panel.add(dateLoanLimit,"grow,wrap");
        panel.add(dobanda,"span,center,wrap");
        panel.add(addLoanButton,"span, split 2, center");
        panel.add(cancelLoanButton);

        addNewLoan.add(panel);
        addNewLoan.setUndecorated(true);
        addNewLoan.pack();
        addNewLoan.setVisible(true);
        addNewLoan.setLocationRelativeTo(mainFrame);

        addLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numePersoana= textFieldPersonName.getText();
                Double value_string= Double.valueOf(textFieldValue.getText());
                Date date_made=dateLoanMade.getDate();
                Date date_limit=dateLoanLimit.getDate();
                int dobanda_type_int=dobanda.getSelectedIndex();

                if ((numePersoana!=null)&&(numePersoana.length()>0)&&(value_string!=null)) {
                    Logic.getInstance().addMadeLoans(value_string, date_made, date_limit, dobanda_type_int, numePersoana,
                            tabelModelLoanMade,depozitName);
                }
                instance.iniIncomeTab();
                instance.iniExpensesTab();

                addNewLoan.dispose();
            }
        });

        cancelLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLoan.dispose();
            }
        });
    }
}
