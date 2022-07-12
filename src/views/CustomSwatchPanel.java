package views;

import javax.swing.colorchooser.AbstractColorChooserPanel;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import javax.accessibility.*;
import javax.swing.colorchooser.ColorSelectionModel;

public class CustomSwatchPanel extends AbstractColorChooserPanel {

    SwatchPanel swatchPanel;
    MouseListener mainSwatchListener;
    private KeyListener mainSwatchKeyListener;

    public CustomSwatchPanel(){
        super();
        setInheritsPopupMenu(true);
    }

    public String getDisplayName() {
        return UIManager.getString("ColorChooser.swatchesNameText", getLocale());
    }

    void setSelectedColor(Color color) {
        ColorSelectionModel model = getColorSelectionModel();
        if (model != null) {
            model.setSelectedColor(color);
        }
    }

    public Icon getSmallDisplayIcon() {
        return null;
    }

    public Icon getLargeDisplayIcon() {
        return null;
    }

    public void installChooserPanel(JColorChooser enclosingChooser) {
        super.installChooserPanel(enclosingChooser);
    }

    protected void buildChooser() {
        String recentStr = UIManager.getString("ColorChooser.swatchesRecentText", getLocale());
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel superHolder = new JPanel(gb);

        swatchPanel =  new MainSwatchPanel();
        swatchPanel.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY,
                getDisplayName());
        swatchPanel.setInheritsPopupMenu(true);

        mainSwatchKeyListener = new MainSwatchKeyListener();
        mainSwatchListener = new MainSwatchListener();
        swatchPanel.addMouseListener(mainSwatchListener);
        swatchPanel.addKeyListener(mainSwatchKeyListener);
        JPanel mainHolder = new JPanel(new BorderLayout());
        Border border = new CompoundBorder( new LineBorder(Color.black),
                new LineBorder(Color.white) );
        mainHolder.setBorder(border);
        mainHolder.add(swatchPanel, BorderLayout.CENTER);

        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        Insets oldInsets = gbc.insets;
        gbc.insets = new Insets(0, 0, 0, 10);
        superHolder.add(mainHolder, gbc);
        gbc.insets = oldInsets;

        gbc.weighty = 0;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 0, 2);

        superHolder.setInheritsPopupMenu(true);
        add(superHolder);
    }

    public void uninstallChooserPanel(JColorChooser enclosingChooser) {
        super.uninstallChooserPanel(enclosingChooser);
        swatchPanel.removeMouseListener(mainSwatchListener);
        swatchPanel.removeKeyListener(mainSwatchKeyListener);
        swatchPanel = null;
        mainSwatchListener = null;
        mainSwatchKeyListener = null;
        removeAll();
    }

    public void updateChooser() {

    }


    private class MainSwatchKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (KeyEvent.VK_SPACE == e.getKeyCode()) {
                Color color = swatchPanel.getSelectedColor();
                setSelectedColor(color);
            }
        }
    }


    class MainSwatchListener extends MouseAdapter implements Serializable {
        public void mousePressed(MouseEvent e) {
            if (isEnabled()) {
                Color color = swatchPanel.getColorForLocation(e.getX(), e.getY());
                setSelectedColor(color);
                swatchPanel.setSelectedColorFromLocation(e.getX(), e.getY());
                swatchPanel.requestFocusInWindow();
            }
        }
    }
}


class SwatchPanel extends JPanel {

    protected Color[] colors;
    protected Dimension swatchSize;
    protected Dimension numSwatches;
    protected Dimension gap;

    private int selRow;
    private int selCol;

    public SwatchPanel() {
        initValues();
        initColors();
        setToolTipText(""); // register for events
        setOpaque(true);
        setBackground(Color.WHITE);
        setFocusable(true);
        setInheritsPopupMenu(true);
        addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                repaint();
            }

