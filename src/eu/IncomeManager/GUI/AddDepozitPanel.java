package eu.IncomeManager.GUI;

import eu.IncomeManager.GUI.customButtons.custom.component.button.ButtonType;
import eu.IncomeManager.GUI.customButtons.custom.component.button.GlossyButton;
import eu.IncomeManager.GUI.customButtons.util.Theme;
import eu.IncomeManager.GUI.customPanel.CurvedGradientPanel;
import eu.IncomeManager.Logic.Logic;
import eu.IncomeManager.Utils.Colors;
import eu.IncomeManager.Utils.IconChoiser;
import eu.IncomeManager.Utils.Language.DefaultLang;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AddDepozitPanel {

    private DefaultLang lang;
    private IconChoiser icon=new IconChoiser();
    private DepozitPanel instance  ;

    private JLabel depozitNameLabel=new JLabel();

    private JTextField depozitName= new JTextField();

    private GlossyButton addDepozitOk;
    private GlossyButton addDepozitCancel;

    final JFrame jFrame=new JFrame();
    private JFrame mainFrame;

    public AddDepozitPanel(DefaultLang lang, JFrame mainFrame, DepozitPanel instance) {
        this.lang = lang;
        this.mainFrame=mainFrame;
        this.instance=instance;

        addDepozitOk =new GlossyButton(lang.add_new_depozit, Theme.GLOSSY_GREEN_THEME,
                ButtonType.BUTTON_ROUNDED,icon.cashback());
        addDepozitCancel =new GlossyButton(lang.cancel_depozit, Theme.GLOSSY_RED_THEME,
                ButtonType.BUTTON_ROUNDED,icon.remove());

        showDepozitAddPanel();
    }

    private void iniDepozitPanel(){
       depozitNameLabel.setText(lang.depozitName);
       depozitName.setColumns(15);
       actionAddDepozit();
    }

    private void actionAddDepozit(){
        addDepozitCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });

        addDepozitOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();

                String text = depozitName.getText();
                Logic.getInstance().addNewDepozit(text);

                MainPanel.getInstance().refreshMainPanel();
            }
        });
    }

    private void showDepozitAddPanel(){
        iniDepozitPanel();

        MigLayout layout=new MigLayout("","[grow]","[grow]");
        CurvedGradientPanel panel=new CurvedGradientPanel(Colors.green1,Colors.white,layout);

        panel.add(depozitNameLabel);
        panel.add(depozitName,"wrap 2");
        panel.add(addDepozitOk,"span,split 2,center");
        panel.add(addDepozitCancel);

        jFrame.add(panel);
        jFrame.setUndecorated(true);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(mainFrame);
    }


}
