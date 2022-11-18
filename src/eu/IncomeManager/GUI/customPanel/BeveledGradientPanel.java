package eu.IncomeManager.GUI.customPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * BeveledGradientPanel.java
 * <p/>
 * An example  for  custom BeveledGradientPanel JPanel.
 * @author JDhilsukh
 *
 */

public class BeveledGradientPanel extends JPanel {
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
    private int outerRoundRectSize = 10;
    private int innerRoundRectSize = 8;
    /**
     * The start color of the gradient
     */
    private Color startColor = new Color(114, 166, 252);

    /**
     * The end color of the gradient
     */
    private Color endColor = new Color(255, 255, 255);

    /**
     * The direction of the gradient
     */
    private int direction = VERTICAL;

    // ------------------------------------------------------------------------------------------------------------------
    //  Constructors and getter/setter methods
    // ------------------------------------------------------------------------------------------------------------------

    /**
     * Create a default SimpleGradientPanel instance.
     */
    public BeveledGradientPanel() {
        super();
    }

    /**
     * Create a SimpleGradientPanel with the given start and end colors.
     *
     * @param aStart The start color for the gradient.
     * @param aEnd   The end color for the gradient.
     */
    public BeveledGradientPanel(Color aStart, Color aEnd) {
        super();
        startColor = aStart;
        endColor = aEnd;
    }

    /**
     * Create a SimpleGradientPanel with the given start and end colors.
     *
     * @param aStart 		The start color for the gradient.
     * @param aEnd   		The end color for the gradient.
     * @param aDirection The direction of the gradient.
     */
    public BeveledGradientPanel(Color aStart, Color aEnd, int aDirection) {
        super();
        startColor = aStart;
        endColor = aEnd;
        direction = aDirection;
    }

    /**
     * Get the ending color of the gradient.
     */
    public Color getEndColor() {
        return endColor;
    }

    /**
     * Set the ending color to use.
     *
     * @param aColor The color to use.
     */
    public void setEndColor(Color aColor) {
        Color oldEndColor = endColor;
        endColor = aColor;
        super.firePropertyChange("endColor", oldEndColor, endColor);
        repaint();
    }

    /**
     * Get the start color of the gradient
     */
    public Color getStartColor() {
        return startColor;
    }

    /**
     * Set the starting color.
     *
     * @param aColor The color to use
     */
    public void setStartColor(Color aColor) {
        Color oldStartColor = endColor;
        startColor = aColor;
        super.firePropertyChange("startColor", oldStartColor, startColor);
        repaint();
    }

    /**
     * Get the direction (vertical or horizontal) of the gradient.
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Set the direction
     *
     * @param aDirection The direction of the gradient
     */
    public void setDirection(int aDirection) {
        int oldDirection = direction;
        direction = aDirection;
        super.firePropertyChange("direction", oldDirection, aDirection);
        repaint();
    }

    // ------------------------------------------------------------------------------------------------------------------
    //  Custom painting methods
    // ------------------------------------------------------------------------------------------------------------------

    /**
     * Override the default paintComponent method to paint the gradient in the panel.
     *
     * @param g
     */
    public void paintComponent(Graphics g) {
        Dimension dim = getSize();
        Graphics2D g2 = (Graphics2D) g;
        Insets inset = getInsets();
        int vWidth = dim.width - (inset.left + inset.right);
        int vHeight = dim.height - (inset.top + inset.bottom);

        if (direction == HORIZONTAL) {
            paintHorizontalGradient(g2, inset.left, inset.top, vWidth, vHeight,
                    dim.width);
        } else {
            paintVerticalGradient(g2, inset.left, inset.top, vWidth, vHeight,
                    dim.height);
        }

    }

    /**
     * Paints a vertical gradient background from the start color to the end color.
     */
    private void paintVerticalGradient(Graphics2D g2d, int x, int y, int w,
                                       int h, int height) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint p1;
        GradientPaint p2;
        GradientPaint GP = new GradientPaint(0, 0, startColor, 0, h,
                startColor, true);
        g2d.setPaint(GP);

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

        Color bottomGradientStart = new Color(0, 0, 0, 5);
        Color bottomGradientEnd = new Color(0, 0, 0, 60);
        g2d.setPaint(new GradientPaint(0.0F, h - 6, bottomGradientStart, 0.0F,
                h, bottomGradientEnd));
        g2d.fillRoundRect(0, h - 6, w - 2, 6, 10, 10);

        p1 = new GradientPaint(0, 0, Color.WHITE, 0, 10, new Color(114, 166,
                252));
        g2d.setPaint(p1);
        g2d.fillRoundRect(2, 1, w - 4, 10, 6, 6);

        g2d.setPaint(new GradientPaint(0.0F, h - 15, new Color(114, 166, 252),
                0.0F, h, Color.WHITE));
        g2d.fillRoundRect(2, h - 15, w - 4, 13, 8, 8);
    }

    /**
     * Paints a horizontal gradient background from the start color to the end color.
     */
    private void paintHorizontalGradient(Graphics2D g2, int x, int y, int w,
                                         int h, int width) {
        g2.setPaint(new GradientPaint(x, 0, startColor, width, 0, endColor));
        g2.fillRect(x, y, w, h);
    }

}
