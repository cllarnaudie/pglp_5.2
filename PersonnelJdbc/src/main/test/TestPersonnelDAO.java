package main.test;

import org.junit.Before;
import org.junit.Test;

import main.java.Personnel;
import main.java.Personnel.Builder;
import main.java.dao.DAOFactory;
import main.java.dao.PersonnelDAO;


public class TestPersonnelDAO {

	String nomFichier = "fichierPersonnel";
	private Personnel perso = null;
	private Personnel perso1 = null;
	private Personnel perso2 = null;
	private Personnel perso3 = null;
	private Personnel perso4 = null;


	@Before 
	public void init () {
		perso = new Builder("larnaudie","claire")
				.fonction("etudiante")
				.numeroTelephone("1111111")
				.numeroTelephone("2222222")
				.build(); 

		perso1 = new Builder("larnaudie","audrey")
				.fonction("etudiante")
				.numeroTelephone("123456789")
				.numeroTelephone("13245896")
				.build(); 

		perso2 = new Builder("larnaudie","claire")
				.fonction("etudiante")
				.numeroTelephone("012345")
				.numeroTelephone("987654")
				.build(); 

		perso3 = new Builder("larnaudie","caroline")
				.fonction("etudiante")
				.numeroTelephone("012345")
				.numeroTelephone("987654")
				.build(); 

		perso4 = new Builder("dupont","paul")
				.fonction("etudiant")
				.numeroTelephone("012345")
				.numeroTelephone("987654")
				.build(); 

	}



	@Test
	public void testCreate() {

		System.out.println("\n Test de creation de personnel dans la table");

		DAOFactory.getPersonnelDAO(nomFichier).create(perso);

		DAOFactory.getPersonnelDAO(nomFichier).create(perso1);
		DAOFactory.getPersonnelDAO(nomFichier).create(perso4);


		/**affichage du fichier des personnels*/
		PersonnelDAO.getInstance(nomFichier).afficheFichierPersonnels();

	}


	@Test
	public void testRecherche() {

		System.out.println("\n Test de recherche de personnel dans la table");


		/**element existant*/
		System.out.println("recherche d un element existant");

		String cle = PersonnelDAO.getKeyPersonnel(perso4);
		Personnel res = DAOFactory.getPersonnelDAO(nomFichier).find(cle);

		if (res != null) {
			res.affichePersonnel();
		}

		/**element non existant*/
		System.out.println("recherche d un element non existant");
		res = DAOFactory.getPersonnelDAO(nomFichier).find("xxxxxxxyyyyyyyy");

		if (res != null) {
			res.affichePersonnel();
		}

	}



	@Test
	public void testUpdate() {

		System.out.println("\n Test de mise à jours de personnel dans la table");


		/**mise a jour - personnel déjà existant dans la table
		    perso2 remplace perso*/
		DAOFactory.getPersonnelDAO(nomFichier).update(perso2);

		/**mise a jour - personnel non dans la table
	        ajoute perso3*/

		DAOFactory.getPersonnelDAO(nomFichier).update(perso3);

		/**affichage du fichier des personnels*/
		PersonnelDAO.getInstance(nomFichier).afficheFichierPersonnels();
	}


	@Test
	public void testDelete() {

		System.out.println("\n Test de suppression de personnel dans la table");

		/**affichage du fichier des personnels*/
		PersonnelDAO.getInstance(nomFichier).afficheFichierPersonnels();

		System.out.println("\n affichage apres  suppression du personnel");
		DAOFactory.getPersonnelDAO(nomFichier).delete(perso1);

		/**affichage du fichier des personnels*/
		PersonnelDAO.getInstance(nomFichier).afficheFichierPersonnels();

	}

}

