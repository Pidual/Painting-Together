package presenter;

import network.Network;
import views.Views;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class Presenter implements MouseListener, MouseMotionListener {

    private Views view;
    private Network network;
    private ArrayList<String> coordinates;

    public Presenter() throws IOException {
        network = new Network(); //Primero vemos si nos podemos conectar
        coordinates = new ArrayList<>(); //Iniciamos nuestro array de coordenadas
        view = new Views(this,this); //Despues si se muestra la vista
        waitForCoordinates();
    }

    private void waitForCoordinates() {
        String[] list = null;
        int x = 0;
        int y = 0;
        while (true){
            String data = network.getDataToBeDrawn();
            System.out.println(data);
            list = data.split(",");
            for (int i = 0; i < list.length; i+=2) {
                x = Integer.parseInt(list[i]);
                y = Integer.parseInt(list[i+1]);
                view.drawShape(x,y,view.getDrawSize());
            }
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) { //Cuando arrastra el mouse guarda las coordenadas
            coordinates.add(e.getX()+","+e.getY());
    }

//    @Override
//    public void mouseClicked(MouseEvent e) { //Cuando clickea dibuja
//            coordinates.add(e.getX()+","+e.getY());
//            network.sendDataToBeDrawn(coordinates);
//            coordinates.clear();
//    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
            coordinates.add(e.getX()+","+e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) { //Cuando se termina de dibujar se mandan las coordenadas
            coordinates.add(e.getX()+","+e.getY());
            network.sendDataToBeDrawn(coordinates);
            coordinates.clear();
    }



    //Metodos no implementados pero que tocaba meter por que son abstractos
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

