package eu.IncomeManager.GUI.customTextField;

import eu.IncomeManager.Utils.Colors;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by adrian on 24.05.2014.
 */
public class BorderTextField extends JTextField {

    public  JTextField jTextField=new JTextField();

    public BorderTextField() {
        super();
        iniTextField();
    }

    private void iniTextField(){
        jTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                jTextField.setBorder(BorderFactory.createEtchedBorder(Colors.blue1,Colors.white));
            }

            @Override
            public void focusLost(FocusEvent e) {
                jTextField.setBorder(BorderFactory.createEtchedBorder(Colors.white,Colors.white));
            }
        });
    }
}
