package eu.IncomeManager.GUI.customPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
/**
 *
 * SimpleGradientPanel.java
 * <p/>
 * An example of for custom JPanel.
 * @author JDhilsukh
 *
 */
public class SimpleGradientPanel extends JPanel {
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL_RAISED = 2;
    public static final int HORIZONTAL_RAISED =3;
    private int outerRoundRectSize = 10;
    private int innerRoundRectSize = 8;

    /**
     * The start color of the gradient
     */
    private Color startColor = new Color(255,255,255);

    /**
     * The end color of the gradient
     */
    private Color endColor = new Color(82, 82, 82);

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
    public SimpleGradientPanel() {
        super();
    }

    /**
     * Create a SimpleGradientPanel with the given start and end colors.
     *
     * @param aStart The start color for the gradient.
     * @param aEnd   The end color for the gradient.
     */
    public SimpleGradientPanel(Color aStart, Color aEnd) {
        super();
        startColor = aStart;
        endColor = aEnd;
    }

    /**
     * Create a SimpleGradientPanel with the given start and end colors and direction.
     *
     * @param aStart 		The start color for the gradient.
     * @param aEnd   		The end color for the gradient.
     * @param aDirection The direction of the gradient.
     */
    public SimpleGradientPanel(Color aStart, Color aEnd, int aDirection) {
        super();
        startColor = aStart;
        endColor = aEnd;
        direction = aDirection;
    }
    /**
     * Create a SimpleGradientPanel with the given start and end colors and direction.
     *
     * @param aStart 		      The start color for the gradient.
     * @param aEnd   		      The end color for the gradient.
     * @param aDirection          The direction of the gradient.
     * @param aOuterRoundRectSize The OuterRectSizde size of the RoundedRectangle.
     * @param aInnerRoundRectSize The innerRectsize of the RoundedRectange.
     */
    public SimpleGradientPanel(Color aStart, Color aEnd, int aDirection,int aOuterRoundRectSize,int aInnerRoundRectSize) {
        super();
        startColor = aStart;
        endColor = aEnd;
        direction = aDirection;
        outerRoundRectSize = aOuterRoundRectSize;
        innerRoundRectSize = aInnerRoundRectSize;
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
        } else if (direction == VERTICAL) {
            paintVerticalGradient(g2, inset.left, inset.top, vWidth, vHeight,
                    dim.height);
        } else if (direction == HORIZONTAL_RAISED) {
            paintHorizontalRaisedGradient(g2, inset.left, inset.top, vWidth, vHeight,
                    dim.width);
        } else if (direction == VERTICAL_RAISED) {
            paintVerticalRaisedGradient(g2, inset.left, inset.top, vWidth, vHeight,
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
        GradientPaint GP = new GradientPaint(0, 0, startColor, 0, h, endColor, true);
        g2d.setPaint(GP);

        p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1, new Color(
                100, 100, 100));
        p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,
                new Color(255, 255, 255, 100));
        g2d.setPaint(Color.BLACK);
        g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,
                outerRoundRectSize);
        g2d.setPaint(GP);
        g2d.fillRoundRect(1, 1, w - 2, h - 2, outerRoundRectSize,
                outerRoundRectSize);

    }

    /**
     * Paints a horizontal gradient background from the start color to the end color.
     */
    private void paintHorizontalGradient(Graphics2D g2d, int x, int y, int w,
                                         int h, int width) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint p1;
        GradientPaint p2;
        GradientPaint GP = new GradientPaint(0, 0, startColor, w, 0, endColor,
                true);
        g2d.setPaint(GP);

        p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1, new Color(
                100, 100, 100));
        p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,
                new Color(255, 255, 255, 100));
        g2d.setPaint(Color.BLACK);
        g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,
                outerRoundRectSize);
        g2d.setPaint(GP);
        g2d.fillRoundRect(1, 1, w - 2, h - 2, outerRoundRectSize,
                outerRoundRectSize);


    }

    /**
     * Paints a vertical-raised gradient background from the start color to the end color.
     */
    private void paintVerticalRaisedGradient(Graphics2D g2d, int x, int y,
                                             int w, int h, int height) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint p1;
        GradientPaint p2;
        GradientPaint GP = new GradientPaint(0, 0, startColor, 0, h, endColor, true);
        g2d.setPaint(GP);

        p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1, new Color(
                100, 100, 100));
        p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,
                new Color(255, 255, 255, 100));
        g2d.setPaint(Color.BLACK);
        g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,
                outerRoundRectSize);
        g2d.setPaint(GP);
        g2d.fillRoundRect(1, 1, w - 2, h - 2, outerRoundRectSize,
                outerRoundRectSize);

        g2d.setPaint(p1);
        g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,
                outerRoundRectSize);
        g2d.setPaint(p2);
        g2d.drawRoundRect(1, 1, w - 3, h - 3, innerRoundRectSize,
                innerRoundRectSize);

    }

    /**
     * Paints a horizontal-raised gradient background from the start color to the end color.
     */
    private void paintHorizontalRaisedGradient(Graphics2D g2d, int x, int y,
                                               int w, int h, int width) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint p1;
        GradientPaint p2;
        GradientPaint GP = new GradientPaint(0, 0, startColor, w, 0, endColor,
                true);
        g2d.setPaint(GP);
        p1 = new GradientPaint(0, 0, new Color(0, 0, 0), w - 3, 0, new Color(
                100, 100, 100));
        p2 = new GradientPaint(1, 0, new Color(0, 0, 0, 50), w - 3, 0,
                new Color(255, 255, 255, 100));

        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1,
                h - 1, outerRoundRectSize, outerRoundRectSize);
        Shape clip = g2d.getClip();
        g2d.clip(r2d);
        g2d.fillRect(0, 0, w, h);
        g2d.setClip(clip);
        g2d.setPaint(p1);

        g2d.setPaint(Color.BLACK);
        g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,
                outerRoundRectSize);
        g2d.setPaint(p2);
        g2d.fillRoundRect(1, 1, w - 2, h - 2, outerRoundRectSize,
                outerRoundRectSize);

        g2d.setPaint(Color.WHITE);
        g2d.drawRoundRect(1, 1, w - 3, h - 3, outerRoundRectSize,
                outerRoundRectSize);
        g2d.setPaint(p2);
        g2d.drawRoundRect(1, 1, w - 2, h - 2, innerRoundRectSize,
                innerRoundRectSize);

    }
    /**
     * Get the OuterRoundedSize of the RoundedRectangle
     */
    public int getOuterRoundRectSize() {
        return outerRoundRectSize;
    }
    /**
     * Set the OuterRoundSize of Rectangle
     *
     * @param outerRoundRectSize
     */
    public void setOuterRoundRectSize(int outerRoundRectSize) {
        this.outerRoundRectSize = outerRoundRectSize;
    }

    /**
     * Get the innerRoundedSize of the RoundedRectangle
     */
    public int getInnerRoundRectSize() {
        return innerRoundRectSize;
    }

    /**
     * Set the InnerRoundSize of Rectangle
     *
     * @param innerRoundRectSize
     */
    public void setInnerRoundRectSize(int innerRoundRectSize) {
        this.innerRoundRectSize = innerRoundRectSize;
    }

}
