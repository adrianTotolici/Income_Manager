package eu.IncomeManager.GUI;

import eu.IncomeManager.GUI.customButtons.custom.component.button.ButtonType;
import eu.IncomeManager.GUI.customButtons.custom.component.button.GlossyButton;
import eu.IncomeManager.GUI.customButtons.util.Theme;
import eu.IncomeManager.GUI.customPanel.CurvedGradientPanel;
import eu.IncomeManager.Logic.Logic;
import eu.IncomeManager.Utils.Colors;
import eu.IncomeManager.Utils.IconChoiser;
import eu.IncomeManager.Utils.Language.DefaultLang;
import eu.IncomeManager.dataBase.Loans;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by adrian on 5/19/14.
 */
public class PayBackLoanPanel {

    private IconChoiser icon=new IconChoiser();
    private DefaultLang lang;
    private JFrame mainFrame;
    private DepozitPanel instance;

    private JLabel indexNrLabel=new JLabel();
    private JLabel loanValueLabel=new JLabel();
    private JLabel loanInfo=new JLabel();

    private Loans loan;
    final private JTextField loanValue=new JTextField();

    private GlossyButton payButton;
    private GlossyButton payPartialButton;
    private GlossyButton cancelButton;


    public PayBackLoanPanel( DefaultLang lang, JFrame mainFrame, Loans loan, DepozitPanel instance) {
        this.lang = lang;
        this.mainFrame = mainFrame;
        this.instance=instance;
        this.loan=loan;

        payButton=new GlossyButton(lang.pay_total, Theme.GLOSSY_GREEN_THEME, ButtonType.BUTTON_ROUNDED,icon.payAll());
        payPartialButton=new GlossyButton(lang.pay_partial, Theme.GLOSSY_YELLOW_THEME,ButtonType.BUTTON_ROUNDED,icon.payPartial());
        cancelButton=new GlossyButton(lang.cancel_loan, Theme.GLOSSY_RED_THEME,ButtonType.BUTTON_ROUNDED,icon.remove());

        loanInfo.setText(loan.getPerson_name()+" --- "+lang.loan+": "+loan.getValue());
        showPanel();
    }

    private void showPanel(){
        indexNrLabel.setText(lang.NR);
        loanValueLabel.setText(lang.value);
        loanValue.setColumns(15);

        final JFrame payLoanFrame=new JFrame(lang.pay_loan);

        MigLayout layout=new MigLayout("","","");
        CurvedGradientPanel panel=new CurvedGradientPanel(Colors.green1,Colors.white,layout);

        panel.add(loanInfo,"wrap");
        panel.add(loanValueLabel);
        panel.add(loanValue,"wrap");
        panel.add(payButton,"span,split 3,center");
        panel.add(payPartialButton);
        panel.add(cancelButton);


        payLoanFrame.add(panel);
        payLoanFrame.setUndecorated(true);
        payLoanFrame.pack();
        payLoanFrame.setVisible(true);
        payLoanFrame.setLocationRelativeTo(mainFrame);

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.getInstance().payUpLoan(loan);
                instance.iniIncomeTab();
                instance.iniExpensesTab();
                instance.iniLoansTable();
                payLoanFrame.dispose();
            }
        });

        payPartialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value_string=loanValue.getText();
                Logic.getInstance().payUpPartialLoan(loan,value_string);
                instance.iniIncomeTab();
                instance.iniExpensesTab();
                instance.iniLoansTable();
                payLoanFrame.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payLoanFrame.dispose();
            }
        });
    }
}
