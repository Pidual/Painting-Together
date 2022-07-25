package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Shapes extends JPanel {

    private CustomJButton circle;
    private CustomJButton triangle;
    private CustomJButton square;
    private CustomJButton diamond;
    private final String circleIconPath = "src/resources/Circle.png";

    public Shapes(ActionListener listener){
        this.setPreferredSize(new Dimension(100,400));
        initComponents(listener);
    }

    private void initComponents(ActionListener listener) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy =0;
        gbc.gridx = 0;

        circle = new CustomJButton(new ImageIcon(circleIconPath),new ImageIcon("src/resources/CircleHover.png"),new ImageIcon("src/resources/CirclePressed.png"));
        circle.addActionListener(listener);
        circle.setActionCommand("DRAWING_SHAPE_TOOL_CIRCLE");
        this.add(circle,gbc);

        gbc.gridx = 1;
        square = new CustomJButton(new ImageIcon("src/resources/Square.png"),new ImageIcon("src/resources/SquareHover.png"),new ImageIcon("src/resources/SquarePressed.png"));
        square.addActionListener(listener);
        square.setActionCommand("DRAWING_SHAPE_TOOL_SQUARE");
        this.add(square,gbc);

        gbc.gridy= 1;
        gbc.gridx = 0;

        triangle =  new CustomJButton(new ImageIcon("src/resources/Triangle.png"),new ImageIcon("src/resources/TriangleHover.png"),new ImageIcon("src/resources/TrianglePressed.png"));
        triangle.addActionListener(listener);
        triangle.setActionCommand("DRAWING_SHAPE_TOOL_TRIANGLE");
        this.add(triangle,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        diamond = new CustomJButton(new ImageIcon("src/resources/Diamond.png"),new ImageIcon("src/resources/DiamondHover.png"),new ImageIcon("src/resources/DiamondPressed.png"));
        diamond.addActionListener(listener);
        diamond.setActionCommand("DRAWING_SHAPE_TOOL_DIAMOND");
        this.add(diamond,gbc);
    }

}
