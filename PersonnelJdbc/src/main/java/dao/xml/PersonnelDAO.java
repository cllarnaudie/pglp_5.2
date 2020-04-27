package main.java.dao.xml;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import main.java.Personnel;
import main.java.dao.DAO;


public class PersonnelDAO extends DAO <Personnel> {

	// instance de classe singleton
	private static PersonnelDAO instance;  
	
	
	// Table de hachage des personnels avec pour cle : nom+prenom+fonction
	private static Map <String, Personnel> mapPersonnels ; 
	
	
	// nom du fichier contenant les personnels
	private static String nomFichierPersonnel ; 

	
	
	
	/**
	 * Constructeur prive pour une classe singleton
	 */
	
	private PersonnelDAO() {
		
	}
	
	
	/**
	 * 
	 * @return
	 */
	
	public static PersonnelDAO getInstance(String nomFichier) {
		if (instance == null) {
			instance = new PersonnelDAO(); 
			
			mapPersonnels = new HashMap<>();
			
			nomFichierPersonnel = nomFichier;
			
			// initialisation de la table des Personnels à partir du fichier
			initMapPersonnels();
			
		}
		return instance; 
	}
	

	
	
	@Override
	/**
	 * 
	 */
	
	public Personnel create(Personnel perso) {
		if (perso != null) {
			String cle = getKeyPersonnel(perso);

			if (cle != null) {

				// mise a jour de la table
				mapPersonnels.put(cle, perso);
				
				// mise a jour du fichier
				ecrireFichierPersonnels();
			}

		}

		return perso;
	}

	@Override
	/**
	 * 
	 */

	
	public Personnel find(String id) {
		
		Personnel res = null ;
		if (id != null) {
			
			if ( mapPersonnels.containsKey(id)) {
				// mise a jour de la table
				res = mapPersonnels.get(id) ;
				
			}
			
		}
		
		return res;
	}

	
	@Override
	/**
	 * 
	 */
	public Personnel update(Personnel perso) {

		String cle = getKeyPersonnel(perso);

		if ( mapPersonnels.containsKey(cle)) {
			// mise a jour de la table
			mapPersonnels.replace(cle, perso) ;
		}
		else {
			// mise a jour de la table
			mapPersonnels.put(cle, perso) ;
		}
		// mise a jour du fichier
		ecrireFichierPersonnels();


		return null;
	}

	
	@Override
	/**
	 * 
	 */
	

	public void delete(Personnel perso) {

		String cle = getKeyPersonnel(perso);

		if (cle != null) {

			if ( mapPersonnels.containsKey(cle)) {

				// mise a jour de la table
				mapPersonnels.remove(cle);
				
				// mise a jour du fichier
				ecrireFichierPersonnels();
			}
		}

	}
	
	/**
	 * 
	 * @param personnel
	 * @return
	 */
	public static String getKeyPersonnel(Personnel personnel) {
		
		String res = null;
		if (personnel != null) {
			
			res = personnel.getNom() + personnel.getPrenom()+personnel.getFonction();
		
		}
		
		return res;
	}
	
	/**
	 * Lit le fichier des personnels
	 * 
	 */
	private static LinkedList<Personnel> lireFichierPersonnels () {
		
		FileInputStream fis = null;
		ObjectInputStream ois =null ;
		
		LinkedList<Personnel> persLus = new LinkedList<Personnel>() ;
		
		File fichier =  new File(nomFichierPersonnel) ;

		if (fichier.exists()) {
		try {

				fis = new FileInputStream( fichier);

				/**ouverture d un flux sur un fichier*/
				ois =  new ObjectInputStream(
						new BufferedInputStream(
								fis));

				/**deserialization de l objet*/
				Object objetLu = null;
				while ( (objetLu = ois.readObject()) != null) {

					if (objetLu instanceof Personnel) {
						Personnel personnel = (Personnel)objetLu ;

						persLus.add(personnel);

					}
				}

				ois.close();
				fis.close();
			}

			catch (EOFException ex) {

				//
			}
			catch (IOException ex) {
			
				ex.printStackTrace();
			}
			catch (ClassNotFoundException ex) {

				ex.printStackTrace();
			}

			
		}
		
		return persLus ;
		
		
	}
	
	
	/**
	 * Initilisation de la Map des personnels
	 */
	
	private static void  initMapPersonnels() {
		
		// lecture du fichier ds personnels
		LinkedList<Personnel> persLus = lireFichierPersonnels();
		
		
		for (Personnel personnel : persLus) {
				mapPersonnels.put (getKeyPersonnel(personnel), personnel);
		}	
	}
	
	
	/**
	 * Ecriture du fichier des personnels
	 * 
	 */
	private void ecrireFichierPersonnels(){
		
		ObjectOutputStream oos=null ;
		try {
			
			FileOutputStream fos = new FileOutputStream(new File(nomFichierPersonnel));

			/**ouverture d un flux sur un fichier*/
			oos =  new ObjectOutputStream(
					new BufferedOutputStream(
							fos) );


			/**serialization de l objet*/
			if (!mapPersonnels.isEmpty()) {
				for ( Personnel personnel :mapPersonnels.values() ) {
					oos.writeObject(personnel) ;
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
	 * 
	 */
	public void afficheFichierPersonnels() {

		// lecture du fichier ds personnels
		LinkedList<Personnel> persLus = lireFichierPersonnels();

		
		for (Personnel personnel : persLus) {
				personnel.affichePersonnel();
		}
		
	}

}
