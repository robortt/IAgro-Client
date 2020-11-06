package com.presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import com.application.IAgro;
import com.entities.Funcionalidad;
import com.entities.Rol;

import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ListarRoles implements IFrame<Rol> {

	private JFrame frame;
	private JTable table;
	private JTextField textFieldNombre;
	List<Rol> roles;
	private TableRowSorter<ModeloTabla> sorter;
	
	private IAgro iagro;
	
	Long id;

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
	 *
	 * Hago visible la ventana de Listar Roles
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
	public ListarRoles() {
		initialize();
	}
	@Override
	public void setFields(Rol o) {
		
		
	}
	
	/**
	 * 
	 * Constructor con la aplicacion de IAgro inyectada.
	 * @return 
	 */
	public ListarRoles(IAgro iagro) {
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
		
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(173, 216, 230));
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		roles = iagro.getRoles();
		String[] columnas = iagro.getColumnasRoles();
		
		int x = roles.size();
		int y = columnas.length;
		
		Object[][] datos = iagro.matrixRoles();
		
		ModeloTabla model = new ModeloTabla(columnas, datos);
		
		sorter = new TableRowSorter<ModeloTabla>(model);
		table = new JTable(model);
		table.setRowSorter(sorter);
//		table.setBounds(10, 246, 633, 161);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 246, 633, 161);
//		scrollPane.add();
		desktopPane.add(scrollPane);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.setBounds(10, 11, 84, 23);
		desktopPane.add(btnMenu);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarRoles();
			}
		});
		btnLimpiar.setBounds(10, 149, 108, 39);
		desktopPane.add(btnLimpiar);
		
		JButton btnEliminar = new JButton("Eliminar Seleccionado");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int selectedRow = table.getSelectedRow();
				Rol rolDelete = iagro.readRol(table.getValueAt(table.getSelectedRow(),0).toString());
				boolean result = iagro.delete(rolDelete.getId(), Rol.class);
				if(result) {
					model.setData(iagro.matrixRoles());
					model.refresh();
					limpiarRoles();
					JOptionPane.showMessageDialog(null, "Se logro eliminar el Rol","Exito",JOptionPane.DEFAULT_OPTION);
				}
				else {
					JOptionPane.showMessageDialog(null, "No se logro eliminar el Rol","Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnEliminar.setBounds(478, 136, 165, 23);
		desktopPane.add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar Seleccionado");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				Rol rolUpdate = iagro.readRol(table.getValueAt(selectedRow, 0).toString());
				iagro.show(CrearRol.class, rolUpdate);
				frame.dispose();
			}
		});
		btnModificar.setBounds(478, 194, 165, 23);
		desktopPane.add(btnModificar);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(320, 174, 86, 20);
		desktopPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldNombre.getDocument().addDocumentListener(
				new DocumentListener() {

					@Override
					public void insertUpdate(DocumentEvent e) {
						filterColumns();
						
					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						filterColumns();
						
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						filterColumns();
						
					}
					
				});
		
		JLabel lblFiltros = new JLabel("Filtrar");
		lblFiltros.setBounds(285, 149, 47, 14);
		desktopPane.add(lblFiltros);
		lblFiltros.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(190, 174, 100, 14);
		desktopPane.add(lblNombre);
		
		JLabel lblTitulo = new JLabel("Roles");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(135, 32, 370, 81);
		desktopPane.add(lblTitulo);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ListarRoles.class.getResource("/img/IAgro icon.png")));
		lblFondo.setBounds(0, 0, 653, 417);
		desktopPane.add(lblFondo);
	}
	
	public void limpiarRoles() {
		textFieldNombre.setText("");
	}
	
	
	
	/** 
     * Update the row filter regular expression from the expression in
     * the text box.
     */
    private void filterColumns() {
        RowFilter<ModeloTabla, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
        	List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(1);
//        	filters.add(RowFilter.regexFilter(textFieldNombre.getText(), 1));
            rf = RowFilter.regexFilter(textFieldNombre.getText().toUpperCase(), 1);
//        	rf = RowFilter.andFilter(filters);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

	
}
