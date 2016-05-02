package eole;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PopUpSkipperVoilier extends Outils{
	
	private JTable table;
	private String nomRegate;
	
	public PopUpSkipperVoilier(String NumRegate){
		final JFrame frmListeDesRegates = new JFrame("Créer un Voilier");
		frmListeDesRegates.setTitle("Liste des R\u00E9gates");
		frmListeDesRegates.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmListeDesRegates.setSize(573, 400);
		frmListeDesRegates.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmListeDesRegates.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 228, 292);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		
		String[] entetes = {"Voilier", "Skipper"};
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
	    		modeleListe.addRow(new Object[] {rs.getString(2), rs.getString(3)});
	    	}
	    	conn.close();
	    	s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		scrollPane.setViewportView(table);
		
		JComboBox<String> cmbVoilier = new JComboBox<String>();
    	requete = "Select * From Voilier";
		try {
			conn = DriverManager.getConnection(cheminBdd);
	    	Statement s = conn.createStatement();
	    	ResultSet rs = s.executeQuery(requete);
	    	while (rs.next()) {
	    		cmbVoilier.addItem(rs.getString(1));
	    	}
	    	conn.close();
	    	s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cmbVoilier.setBounds(271, 38, 113, 28);
		panel.add(cmbVoilier);
		
		
		JComboBox<String> cmbSkipper = new JComboBox<>();
		requete = "Select * From Skipper";
		try {
			conn = DriverManager.getConnection(cheminBdd);
	    	Statement s = conn.createStatement();
	    	ResultSet rs = s.executeQuery(requete);
	    	while (rs.next()) {
	    		cmbSkipper.addItem(rs.getString(1) + " " + rs.getString(2));
	    	}
	    	conn.close();
	    	s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cmbSkipper.setBounds(407, 40, 113, 24);
		panel.add(cmbSkipper);
		
		// récupération du nom de régate 
		requete = "Select NomRegate FROM Regate WHERE NumRegate ="+NumRegate;
		try {
			conn = DriverManager.getConnection(cheminBdd);
	    	Statement s = conn.createStatement();
	    	ResultSet rs = s.executeQuery(requete);
	    	while (rs.next()) {
	    		nomRegate=rs.getString(1);
	    	}
	    	conn.close();
	    	s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		JButton btnInscrire = new JButton("Inscrire");
		btnInscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		    	Connection conn;
		    	String sousRequeteVoilier = "Select NumVoilier From Voilier Where NomVoilier = '" + cmbVoilier.getSelectedItem().toString() +"'";
		    	String sousRequeteSkipper = "Select NumSkipper From Skipper Where NomSkipper = '" + cmbSkipper.getSelectedItem().toString().split(" ")[0] + "'";
		    	sousRequeteSkipper += " And PrenomSkipper = '" + cmbSkipper.getSelectedItem().toString().split(" ")[1] + "'";
		    	String resVoilier = "";
		    	String resSkipper = "";
				try {
					conn = DriverManager.getConnection(cheminBdd);
			    	Statement s = conn.createStatement();
			    	ResultSet rsVoilier = s.executeQuery(sousRequeteVoilier);
			    	rsVoilier.next();
			    	resVoilier = rsVoilier.getString(1);
			    	ResultSet rsSkipper = s.executeQuery(sousRequeteSkipper);
			    	rsSkipper.next();
			    	resSkipper = rsSkipper.getString(1);
			    	conn.close();
			    	s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
		    	String requete = "INSERT INTO Participer(NumRegate, NumVoilier, NumSkipper, TmpsReel)";
		    	requete += "VALUES (" + NumRegate +", '" + resVoilier + "', '" + resSkipper + "', 0)";
				System.out.println(requete);
		    	try {
					conn = DriverManager.getConnection(cheminBdd);
			    	Statement s = conn.createStatement();
			    	s.executeUpdate(requete);
			    	conn.close();
			    	s.close();
			    	javax.swing.JOptionPane.showMessageDialog(null,"Couple Skipper/Voilier inscri avec succès !");
			    	modeleListe.addRow(new Object[] {resVoilier, resSkipper});
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnInscrire.setBounds(248, 173, 89, 23);
		panel.add(btnInscrire);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {
	      		frmListeDesRegates.dispose();
	      	}
	      });
		btnQuitter.setBounds(458, 173, 89, 23);
		panel.add(btnQuitter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rep = JOptionPane.showConfirmDialog(null,"Voulez vous vraiment supprimer ce couple ?");
				if (rep == 1 || rep == 2){
					javax.swing.JOptionPane.showMessageDialog(null,"Ce couple n'a pas été supprimé !");
				}
				if (rep == 0){
					Connection conn;
					String requete = "Delete from Participer Where NumRegate =" + NumRegate;
					requete += " And NumVoilier = " + table.getValueAt(table.getSelectedRow(), 0);
					requete += " And NumSkipper = " + table.getValueAt(table.getSelectedRow(), 1);
					System.out.println(requete);
					try {
						conn = DriverManager.getConnection(cheminBdd);
				    	Statement s = conn.createStatement();
				    	s.executeUpdate(requete);
				    	conn.close();
				    	s.close();
				    	javax.swing.JOptionPane.showMessageDialog(null,"Ce Couple a été supprimé avec succès !");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					
					String[] entetes = {"Voilier", "Skipper"};
					Object[][] data = {};
					DefaultTableModel modeleListe = new DefaultTableModel(data, entetes);
					table.setModel(modeleListe);
					
			    	requete = "Select * From Participer Where NumRegate = " + NumRegate;
					try {
						conn = DriverManager.getConnection(cheminBdd);
				    	Statement s = conn.createStatement();
				    	ResultSet rs = s.executeQuery(requete);
				    	while (rs.next()) {
				    		modeleListe.addRow(new Object[] {rs.getString(2), rs.getString(3)});
				    	}
				    	conn.close();
				    	s.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnSupprimer.setBounds(347, 173, 101, 23);
		panel.add(btnSupprimer);
		
		
		JLabel lblNomRegate = new JLabel("regate");
		lblNomRegate.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomRegate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNomRegate.setForeground(Color.DARK_GRAY);
		lblNomRegate.setText("Régate : "+nomRegate);
		lblNomRegate.setBounds(311, 110, 137, 24);
		panel.add(lblNomRegate);
		
		
		frmListeDesRegates.setVisible(true);
	}
}