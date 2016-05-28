package eole;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang.CharRange;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Accueil {	
	private static JTable tblResultat;
	static JList listRegates;
	static DefaultListModel model = new DefaultListModel();
	
	public static void main(String[] args) {
      JFrame f = new JFrame("A JFrame");
      f.setTitle("Accueil");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setSize(1009, 706);
      f.setLocation(200,10);
      f.getContentPane().setLayout(new BorderLayout(0, 0));
      
      JPanel panel = new JPanel();
    		  
      f.getContentPane().add(panel, BorderLayout.CENTER);
      
      // création des menu toollbar
      
      JMenuBar menuBar = new JMenuBar();
      f.setJMenuBar(menuBar);
      
      JMenu mnVoilier = new JMenu("Voilier");
      menuBar.add(mnVoilier);
      
      JMenuItem mntmCrerUnVoilier = new JMenuItem("Créer un Voilier");
      mntmCrerUnVoilier.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		new CreerVoilier();
      	}
      });
      mnVoilier.add(mntmCrerUnVoilier);
      
      JMenuItem mntmModifierVoilier = new JMenuItem("Modifier Voilier");
      mntmModifierVoilier.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new ModifierVoilier();
        	}
        });
      mnVoilier.add(mntmModifierVoilier);
      
      JMenuItem mntmSupprimerVoilier = new JMenuItem("Supprimer Voilier");
      mntmSupprimerVoilier.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		new SupprimerVoilier();
      	}
      });
      mnVoilier.add(mntmSupprimerVoilier);
      
      JMenuItem mntmListeVoilier = new JMenuItem("Liste Voilier");
      mntmListeVoilier.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		new ListerVoilier();
      	}
      });
      mnVoilier.add(mntmListeVoilier);
      
      JMenu mnRgate = new JMenu("Régate");
      menuBar.add(mnRgate);
      
      JMenuItem mntmCrerUneRgate = new JMenuItem("Créer une Régate");
      mntmCrerUneRgate.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		new CreerRegate();
      	}
      });
      mnRgate.add(mntmCrerUneRgate);
      
      JMenuItem mntmModifierRgate = new JMenuItem("Modifier R\u00E9gate");
      mntmModifierRgate.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		new ModifierRegate();
      	}
      });
      mnRgate.add(mntmModifierRgate);
      
      JMenuItem mntmSupprimerRgate = new JMenuItem("Supprimer R\u00E9gate");
      mntmSupprimerRgate.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		new SupprimerRegate();
      	}
      });
      mnRgate.add(mntmSupprimerRgate);
      
      JMenuItem mntmListerRgate = new JMenuItem("Lister R\u00E9gate");
      mntmListerRgate.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		new ListerRegate();
      	}
      });
      mnRgate.add(mntmListerRgate);
      
      JMenuItem mntmInscrireVoilierSkipper = new JMenuItem("Inscrire Voilier/Skipper");
      mntmInscrireVoilierSkipper.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		new InscrireVoilierSkipper();
      	}
      });
      mnRgate.add(mntmInscrireVoilierSkipper);
      
      JMenuItem mntmLancerRgate = new JMenuItem("Lancer R\u00E9gate");
      mntmLancerRgate.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		new LancerRegate();
      	}
      });
      mnRgate.add(mntmLancerRgate);
      
      JMenuItem mntmRsultat = new JMenuItem("R\u00E9sultat");
      mntmRsultat.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		new ListerResultat();
      	}
      });
      mnRgate.add(mntmRsultat);
      
      JMenu mnSkipper = new JMenu("Skipper");
      menuBar.add(mnSkipper);
      
      JMenuItem mntmAjouterSkipper = new JMenuItem("Ajouter Skipper");
      mntmAjouterSkipper.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		new CreerSkipper();
      	}
      });
      mnSkipper.add(mntmAjouterSkipper);
      
      JMenuItem mntmModifier = new JMenuItem("Modifier Skipper");
      mntmModifier.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		new ModifierSkipper();
      	}
      });
      mnSkipper.add(mntmModifier);
      
      JMenuItem mntmSupprimerSkipper = new JMenuItem("Supprimer Skipper");
      mntmSupprimerSkipper.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		new SupprimerSkipper();
      	}
      });
      mnSkipper.add(mntmSupprimerSkipper);
      
      JMenuItem mntmLister = new JMenuItem("Liste des Skipper");
      mntmLister.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		new ListerSkipper();
      	}
      });
      mnSkipper.add(mntmLister);
      panel.setLayout(null);
      
      // contenu de la fenetre acceuil
      
      JLabel lblTitreFenetre = new JLabel("R\u00E9gate");
      lblTitreFenetre.setForeground(new Color(220, 20, 60));
      lblTitreFenetre.setFont(new Font("Arial", Font.BOLD, 31));
      lblTitreFenetre.setBackground(Color.WHITE);
      lblTitreFenetre.setBounds(427, 88, 151, 46);
      panel.add(lblTitreFenetre);
      
      JLabel lbResultat = new JLabel("Resultats :");
      lbResultat.setBounds(40, 137, 78, 14);
      panel.add(lbResultat);
      
      /////////// tableau !!!!!!!!!!!!!!!///////////////////
      
      JScrollPane scrollPaneResultat = new JScrollPane();
      
      tblResultat = new JTable();
      tblResultat.setRowSelectionAllowed(false);
      tblResultat.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      
      String[] entetes = {"Classes", "Voiliers","Temps Compensé"};
      Object[][] data = {};
      DefaultTableModel modeleResultat = new DefaultTableModel(data, entetes);
      tblResultat.setModel(modeleResultat);
      //modeleResultat.addRow(new Object[] {"String1","String2","String3"});
      
      scrollPaneResultat.setBounds(40, 161, 272, 362);
      scrollPaneResultat.setViewportView(tblResultat);
      panel.add(scrollPaneResultat);
      
      //////////////////////////////////////////// !!!!!!!!!!!!!!!!
      
      JLabel lblListeRegates = new JLabel("Liste Regates :");
      lblListeRegates.setToolTipText("");
      lblListeRegates.setBounds(675, 137, 92, 14);
      panel.add(lblListeRegates);
      
      Outils outils = new Outils();
      outils.chargeListeRegAcceuil();
      listRegates = new JList(model);
      listRegates.setToolTipText("");
      listRegates.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      listRegates.setBounds(675, 161, 171, 362);
      panel.add(listRegates);
      
      JButton btnCreationRegate = new JButton("Cr\u00E9ation R\u00E9gate");
      btnCreationRegate.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		new CreerRegate();
      	}
      });
      btnCreationRegate.setBounds(405, 220, 151, 23);
      panel.add(btnCreationRegate);
      
      JButton btnModiffRegate = new JButton("Modification R\u00E9gate");
      btnModiffRegate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new ModifierRegate();
        	}
        });
      btnModiffRegate.setBounds(405, 271, 151, 23);
      panel.add(btnModiffRegate);
      
      JButton btnLancerRegate = new JButton("Lancer R\u00E9gate");
      btnLancerRegate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		new LancerRegate();
        	}
        });
      btnLancerRegate.setBounds(405, 320, 151, 23);
      panel.add(btnLancerRegate);
      
      JButton btnResultat = new JButton("R\u00E9sultat");
      btnResultat.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		//new ListerResultat();
      		//System.out.println(listRegates.getSelectedValue());
      		//modeleResultat.addRow(new Object[] {"String1","String2","String3"});
      		modeleResultat.setRowCount(0);
      		
      		Connection conn;
        	String requete = "SELECT p.NumVoilier, TmpsCompense, Classe FROM Participer p, Voilier v";
        	requete += " Where p.NumVoilier = v.NumVoilier and p.NumRegate IN( Select NumRegate FROM Regate Where NomRegate = '" + listRegates.getSelectedValue() + "') ORDER BY TmpsCompense DESC";
        	System.out.println(requete);
    		try {
    			conn = DriverManager.getConnection(Outils.cheminBdd);
    	    	Statement s = conn.createStatement();
    	    	ResultSet rs = s.executeQuery(requete);
    	    	while (rs.next()) {
    	    		modeleResultat.addRow(new Object[] {rs.getString(3),rs.getString(1),rs.getInt(2)});   	    		
    	    	}
    	    	conn.close();
    	    	s.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
      		
      		
      	}
      });
      btnResultat.setBounds(405, 371, 151, 23);
      panel.add(btnResultat);
      
      JButton btnExit = new JButton("Quitter");
      btnExit.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		System.exit(JFrame.DISPOSE_ON_CLOSE);      		
      	}
      });
      btnExit.setBounds(405, 421, 151, 23);
      panel.add(btnExit);
      
      f.setVisible(true);
    }
		
}
