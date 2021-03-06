package com.presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListModel;

import com.application.IAgro;
import com.entities.Actividad;
import com.entities.Casilla;
import com.entities.Formulario;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class EditarCasillas implements IFrame<Formulario>{

	private JFrame frame;
	private Long id;
	private IAgro iagro;
	private JButton btnAgregar;
	private JButton btnGuardar;
	private JList casillasFormulario;
	private JCheckBox chckbxMetodo;
	private JCheckBox chckbxEstacion;
	private JCheckBox chckbxDepartamento;
	private JCheckBox chckbxUbicacion;
	private JCheckBox chckbxImagen;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JList casillasDisponibles;
	private DefaultListModel<String> disponiblesModel;
	private DefaultListModel<String> formularioModel;
	private Formulario formulario;
	private int mode;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarCasillas window = new EditarCasillas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Start the application.
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
	public EditarCasillas() {
		initialize();
	}
	
	/**
	 * Constructor con la aplicacion de IAgro inyectada.
	 */
	public EditarCasillas(IAgro iagro) {
		id = 0L;
		this.iagro = iagro;
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 731, 516);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(173, 216, 230));
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
//		List<Casilla> casillas = iagro.getCasillas();
//		for (Casilla casilla : casillas) {
//			casillasFormulario.add(casillasFormulario, casilla.getParametro());
//		}
		
		disponiblesModel = new DefaultListModel<>();
		// obtengo todas las casillas del sistema registradas en IAgro
		for(Casilla casilla: iagro.getCasillas()) {
			disponiblesModel.addElement(casilla.getParametro());
		}
		
		casillasDisponibles = new JList(disponiblesModel);
		mode = casillasDisponibles.getSelectionMode();
		casillasDisponibles.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		casillasDisponibles.setBounds(483, 127, 232, 147);
		desktopPane.add(casillasDisponibles);
		
//		casillasDisponibles.add(casillasFormulario.getComponent(mode));
		/*List<Casilla> casills = iagro.getCasillas();
		for (Casilla cas : casills) {
			casillasDisponibles.add(casillasDisponibles, cas.getParametro());
		}*/
		
		formularioModel = new DefaultListModel<>();
		// obtengo todas las casillas del formulario cargado
//		for(Casilla casilla: formulario.getCasillas()) {
//			formularioModel.addElement(casilla.getParametro());
//		}
		
		casillasFormulario = new JList(formularioModel);
		mode = casillasFormulario.getSelectionMode();
		casillasFormulario.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		casillasFormulario.setBounds(227, 127, 232, 144);
		desktopPane.add(casillasFormulario);
		
		btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Agrega el elemento seleccionado al JList casillasFormulario
				String selectedCasilla = casillasDisponibles.getSelectedValue().toString();
