package MainServer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class InterfaceToClient extends JFrame {

	private JPanel contentPane;
	public JTextField txtIngresaCuenta;
	private JButton ingresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceToClient frame = new InterfaceToClient();
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
	public InterfaceToClient() {
		setTitle("Cajero automatico");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 274);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Banco");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Ingresa Cuenta");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		ingresar = new JButton("Ingresar");
		ingresar.setForeground(Color.ORANGE);
		ingresar.setBackground(Color.BLACK);
		ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Menu frame = new Menu(txtIngresaCuenta);
					frame.setVisible(true);
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				//cerrar ventana actual
				dispose();
			}
		});
		
		txtIngresaCuenta = new JTextField();
		GridBagConstraints gbc_txtIngresaCuenta = new GridBagConstraints();
		gbc_txtIngresaCuenta.gridwidth = 7;
		gbc_txtIngresaCuenta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIngresaCuenta.insets = new Insets(0, 0, 5, 5);
		gbc_txtIngresaCuenta.gridx = 4;
		gbc_txtIngresaCuenta.gridy = 3;
		contentPane.add(txtIngresaCuenta, gbc_txtIngresaCuenta);
		txtIngresaCuenta.setColumns(10);
		//disenio boton
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 4;
		contentPane.add(ingresar, gbc_btnNewButton);
	}

}