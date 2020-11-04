package com.presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import com.application.IAgro;
import com.entities.Usuario;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

public class ListarUsuarios implements IFrame {

	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	List<Usuario> usuarios;
	
	IAgro iagro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarUsuarios window = new ListarUsuarios();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListarUsuarios() {
		initialize();
	}

	/**
	 * Create the application.
	 */
	public ListarUsuarios(IAgro iagro) {
		this.iagro = iagro;
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 669, 456);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(173, 216, 230));
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setBounds(10, 246, 633, 161);
		
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 246, 633, 161);
		desktopPane.add(scrollPane);
		scrollPane.add(table);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.setBounds(10, 11, 84, 23);
		desktopPane.add(btnMenu);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnLimpiar.setBounds(10, 149, 108, 39);
		desktopPane.add(btnLimpiar);
		
		JButton btnEliminar = new JButton("Eliminar Seleccionado");
		btnEliminar.setBounds(478, 138, 165, 23);
		desktopPane.add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar Seleccionado");
		btnModificar.setBounds(478, 192, 165, 23);
		desktopPane.add(btnModificar);
		
		JLabel lblFiltros = new JLabel("Filtrar");
		lblFiltros.setBounds(277, 105, 47, 14);
		desktopPane.add(lblFiltros);
		lblFiltros.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(146, 133, 100, 14);
		desktopPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(146, 157, 100, 14);
		desktopPane.add(lblApellido);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setBounds(146, 182, 125, 14);
		desktopPane.add(lblNombreDeUsuario);
		
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setBounds(146, 207, 100, 14);
		desktopPane.add(lblRol);
		
		textField = new JTextField();
		textField.setBounds(281, 131, 86, 20);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(281, 155, 86, 20);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(281, 180, 86, 20);
		desktopPane.add(textField_2);
		textField_2.setColumns(10);
		
		JComboBox comboBoxRol = new JComboBox();
		comboBoxRol.setBounds(281, 204, 86, 22);
		desktopPane.add(comboBoxRol);
		
		JButton btnBuscar1 = new JButton("Buscar");
		btnBuscar1.setBounds(377, 130, 89, 23);
		desktopPane.add(btnBuscar1);
		
		JButton btnBuscar2 = new JButton("Buscar");
		btnBuscar2.setBounds(377, 154, 89, 23);
		desktopPane.add(btnBuscar2);
		
		JButton btnBuscar3 = new JButton("Buscar");
		btnBuscar3.setBounds(377, 179, 89, 23);
		desktopPane.add(btnBuscar3);
		
		JButton btnBuscar4 = new JButton("Buscar");
		btnBuscar4.setBounds(377, 204, 89, 23);
		desktopPane.add(btnBuscar4);
		
		JLabel lblTitulo = new JLabel("Usuarios");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(120, 38, 412, 56);
		desktopPane.add(lblTitulo);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ListarUsuarios.class.getResource("/img/IAgro icon.png")));
		lblFondo.setBounds(0, 0, 653, 417);
		desktopPane.add(lblFondo);
	}
}
