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
import javax.swing.JComboBox;

public class ListarRoles {

	private JFrame frame;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarRoles window = new ListarRoles();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListarRoles() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 669, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setBounds(10, 246, 633, 161);
		
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 246, 633, 161);
		desktopPane.add(scrollPane);
		scrollPane.add(table);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.setBounds(10, 11, 59, 23);
		desktopPane.add(btnMenu);
		
		JButton btnListarTodo = new JButton("Listar Todos");
		btnListarTodo.setBounds(10, 149, 108, 39);
		desktopPane.add(btnListarTodo);
		
		JDesktopPane desktopPaneFiltros = new JDesktopPane();
		desktopPaneFiltros.setBounds(141, 104, 327, 129);
		desktopPane.add(desktopPaneFiltros);
		
		JLabel lblFiltros = new JLabel("Filtrar");
		lblFiltros.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFiltros.setBounds(141, 5, 47, 14);
		desktopPaneFiltros.add(lblFiltros);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 33, 100, 14);
		desktopPaneFiltros.add(lblNombre);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(114, 30, 86, 20);
		desktopPaneFiltros.add(textField);
		
		JButton btnBuscar1 = new JButton("Buscar");
		btnBuscar1.setBounds(210, 29, 89, 23);
		desktopPaneFiltros.add(btnBuscar1);
		
		JButton btnEliminar = new JButton("Eliminar Seleccionado");
		btnEliminar.setBounds(497, 136, 146, 23);
		desktopPane.add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar Seleccionado");
		btnModificar.setBounds(497, 194, 146, 23);
		desktopPane.add(btnModificar);
	}
}