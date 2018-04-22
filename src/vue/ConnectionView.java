/**
 * Fenêtre de connexion
 */
package vue;

/**
 * Librairies importées
 */
import controleur.ConnectionController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modele.EFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSeparator;

/**
 *  Fenêtre de connexion
 * @author Tom Guillermou, Maxime Michel
 */
public class ConnectionView extends EFrame {

    private static final long serialVersionUID = 1L;
    
    // Contrôleur de la fenêtre de connexion
    private final ConnectionController connectionController;
    
    // Eléments graphiques de la fenêtre de connexion
    private final JLabel labelDatabase;
    private final JLabel labelUser;
    private final JLabel labelPassword;
    private final JButton buttonConnection;
    private final JSeparator separator_1;
    
    // Entrées utilisateur de la fenêtre de connexion
    private final JTextField fieldDatabase;
    private final JTextField fieldUser;
    private final JTextField fieldPassword;
    
    /**
     * Constructeur surchargé
     * @param connectionController Contrôleur associé à la fenêtre
     * @param title Titre de la fenêtre 
     * @param height Hauteur de la fenêtre
     * @param width Largeur de la fenêtre
     * @param actionOnClose Action à effectuer lors de la fermeture de la fenêtre
     */
    public ConnectionView(ConnectionController connectionController, String title, int height, int width, int actionOnClose) {
        // Appel du constructeur de la classe mère
        super(title, height, width, actionOnClose);
        
        // Lie le contrôleur passé en paramètre au contrôleur de la classe
        this.connectionController = connectionController;
        
        // Look and feel natif de l'OS hôte
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        getContentPane().setLayout(new MigLayout("", "[74.00px][164.00,grow,fill]", "[30.00px][][30.00px][][21.00][40.00,grow]"));
        
        // Initialise les composants
        labelDatabase = new JLabel("Base de données :");
        getContentPane().add(labelDatabase, "cell 0 0,alignx right");
        fieldDatabase = new JTextField();
        fieldDatabase.setText("hopital");
        getContentPane().add(fieldDatabase, "cell 1 0,growx");
        
        separator_1 = new JSeparator();
        getContentPane().add(separator_1, "cell 0 1 2 1,growx");
        labelUser = new JLabel("Utilisateur :");
        getContentPane().add(labelUser, "cell 0 2,alignx right");
        fieldUser = new JTextField();
        fieldUser.setText("root");
        getContentPane().add(fieldUser, "cell 1 2,growx");
        labelPassword = new JLabel("Mot de passe :");
        getContentPane().add(labelPassword, "cell 0 3,alignx right");
        fieldPassword = new JPasswordField();
        getContentPane().add(fieldPassword, "cell 1 3,growx");
        buttonConnection = new JButton("Connexion");
        getContentPane().add(buttonConnection, "cell 0 5 2 1,grow");
        
        // Ajout de l'ActionListener du bouton de connexion
        buttonConnection.addActionListener(new ButtonConnectionListener());
        
    }
    
    /**
     * Classe interne implémentant l'interface ActionListener
     */
    class ButtonConnectionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            // Récupère les données entrées par l'utilisateur
            String database = fieldDatabase.getText();
            String user = fieldUser.getText();
            String password = fieldPassword.getText();
            
            // Comportement du bouton de connexion
            connectionController.ButtonConnectionPressed(database, user, password);
            
            // Ferme la fenêtre de connexion si la fenêtre de gestion est visible
            if(connectionController.isManagerViewVisible()) {
                dispose();
            }
        }
    }
    
}
