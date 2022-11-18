package eu.IncomeManager.GUI.customPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/**
 *
 * RoundRectGradientPanel.java
 * <p/>
 * An example of for  custom RoundRectGradientPanel JPanel.
 * @author JDhilsukh
 *
 */

public class RoundRectGradientPanel extends JPanel {
    private Color COLOR1 = new Color(255, 255, 255);
    private Color COLOR2 = new Color(115, 201, 29);
    private int outerRoundRectSize = 8;
    private int innerRoundRectSize = 6;
    private Color unselectedLow; // Lowlight for unselected tab
    private Color unselectedHigh; // Highlight for unselected tab
    private Color unselectedMid;

    public RoundRectGradientPanel() {
        super();
        unselectedLow = Color.BLUE;
        unselectedMid = new Color(0x330099);
        unselectedHigh = Color.WHITE;
        setForeground(Color.WHITE);
        setFocusable(false);

    }

    public RoundRectGradientPanel(Color color1, Color color2,
                                  int outerRoundRectSize, int innerRoundRectSize) {
        super();
        COLOR1 = color1;
        COLOR2 = color2;
        unselectedLow = color1;
        unselectedMid = new Color(0x330099);
        unselectedHigh = color2;

        this.outerRoundRectSize = outerRoundRectSize;
        this.innerRoundRectSize = innerRoundRectSize;
        setForeground(Color.BLACK);
        setFocusable(false);

    }

    /**
     * Override the default paintComponent method to paint the gradient in the
     * panel.
     *
     * @param g
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int h = getHeight();
        int w = getWidth();
        GradientPaint GP = new GradientPaint(0, 0, COLOR1, 0, h, COLOR2, true);
        g2d.setPaint(GP);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint p1;
        GradientPaint p2;
        p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1, new Color(
                100, 100, 100));
        p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,
                new Color(255, 255, 255, 100));
        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1,
                h - 1, outerRoundRectSize, outerRoundRectSize);
        Shape clip = g2d.getClip();
        g2d.clip(r2d);
        g2d.fillRect(0, 0, w, h);
        g2d.setClip(clip);
        g2d.setPaint(p1);
        g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,
                outerRoundRectSize);
        g2d.setPaint(p2);
        g2d.drawRoundRect(1, 1, w - 3, h - 3, innerRoundRectSize,
                innerRoundRectSize);
        _drawRect(g2d, 0, w, unselectedHigh, unselectedMid, unselectedLow);

        super.paintComponents(g2d);
    }

    /**
     * Draw Rectangle
     * @param g2
     * @param x
     * @param w
     * @param hiCol
     * @param mdCol
     * @param loCol
     */
    private void _drawRect(Graphics2D g2, int x, int w, Color hiCol,
                           Color mdCol, Color loCol) {
        Dimension d = getSize();

        int h = (int) d.getHeight();
        GradientPaint p1;
        GradientPaint p2;
        p1 = new GradientPaint(0, 0, loCol, 0, 10, hiCol);
        p2 = new GradientPaint(0, 10, hiCol, 0, 200, loCol);
        g2.setPaint(p1);
        g2.fillRoundRect(1, 1, w - 2, 10, 6, 6);
        g2.setPaint(Color.WHITE);
        g2.fillRect(1, 10, w - 2, h - 12);
        g2.setPaint(p1);
        g2.fillRect(1, h - 12, w - 2, h - 10);
        Color bottomGradientStart = hiCol;
        Color bottomGradientEnd = loCol;
        g2.setPaint(new GradientPaint(0.0F, h - 10, bottomGradientStart, 0.0F,
                h, bottomGradientEnd));
        g2.fillRoundRect(1, h - 10, w - 2, 10, 4, 4);

    }

    /**
     *  This method sets the Actual Background Color of the Button
     */
    public void setStartColor(Color color) {
        COLOR1 = color;
    }

    /**
     *  This method sets the Pressed Color of the Button
     */
    public void setEndColor(Color pressedColor) {
        COLOR2 = pressedColor;
    }

    /**
     *  This method sets the OuterRoundRect Size of the Button
     */
    public void setOuterRoundRectSize(int outerRoundRectSize) {
        this.outerRoundRectSize = outerRoundRectSize;
    }

    /**
     *  This method sets the InnerRoundRect Size of the Button
     */
    public void setInnerRoundRectSize(int innerRoundRectSize) {
        this.innerRoundRectSize = innerRoundRectSize;
    }

    /**
     * @return  Starting Color of the Button
     */
    public Color getStartColor() {
        return COLOR1;
    }

    /**
     * @return  Ending Color of the Button
     */
    public Color getEndColor() {
        return COLOR2;
    }

    /**
     * @return the OuterRoundRect Size of the Button
     */
    public int getOuterRoundRectSize() {
        return this.outerRoundRectSize;
    }

    /**
     * @return the InnerRoundRect Size of the Button
     */
    public int getInnerRoundRectSize() {
        return this.innerRoundRectSize;
    }

}
