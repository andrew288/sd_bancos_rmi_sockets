package bankA;

import java.io.*;
import java.net.*;
import java.util.logging.*;

public class ServerBankA {

	public static void main(String[] args) {
		ServerSocket ss;
        System.out.print("Inicializando servidor... ");
        try {
            ss = new ServerSocket(10578);
            System.out.println("\t[OK]");
            int idSession = 0;
            while (true) {
                Socket socket;
                socket = ss.accept();
                System.out.println("Nueva conexión entrante: "+socket);
                ((ServerThreadBankA) new ServerThreadBankA(socket, idSession)).start();
                idSession++;
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerBankA.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

}
