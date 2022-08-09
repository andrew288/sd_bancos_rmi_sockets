package MainServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {

	public static void main(String[] args) throws Exception {
	      String registryURL; 
	      try{     
	         int RMIPortNum = 2000;
	         startRegistry(RMIPortNum);
	         MainServerImplement exportedObj = new MainServerImplement();
	         registryURL = "rmi://localhost:" + 2000 + "/mainServer";
	         Naming.rebind(registryURL, exportedObj);
	/**/     System.out.println("Servidor registrado.  El registro contiene actualmente:");
	/**/     listRegistry(registryURL);
	         System.out.println("Hola, Servidor listo!");
	      }// end try
	      catch (Exception re) {
	         System.out.println("Excepcion en MainServer: " + re);
	      } // end catch
	  } // end main


	   private static void startRegistry(int RMIPortNum)
	      throws RemoteException{
	      try {
	         Registry registry = LocateRegistry.getRegistry(RMIPortNum);
	         registry.list( );
	      }
	      catch (RemoteException e) { 
	         // No valid registry at that port.
	/**/     System.out.println("El registro RMI, aun no existe en el puerto " + RMIPortNum);
	         Registry registry = LocateRegistry.createRegistry(RMIPortNum);
	/**/     System.out.println("Registro RMI creado en el puerto " + RMIPortNum);
	      }
	   }


	  private static void listRegistry(String registryURL)
	     throws RemoteException, MalformedURLException {
	       System.out.println("Registro " + registryURL + " contiene: ");
	       
	       String [ ] names = Naming.list(registryURL);
	       for (int i=0; i < names.length; i++)
	          System.out.println(names[i]);
	  }
		
//		Registry registry = LocateRegistry.createRegistry(5099);
//		registry.rebind("mainServer", new MainServerImplement());
//		System.out.println("Server Main Started");
	
}
