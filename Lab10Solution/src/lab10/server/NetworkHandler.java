package lab10.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkHandler implements Runnable {
    private boolean keepGoing = true;
    private Controller serverController = null;
    private ServerSocket serverSocket = null;

    public NetworkHandler(Controller serverController, ServerSocket serverSocket) {
        this.serverController = serverController;
        this.serverSocket = serverSocket;
    }

    public void run() {
        try {
            System.out.println("Listening for incoming client connections.");
            while (this.keepGoing) {
                Socket clientSocket = serverSocket.accept();
                ChatClientHandler handler = new ChatClientHandler(this.serverController, clientSocket);
                this.serverController.addClientHandler(handler);
                Thread handlerThread = new Thread(handler);
                handlerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        this.keepGoing = false;
    }
}
