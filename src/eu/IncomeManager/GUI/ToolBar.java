package eu.IncomeManager.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by adrian on 14.11.2014.
 */
public class ToolBar {

    private JMenuBar menuBar=new JMenuBar();
    private JToolBar toolBar=new JToolBar();



    public void addMenuItem(String menuName, List<JButton> toolButtons) {
        JMenu menu = new JMenu(menuName);
        menuBar.add(menu);

        for (JButton toolButton : toolButtons) {
            toolBar.add(toolButton);
        }
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }
}
