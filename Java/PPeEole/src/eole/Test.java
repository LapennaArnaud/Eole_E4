package eole;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//CTRL + SHIFT + O pour générer les imports
public class Test extends JFrame{

  public Test(){
    super("Test de Drag'n Drop");
    setSize(300, 200);
      
    JPanel pan = new JPanel();
    pan.setBackground(Color.white);
    pan.setLayout(new BorderLayout());
      
    //Notre textearea avec son contenu déplaçable
    JTextArea label = new JTextArea("Texte déplaçable !");
    label.setPreferredSize(new Dimension(300, 130));
    //--------------------------------------------------
    //C'est cette instruction qui permet le déplacement de son contenu
    label.setDragEnabled(true);
    //--------------------------------------------------
      
    pan.add(new JScrollPane(label), BorderLayout.NORTH);
      
    JPanel pan2 = new JPanel();
    pan2.setBackground(Color.white);
    pan2.setLayout(new BorderLayout());
      
    //On crée le premier textfield avec contenu déplaçable
    JTextField text = new JTextField();
    //--------------------------------------------------
    text.setDragEnabled(true);
    //--------------------------------------------------
    //Et le second, sans
    JTextField text2 = new JTextField();
      
    pan2.add(text2, BorderLayout.SOUTH);
    pan2.add(text, BorderLayout.NORTH);
      
    pan.add(pan2, BorderLayout.SOUTH);
    add(pan, BorderLayout.CENTER);
      
    setVisible(true);
  }

  public static void main(String[] args){
    new Test();
  }  
}