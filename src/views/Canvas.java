package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Canvas extends JPanel implements MouseMotionListener, MouseListener {

    private Color actualColor = Color.BLACK;
    private int currentShape = 0; // 0 for circle 1 for

    public Canvas(FocusListener focusListener){
        addMouseMotionListener(this);
        addMouseListener(this);
        setSize(1200,700);
        setBackground(Color.WHITE);
        addFocusListener(focusListener);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drawShape(e.getX(),e.getY(),20,20);
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
        drawShape(e.getX(),e.getY(),20,20);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    private void drawShape(int x, int y , int width,int heigth){
        Graphics g = getGraphics();
        g.setColor(actualColor);
        switch (currentShape){
            case 0: //circle
                g.fillOval(x ,y,width,heigth);
                break;
            case 1: //square
                g.fillRect(x,y,width,heigth);
                break;
            case 2://Triangle
                g.drawPolygon(new int[] {10, 20, 30}, new int[] {100, 20, 100}, 3);
                break;
            case 3: //Diamond
                g.drawPolygon(new int[]{x+width, x, x-width,x},new int[]{y,y-width,y+width,y},4);
                break;
            case 4: //RELOJ DE ARENA XDDXDX
                g.drawPolygon(new int[]{x-width, x , x , x+width},new int[]{y,y-width,y+width,y},4);
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
