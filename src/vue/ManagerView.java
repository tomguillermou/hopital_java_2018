/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.ManagerController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import modele.EButton;
import modele.EFrame;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Tom
 */
public class ManagerView extends EFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Contrôleur de la vue
    private final ManagerController managerController;
    
    // Boutons présents dans la fenêtre
    private final EButton buttonReporting;
    private final EButton buttonSearch;
    private final EButton buttonUpdate;
    
    /**
     *  Constructeur surchargé
     * @param managerController
     * @param title
     * @param height
     * @param width
     * @param actionOnClose
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
			myPicture = ImageIO.read(new File("res\\logo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        getContentPane().add(picLabel, "cell 0 0");
        
        // Bouton Update
        buttonUpdate = new EButton("Modifier la base de données");
        buttonUpdate.addActionListener(new ButtonUpdateListener());
        
        // Bouton Search
        buttonSearch = new EButton("Recherche dans la base de données");
        buttonSearch.addActionListener(new ButtonSearchListener());
        
        // Bouton Reporting
        buttonReporting = new EButton("Analyse des données");
        buttonReporting.addActionListener(new ButtonReportingListener());
        this.getContentPane().add(buttonReporting, "cell 0 1,grow");
        this.getContentPane().add(buttonSearch, "cell 0 2,alignx center,growy");
        this.getContentPane().add(buttonUpdate, "cell 0 3,grow");
    }
    
    /*
    * Listener du bouton Reporting
    */
    class ButtonReportingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            managerController.ButtonReportingPressed();
        }
    }
    
    /*
    * Listener du bouton Search
    */
    class ButtonSearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            managerController.ButtonSearchPressed();
        }
    }
    
    /*
    * Listener du bouton Update
    */
    class ButtonUpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            managerController.ButtonUpdatePressed();
        }
    }
    
}