            public void focusLost(FocusEvent e) {
                repaint();
            }
        });

    }

    public Color getSelectedColor() {
        return getColorForCell(selCol, selRow);
    }

    protected void initValues() {

    }

    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth(), getHeight());
        for (int row = 0; row < numSwatches.height; row++) {
            int y = row * (swatchSize.height + gap.height);
            for (int column = 0; column < numSwatches.width; column++) {
                Color c = getColorForCell(column, row);
                g.setColor(c);
                int x;
                if (!this.getComponentOrientation().isLeftToRight()) {
                    x = (numSwatches.width - column - 1) * (swatchSize.width + gap.width);
                } else {
                    x = column * (swatchSize.width + gap.width);
                }
                g.fillRect( x, y, swatchSize.width, swatchSize.height);
                g.setColor(Color.black);
                g.drawLine( x+swatchSize.width-1, y, x+swatchSize.width-1, y+swatchSize.height-1);
                g.drawLine( x, y+swatchSize.height-1, x+swatchSize.width-1, y+swatchSize.height-1);

                if (selRow == row && selCol == column && this.isFocusOwner()) {
                    Color c2 = new Color(c.getRed() < 125 ? 255 : 0,
                            c.getGreen() < 125 ? 255 : 0,
                            c.getBlue() < 125 ? 255 : 0);
                    g.setColor(c2);

                    g.drawLine(x, y, x + swatchSize.width - 1, y);
                    g.drawLine(x, y, x, y + swatchSize.height - 1);
                    g.drawLine(x + swatchSize.width - 1, y, x + swatchSize.width - 1, y + swatchSize.height - 1);
                    g.drawLine(x, y + swatchSize.height - 1, x + swatchSize.width - 1, y + swatchSize.height - 1);
                    g.drawLine(x, y, x + swatchSize.width - 1, y + swatchSize.height - 1);
                    g.drawLine(x, y + swatchSize.height - 1, x + swatchSize.width - 1, y);
                }
            }
        }
    }

    public Dimension getPreferredSize() {
        int x = numSwatches.width * (swatchSize.width + gap.width) - 1;
        int y = numSwatches.height * (swatchSize.height + gap.height) - 1;
        return new Dimension( x, y );
    }

    protected void initColors() {


    }

    public String getToolTipText(MouseEvent e) {
        Color color = getColorForLocation(e.getX(), e.getY());
        return color.getRed()+", "+ color.getGreen() + ", " + color.getBlue();
    }

    public void setSelectedColorFromLocation(int x, int y) {
        if (!this.getComponentOrientation().isLeftToRight()) {
            selCol = numSwatches.width - x / (swatchSize.width + gap.width) - 1;
        } else {
            selCol = x / (swatchSize.width + gap.width);
        }
        selRow = y / (swatchSize.height + gap.height);
        repaint();
    }

    public Color getColorForLocation( int x, int y ) {
        int column;
        if (!this.getComponentOrientation().isLeftToRight()) {
            column = numSwatches.width - x / (swatchSize.width + gap.width) - 1;
        } else {
            column = x / (swatchSize.width + gap.width);
        }
        int row = y / (swatchSize.height + gap.height);
        return getColorForCell(column, row);
    }

    private Color getColorForCell( int column, int row) {
        return colors[ (row * numSwatches.width) + column ]; // (STEVE) - change data orientation here
    }

}

class MainSwatchPanel extends SwatchPanel {

    protected void initValues() {
        swatchSize = UIManager.getDimension("ColorChooser.swatchesSwatchSize", getLocale());
        numSwatches = new Dimension( 9, 31 );
        gap = new Dimension(1, 1);
    }

    protected void initColors() {
        int[] rawValues = initRawValues();
        int numColors = rawValues.length / 3;

        colors = new Color[numColors];
        for (int i = 0; i < numColors ; i++) {
            colors[i] = new Color( rawValues[(i*3)], rawValues[(i*3)+1], rawValues[(i*3)+2] );
        }
    }

