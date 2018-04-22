package vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import modele.Connexion;
import modele.EFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

/**
 *
 * @author Tom, Maxime
 * @see http://zetcode.com/java/imageicon/
 */
public class SearchView extends EFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Elements graphiques pour la fenêtre de recherche
	private final Container container;
	private final JLabel searchTableLabel = new JLabel("Recherche dans la table :");
	private final JLabel searchColumnLabel = new JLabel("Recherche dans la colonne :");
	private final JLabel searchValueLabel = new JLabel("Avec la valeur :");
	private final JComboBox<?> boxTable;
	private final JComboBox<?> boxColumn;
	private final JTextField fieldValue;
	private final JButton buttonSearch = new JButton("Recherche");
	private JTable table = new JTable();
	private JScrollPane scrollPane = new JScrollPane(table);

	// Modèle de ComboBox pour sélectionner les colonnes des différentes tables de
	// la BDD
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private final DefaultComboBoxModel chambreModel = new DefaultComboBoxModel(
			new String[] { "tout", "code_service", "no_chambre", "surveillant", "nb_lits" });
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private final DefaultComboBoxModel docteurModel = new DefaultComboBoxModel(
			new String[] { "tout", "numero", "specialite" });
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private final DefaultComboBoxModel employeModel = new DefaultComboBoxModel(
			new String[] { "tout", "numero", "nom", "prenom", "adresse", "tel" });
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private final DefaultComboBoxModel hospitalisationModel = new DefaultComboBoxModel(
			new String[] { "tout", "no_malade", "code_service", "no_chambre", "lit" });
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private final DefaultComboBoxModel infirmierModel = new DefaultComboBoxModel(
			new String[] { "tout", "numero", "code_service", "rotation", "salaire" });
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private final DefaultComboBoxModel maladeModel = new DefaultComboBoxModel(
			new String[] { "tout", "numero", "nom", "prenom", "adresse", "tel", "mutuelle" });
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private final DefaultComboBoxModel serviceModel = new DefaultComboBoxModel(
			new String[] { "tout", "code", "nom", "batiment", "directeur" });
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private final DefaultComboBoxModel soigneModel = new DefaultComboBoxModel(
			new String[] { "tout", "no_docteur", "no_malade" });
	private final JSeparator separator = new JSeparator();
	/**
	 * Constructeur surchargé
	 * 
	 * @param tittle
	 * @param height
	 * @param width
	 * @param actionOnClose
	 */
	@SuppressWarnings("unchecked")
	public SearchView(String tittle, int height, int width, int actionOnClose) {

		// Appel le constructeur de la classe mère
		super(tittle, height, width, actionOnClose);

		// Initialise le container & lui ajoute un FlowLayout
		container = this.getContentPane();
		getContentPane()
				.setLayout(new MigLayout("", "[150.00px][218px,grow]", "[][][][35.00px,bottom][][37.00,grow,fill]"));
		getContentPane().add(searchTableLabel, "cell 0 0,grow");

		// ComboBox contenant les choix de recherche que l'ont peut effectuer
		boxTable = new JComboBox<String>(Connexion.getInstance().getTablesNames());
		getContentPane().add(boxTable, "cell 1 0,grow");

		// Ajoute le ActionListener adapté à la ComboBox
		boxTable.addActionListener(new BoxTableListener());
		getContentPane().add(searchColumnLabel, "cell 0 1,grow");

		boxColumn = new JComboBox<Object>();
		getContentPane().add(boxColumn, "cell 1 1,grow");
		boxColumn.setModel(chambreModel);
		getContentPane().add(searchValueLabel, "cell 0 2,grow");
		fieldValue = new JTextField();
		getContentPane().add(fieldValue, "cell 1 2,grow");

		buttonSearch.setIcon(new ImageIcon("res/search32.png"));
		buttonSearch.addActionListener(new ButtonSearchListener());
		container.add(buttonSearch, "cell 1 3,alignx right,growy");

		getContentPane().add(separator, "cell 0 4 2 1,growx");
		separator.setVisible(false);
		container.add(scrollPane, "cell 0 5 2 1,grow");
		scrollPane.setVisible(false);
	}

	// Classe interne implémentant l'interface ItemListener
	class BoxTableListener implements ActionListener {

		@SuppressWarnings({ "unchecked" })
		@Override
		public void actionPerformed(ActionEvent ae) {

			// Affecte le modèle de la seconde ComboBox en fonction du choix fait dans la
			// première ComboBox
			if ("chambre".equals(boxTable.getSelectedItem())) {
				boxColumn.setModel(chambreModel);
			} else if ("docteur".equals(boxTable.getSelectedItem())) {
				boxColumn.setModel(docteurModel);
			} else if ("employe".equals(boxTable.getSelectedItem())) {
				boxColumn.setModel(employeModel);
			} else if ("hospitalisation".equals(boxTable.getSelectedItem())) {
				boxColumn.setModel(hospitalisationModel);
			} else if ("infirmier".equals(boxTable.getSelectedItem())) {
				boxColumn.setModel(infirmierModel);
			} else if ("malade".equals(boxTable.getSelectedItem())) {
				boxColumn.setModel(maladeModel);
			} else if ("service".equals(boxTable.getSelectedItem())) {
				boxColumn.setModel(serviceModel);
			} else if ("soigne".equals(boxTable.getSelectedItem())) {
				boxColumn.setModel(soigneModel);
			}
		}
	}

	class ButtonSearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {

			// Entrées utilisateur
			String tableName = boxTable.getSelectedItem().toString();
			String columnName = boxColumn.getSelectedItem().toString();
			String value = fieldValue.getText();

			separator.setVisible(true);
			scrollPane.setVisible(true);
			setResizable(true);
			setMinimumSize(new Dimension(500, 300));
			setSize(500, 500);

			try {
				table.setModel(Connexion.getInstance()
						.buildTableModel(Connexion.getInstance().searchTable(tableName, columnName, value)));
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

}
