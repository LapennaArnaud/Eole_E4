package eole;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Window.Type;

public class PopUpLancerRegate extends Outils{
	private JTable table;
	private JTextField tfHeure;
	private JTextField tfMinute;
	private JTextField tfSeconde;
	public PopUpLancerRegate(String NumRegate){
		final JFrame frmListeDesRegates = new JFrame("Créer un Voilier");
		frmListeDesRegates.setTitle("Voiliers en course");
		frmListeDesRegates.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmListeDesRegates.setSize(573, 346);
		frmListeDesRegates.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmListeDesRegates.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 250, 292);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		
		String[] entetes = {"Voilier", "Skipper", "Temps"};
		Object[][] data = {};
		DefaultTableModel modeleListe = new DefaultTableModel(data, entetes);
		table.setModel(modeleListe);
		
		Connection conn;
    	String requete = "Select * From Participer Where NumRegate = " + NumRegate;
		try {
			conn = DriverManager.getConnection(cheminBdd);
	    	Statement s = conn.createStatement();
	    	ResultSet rs = s.executeQuery(requete);
	    	while (rs.next()) {
	    		modeleListe.addRow(new Object[] {rs.getString(2), rs.getString(3), rs.getString(4)});
	    	}
	    	conn.close();
	    	s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		scrollPane.setViewportView(table);
		
		tfHeure = new JTextField();
		tfHeure.setBounds(270, 90, 44, 20);
		panel.add(tfHeure);
		tfHeure.setColumns(10);
		
		tfMinute = new JTextField();
		tfMinute.setColumns(10);
		tfMinute.setBounds(324, 90, 44, 20);
		panel.add(tfMinute);
		
		tfSeconde = new JTextField();
		tfSeconde.setColumns(10);
		tfSeconde.setBounds(378, 90, 44, 20);
		panel.add(tfSeconde);
		
		JLabel lblHeure = new JLabel("Heure");
		lblHeure.setBounds(268, 67, 46, 14);
		panel.add(lblHeure);
		
		JLabel lblMinute = new JLabel("Minute");
		lblMinute.setBounds(324, 67, 46, 14);
		panel.add(lblMinute);
		
		JLabel lblSeconde = new JLabel("Seconde");
		lblSeconde.setBounds(376, 67, 46, 14);
		panel.add(lblSeconde);
		
		JButton btnNoterTemps = new JButton("Noter temps");
		btnNoterTemps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tmps = tempsReel(Integer.valueOf(tfHeure.getText()), Integer.valueOf(tfMinute.getText()), Integer.valueOf(tfSeconde.getText()));
				int rating = 0;
				int distance = 0;
				Connection conn;
				String sousRequete = "SELECT TmpsReel, Rating, Distance FROM Participer p, Voilier v, Regate r";
				sousRequete += " Where p.NumVoilier = v.NumVoilier And p.NumRegate = r.NumRegate";
				sousRequete += " And NumVoilier = '" + table.getValueAt(table.getSelectedRow(), 0) + "'";
				sousRequete += " And NumSkipper = '" + table.getValueAt(table.getSelectedRow(), 1) + "'";
				sousRequete += " and p.NumRegate = " + NumRegate + "ORDER BY TmpsReel DESC";
				try {
					conn = DriverManager.getConnection(cheminBdd);
			    	Statement s = conn.createStatement();
			    	ResultSet rs = s.executeQuery(requete);
			    	conn.close();
			    	s.close();
			    	rs.next();
			    	rating = rs.getInt(2);
			    	distance = rs.getInt(3);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
		    	String requete = "UPDATE Participer SET TmpsReel = " + tmps;
		    	requete += " , TmpsCompense = " + tempsCompensé(tmps, rating, distance);
		    	requete += " Where NumRegate = " + NumRegate;
		    	requete += " And NumVoilier = '" + table.getValueAt(table.getSelectedRow(), 0) + "'";
				requete += " And NumSkipper = '" + table.getValueAt(table.getSelectedRow(), 1) + "'";
				try {
					conn = DriverManager.getConnection(cheminBdd);
			    	Statement s = conn.createStatement();
			    	s.executeUpdate(requete);
			    	conn.close();
			    	s.close();
			    	javax.swing.JOptionPane.showMessageDialog(null,"Le temps a était noté avec succès !");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
				
				
				String[] entetes = {"Voilier", "Skipper", "Temps"};
				Object[][] data = {};
				DefaultTableModel modeleListe = new DefaultTableModel(data, entetes);
				table.setModel(modeleListe);
				
		    	requete = "Select * From Participer Where NumRegate = " + NumRegate;
				try {
					conn = DriverManager.getConnection(cheminBdd);
			    	Statement s = conn.createStatement();
			    	ResultSet rs = s.executeQuery(requete);
			    	while (rs.next()) {
			    		modeleListe.addRow(new Object[] {rs.getString(2), rs.getString(3), rs.getString(4)});
			    	}
			    	conn.close();
			    	s.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
				tfHeure.setText("");
				tfMinute.setText("");
				tfSeconde.setText("");				
			}
		});
		btnNoterTemps.setBounds(270, 182, 110, 23);
		panel.add(btnNoterTemps);
		
		JButton btnTerminer = new JButton("Terminer");
		btnTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn;				
		    	String requete = "UPDATE Regate SET estTerminé = True";
		    	requete += " Where NumRegate = " + NumRegate;
				try {
					conn = DriverManager.getConnection(cheminBdd);
			    	Statement s = conn.createStatement();
			    	s.executeUpdate(requete);
			    	conn.close();
			    	s.close();
			    	javax.swing.JOptionPane.showMessageDialog(null,"Régate terminé !");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnTerminer.setBounds(406, 182, 89, 23);
		panel.add(btnTerminer);
		
		frmListeDesRegates.setVisible(true);
	}
}