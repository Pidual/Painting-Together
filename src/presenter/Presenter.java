package presenter;

import network.Network;
import views.Views;

import java.awt.event.*;
import java.io.IOException;

public class Presenter implements MouseListener, MouseMotionListener {

    private Views view;
    private Network network;


    public Presenter() throws IOException {
        view = new Views(this,this);
        network = new Network();
    }

    public void draw(int [] data){
        view.drawShape(data[0],data[1],data[2]);
        System.out.println(data[0]+" "+data[1]+" "+data[2]);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
            network.sendDataToBeDrawn(e.getX(),e.getY(), view.getDrawSize());
            draw(network.getDataToBeDrawn());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
            network.sendDataToBeDrawn(e.getX(),e.getY(),view.getDrawSize());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }



    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

