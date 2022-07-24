package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Canvas extends JPanel{

    private Color actualColor = Color.BLACK;
    private int currentShape = 0;
    private int size;
    public Canvas(MouseMotionListener mouseMotionListener,MouseListener mouseListener){
        size = 20;
        addMouseMotionListener(mouseMotionListener);
        addMouseListener(mouseListener);
        setSize(1200,700);
        setBackground(Color.white);
    }

    public void drawShape(int x, int y, int size){
        Graphics g = getGraphics();
        g.setColor(actualColor);
        switch (currentShape){
            case 0: //circle
                g.fillOval(x ,y,size,size);
                break;
            case 1: //square
                g.fillRect(x,y,size,size);
                break;
            case 2://Triangle
                g.fillPolygon(new int[] {x, x-size, x+size}, new int[] {y-size, y+size, y+size}, 3);
                break;
            case 3: //Diamond
                // x coordinates of vertices
                g.fillPolygon(new int[]{x-size, x, x+size,x},new int[]{y,y+size,y,y-size},4);
                break;
            case 4: //RELOJ DE ARENA XDDXDX
                g.fillPolygon(new int[]{x-size, x , x , x+size},new int[]{y,y-size,y+size,y},4);
                break;
        }
    }

    public void setColor(Color newColor){
        this.actualColor = newColor;
    }

    public void setCurrentShape(int currentShape) {
        this.currentShape = currentShape;
    }

    public int getDrawSize() {
        return size;
    }
}
