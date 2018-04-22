/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

/**
 * Librairies importées
 */
import controleur.ManagerController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import modele.EFrame;
import net.miginfocom.swing.MigLayout;

/**
 * Fenêtre de gestion des modules de l'application
 * @author Tom Guillermou, Maxime Michel
 */
public class ManagerView extends EFrame {
    
    private static final long serialVersionUID = 1L;

    // Contrôleur de la vue de gestion des modules
    private final ManagerController managerController;
    
    // Eléments graphiques de la fenêtre de gestion 
    private final JButton buttonReporting;
    private final JButton buttonSearch;
    private final JButton buttonUpdate;
    
    /**
     *  Constructeur surchargé
     * @param managerController
     * @param title Titre de la fenêtre 
     * @param height Hauteur de la fenêtre
     * @param width Largeur de la fenêtre
     * @param actionOnClose Action à effectuer lors de la fermeture de la fenêtre
     */
    public ManagerView(ManagerController managerController, String title, int height, int width, int actionOnClose) {
        
        // Appel le constructeur de la classe mère
        super(title, height, width, actionOnClose);
        
        // Lie le contrôleur de la fenêtre avec celui passé en paramètre
        this.managerController = managerController;
        
        getContentPane().setLayout(new MigLayout("", "[198.00px,grow,fill]", "[88px,center][grow][grow][grow]"));
        /// WIP 
        //Image d'accueil
        BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("res/logo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        getContentPane().add(picLabel, "cell 0 0");
        
        // Bouton Update
        buttonUpdate = new JButton("Modifier la base de données");
        buttonUpdate.addActionListener(new ButtonUpdateListener());
        
        // Bouton Search
        buttonSearch = new JButton("Recherche dans la base de données");
        buttonSearch.addActionListener(new ButtonSearchListener());
        
        // Bouton Reporting
        buttonReporting = new JButton("Analyse des données");
        buttonReporting.addActionListener(new ButtonReportingListener());
        this.getContentPane().add(buttonReporting, "cell 0 1,grow");
        this.getContentPane().add(buttonSearch, "cell 0 2,alignx center,growy");
        this.getContentPane().add(buttonUpdate, "cell 0 3,grow");
    }
    
    /**
     * Classe interne implémentant l'interface ActionListener
     */
    class ButtonReportingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            managerController.ButtonReportingPressed();
        }
    }
    
    /**
     * Classe interne implémentant l'interface ActionListener
     */
    class ButtonSearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            managerController.ButtonSearchPressed();
        }
    }
    
    /**
     * Classe interne implémentant l'interface ActionListener
     */
    class ButtonUpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            managerController.ButtonUpdatePressed();
        }
    }
    
}
