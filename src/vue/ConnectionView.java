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

/**
 *  Design-pattern Singleton
 * @author Tom
 */
public class ConnectionView extends EFrame {
    
    // Contrôleur de la fenêtre de connexion
    private final ConnectionController conController;
    
    // Panneau contenant des composants graphiques
    private final JPanel boxComponents;
    
    // Labels database, user, password
    private final JLabel labelDatabase;
    private final JLabel labelUser;
    private final JLabel labelPassword;
    
    // Text fields database, user, password
    private final JTextField fieldDatabase;
    private final JTextField fieldUser;
    private final JTextField fieldPassword;
    
    private final EButton buttonConnection;
    
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
        
        // Ajoute un BorderLayout au panneau principal
        this.setLayout(new BorderLayout());
        
        boxComponents = new JPanel();
        boxComponents.setLayout(new GridLayout(3, 2));
        
        // Initialise les composants
        labelDatabase = new JLabel("Database :");
        fieldDatabase = new JTextField();
        labelUser = new JLabel("User :");
        fieldUser = new JTextField();
        labelPassword = new JLabel("Password :");
        fieldPassword = new JTextField();
        
        // Création du bouton de connexion
        buttonConnection = new EButton("Connexion");
        // Ajoute un ActionListener adapté au bouton de connexion
        buttonConnection.addActionListener(new ButtonConnectionListener());
        
        // Ajout des composants à leur panneau associé
        boxComponents.add(labelDatabase);
        boxComponents.add(fieldDatabase);
        boxComponents.add(labelUser);
        boxComponents.add(fieldUser);
        boxComponents.add(labelPassword);
        boxComponents.add(fieldPassword);
        
        // Ajout du panneau composants au panneau principal
        this.getContentPane().add(boxComponents, BorderLayout.CENTER);
        // Ajout du bouton au panneau principal
        this.getContentPane().add(buttonConnection, BorderLayout.SOUTH);
        
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
