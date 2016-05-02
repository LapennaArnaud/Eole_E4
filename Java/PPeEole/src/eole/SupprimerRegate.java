package eole;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SupprimerRegate extends Outils{
	private JTable table;
	private Statement s;
	public SupprimerRegate(){
		final JFrame frmSupprimerRegate = new JFrame("Cr�er un Voilier");
		frmSupprimerRegate.setTitle("Supprimer Regate");
		frmSupprimerRegate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSupprimerRegate.setSize(393, 407);
		frmSupprimerRegate.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmSupprimerRegate.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 357, 292);
		panel.add(scrollPane);
		
		table = new JTable();
		
		String[] entetes = {"Nom", "Distance", "Date"};
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
	    		modele.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3).substring(0, 10)});
	    	}
	    	conn.close();
	    	s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		scrollPane.setViewportView(table);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rep = JOptionPane.showConfirmDialog(null,"Voulez vous vraiment supprimer cette r�gate ?");
				if (rep == 1 || rep == 2){
					javax.swing.JOptionPane.showMessageDialog(null,"Cette r�gate n'a pas �t� supprim�e !");
				}
				if (rep == 0){
					Connection conn;
					String requete = "Delete from Regate Where NomRegate like '" + table.getValueAt(table.getSelectedRow(), 0)  +"'";
					requete += " And Distance = " + table.getValueAt(table.getSelectedRow(), 1) + "";
					requete += " And DateHeure = #" + table.getValueAt(table.getSelectedRow(), 2) + "#";
					System.out.println(requete);
					try {
						conn = DriverManager.getConnection(cheminBdd);
				    	Statement s = conn.createStatement();
				    	s.executeUpdate(requete);
				    	conn.close();
				    	s.close();
				    	javax.swing.JOptionPane.showMessageDialog(null,"Cette R�gate a �t� supprim�e avec succ�s !");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					
					String[] entetes = {"Nom", "Distance", "Date"};
					Object[][] data = {};
					DefaultTableModel modele = new DefaultTableModel(data, entetes);
					table.setModel(modele);				
			    	requete = "Select * From Regate";
					try {
						conn = DriverManager.getConnection(cheminBdd);
				    	Statement s = conn.createStatement();
				    	ResultSet rs = s.executeQuery(requete);
				    	while (rs.next()) {
				    		modele.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3).substring(0, 10)});
				    	}
				    	conn.close();
				    	s.close();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btnSupprimer.setBounds(38, 324, 94, 23);
		panel.add(btnSupprimer);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		frmSupprimerRegate.dispose();
		      	}
		      });
		btnQuitter.setBounds(237, 324, 89, 23);
		panel.add(btnQuitter);
		
		frmSupprimerRegate.setVisible(true);
	}
}