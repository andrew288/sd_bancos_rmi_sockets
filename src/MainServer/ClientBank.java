package MainServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class ClientBank {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner src = new Scanner(System.in);
		String result = "";
		JSONObject resultOject;
		
		try {
			int RMIPort;
	         String hostName;
	         InputStreamReader is = new InputStreamReader(System.in);
	         BufferedReader br = new BufferedReader(is);
	         System.out.println("Ingresa el host del registro RMI:");
	         hostName = br.readLine();
	         System.out.println("Ingresa el puerto del registro RMI:");
	         String portNum = br.readLine();
	         RMIPort = Integer.parseInt(portNum);
	         String registryURL = 
	            "rmi://" + hostName+ ":" + portNum + "/mainServer";  
	         // find the remote object and cast it to an interface object
	         MainServerInterface server = (MainServerInterface) Naming.lookup(registryURL);
	         
	         int seleccion;
	         String numeroCuenta = "";
	         double monto;
	         boolean decision = true;
	         
	         while(decision) {
	        	 System.out.println("***CAJERO AUTOMATICO***\n"
	        			 + "1.- Consultar saldo\n"
	        			 + "2.- Retirar dinero\n"
	        			 + "3.- Despositar dinero\n"
	        			 + "4.- Salir");
	        	 System.out.println("Ingrese una opcion: ");
	        	 seleccion = src.nextInt();
	        	 switch(seleccion) {
	        	 case 1: 
	        		 System.out.println("Ingrese el numero de cuenta: ");
	        		 numeroCuenta = src.next();
	        		 result = server.consultarCuenta(numeroCuenta);
//		 		 result = server.consultarCuenta("A240602");
	        		 System.out.println(result);
	        		 break;
	        	 case 2:
	        		 System.out.println("Ingrese el numero de cuenta: ");
	        		 numeroCuenta = src.next();
	        		 System.out.println("Ingrese el monto a retirar: ");
	        		 monto = src.nextDouble();
	        		 result = server.retirarSaldo(numeroCuenta, monto);
	        		 System.out.println(result);
	        		 break;
	        	 case 3:
	        		 System.out.println("Ingrese el numero de cuenta: ");
	        		 numeroCuenta = src.next();
	        		 System.out.println("Ingrese el monto a depositar: ");
	        		 monto = src.nextDouble();
	        		 result = server.depositarSaldo(numeroCuenta, monto);
	        		 System.out.println(result);
	        		 break;
	        	 case 4:
	        		 decision = false;
	        		 break;
	        	 default:
	        		 System.out.println("Elección no existente");
	        		 break;
	        	 }
	         }
	      } // end try 
	      catch (Exception e) {
	         System.out.println("Excepcion en ClientBank: " + e);
	      } 

	}

}
