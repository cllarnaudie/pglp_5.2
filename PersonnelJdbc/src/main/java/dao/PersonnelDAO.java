package main.java.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import main.java.GroupePersonnel;
import main.java.Personnel;


public class PersonnelDAO extends DAO<Personnel> {

	/**instance de classe singleton*/
	private static PersonnelDAO instance;  


	/**Table de hachage des personnels avec pour cle : nom+prenom+fonction*/
	private static Map<String, Personnel> mapPersonnels; 

	/**nom du fichier contenant les personnels*/
	private static String nomFichierPersonnel; 


	/**
	 * Constructeur prive pour une classe singleton
	 */
	private PersonnelDAO() {

	}



	/**
	 * @param nomFichier
	 * @return instance
	 */
	public static PersonnelDAO getInstance(String nomFichier) {
		if (instance == null) {
			instance = new PersonnelDAO(); 
			
			mapPersonnels = new HashMap<String, Personnel>();

			nomFichierPersonnel = new String(nomFichier);

			/**initialisation de la table des Personnels a partir du fichier*/
			initMapPersonnels();
			
		}
		return instance; 
	}


	@Override
	public Personnel create(Personnel perso) {
		try {
			if (perso != null) {
				String cle = getKeyPersonnel(perso);
				if (cle != null) {
					ResultSet result = this	.connect
							.createStatement(
									ResultSet.TYPE_SCROLL_INSENSITIVE, 
									ResultSet.CONCUR_UPDATABLE
									).executeQuery(
											"SELECT NEXTVAL('langage_lan_id_seq') as id"
											);

					if(result.first()){
						long id = result.getLong("id");
						PreparedStatement prepare = this.connect
								.prepareStatement(
										"INSERT INTO Personnel (cle, perso) VALUES(?, ?)" + mapPersonnels.put(cle, perso)
										);	

					} } 
				/**mise a jour du fichier*/
				ecrireFichierPersonnels();    

			} } catch (SQLException e) {
				e.printStackTrace();
			}
		return perso;
	}



	@Override
	public Personnel find(String id) {

		Personnel perso = null;
		try {
			if(id != null) {
				if (mapPersonnels.containsKey(id)) {
					ResultSet result = this.connect
							.createStatement(
									ResultSet.TYPE_SCROLL_INSENSITIVE, 
									ResultSet.CONCUR_UPDATABLE
									).executeQuery(
											"SELECT * FROM Personnel WHERE lan_id = " + mapPersonnels.get(id)
											);

				} }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return perso;
	}


	@Override
	public Personnel update(Personnel perso) {

		String cle = getKeyPersonnel(perso);
		try {
			if (mapPersonnels.containsKey(cle)) {
				this .connect	
				.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE
						).executeUpdate(
								"UPDATE Personnel SET GroupePersonnel_nom = '" + mapPersonnels.replace(cle, perso)
								);
			}
			else {
				/**mise a jour de la table*/
				mapPersonnels.put(cle,perso);
			}

			/**mise a jour du fichier*/
			ecrireFichierPersonnels();


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}


	 


	@Override
	public void delete(Personnel perso) {

		String cle = getKeyPersonnel(perso);
		
		try {
			if (cle != null) {

				if(mapPersonnels.containsKey(cle)) {

					this    .connect
					.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE, 
							ResultSet.CONCUR_UPDATABLE
							).executeUpdate(
									"DELETE FROM Personnel WHERE p_id = " + mapPersonnels.remove(cle)
									);
				}                
				/**mise a jour du fichier*/
				ecrireFichierPersonnels();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @param personnel
	 * @return res
	 */
	public static String getKeyPersonnel(Personnel personnel) {

		String res = null;
		if (personnel != null) {

			res = personnel.getNom() + personnel.getPrenom() + personnel.getFonction();
		}

		return res;
	}


	/**
	 * Lit le fichier des personnels
	 */
	private static LinkedList<Personnel> lireFichierPersonnels() {

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		LinkedList<Personnel> persLus = new LinkedList<Personnel>();

		File fichier =  new File(nomFichierPersonnel);

		if (fichier.exists()) {
			try {

				fis = new FileInputStream(fichier);

				/**ouverture d un flux sur un fichier*/
				ois =  new ObjectInputStream(
						new BufferedInputStream(
								fis));

				/**deserialization de l objet*/
				Object objetLu = null;
				while ((objetLu = ois.readObject()) != null) {

					if (objetLu instanceof Personnel) {
						Personnel personnel = (Personnel)objetLu;

						persLus.add(personnel);

					}
				}

				ois.close();
				fis.close();
			}

			catch (EOFException ex) {


			}
			catch (IOException ex) {

				ex.printStackTrace();
			}
			catch (ClassNotFoundException ex) {

				ex.printStackTrace();
			}

			finally {
				try {
					ois.close();
					fis.close();
				}
				catch (IOException ex) {

					ex.printStackTrace();
				}
			}
		}

		return persLus;
	}


	/**
	 * Initilisation de la Map des personnels
	 */
	private static void  initMapPersonnels(){

		/**lecture du fichier ds personnels*/
		LinkedList<Personnel> persLus = lireFichierPersonnels();

		if (persLus != null) {
			for (Personnel personnel : persLus) {
				
				mapPersonnels.put(getKeyPersonnel(personnel), personnel);
			}
		}
	}


	/**
	 * Ecriture du fichier des personnels
	 */
	private void ecrireFichierPersonnels() {

		ObjectOutputStream oos = null;
		try {

			FileOutputStream fos = new FileOutputStream(new File(nomFichierPersonnel));

			/**ouverture d un flux sur un fichier*/
			oos =  new ObjectOutputStream(
					new BufferedOutputStream(
							fos));


			/**serialization de l objet*/
			if (!mapPersonnels.isEmpty()) {
				for (Personnel personnel :mapPersonnels.values()) {
					oos.writeObject(personnel);
				}
			}

			oos.flush();
			oos.close();
			fos.close();

		}
		catch (IOException ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}
	}


	/**
	 * Affiche les personnels contenus dans le fichier
	 */
	public void afficheFichierPersonnels() {

		/**lecture du fichier dans personnels*/
		LinkedList<Personnel> persLus = lireFichierPersonnels();

		if (persLus != null) {
			for (Personnel personnel : persLus) {
				personnel.affichePersonnel();
			}
		}

	}

}

