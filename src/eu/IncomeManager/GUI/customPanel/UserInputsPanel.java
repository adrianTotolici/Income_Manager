package eu.IncomeManager.GUI.customPanel;

import java.awt.*;
import java.awt.geom.GeneralPath;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * UserInputsPanel.java
 * <p/>
 * An example of for  custom  JPanel.
 * @author JDhilsukh
 *
 */

public class UserInputsPanel extends JPanel {

    private Color startColor = new Color(238, 238, 238);
    private Color endColor = new Color(255, 255, 255);
    GeneralPath path;
    Color accentColor = new Color(0x80ffffff);
    Color textColor = new Color(0x0f0f0f);
    private String title;
    private ImageIcon icon;

    public UserInputsPanel() {
        super();

    }

    public UserInputsPanel(Color color1, Color color2) {
        super();
        startColor = color1;
        endColor = color2;

    }

    public UserInputsPanel(LayoutManager layoutManager) {
        super(layoutManager);
    }

    public UserInputsPanel(Color color1, Color color2, LayoutManager layoutManager) {
        super(layoutManager);
        startColor = color1;
        endColor = color2;

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
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        /**
         * Top Polygon
         */
        GeneralPath path = new GeneralPath();
        path.moveTo(70, 0);
        path.lineTo(8, 0);
        path.quadTo(0, 0, 0, 7);
        path.lineTo(0, 55);
        path.lineTo(getWidth() - 1, 55);
        path.lineTo(getWidth() - 1, 7);
        path.quadTo(getWidth() - 1, 0, getWidth() - 8, 0);
        path.lineTo(30, 0);

        Rectangle bounds1 = path.getBounds();
        GradientPaint painter = new GradientPaint(0, path.getBounds().y,
                true ? endColor : startColor, 0,
                bounds1.y + bounds1.height - 1, true ? startColor : endColor);
        g2d.setPaint(painter);
        g2d.fill(path);
        Rectangle rectangle = new Rectangle(0, 40, getWidth(), 20);
        g2d.fill(rectangle);
        g2d.setColor(new Color(128, 128, 128));
        g2d.draw(path);

        /**
         * Middle Rectangle
         */
        g2d.setPaint(new Color(240, 240, 240));
        g2d.fillRect(0, 31, getWidth() - 1, h - 50);
        g2d.setColor(new Color(128, 128, 128));
        g2d.drawLine(12, 0, getWidth() - 10, 0);
        g2d.drawRect(0, 30, getWidth() - 1, h - 50);

        /**
         * Bottom Polygon
         */
        h = h - 30;
        path = new GeneralPath();
        path.moveTo(0, h);
        path.lineTo(0, h + 22);
        path.quadTo(0, h + 29, 8, h + 29);
        path.lineTo(getWidth() - 8, h + 29);
        path.quadTo(getWidth() - 1, h + 29, getWidth() - 1, h + 22);
        path.lineTo(getWidth() - 1, h);
        g2d.setColor(Color.GRAY);
        startColor = new Color(192, 192, 192);
        endColor = new Color(238, 238, 238);
        bounds1 = path.getBounds();
        painter = new GradientPaint(0, path.getBounds().y, endColor, 0,
                bounds1.y + bounds1.height - 1, startColor);
        g2d.setPaint(painter);
        g2d.fill(path);
        g2d.setColor(new Color(128, 128, 128));
        g2d.draw(path);
        g2d.setColor(new Color(128, 128, 128));
        g2d.drawLine(0, h - 1, getWidth() - 1, h - 1);

        /**
         *  Title
         */
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(
                (float) g2d.getFont().getSize() + 1));

        g2d.setColor(accentColor);
        g2d.drawString(title, 40, 22);
        g2d.setColor(textColor);
        g2d.drawString(title, 40, 21);

        /**
         *  image
         */
        g2d.drawImage(icon.getImage(), 10, 0,null, null);
    }

    /**
     *  This method sets the Actual Background Color of the Button
     */
    public void setStartColor(Color color) {
        startColor = color;
    }

    /**
     *  This method sets the Pressed Color of the Button
     */
    public void setEndColor(Color pressedColor) {
        endColor = pressedColor;
    }

    /**
     * @return  Starting Color of the Button
     */
    public Color getStartColor() {
        return startColor;
    }

    /**
     * @return  Ending Color of the Button
     */
    public Color getEndColor() {
        return endColor;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}
