package eole;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListerRegate extends Outils{
	private JTable table;
	public ListerRegate(){
		final JFrame frmListeDesRegates = new JFrame("Créer un Voilier");
		frmListeDesRegates.setTitle("Liste des R\u00E9gates");
		frmListeDesRegates.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmListeDesRegates.setSize(393, 407);
		frmListeDesRegates.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmListeDesRegates.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 357, 292);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		
		String[] entetes = {"Nom", "Distance", "Date", "Id"};
		Object[][] data = {};
		DefaultTableModel modele = new DefaultTableModel(data, entetes);
		table.setModel(modele);
		
		Connection conn;
    	String requete = "Select * From Regate";
		try {
			conn = DriverManager.getConnection(cheminBdd);
	    	Statement s = conn.createStatement();
	    	ResultSet rs = s.executeQuery(requete);
	    	while (rs.next()) {
	    		modele.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3).substring(0, 10), rs.getString(4)});
	    	}
	    	conn.close();
	    	s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		scrollPane.setViewportView(table);
		
		frmListeDesRegates.setVisible(true);
	}
}