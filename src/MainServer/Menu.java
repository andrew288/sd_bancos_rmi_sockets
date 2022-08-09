package MainServer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JOptionPane;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu(cuenta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu(JTextField cuenta) {
		setTitle("Cajero Automatico - " + cuenta.getText());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{595, 0};
		gbl_contentPane.rowHeights = new int[]{29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Consultar saldo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//consultar saldo
				consultarSaldo(cuenta);
			}

			private void consultarSaldo(JTextField cuenta) {
				// TODO Auto-generated method stub
				Scanner src = new Scanner(System.in);
				String result = "";
				JSONObject resultOject;
				
				try {
			         InputStreamReader is = new InputStreamReader(System.in);
			         BufferedReader br = new BufferedReader(is);
			         //System.out.println("Ingresa el host del registro RMI:");
			         //hostName = br.readLine();
			         //System.out.println("Ingresa el puerto del registro RMI:");
			         //String portNum = br.readLine();
			         String hostName = "localhost";
			         String portNum = "2000";  //los puertos seran 2000
			         
			         String registryURL = 
			            "rmi://" + hostName+ ":" + portNum + "/mainServer";  
			         // find the remote object and cast it to an interface object
			         MainServerInterface server = (MainServerInterface) Naming.lookup(registryURL);
			         
			         int seleccion;
			         String numeroCuenta = "";
			         double monto;
			         //numeroCuenta = JOptionPane.showInputDialog(null, "Ingrese el numero de cuenta: ");
	        		 result = server.consultarCuenta(cuenta.getText());
//				 		 result = server.consultarCuenta("A240602");
	        		 JOptionPane.showMessageDialog(null, "Saldo: " + result);
		         }catch (Exception e) {
			         System.out.println("Excepcion en ClientBank: " + e);
			      } 
			}
		});
		btnNewButton.setForeground(Color.ORANGE);
		btnNewButton.setBackground(SystemColor.activeCaptionText);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 5;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Depositar saldo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//depositar saldo
				depositarSaldo();
			}

			private void depositarSaldo() {
				// TODO Auto-generated method stub
				Scanner src = new Scanner(System.in);
				String result = "";
				JSONObject resultOject;
				
				try {
			         InputStreamReader is = new InputStreamReader(System.in);
			         BufferedReader br = new BufferedReader(is);
			         //System.out.println("Ingresa el host del registro RMI:");
			         //hostName = br.readLine();
			         //System.out.println("Ingresa el puerto del registro RMI:");
			         //String portNum = br.readLine();
			         String hostName = "localhost";
			         String portNum = "2000";  //los puertos seran 2000
			         
			         String registryURL = 
			            "rmi://" + hostName+ ":" + portNum + "/mainServer";  
			         // find the remote object and cast it to an interface object
			         MainServerInterface server = (MainServerInterface) Naming.lookup(registryURL);
			         
			         int seleccion;
			         double monto;
			         monto = Double.parseDouble(
			        		 JOptionPane.showInputDialog(null, "Ingrese el monto a depositar: "));
//				 		 result = server.consultarCuenta("A240602");
			         
			         result = server.depositarSaldo(cuenta.getText(), monto);
			         
	        		 JOptionPane.showMessageDialog(null, "Saldo actual: " + result);

		         }catch (Exception e) {
			         System.out.println("Excepcion en ClientBank: " + e);
			      } 
				
			}
		});
		btnNewButton_1.setForeground(Color.ORANGE);
		btnNewButton_1.setBackground(Color.BLACK);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 6;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Retirar saldo");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//retirar saldo
				retirarSaldo();
			}

			private void retirarSaldo() {
				// TODO Auto-generated method stub
				Scanner src = new Scanner(System.in);
				String result = "";
				JSONObject resultOject;
				
				try {
			         InputStreamReader is = new InputStreamReader(System.in);
			         BufferedReader br = new BufferedReader(is);
			         //System.out.println("Ingresa el host del registro RMI:");
			         //hostName = br.readLine();
			         //System.out.println("Ingresa el puerto del registro RMI:");
			         //String portNum = br.readLine();
			         String hostName = "localhost";
			         String portNum = "2000";  //los puertos seran 2000
			         
			         String registryURL = 
			            "rmi://" + hostName+ ":" + portNum + "/mainServer";  
			         // find the remote object and cast it to an interface object
			         MainServerInterface server = (MainServerInterface) Naming.lookup(registryURL);
			         
			         int seleccion;
			         double monto;
			         monto = Double.parseDouble(
			        		 JOptionPane.showInputDialog(null, "Ingrese el monto a retirar: "));
//				 		 result = server.consultarCuenta("A240602");
			         
	        		 result = server.retirarSaldo(cuenta.getText(), monto);
	        		 JOptionPane.showMessageDialog(null, "Saldo actual "+result);
	        		 
		         }catch (Exception e) {
			         System.out.println("Excepcion en ClientBank: " + e);
			      } 
			}
		});
		btnNewButton_2.setForeground(Color.ORANGE);
		btnNewButton_2.setBackground(Color.BLACK);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 7;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Salir");
		btnNewButton_3.setForeground(Color.RED);
		btnNewButton_3.setBackground(Color.BLACK);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 9;
		contentPane.add(btnNewButton_3, gbc_btnNewButton_3);
	}

}