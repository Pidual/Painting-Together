package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;

public class DrawingTools extends JPanel {

    private CustomJColorChooser colorChooser;
    private Shapes shapes;

    public DrawingTools(ActionListener listener, MouseListener mouseListener) {
        this.setBackground(Color.BLACK);
        initComponents(listener,mouseListener);
    }

    private void initComponents(ActionListener listener,MouseListener mouseListener) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty=1.0;
        gbc.gridy = 0;
        colorChooser = new CustomJColorChooser(mouseListener);
        this.add(colorChooser,gbc);
        gbc.gridy=1;
        shapes = new Shapes(listener);
        this.add(shapes,gbc);
    }

    public CustomJColorChooser getColorChooser() {
        return colorChooser;
    }

}
