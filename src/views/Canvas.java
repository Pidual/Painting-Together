package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Canvas extends JPanel implements MouseMotionListener, MouseListener {

    private Color actualColor = Color.BLACK;
    private int currentShape = 0;

    public Canvas( ){
        addMouseMotionListener(this);
        addMouseListener(this);
        setSize(1200,700);
        setBackground(Color.WHITE);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drawShape(e.getX(),e.getY(),20);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawShape(e.getX(),e.getY(),20);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    private void drawShape(int x, int y , int width){
        Graphics g = getGraphics();
        g.setColor(actualColor);
        switch (currentShape){
            case 0: //circle
                g.fillOval(x ,y,width,width);
                break;
            case 1: //square
                g.fillRect(x,y,width,width);
                break;
            case 2://Triangle
                g.fillPolygon(new int[] {x, x-width, x+width}, new int[] {y-width, y+width, y+width}, 3);
                break;
            case 3: //Diamond
                // x coordinates of vertices
                g.fillPolygon(new int[]{x-width, x, x+width,x},new int[]{y,y+width,y,y-width},4);
                break;
            case 4: //RELOJ DE ARENA XDDXDX
                g.fillPolygon(new int[]{x-width, x , x , x+width},new int[]{y,y-width,y+width,y},4);
                break;
        }
    }


    public void setColor(Color newColor){
        this.actualColor = newColor;
    }

    public void setCurrentShape(int currentShape) {
        this.currentShape = currentShape;
    }


}
