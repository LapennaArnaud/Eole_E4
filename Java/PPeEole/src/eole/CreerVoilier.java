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

public class CreerVoilier extends Outils{
	private JTextField tfNom;
	private JTextField tfClasse;
	private JTextField tfRating;
	public CreerVoilier(){
		final JFrame frame = new JFrame("Cr�er un Voilier");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(279, 337);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(50, 53, 46, 14);
		panel.add(lblNom);
		
		tfNom = new JTextField();
		tfNom.setBounds(106, 50, 86, 20);
		panel.add(tfNom);
		tfNom.setColumns(10);
		
		JLabel lblClasse = new JLabel("Classe");
		lblClasse.setBounds(50, 92, 46, 14);
		panel.add(lblClasse);
		
		tfClasse = new JTextField();
		tfClasse.setBounds(106, 89, 86, 20);
		panel.add(tfClasse);
		tfClasse.setColumns(10);
		
		JLabel lblRating = new JLabel("Rating");
		lblRating.setBounds(50, 134, 46, 14);
		panel.add(lblRating);
		
		tfRating = new JTextField();
		tfRating.setBounds(106, 131, 86, 20);
		panel.add(tfRating);
		tfRating.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfNom.getText().isEmpty()){
					javax.swing.JOptionPane.showMessageDialog(null,"Le nom doit �tre renseign� !");
				}else if(estEntier(tfClasse.getText())){
					javax.swing.JOptionPane.showMessageDialog(null,"La classe doit �tre correctement renseign� !");
				}else if(estEntier(tfRating.getText())){
					javax.swing.JOptionPane.showMessageDialog(null,"Le rating doit �tre correctement renseign� !");
				}else{
			    	Connection conn;
			    	String requete = "INSERT INTO VOILIER(NOMVOILIER, CLASSE, RATING) VALUES ('" + tfNom.getText() + "', " +tfClasse.getText()+ ", " +tfRating.getText() + ")";
					System.out.println(requete);
			    	try {
						conn = DriverManager.getConnection(cheminBdd);
				    	Statement s = conn.createStatement();
				    	s.executeUpdate(requete);
				    	conn.close();
				    	s.close();
				    	javax.swing.JOptionPane.showMessageDialog(null,"Le voilier a �t� ajout� avec succ�s !");
				    	tfNom.setText("");
				    	tfClasse.setText("");
				    	tfRating.setText("");
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
				//System.exit(JFrame.DISPOSE_ON_CLOSE);
				frame.dispose();
			}
		});
		btnAnnuler.setBounds(160, 260, 89, 23);
		panel.add(btnAnnuler);
		frame.setVisible(true);
	}
}