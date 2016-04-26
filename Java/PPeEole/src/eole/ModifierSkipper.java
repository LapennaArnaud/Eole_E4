package eole;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

public class ModifierSkipper extends Outils {
	private JTable table;
	private JTextField tfNom;
	private JTextField tfPrenom;
	public ModifierSkipper(){
		final JFrame frmModifierSkipper = new JFrame("Créer un Voilier");
		frmModifierSkipper.setType(Type.POPUP);
		frmModifierSkipper.setTitle("Modifier Skipper");
		frmModifierSkipper.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmModifierSkipper.setSize(737, 357);
		frmModifierSkipper.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmModifierSkipper.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 357, 292);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		String[] entetes = {"Nom", "Prenom"};
		Object[][] data = {};
		DefaultTableModel modele = new DefaultTableModel(data, entetes);
		table.setModel(modele);
		// Rajouter une colonne et une ligne (2eme méthode)
		
		Connection conn;
    	String requete = "Select * From Skipper";
		try {
			conn = DriverManager.getConnection(cheminBdd);
	    	Statement s = conn.createStatement();
	    	ResultSet rs = s.executeQuery(requete);
	    	while (rs.next()) {
	    		modele.addRow(new Object[] {rs.getString(1), rs.getString(2)});
	    	}
	    	conn.close();
	    	s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent event) {
		        if (table.getSelectedRow() > -1) {
		        	tfNom.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
		        	tfPrenom.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
		        }
		    }
		});
		
		scrollPane.setViewportView(table);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(455, 43, 46, 14);
		panel.add(lblNom);
		
		tfNom = new JTextField();
		tfNom.setColumns(10);
		tfNom.setBounds(511, 40, 86, 20);
		panel.add(tfNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(455, 82, 46, 14);
		panel.add(lblPrenom);
		
		tfPrenom = new JTextField();
		tfPrenom.setColumns(10);
		tfPrenom.setBounds(511, 79, 86, 20);
		panel.add(tfPrenom);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn;
		    	String requete = "UPDATE Skipper SET NomSkipper = '" + tfNom.getText() + "', PrenomSkipper = '" +tfPrenom.getText() + "'";
		    	requete += " Where NomSkipper like '" + table.getValueAt(table.getSelectedRow(), 0)  +"'";
		    	requete += " And PrenomSkipper = '" + table.getValueAt(table.getSelectedRow(), 1) + "'";
				try {
					conn = DriverManager.getConnection(cheminBdd);
			    	Statement s = conn.createStatement();
			    	s.executeUpdate(requete);
			    	conn.close();
			    	s.close();
			    	javax.swing.JOptionPane.showMessageDialog(null,"Le skipper a était modifié avec succès !");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				String[] entetes = {"Nom", "Prenom"};
				Object[][] data = {};
				DefaultTableModel modele = new DefaultTableModel(data, entetes);
				table.setModel(modele);				
		    	requete = "Select * From Skipper";
				try {
					conn = DriverManager.getConnection(cheminBdd);
			    	Statement s = conn.createStatement();
			    	ResultSet rs = s.executeQuery(requete);
			    	while (rs.next()) {
			    		modele.addRow(new Object[] {rs.getString(1), rs.getString(2)});
			    	}
			    	conn.close();
			    	s.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			
		});
		btnValider.setBounds(415, 250, 89, 23);
		panel.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(565, 250, 89, 23);
		panel.add(btnAnnuler);
		
		frmModifierSkipper.setVisible(true);
	}
}