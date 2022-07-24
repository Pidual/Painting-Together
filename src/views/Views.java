package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Views extends JFrame implements ActionListener,MouseListener{

    private Canvas canvas;
    private DrawingTools tools;

    public Views(MouseMotionListener mouseMotionListener,MouseListener mouseListener) {
        super("Paint Online");
        this.setSize(1300, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        initComponents(mouseMotionListener,mouseListener);
        this.setVisible(true);
    }

    private void initComponents(MouseMotionListener mouseMotionListener, MouseListener mouseListener) {
        canvas = new Canvas(mouseMotionListener,mouseListener);
        tools = new DrawingTools(this,this);
        this.add(canvas,BorderLayout.CENTER);
        this.add(tools,BorderLayout.WEST);
    }

    public void changeDrawingTool(int drawingTool){
        canvas.setCurrentShape(drawingTool);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command){
            case "DRAWING_SHAPE_TOOL_CIRCLE":
                changeDrawingTool(0);
                break;
            case "DRAWING_SHAPE_TOOL_SQUARE":
                changeDrawingTool(1);
                break;
            case "DRAWING_SHAPE_TOOL_TRIANGLE":
                changeDrawingTool(2);
                break;
            case "DRAWING_SHAPE_TOOL_DIAMOND":
                changeDrawingTool(3);
                break;
        }
    }

    public void drawShape(int x, int y, int size) {
        canvas.drawShape(x,y,size);
    }

    public int getDrawSize(){
        return canvas.getDrawSize();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvas.setColor(tools.getColorChooser().getColor());
    }

    //Metodos no implementados
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
