package vue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import modele.Connexion;
import modele.EFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Maxime
 * @see http://zetcode.com/java/jfreechart/
 */
public class ReportingView extends EFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6951151443028082767L;
	private JButton btnDisplayGraph;
	private ChartPanel chartPanel;
	private JComboBox<String> comboBox;

	/**
	 * Constructeur surchargé
	 * 
	 * @param title
	 * @param height
	 * @param width
	 * @param actionOnClose
	 */
	public ReportingView(String title, int height, int width, int actionOnClose) {
		super(title, height, width, actionOnClose);

		// Look and feel natif de l'OS hôte
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		// Définition du layout (MigLayout)
		getContentPane().setLayout(new MigLayout("", "[500.00,grow][grow,right]", "[31.00][]"));

		comboBox = new JComboBox<String>();
		getContentPane().add(comboBox, "cell 0 0,growx");
		comboBox.addItem("Personnes hospitalisées par service");
		comboBox.addItem("Docteurs par service");
		comboBox.addItem("Salaire moyen par service et rotation");

		btnDisplayGraph = new JButton("Afficher");
		btnDisplayGraph.addActionListener(this);
		getContentPane().add(btnDisplayGraph, "cell 1 0,growx");
	}

	private void chartInit() {
		// Chart creation
		JFreeChart chart = null;

		switch (comboBox.getSelectedIndex()) {
		case 0:
			PieDataset dataset1 = createDataset_MaladeParService();
			chart = ChartFactory.createPieChart("Nombre de malades par service", dataset1);
			break;
		case 1:
			PieDataset dataset3 = createDataset_DocteurParService();
			chart = ChartFactory.createPieChart("Nombre de docteurs par service", dataset3);
			break;
		case 2:
			DefaultCategoryDataset dataset2 = createDataset_Salaire();
			chart = ChartFactory.createBarChart("Salaire moyen par service et rotation", "Service", "Salaire moyen",
					dataset2);
			break;
		}

		chartPanel = new ChartPanel(chart);
	}

	private static PieDataset createDataset_DocteurParService() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		ResultSet result = null;

		double nbAne = 1, nbCar = 1, nbOrt = 1, nbPne = 1, nbRad = 1, nbTra = 1;
		try {
			result = Connexion.getInstance().getStatement()
					.executeQuery("SELECT COUNT(CASE WHEN specialite='Anesthesiste' THEN 1 END) as nbAne,"
							+ "COUNT(CASE WHEN specialite='Cardiologue' THEN 1 END) as nbCar,"
							+ "COUNT(CASE WHEN specialite='Orthopediste' THEN 1 END) as nbOrt,"
							+ "COUNT(CASE WHEN specialite='Pneumologue' THEN 1 END) as nbPne,"
							+ "COUNT(CASE WHEN specialite='Radiologue' THEN 1 END) as nbRad,"
							+ "COUNT(CASE WHEN specialite='Traumatologue' THEN 1 END) as nbTra" + " FROM docteur");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (result.next()) {
				nbAne = result.getDouble("nbAne");
				nbCar = result.getDouble("nbCar");
				nbOrt = result.getDouble("nbOrt");
				nbPne = result.getDouble("nbPne");
				nbRad = result.getDouble("nbRad");
				nbTra = result.getDouble("nbTra");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		dataset.setValue("Anesthésiste", nbAne);
		dataset.setValue("Cardiologue", nbCar);
		dataset.setValue("Orthopédiste", nbOrt);
		dataset.setValue("Pneumologue", nbPne);
		dataset.setValue("Radiologue", nbRad);
		dataset.setValue("Traumatologue", nbTra);

		return dataset;
	}

	private static PieDataset createDataset_MaladeParService() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		ResultSet result = null;

		double nbRea = 1, nbChg = 1, nbCar = 1;
		try {
			result = Connexion.getInstance().getStatement().executeQuery(
					"SELECT COUNT(CASE WHEN code_service='REA' THEN 1 END) as nbRea,COUNT(CASE WHEN code_service='CHG' THEN 1 END) as nbChg,COUNT(CASE WHEN code_service='CAR' THEN 1 END) as nbCar FROM hospitalisation");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			while (result.next()) {
				nbRea = result.getDouble("nbRea");
				nbChg = result.getDouble("nbChg");
				nbCar = result.getDouble("nbCar");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		dataset.setValue("Réanimation", nbRea);
		dataset.setValue("Chirurgie", nbChg);
		dataset.setValue("Cardiologie", nbCar);

		return dataset;
	}

	private static DefaultCategoryDataset createDataset_Salaire() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ResultSet result1 = null;
		ResultSet result2 = null;
		ResultSet result3 = null;
		ResultSet result4 = null;
		ResultSet result5 = null;
		ResultSet result6 = null;

		double avgSalReaJour = 1, avgSalReaNuit = 1, avgSalChgJour = 1, avgSalChgNuit = 1, avgSalCarJour = 1,
				avgSalCarNuit = 1;
		try {
			result1 = Connexion.getInstance().getStatement().executeQuery(
					"SELECT AVG(salaire) as avg FROM infirmier WHERE rotation='JOUR' AND code_service='REA'");
			while (result1.next())
				avgSalReaJour = result1.getDouble("avg");
			result2 = Connexion.getInstance().getStatement().executeQuery(
					"SELECT AVG(salaire) as avg FROM infirmier WHERE rotation='NUIT' AND code_service='REA'");
			while (result2.next())
				avgSalReaNuit = result2.getDouble("avg");
			result3 = Connexion.getInstance().getStatement().executeQuery(
					"SELECT AVG(salaire) as avg FROM infirmier WHERE rotation='JOUR' AND code_service='CHG'");
			while (result3.next())
				avgSalChgJour = result3.getDouble("avg");
			result4 = Connexion.getInstance().getStatement().executeQuery(
					"SELECT AVG(salaire) as avg FROM infirmier WHERE rotation='NUIT' AND code_service='CHG'");
			while (result4.next())
				avgSalChgNuit = result4.getDouble("avg");
			result5 = Connexion.getInstance().getStatement().executeQuery(
					"SELECT AVG(salaire) as avg FROM infirmier WHERE rotation='JOUR' AND code_service='CAR'");
			while (result5.next())
				avgSalCarJour = result5.getDouble("avg");
			result6 = Connexion.getInstance().getStatement().executeQuery(
					"SELECT AVG(salaire) as avg FROM infirmier WHERE rotation='NUIT' AND code_service='CAR'");
			while (result6.next())
				avgSalCarNuit = result6.getDouble("avg");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		dataset.addValue(avgSalReaJour, "Jour", "Réanimation");
		dataset.addValue(avgSalReaNuit, "Nuit", "Réanimation");
		dataset.addValue(avgSalChgJour, "Jour", "Chirurgie");
		dataset.addValue(avgSalChgNuit, "Nuit", "Chirurgie");
		dataset.addValue(avgSalCarJour, "Jour", "Cardiologie");
		dataset.addValue(avgSalCarNuit, "Nuit", "Cardiologie");

		return dataset;
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		this.setSize(500, 400);

		if (source == this.btnDisplayGraph) {
			// Delete the old graph
			this.removeGraph();
			// Add the new graph
			this.addGraph();

			// Refreshing the display
			this.revalidate();
			this.repaint();
		}
	}

	private void removeGraph() {
		if (chartPanel != null) {
			getContentPane().remove(this.chartPanel);
		}
	}

	private void addGraph() {
		this.chartInit();
		getContentPane().add(this.chartPanel, "cell 0 1 2 1,grow");
	}
}
