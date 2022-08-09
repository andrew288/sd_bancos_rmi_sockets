package MainServer;

import java.rmi.Remote;

public interface MainServerInterface extends Remote{
	public String consultarCuenta(String numeroCuenta) throws Exception;
	public String retirarSaldo(String numeroCuenta, double monto) throws Exception;
	public String depositarSaldo(String numeroCuenta, double monto) throws Exception;
//	public String depositarRetirar(String numeroCuentaOrigen, String numeroCuentaDestino) throws Exception;
}
