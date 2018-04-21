/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.ConnectionController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modele.EButton;
import modele.EFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSeparator;

/**
 *  Design-pattern Singleton
 * @author Tom
 */
public class ConnectionView extends EFrame {
    
    // Contrôleur de la fenêtre de connexion
    private final ConnectionController conController;
    
    // Labels database, user, password
    private final JLabel labelDatabase;
    private final JLabel labelUser;
    private final JLabel labelPassword;
    
    // Text fields database, user, password
    private final JTextField fieldDatabase;
    private final JTextField fieldUser;
    private final JTextField fieldPassword;
    
    private final EButton buttonConnection;
    private JSeparator separator_1;
    
    public ConnectionView(ConnectionController conController, String title, int height, int width) {
        // Appel du constructeur de la classe mère
        super(title, height, width);
        
        // Look and feel natif de l'OS hôte
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Lie le contrôleur passé en paramètre au contrôleur de la classe
        this.conController = conController;
        getContentPane().setLayout(new MigLayout("", "[74.00px][164.00,grow,fill]", "[30.00px][][30.00px][][21.00][40.00]"));
        
        // Initialise les composants
        labelDatabase = new JLabel("Database :");
        getContentPane().add(labelDatabase, "cell 0 0,alignx right");
        fieldDatabase = new JTextField();
        fieldDatabase.setText("hopital");
        getContentPane().add(fieldDatabase, "cell 1 0,growx");
        
        separator_1 = new JSeparator();
        getContentPane().add(separator_1, "cell 0 1 2 1,growx");
        labelUser = new JLabel("User :");
        getContentPane().add(labelUser, "cell 0 2,alignx right");
        fieldUser = new JTextField();
        fieldUser.setText("root");
        getContentPane().add(fieldUser, "cell 1 2,growx");
        labelPassword = new JLabel("Password :");
        getContentPane().add(labelPassword, "cell 0 3,alignx right");
        fieldPassword = new JTextField();
        getContentPane().add(fieldPassword, "cell 1 3,growx");
        
        // Création du bouton de connexion
        buttonConnection = new EButton("Connexion");
        // Ajoute un ActionListener adapté au bouton de connexion
        buttonConnection.addActionListener(new ButtonConnectionListener());
        // Ajout du bouton au panneau principal
        this.getContentPane().add(buttonConnection, "cell 0 5 2 1,grow");
        
    }
        
    class ButtonConnectionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            // Récupère les données des champs de saisie de texte
            String database = fieldDatabase.getText();
            String user = fieldUser.getText();
            String password = fieldPassword.getText();
            
            // Appel à la fonction de connexion du contrôleur
            conController.ButtonConnectionPressed(database, user, password);
            
            // Libère la fenêtre de connexion si la fenêtre suivante est affichée
            if(conController.isNextFrameShown()) {
                dispose();
            }
        }
    }
    
}
