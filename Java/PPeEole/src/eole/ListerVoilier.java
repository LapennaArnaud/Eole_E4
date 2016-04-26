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
import javax.swing.JScrollPane;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

public class ListerVoilier extends Outils {
	private JTable table;
	public ListerVoilier(){
		final JFrame frmListeDesVoiliers = new JFrame("Créer un Voilier");
		frmListeDesVoiliers.setTitle("Liste des Voiliers");
		frmListeDesVoiliers.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmListeDesVoiliers.setSize(393, 407);
		frmListeDesVoiliers.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmListeDesVoiliers.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 357, 292);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		
		String[] entetes = {"Nom", "Classe", "Rating"};
		Object[][] data = {};
		DefaultTableModel modele = new DefaultTableModel(data, entetes);
		table.setModel(modele);
		// Rajouter une colonne et une ligne (2eme méthode)
		
		Connection conn;
    	String requete = "Select * From Voilier";
		try {
			conn = DriverManager.getConnection(cheminBdd);
	    	Statement s = conn.createStatement();
	    	ResultSet rs = s.executeQuery(requete);
	    	while (rs.next()) {
	    		modele.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3)});
	    	}
	    	conn.close();
	    	s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		scrollPane.setViewportView(table);
		
		frmListeDesVoiliers.setVisible(true);
	}
}