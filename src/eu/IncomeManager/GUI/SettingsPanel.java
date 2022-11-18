package eu.IncomeManager.GUI;

import eu.IncomeManager.GUI.customButtons.custom.component.button.ButtonType;
import eu.IncomeManager.GUI.customButtons.custom.component.button.GlossyButton;
import eu.IncomeManager.GUI.customButtons.util.Theme;
import eu.IncomeManager.GUI.customPanel.CurvedGradientPanel;
import eu.IncomeManager.Utils.*;
import eu.IncomeManager.Utils.Language.DefaultLang;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by adrian on 14.11.2014.
 */
public class SettingsPanel {
    private IconChoiser icon=new IconChoiser();
    private DefaultLang lang;
    private JFrame mainFrame;
    private MainPanel mainPanel;

    private JLabel localIp =new JLabel();
    private JLabel mobileIp =new JLabel();
    private JLabel languageLabel =new JLabel();

    final private JTextField mobileValue =new JTextField();
    final private JTextField localIpValue= new JTextField();

    private GlossyButton saveSettings;
    private GlossyButton cancelButton;

    private JComboBox languageCombo=new JComboBox();

    public SettingsPanel( DefaultLang lang, JFrame mainFrame, MainPanel mainPanel) {
        this.lang = lang;
        this.mainFrame = mainFrame;
        this.mainPanel=mainPanel;

        saveSettings =new GlossyButton(lang.save, Theme.GLOSSY_GREEN_THEME, ButtonType.BUTTON_ROUNDED,icon.save());
        cancelButton=new GlossyButton(lang.cancel, Theme.GLOSSY_RED_THEME,ButtonType.BUTTON_ROUNDED,icon.remove());

        showPanel();
    }

    private void showPanel(){
        String ipLabel = Utils.getLocalIp();
        if (ipLabel==null){
            ipLabel=lang.noIpDetected;
        }

        localIp.setText(lang.localIp);
        mobileIp.setText(lang.mobileIp);
        languageLabel.setText(lang.language);
        mobileValue.setColumns(15);
        localIpValue.setColumns(15);

        localIpValue.setText(ipLabel);
        localIpValue.setEditable(false);

        mobileValue.setText(Constante.mobileIP);

        iniCombox();

        final JFrame settingFrame=new JFrame(lang.setari);
        settingFrame.setIconImage(icon.settings().getImage());

        MigLayout layout=new MigLayout("","","");
        CurvedGradientPanel panel=new CurvedGradientPanel(Colors.green1,Colors.white,layout);

        panel.add(localIp);
        panel.add(localIpValue,"wrap");
        panel.add(mobileIp);
        panel.add(mobileValue,"wrap");
        panel.add(languageLabel);
        panel.add(languageCombo,"span,wrap");
        panel.add(saveSettings,"span,split 3,center");
        panel.add(cancelButton);

        settingFrame.add(panel);
        settingFrame.setUndecorated(true);
        settingFrame.pack();
        settingFrame.setVisible(true);
        settingFrame.setLocationRelativeTo(mainFrame);

        saveSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ipMobile = mobileValue.getText();
                String text = languageCombo.getSelectedItem().toString();
                Constante.setLanguage(text);
                Constante.setMobileIP(ipMobile);

                Utils.saveSettings();
                settingFrame.dispose();
                mainFrame.dispose();

                MainPanel.setInstance(null);
                MainPanel.getInstance().showMainTabPanel();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingFrame.dispose();
            }
        });
    }

    private void iniCombox(){
        languageCombo.addItem(Enumerari.Language.Englaza.getLang());
        languageCombo.addItem(Enumerari.Language.Romanian.getLang());

        languageCombo.setSelectedItem(Constante.language);
    }
}
