package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

public class Views extends JFrame{

    private Canvas canvas;
    private DrawingTools tools;

    public Views(ActionListener actionListener, FocusListener focusListener) {
        super("Paint Online");
        this.setSize(1300, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        initComponents(actionListener,focusListener);
        this.setVisible(true);
    }

    private void initComponents(ActionListener listener, FocusListener focusListener) {
        canvas = new Canvas(focusListener);
        tools = new DrawingTools(listener,focusListener);
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
