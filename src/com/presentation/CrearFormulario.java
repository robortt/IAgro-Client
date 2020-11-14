package com.presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class CrearFormulario {

	private JFrame frame;
	private JTextField textFieldNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearFormulario window = new CrearFormulario();
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
	public CrearFormulario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(173, 216, 230));
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(10, 104, 127, 20);
		desktopPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(47, 79, 46, 14);
		desktopPane.add(lblNombre);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(275, 104, 149, 146);
		desktopPane.add(textArea);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(324, 79, 75, 14);
		desktopPane.add(lblDescripcion);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(10, 152, 127, 40);
		desktopPane.add(btnGuardar);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(CrearFormulario.class.getResource("/img/CrearFormulario.png")));
		lblFondo.setBounds(90, 0, 254, 261);
		desktopPane.add(lblFondo);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 210, 127, 40);
		desktopPane.add(btnCancelar);
	}
}