//				List<String> listaCasillasFormulario = casillasFormulario.
//				for(String parametro: listaNombresFuncionalidades.)
				boolean existe = false;
				for(int i=0; i < casillasFormulario.getModel().getSize(); i++) {
					if(casillasFormulario.getModel().getElementAt(i).equals(selectedCasilla)) {
						existe = true;
					}
				}
				if(!existe) {
					List<Casilla> casillas = formulario.getCasillas();
					Casilla casillaNueva = iagro.readCasilla(selectedCasilla);
					casillas.add(casillaNueva);
					formularioModel.addElement(casillaNueva.getParametro());
					formulario.setCasillas(casillas);
				} else {
					JOptionPane.showMessageDialog(null, "La casilla ya se encuentra en el formulario","Error",JOptionPane.ERROR_MESSAGE);
					//System.out.println("La casilla ya se encuentra en el formulario");
				}
			}
		});
		btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAgregar.setIcon(new ImageIcon(EditarCasillas.class.getResource("/img/BotonAgregarSeleccionada.png")));
		btnAgregar.setBounds(455, 293, 232, 42);
		desktopPane.add(btnAgregar);
		
		JLabel lblTitulo = new JLabel("");
		lblTitulo.setIcon(new ImageIcon(EditarCasillas.class.getResource("/img/ListarCasillas.png")));
		lblTitulo.setBounds(293, 17, 124, 70);
		desktopPane.add(lblTitulo);
		
		JLabel lblCasillasObligatorias = new JLabel("Casillas Default Obligatorias:");
		lblCasillasObligatorias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCasillasObligatorias.setBounds(10, 99, 187, 21);
		desktopPane.add(lblCasillasObligatorias);
		
		JLabel lblNewLabel = new JLabel("M\u00E9todo de Muestreo");
		lblNewLabel.setBounds(37, 131, 124, 14);
		desktopPane.add(lblNewLabel);
		
		JLabel lblEstacinDeMuestreo = new JLabel("Estaci\u00F3n de Muestreo");
		lblEstacinDeMuestreo.setBounds(37, 156, 124, 14);
		desktopPane.add(lblEstacinDeMuestreo);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(37, 181, 124, 14);
		desktopPane.add(lblDepartamento);
		
		chckbxMetodo = new JCheckBox("New check box");
		chckbxMetodo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		chckbxMetodo.setEnabled(false);
		chckbxMetodo.setSelected(true);
		chckbxMetodo.setBounds(10, 127, 21, 23);
		desktopPane.add(chckbxMetodo);
		
		chckbxEstacion = new JCheckBox("New check box");
		chckbxEstacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		chckbxEstacion.setEnabled(false);
		chckbxEstacion.setSelected(true);
		chckbxEstacion.setBounds(10, 152, 21, 23);
		desktopPane.add(chckbxEstacion);
		
		chckbxDepartamento = new JCheckBox("New check box");
		chckbxDepartamento.setCursor(new Cursor(Cursor.HAND_CURSOR));
		chckbxDepartamento.setEnabled(false);
		chckbxDepartamento.setSelected(true);
		chckbxDepartamento.setBounds(10, 177, 21, 23);
		desktopPane.add(chckbxDepartamento);
		
		JLabel lblObligatorio = new JLabel("*");
		lblObligatorio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblObligatorio.setForeground(Color.RED);
		lblObligatorio.setBounds(160, 131, 15, 14);
		desktopPane.add(lblObligatorio);
		
		JLabel lblObligatorio_1 = new JLabel("*");
		lblObligatorio_1.setForeground(Color.RED);
		lblObligatorio_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblObligatorio_1.setBounds(165, 156, 15, 14);
		desktopPane.add(lblObligatorio_1);
		
		JLabel lblObligatorio_2 = new JLabel("*");
		lblObligatorio_2.setForeground(Color.RED);
		lblObligatorio_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblObligatorio_2.setBounds(140, 181, 15, 14);
		desktopPane.add(lblObligatorio_2);
		
		
		
		btnCancelar = new JButton("");
		btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int seleccion = JOptionPane.showOptionDialog(null, "Seguro desea Cancelar y salir al menu?",  null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
						null, new Object[] { "SI", "NO"},   
						   null);

						if (seleccion == 0) {
							iagro.show(ListarFormularios.class);
							frame.dispose();
						}
				
				
				
			}
		});
		btnCancelar.setIcon(new ImageIcon(EditarCasillas.class.getResource("/img/BotonCancelar.png")));
		btnCancelar.setBounds(107, 426, 137, 40);
		desktopPane.add(btnCancelar);
		
		
		
		JLabel lblCasillasFormulario = new JLabel("Casillas del Formulario:");
		lblCasillasFormulario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCasillasFormulario.setBounds(255, 98, 177, 21);
		desktopPane.add(lblCasillasFormulario);
		
		
		JLabel lblCasillasDisponibles = new JLabel("Casillas Disponibles:");
		lblCasillasDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCasillasDisponibles.setBounds(520, 99, 156, 21);
		desktopPane.add(lblCasillasDisponibles);
		
		chckbxUbicacion = new JCheckBox("New check box");
		chckbxUbicacion.setEnabled(false);
		chckbxUbicacion.setBounds(10, 241, 21, 23);
		desktopPane.add(chckbxUbicacion);
		
		chckbxImagen = new JCheckBox("New check box");
		chckbxImagen.setEnabled(false);
		chckbxImagen.setBounds(10, 267, 21, 23);
		desktopPane.add(chckbxImagen);
		
		
		btnGuardar = new JButton("");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result;
				result = iagro.update(formulario);
				if(result) {
					
					JOptionPane.showMessageDialog(null, "Se han agregado las Casillas al Formulario","Exito",JOptionPane.DEFAULT_OPTION);
					
					
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "No se pudo agregar la Casilla al Formulario","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGuardar.setIcon(new ImageIcon(EditarCasillas.class.getResource("/img/BotonGuardar (2).png")));
		btnGuardar.setBounds(455, 426, 137, 40);
		desktopPane.add(btnGuardar);
		
		
		btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
							//aca el codigo para eliminar la casilla
							String selectedCasilla = casillasFormulario.getSelectedValue().toString();
							if(!selectedCasilla.equals("Metodo de muestreo") && !selectedCasilla.equals("Estacion de Muestreo") && !selectedCasilla.equals("Departamento")) {
								int selectedIndex = casillasFormulario.getSelectedIndex();
								if(selectedIndex != -1) {
									formularioModel.remove(selectedIndex);
									List<Casilla> casillas = new ArrayList<>();
									for(int i=0; i < casillasFormulario.getModel().getSize(); i++) {
										Casilla casilla = iagro.readCasilla(casillasFormulario.getModel().getElementAt(i).toString());
										casillas.add(casilla);
									}
									formulario.setCasillas(casillas);
									
									
									
									
									
								}
							} else {
				
								
								JOptionPane.showMessageDialog(null, "La casilla seleccionada es obligatoria y no puede eliminarse","Error",JOptionPane.ERROR_MESSAGE);
							}
							
						
				
			}
		});
		btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEliminar.setIcon(new ImageIcon(EditarCasillas.class.getResource("/img/BotonEliminarSeleccionada.png")));
		btnEliminar.setBounds(199, 293, 232, 42);
		desktopPane.add(btnEliminar);
		
		
		
		JLabel lblCasillasBasicas = new JLabel("Casillas Basicas no Obligatorias:");
		lblCasillasBasicas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCasillasBasicas.setBounds(10, 213, 187, 21);
		desktopPane.add(lblCasillasBasicas);
		
		JLabel lblUbicacion = new JLabel("Ubicaci\u00F3n");
		lblUbicacion.setBounds(37, 245, 124, 14);
		desktopPane.add(lblUbicacion);
		
		JLabel lblImgen = new JLabel("Im\u00E1gen");
		lblImgen.setBounds(37, 271, 124, 14);
		desktopPane.add(lblImgen);
		
		
		
		
		
		
	}

	@Override
	public void setFields(Formulario o) {
		// busco el formulario pasado para obtener todas sus casillas y lo guardo en formulario
		formulario = (Formulario) iagro.read(o.getId(), Formulario.class);
		// cargo todas las casillas del formulario en el JList casillasFormulario
		if(formulario.getCasillas().size() < 1) {
			System.out.println("El formulario no tiene casillas. Se creara uno con las casillas por defecto");
			List<Casilla> casillas = new ArrayList<>();
			casillas.add(iagro.readCasilla("Metodo de muestreo"));
			casillas.add(iagro.readCasilla("Estacion de Muestreo"));
			casillas.add(iagro.readCasilla("Departamento"));
			formulario.setCasillas(casillas);
		}
//		formularioModel = new DefaultListModel<>();
		// obtengo todas las casillas del formulario cargado
		for(Casilla casilla: formulario.getCasillas()) {
			formularioModel.addElement(casilla.getParametro());
		}
	}
}
