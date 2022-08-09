package MainServer;

import java.io.*;
import java.net.Socket;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.logging.*;

import org.json.simple.JSONObject;


class BankClient extends Thread {
	
	protected Socket sk;
    protected DataOutputStream dos;
    protected DataInputStream dis;
    protected int port;
    protected String result;
    protected String message;
    
    public BankClient(int port, String message) {
    	this.port = port;
    	this.message = message;
    }
    
    public String getResult() {
    	return this.result;
    }
    
    @Override
    public void run() {
    	try {
            sk = new Socket("127.0.0.1", port);
            dos = new DataOutputStream(sk.getOutputStream());
            dis = new DataInputStream(sk.getInputStream());
            dos.writeUTF(this.message);
            String respuesta="";
            respuesta = dis.readUTF();
            this.result = respuesta;
            System.out.println(port+": "+respuesta);
            dis.close();
            dos.close();
            sk.close();
        } catch (IOException ex) {
            Logger.getLogger(BankClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

@SuppressWarnings("unchecked")
public class MainServerImplement extends UnicastRemoteObject implements MainServerInterface {
	
	public MainServerImplement() throws Exception{
		super();
	}
	
	@Override
	public String consultarCuenta(String numeroCuenta) throws Exception {
		
		//VARIABLES
		String result = "";
		String messageString = "";
		int secuencePorts = 10578;
		
		//MENSAJE
		JSONObject messageObject = new JSONObject();
		messageObject.put("cuentaOrigen", numeroCuenta);
		messageObject.put("action", "CONSULTAR");
		
		messageString = messageObject.toJSONString();
		
		//BANKS
		ArrayList<Thread> bankClients = new ArrayList<Thread>();
		for(int i=0; i<3; i++) {
			bankClients.add(new BankClient(secuencePorts+i, messageString));
		}
		
		//Corremos los threads
		for (Thread thread : bankClients) {
            thread.start();
            thread.join();
        }
		
		String aux = "La cuenta no existe";
		for (Thread thread : bankClients) {
			aux = ((BankClient) thread).getResult();
			System.out.println(aux);
            if(!aux.equals("no")) {
            	result = aux;
            	break;
            }
        }
		
		return result;
	}
	

	@Override
	public String retirarSaldo(String numeroCuenta, double monto) throws Exception {
		//VARIABLES
		String result = "";
		String messageString = "";
		int secuencePorts = 10578;
		
		//MENSAJE
		JSONObject messageObject = new JSONObject();
		messageObject.put("cuentaOrigen", numeroCuenta);
		messageObject.put("action", "RETIRAR");
		messageObject.put("monto", monto);
		
		messageString = messageObject.toJSONString();
		
		//BANKS
		ArrayList<Thread> bankClients = new ArrayList<Thread>();
		for(int i=0; i<3; i++) {
			bankClients.add(new BankClient(secuencePorts+i, messageString));
		}
		
		//Corremos los threads
		for (Thread thread : bankClients) {
            thread.start();
            thread.join();
        }
		
		String aux = "La cuenta no existe";
		for (Thread thread : bankClients) {
			aux = ((BankClient) thread).getResult();
			System.out.println(aux);
            if(!aux.equals("no")) {
            	result = aux;
            	break;
            }
        }
		
		return result;
	}

	@Override
	public String depositarSaldo(String numeroCuenta, double monto) throws Exception {
		//VARIABLES
		String result = "";
		String messageString = "";
		int secuencePorts = 10578;
		
		//MENSAJE
		JSONObject messageObject = new JSONObject();
		messageObject.put("cuentaOrigen", numeroCuenta);
		messageObject.put("action", "DEPOSITAR");
		messageObject.put("monto", monto);
		
		messageString = messageObject.toJSONString();
		
		//BANKS
		ArrayList<Thread> bankClients = new ArrayList<Thread>();
		for(int i=0; i<3; i++) {
			bankClients.add(new BankClient(secuencePorts+i, messageString));
		}
		
		//Corremos los threads
		for (Thread thread : bankClients) {
            thread.start();
            thread.join();
        }
		
		String aux = "La cuenta no existe";
		for (Thread thread : bankClients) {
			aux = ((BankClient) thread).getResult();
			System.out.println(aux);
            if(!aux.equals("no")) {
            	result = aux;
            	break;
            }
        }
		
		return result;
	}

}
