package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network {

    private final int PUERTO = 1234;
    private final String HOST = "localhost";
    private final Socket socket;
    private final DataOutputStream salida;
    private final DataInputStream entrada;


    public Network() throws IOException {
        socket = new Socket(HOST, PUERTO);
        salida = new DataOutputStream(socket.getOutputStream());
        entrada = new DataInputStream(socket.getInputStream());
    }

    public void sendDataToBeDrawn(int x,int y,int size) {
        try {
            salida.writeInt(x);
            salida.writeInt(y);
            salida.writeInt(size);
        } catch (IOException e) {
            System.out.println("Error a la hora de enviar datos xdxdxddx");
        }
    }

    public int[] getDataToBeDrawn(){
        int [] data = new int[3];
        try {
            data[0] = entrada.readInt();
            data[1] = entrada.readInt();
            data[2] = entrada.readInt();
        } catch (IOException e) {
            System.out.println("Error con la recibida de datos");
        }
        return data;
    }

}
