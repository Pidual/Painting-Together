package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class Views extends JFrame{

    private Canvas canvas;
    private DrawingTools tools;

    public Views(ActionListener actionListener, MouseListener mouseListener) {
        super("Paint Online");
        this.setSize(1300, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        initComponents(actionListener,mouseListener);
        this.setVisible(true);
    }

    private void initComponents(ActionListener listener, MouseListener mouseListener) {
        canvas = new Canvas();
        tools = new DrawingTools(listener,mouseListener);
        this.add(canvas,BorderLayout.CENTER);
        this.add(tools,BorderLayout.WEST);
    }

    public void changeColor(){
        canvas.setColor(tools.getColorChooser().getColor());
    }

    public void changeDrawingTool(int drawingTool){
        canvas.setCurrentShape(drawingTool);
    }
    


}