    private int[] initRawValues() {
        int[] rawValues = {
                255, 255, 255, // first row.
                204, 255, 255,
                204, 204, 255,
                204, 204, 255,
                204, 204, 255,
                204, 204, 255,
                204, 204, 255,
                204, 204, 255,
                204, 204, 255,
                204, 204, 255,
                204, 204, 255,
                255, 204, 255,
                255, 204, 204,
                255, 204, 204,
                255, 204, 204,
                255, 204, 204,
                255, 204, 204,
                255, 204, 204,
                255, 204, 204,
                255, 204, 204,
                255, 204, 204,
                255, 255, 204,
                204, 255, 204,
                204, 255, 204,
                204, 255, 204,
                204, 255, 204,
                204, 255, 204,
                204, 255, 204,
                204, 255, 204,
                204, 255, 204,
                204, 255, 204,
                204, 204, 204,  // second row.
                153, 255, 255,
                153, 204, 255,
                153, 153, 255,
                153, 153, 255,
                153, 153, 255,
                153, 153, 255,
                153, 153, 255,
                153, 153, 255,
                153, 153, 255,
                204, 153, 255,
                255, 153, 255,
                255, 153, 204,
                255, 153, 153,
                255, 153, 153,
                255, 153, 153,
                255, 153, 153,
                255, 153, 153,
                255, 153, 153,
                255, 153, 153,
                255, 204, 153,
                255, 255, 153,
                204, 255, 153,
                153, 255, 153,
                153, 255, 153,
                153, 255, 153,
                153, 255, 153,
                153, 255, 153,
                153, 255, 153,
                153, 255, 153,
                153, 255, 204,
                204, 204, 204,  // third row
                102, 255, 255,
                102, 204, 255,
                102, 153, 255,
                102, 102, 255,
                102, 102, 255,
                102, 102, 255,
                102, 102, 255,
                102, 102, 255,
                153, 102, 255,
                204, 102, 255,
                255, 102, 255,
                255, 102, 204,
                255, 102, 153,
                255, 102, 102,
                255, 102, 102,
                255, 102, 102,
                255, 102, 102,
                255, 102, 102,
                255, 153, 102,
                255, 204, 102,
                255, 255, 102,
                204, 255, 102,
                153, 255, 102,
                102, 255, 102,
                102, 255, 102,
                102, 255, 102,
                102, 255, 102,
                102, 255, 102,
                102, 255, 153,
                102, 255, 204,
                153, 153, 153, // fourth row
                51, 255, 255,
                51, 204, 255,
                51, 153, 255,
                51, 102, 255,
                51, 51, 255,
                51, 51, 255,
                51, 51, 255,
                102, 51, 255,
                153, 51, 255,
                204, 51, 255,
                255, 51, 255,
                255, 51, 204,
                255, 51, 153,
                255, 51, 102,
                255, 51, 51,
                255, 51, 51,
                255, 51, 51,
                255, 102, 51,
                255, 153, 51,
                255, 204, 51,
                255, 255, 51,
                204, 255, 51,
                153, 255, 51,
                102, 255, 51,
                51, 255, 51,
                51, 255, 51,
                51, 255, 51,
                51, 255, 102,
                51, 255, 153,
                51, 255, 204,
                153, 153, 153, // Fifth row
                0, 255, 255,
                0, 204, 255,
                0, 153, 255,
                0, 102, 255,
                0, 51, 255,
                0, 0, 255,
                51, 0, 255,
                102, 0, 255,
                153, 0, 255,
                204, 0, 255,
                255, 0, 255,
                255, 0, 204,
                255, 0, 153,
                255, 0, 102,
                255, 0, 51,
                255, 0 , 0,
                255, 51, 0,
                255, 102, 0,
                255, 153, 0,
                255, 204, 0,
                255, 255, 0,
                204, 255, 0,
                153, 255, 0,
                102, 255, 0,
                51, 255, 0,
                0, 255, 0,
                0, 255, 51,
                0, 255, 102,
                0, 255, 153,
                0, 255, 204,
                102, 102, 102, // sixth row
                0, 204, 204,
                0, 204, 204,
                0, 153, 204,
                0, 102, 204,
                0, 51, 204,
                0, 0, 204,
                51, 0, 204,
                102, 0, 204,
                153, 0, 204,
                204, 0, 204,
                204, 0, 204,
                204, 0, 204,
                204, 0, 153,
                204, 0, 102,
                204, 0, 51,
                204, 0, 0,
                204, 51, 0,
                204, 102, 0,
                204, 153, 0,
                204, 204, 0,
                204, 204, 0,
                204, 204, 0,
                153, 204, 0,
                102, 204, 0,
                51, 204, 0,
                0, 204, 0,
                0, 204, 51,
                0, 204, 102,
                0, 204, 153,
                0, 204, 204,
                102, 102, 102, // seventh row
                0, 153, 153,
                0, 153, 153,
                0, 153, 153,
                0, 102, 153,
                0, 51, 153,
                0, 0, 153,
                51, 0, 153,
                102, 0, 153,
                153, 0, 153,
                153, 0, 153,
                153, 0, 153,
                153, 0, 153,
                153, 0, 153,
                153, 0, 102,
                153, 0, 51,
                153, 0, 0,
                153, 51, 0,
                153, 102, 0,
                153, 153, 0,
                153, 153, 0,
                153, 153, 0,
                153, 153, 0,
                153, 153, 0,
                102, 153, 0,
                51, 153, 0,
                0, 153, 0,
                0, 153, 51,
                0, 153, 102,
                0, 153, 153,
                0, 153, 153,
                51, 51, 51, // eigth row
                0, 102, 102,
                0, 102, 102,
                0, 102, 102,
                0, 102, 102,
                0, 51, 102,
                0, 0, 102,
                51, 0, 102,
                102, 0, 102,
                102, 0, 102,
                102, 0, 102,
                102, 0, 102,
                102, 0, 102,
                102, 0, 102,
                102, 0, 102,
                102, 0, 51,
                102, 0, 0,
                102, 51, 0,
                102, 102, 0,
                102, 102, 0,
                102, 102, 0,
                102, 102, 0,
                102, 102, 0,
                102, 102, 0,
                102, 102, 0,
                51, 102, 0,
                0, 102, 0,
                0, 102, 51,
                0, 102, 102,
                0, 102, 102,
                0, 102, 102,
                0, 0, 0, // ninth row
                0, 51, 51,
                0, 51, 51,
                0, 51, 51,
                0, 51, 51,
                0, 51, 51,
                0, 0, 51,
                51, 0, 51,
                51, 0, 51,
                51, 0, 51,
                51, 0, 51,
                51, 0, 51,
                51, 0, 51,
                51, 0, 51,
                51, 0, 51,
                51, 0, 51,
                51, 0, 0,
                51, 51, 0,
                51, 51, 0,
                51, 51, 0,
                51, 51, 0,
                51, 51, 0,
                51, 51, 0,
                51, 51, 0,
                51, 51, 0,
                0, 51, 0,
                0, 51, 51,
                0, 51, 51,
                0, 51, 51,
                0, 51, 51,
                51, 51, 51 };
        return rawValues;
    }
}

