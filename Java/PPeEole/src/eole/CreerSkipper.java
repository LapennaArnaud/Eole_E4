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

public class CreerSkipper extends Outils{
	private JTextField tfNom;
	private JTextField tfPrenom;
	public CreerSkipper(){
		final JFrame frmCreerUnSkipper = new JFrame("Créer un Skipper");
		frmCreerUnSkipper.setTitle("Créer un Skipper");
		frmCreerUnSkipper.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCreerUnSkipper.setSize(279, 337);
		frmCreerUnSkipper.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmCreerUnSkipper.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(50, 53, 46, 14);
		panel.add(lblNom);
		
		tfNom = new JTextField();
		tfNom.setBounds(106, 50, 86, 20);
		panel.add(tfNom);
		tfNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Prénom");
		lblPrenom.setBounds(50, 92, 46, 14);
		panel.add(lblPrenom);
		
		tfPrenom = new JTextField();
		tfPrenom.setBounds(106, 89, 86, 20);
		panel.add(tfPrenom);
		tfPrenom.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfNom.getText().isEmpty()){
					javax.swing.JOptionPane.showMessageDialog(null,"Le nom doit être correctement renseigné !");
				}else if(tfPrenom.getText().isEmpty()){
					javax.swing.JOptionPane.showMessageDialog(null,"Le prénom doit être correctement renseigné !");
				}else{
			    	Connection conn;
			    	String requete = "INSERT INTO Skipper(NomSkipper, PrenomSkipper) VALUES ('" + tfNom.getText() + "', '" +tfPrenom.getText()+ "')";
					try {
						conn = DriverManager.getConnection(cheminBdd);
				    	Statement s = conn.createStatement();
				    	s.executeUpdate(requete);
				    	conn.close();
				    	s.close();
				    	javax.swing.JOptionPane.showMessageDialog(null,"Le Skipper a été ajouté avec succès !");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnValider.setBounds(10, 260, 89, 23);
		panel.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {
				frmCreerUnSkipper.dispose();
			}
		});
		btnAnnuler.setBounds(160, 260, 89, 23);
		panel.add(btnAnnuler);
		frmCreerUnSkipper.setVisible(true);
	}
}
