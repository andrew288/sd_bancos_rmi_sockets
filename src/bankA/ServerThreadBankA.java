package bankA;

import java.io.*;
import java.net.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServerThreadBankA extends Thread {
	private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSessio;
    
    public ServerThreadBankA(Socket socket, int id) {
        this.socket = socket;
        this.idSessio = id;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServerThreadBankA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThreadBankA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private double saldoAccount(String numberAccount) throws SQLException {
    	//consulta de existencia de cuenta
    	ConectionDataBaseBankA conection = new ConectionDataBaseBankA();
    	String consulta = "SELECT * FROM Cuenta WHERE numeroCuenta = ?";
    	Double resultSaldo = 0.0;
    		PreparedStatement st = conection.getConn().prepareStatement(consulta);
    		st.setString(1, numberAccount);
    		ResultSet rs = st.executeQuery();
    		
    		if(rs.next()) {
    			resultSaldo = rs.getDouble("saldo");
    			conection.getConn().close();
    			return resultSaldo;
    		}
    		else {
    			conection.getConn().close();
    			return -1;
    		}
    }
    
    private double retirarAccount(String numberAccount, double monto) throws SQLException {
    	//consulta de existencia de cuenta
    	ConectionDataBaseBankA conection = new ConectionDataBaseBankA();
    	String consulta = "SELECT * FROM Cuenta WHERE numeroCuenta = ?";
    	Double resultSaldo = 0.0;
		PreparedStatement st = conection.getConn().prepareStatement(consulta);
		st.setString(1, numberAccount);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			resultSaldo = rs.getDouble("saldo");
			if(resultSaldo<monto) {
				conection.getConn().close();
				return -1;
			}
			else {
				double nuevoSaldo = resultSaldo - monto;
				PreparedStatement stU= conection.getConn().prepareStatement("UPDATE Cuenta SET saldo=? WHERE numeroCuenta=?");
				stU.setDouble(1, nuevoSaldo);
				stU.setString(2, numberAccount);
				int retorno = stU.executeUpdate();
				conection.getConn().close();
				return nuevoSaldo;
			}
		}
		else {
			conection.getConn().close();
			return -1;
		}
    }
    
    private double depositarAccount(String numberAccount, double monto) throws SQLException {
    	//consulta de existencia de cuenta
    	ConectionDataBaseBankA conection = new ConectionDataBaseBankA();
    	String consulta = "SELECT * FROM Cuenta WHERE numeroCuenta = ?";
    	Double resultSaldo = 0.0;
		PreparedStatement st = conection.getConn().prepareStatement(consulta);
		st.setString(1, numberAccount);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			resultSaldo = rs.getDouble("saldo");
			double nuevoSaldo = resultSaldo + monto;
			PreparedStatement stU= conection.getConn().prepareStatement("UPDATE Cuenta SET saldo=? WHERE numeroCuenta=?");
			stU.setDouble(1, nuevoSaldo);
			stU.setString(2, numberAccount);
			int retorno = stU.executeUpdate();
			conection.getConn().close();
			return nuevoSaldo;
			
		}
		else {
			conection.getConn().close();
			return -1;
		}
    }
    
    @Override
    public void run() {
        String accion = "";
        JSONObject accionJSON;
        JSONParser parserAccion = new JSONParser();
        
        try {
            accion = dis.readUTF();
            System.out.println(accion);
            accionJSON = (JSONObject) parserAccion.parse(accion);
            System.out.println(accionJSON);
            
//            CONSULTAR SALDO
            if(accionJSON.get("action").equals("CONSULTAR")) {
            	double result = saldoAccount((String) accionJSON.get("cuentaOrigen"));
            	System.out.println(result);
            	if(result != -1) {            		
            		dos.writeUTF("Saldo: "+ result);
            	}
            	else {
            		dos.writeUTF("no");
            	}
            }
            
//            RETIRAR SALDO
            if(accionJSON.get("action").equals("RETIRAR")) {
            	double result = retirarAccount((String) accionJSON.get("cuentaOrigen"), (double) accionJSON.get("monto"));
            	System.out.println(result);
            	if(result != -1) {            		
            		dos.writeUTF("Saldo actual: "+ result);
            	}
            	else {
            		dos.writeUTF("no");
            	}
            }
            
//            DEPOSITAR SALDO
            if(accionJSON.get("action").equals("DEPOSITAR")) {
            	double result = depositarAccount((String) accionJSON.get("cuentaOrigen"), (double) accionJSON.get("monto"));
            	System.out.println(result);
            	if(result != -1) {            		
            		dos.writeUTF("Saldo actual: "+ result);
            	}
            	else {
            		dos.writeUTF("no");
            	}
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ServerThreadBankA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        desconnectar();
    }
}
