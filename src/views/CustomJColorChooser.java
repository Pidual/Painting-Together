package views;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;


public class CustomJColorChooser extends JColorChooser {

    private CustomSwatchPanel swatchPanel;

    public CustomJColorChooser(MouseListener mouseListener) {
        AbstractColorChooserPanel[] panels = this.getChooserPanels();
        this.removeChooserPanel(panels[0]);
        this.removeChooserPanel(panels[1]);
        this.removeChooserPanel(panels[2]);
        this.removeChooserPanel(panels[3]);
        this.removeChooserPanel(panels[4]);
        swatchPanel = new CustomSwatchPanel(mouseListener);
        this.addChooserPanel(swatchPanel);
        this.setPreviewPanel(new JPanel());
    }

    public CustomSwatchPanel getSwatchPanel() {
        return swatchPanel;
    }
}
