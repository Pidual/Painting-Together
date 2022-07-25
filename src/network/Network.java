package network;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Network{

    private final int PUERTO = 1234;
    private final String HOST = "localhost";
    private final Socket socket;
    private final DataOutputStream output;
    private final DataInputStream input;


    public Network() throws IOException {
        socket = new Socket(HOST, PUERTO);
        output = new DataOutputStream(socket.getOutputStream());
        input = new DataInputStream(socket.getInputStream());
    }

    public void sendDataToBeDrawn(ArrayList<String> coordinates){
        System.out.println(coordinates.toString());
        try {
            output.writeUTF(coordinates.toString());
        } catch (IOException e) {
            System.out.println("ERROR ENVIADO LAS COORDENADAS COMO STRING XDDXDX");
        }
    }

    public String getDataToBeDrawn(){
        String text = "";
        try {
            text = input.readUTF();
        } catch (IOException e) {
            System.out.println("ERROR AL LEER LOS DATOS XDXDXDXDXDX");
        }
        return text;
    }


}