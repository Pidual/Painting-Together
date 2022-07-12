package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

public class DrawingTools extends JPanel {

    private CustomJColorChooser colorChooser;
    private Shapes shapes;

    public DrawingTools(ActionListener listener, FocusListener focusListener) {
        this.setBackground(Color.BLACK);
        initComponents(listener,focusListener);
    }

    private void initComponents(ActionListener listener,FocusListener focusListener) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty=1.0;
        gbc.gridy = 0;
        colorChooser = new CustomJColorChooser();
        System.out.println(colorChooser.isFocusable());
        colorChooser.getSwatchPanel().addFocusListener(focusListener);
        this.add(colorChooser,gbc);

        gbc.gridy=1;
        shapes = new Shapes(listener);
        this.add(shapes,gbc);
    }

    public CustomJColorChooser getColorChooser() {
        return colorChooser;
    }

}
