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

import main.java.GroupePersonnel;
import main.java.dao.DAO;


public class GroupePersonnelDAO extends DAO <GroupePersonnel> {

	/**instance de classe singleton*/
	private static GroupePersonnelDAO instance;  
	
	
	/**Table de hachage de groupe personnels avec pour cle : fonction*/
	private static Map <String, GroupePersonnel> mapGroupePersonnels; 
	
	
	/**nom du fichier contenant le groupe personnels*/
	private static String nomFichierGroupePersonnel; 

	
	
	
	/**
	 * Constructeur prive pour une classe singleton
	 */
	
	private GroupePersonnelDAO () {
		
	}
	
	
	/**
	 * 
	 * @return
	 */
	
	public static GroupePersonnelDAO getInstance(String nomFichier) {
	
		if (instance == null) {
			instance = new GroupePersonnelDAO(); 
			
			mapGroupePersonnels = new HashMap<String, GroupePersonnel>();
			
			nomFichierGroupePersonnel = new String(nomFichier);
			
			/**initialisation de la table des Personnels a partir du fichier*/
			initMapPersonnels();
			
		}
		return instance; 
	}
	

	
	
	@Override
	/**
	 * 
	 */
	
	public GroupePersonnel create(GroupePersonnel gpperso) {
		if (gpperso != null) {
			String cle = getKeyGroupePersonnel(gpperso);

			if (cle != null) {

				/**mise a jour de la table*/
				mapGroupePersonnels.put(cle, gpperso);
				
				/**mise a jour du fichier*/
				ecrireFichierGroupePersonnels();
			}

		}

		return gpperso;
	}

	
	@Override
	/**
	 * 
	 */
	public GroupePersonnel find(String id) {
		
		GroupePersonnel res = null;
		if (id != null) {
			
			if ( mapGroupePersonnels.containsKey(id)) {
				/**mise a jour de la table*/
				res = mapGroupePersonnels.get(id);	
			}
		}
		
		return res;
	}

	
	@Override
	/**
	 * 
	 */
	public GroupePersonnel update(GroupePersonnel gpperso) {

		String cle = getKeyGroupePersonnel(gpperso);

		if ( mapGroupePersonnels.containsKey(cle)) {
			/**mise a jour de la table*/
			mapGroupePersonnels.replace(cle, gpperso);
		}
		else {
			/**mise a jour de la table*/
			mapGroupePersonnels.put(cle, gpperso);
		}
		/**mise a jour du fichier*/
		ecrireFichierGroupePersonnels();
		
		return null;
	}

	
	@Override
	/**
	 * 
	 */
	public void delete(GroupePersonnel gpperso) {

		String cle = getKeyGroupePersonnel(gpperso);

		if (cle != null) {

			if ( mapGroupePersonnels.containsKey(cle)) {

				// mise a jour de la table
				mapGroupePersonnels.remove(cle);
				
				// mise a jour du fichier
				ecrireFichierGroupePersonnels();
			}
		}

	}
	
	
	/**
	 * 
	 * @param personnel
	 * @return
	 */
	public static String getKeyGroupePersonnel(GroupePersonnel gppersonnel) {
		
	     String res = new String("");
		
	     if (gppersonnel != null) {
	    	 res = gppersonnel.getFunction();
	     }
		
	     return res; 
	}
	
	
	
	/**
	 * Lit le fichier des personnels
	 * 
	 */
	private static LinkedList<GroupePersonnel> lireFichierGroupePersonnels() {
		
		FileInputStream fis = null;
		ObjectInputStream ois =null;
		
		LinkedList<GroupePersonnel> gppersLus = new LinkedList<>();
		
		File fichier =  new File(nomFichierGroupePersonnel);

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

					if (objetLu instanceof GroupePersonnel) {
						GroupePersonnel gppersonnel = (GroupePersonnel)objetLu;

						gppersLus.add(gppersonnel);

					}
				}

				//ois.close();
				//fis.close();
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
		
		return gppersLus;	
	}
	
	
	/**
	 * Initilisation de la Map des personnels
	 */
	
	private static void  initMapPersonnels () {
		
		// lecture du fichier ds personnels
		LinkedList<GroupePersonnel> gppersLus = lireFichierGroupePersonnels();
		
		if (gppersLus != null) {
			for (GroupePersonnel gppersonnel : gppersLus) {
				mapGroupePersonnels.put (getKeyGroupePersonnel(gppersonnel), gppersonnel);
			}
		}
		
		
	}
	
	
	/**
	 * Ecriture du fichier des personnels
	 * 
	 */
	private void ecrireFichierGroupePersonnels() {
		
		ObjectOutputStream oos = null ;
		try {
			
			FileOutputStream fos = new FileOutputStream(new File(nomFichierGroupePersonnel));

			/**ouverture d un flux sur un fichier*/
			oos =  new ObjectOutputStream(
					new BufferedOutputStream(
							fos));


			/**serialization de l objet*/
			if (!mapGroupePersonnels.isEmpty()) {
				for ( GroupePersonnel gppersonnel : mapGroupePersonnels.values()) {
					oos.writeObject(gppersonnel);
				}
			}

			oos.flush();
			oos.close();
			fos.close();

		}
		catch (IOException ex) {
			
			ex.printStackTrace();
		}
		
	}
	
	
	/**
	 * Affiche les personnels contenus dans le fichier
	 * 
	 */
	public void afficheFichierGroupePersonnels() {

		/**lecture du fichier ds personnels*/
		LinkedList<GroupePersonnel> gppersLus = lireFichierGroupePersonnels();

		if (gppersLus != null) {
			for (GroupePersonnel gppersonnel : gppersLus) {
				gppersonnel.afficheGroupePersonnel();
			}
		}

	}

}

