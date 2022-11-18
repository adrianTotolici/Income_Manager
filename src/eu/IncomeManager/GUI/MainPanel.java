package eu.IncomeManager.GUI;

import eu.IncomeManager.GUI.customButtons.custom.component.button.ButtonType;
import eu.IncomeManager.GUI.customButtons.custom.component.button.GlossyButton;
import eu.IncomeManager.GUI.customButtons.util.Theme;
import eu.IncomeManager.GUI.customTabPane.PlasticTabbedPaneUI;
import eu.IncomeManager.Logic.DataBaseLogic;
import eu.IncomeManager.Logic.Logic;
import eu.IncomeManager.Utils.Constante;
import eu.IncomeManager.Utils.Enumerari;
import eu.IncomeManager.Utils.IconChoiser;
import eu.IncomeManager.Utils.Language.DefaultLang;
import eu.IncomeManager.Utils.Language.RomanianLang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class MainPanel {

    private static MainPanel instance;
    private JFrame mainFrame;

    private DefaultLang lang;
    private IconChoiser icon=new IconChoiser();

    private JTabbedPane depozitTabPanel=new JTabbedPane();

    public MainPanel() {
        if (Constante.language.equals(Enumerari.Language.Romanian.getLang())) {
            lang = new RomanianLang();
        }else {
            lang=new DefaultLang();
        }
    }
    public static MainPanel getInstance(){
        if (instance==null){
            instance=new MainPanel();
        }
        return instance;
    }

    public static void setInstance(MainPanel instance) {
        MainPanel.instance = instance;
    }

    public void showMainTabPanel(){

        mainFrame=new JFrame(lang.income_organizer);
        mainFrame.setIconImage(icon.pigIcon().getImage());
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);

        addMenuBar();

        depozitTabPanel.setUI(new PlasticTabbedPaneUI());

        final Vector<String> allDepozitName = Logic.getInstance().getAllDepozitName();
        for (String depozitName : allDepozitName) {
            depozitTabPanel.add(depozitName,new DepozitPanel(lang,depozitName,mainFrame).showMainPanel());
        }

        mainFrame.add(depozitTabPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void refreshMainPanel(){
        depozitTabPanel.removeAll();
        final Vector<String> allDepozitName = Logic.getInstance().getAllDepozitName();
        for (String depozitName : allDepozitName) {
            depozitTabPanel.add(depozitName,new DepozitPanel(lang,depozitName,mainFrame).showMainPanel());
        }
    }

    private void addMenuBar(){
        List<JButton> buttonList=new ArrayList<JButton>();

        GlossyButton logOut= new GlossyButton(null, Theme.GLOSSY_BLACK_THEME,ButtonType.BUTTON_ROUNDED_RECTANGLUR, icon.logOut());
        logOut.setToolTipText(lang.iesire);

        GlossyButton settings= new GlossyButton(null, Theme.GLOSSY_BLACK_THEME,ButtonType.BUTTON_ROUNDED_RECTANGLUR, icon.settings());
        settings.setToolTipText(lang.setari);

        GlossyButton safeDataBase= new GlossyButton(null, Theme.GLOSSY_BLACK_THEME,ButtonType.BUTTON_ROUNDED_RECTANGLUR, icon.downloadDataBase());
        safeDataBase.setToolTipText(lang.save_data_base);

        GlossyButton uploadDataBase=new GlossyButton(null, Theme.GLOSSY_BLACK_THEME, ButtonType.BUTTON_ROUNDED_RECTANGLUR, icon.uploadDataBase());
        uploadDataBase.setToolTipText(lang.load_data_base);

        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseLogic.getInstance().exportDataBaseToFile();
                System.exit(0);
            }
        });
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingsPanel(lang,mainFrame,instance);
            }
        });
        safeDataBase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseLogic.getInstance().exportDataBaseToFile();
            }
        });
        uploadDataBase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseLogic.getInstance().importDataBaseFromFile();
                refreshMainPanel();
            }
        });

        buttonList.add(uploadDataBase);
        buttonList.add(safeDataBase);
        buttonList.add(settings);
        buttonList.add(logOut);

        ToolBar menuBarTool=new ToolBar();
        menuBarTool.addMenuItem(lang.setari, buttonList);

        JMenuBar menuBar = menuBarTool.getMenuBar();
        JToolBar toolBar = menuBarTool.getToolBar();

        mainFrame.add(menuBar);
        mainFrame.add(toolBar, BorderLayout.NORTH);
        mainFrame.setSize(mainFrame.getWidth(), 200);
    }
 }
