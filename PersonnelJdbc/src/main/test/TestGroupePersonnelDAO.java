package main.test;

import org.junit.Before;
import org.junit.Test;

import main.java.GroupePersonnel;
import main.java.Personnel;
import main.java.dao.DAOFactory;
import main.java.dao.GroupePersonnelDAO;


public class TestGroupePersonnelDAO {

	String nomFichier = "fichierGroupePersonnel";
	private GroupePersonnel gp1 = null;
	private GroupePersonnel gp2 = null;
	private GroupePersonnel gp3 = null;
	private Personnel p1 = null;
	private Personnel p2 = null;
	private Personnel p3= null;
	private Personnel p4= null;
	private Personnel p5= null;


	@Before 
	public void init() {
		gp1 = new GroupePersonnel();  	
		p1 = new Personnel.Builder("Gertrude", "Germaine").fonction("chef")
				.build(); 
		p2 = new Personnel.Builder("Gribouille", "Larnaudie").fonction("chef")
				.build(); 
		gp1.ajouter_personnel(p1);
		gp1.ajouter_personnel(p2);

		gp2 = new GroupePersonnel();  	
		p3 = new Personnel.Builder("Camille", "Desmoulins").fonction("souschef")
				.build(); 
		p4= new Personnel.Builder("Robin", "Ellacot").fonction("souschef")
				.build(); 
		gp2.ajouter_personnel(p3);
		gp2.ajouter_personnel(p4);


		gp3 = new GroupePersonnel();  	
		p5 = new Personnel.Builder("Gert", "Germaine").fonction("chef")
				.build(); 

		gp3.ajouter_personnel(p5);
	}

	@Test
	public void testCreate() {

		System.out.println("\n Test de creation de groupe personnel dans la table");

		DAOFactory.getGroupePersonnelDAO(nomFichier).create(gp1);

		DAOFactory.getGroupePersonnelDAO(nomFichier).create(gp2);

		/**affichage du fichier du groupe de personnels*/
		GroupePersonnelDAO.getInstance(nomFichier).afficheFichierGroupePersonnels();

	}


	@Test
	public void testDelete() {

		System.out.println("\n Test de suppression de groupe de personnel dans la table");

		/**affichage du fichier de groupe de personnels*/
		GroupePersonnelDAO.getInstance(nomFichier).afficheFichierGroupePersonnels();

		System.out.println("\n affichage apres  suppression du personnel");
		DAOFactory.getGroupePersonnelDAO(nomFichier).delete(gp2);

		/**affichage du fichier des personnels*/
		GroupePersonnelDAO.getInstance(nomFichier).afficheFichierGroupePersonnels(); 
	}


	@Test
	public void testRecherche() {

		System.out.println("\n Test de recherche de groupe de personnel dans la table");


		/**element existant*/
		System.out.println("recherche d un element existant");

		String cle = GroupePersonnelDAO.getKeyGroupePersonnel(gp1);
		GroupePersonnel res = DAOFactory.getGroupePersonnelDAO(nomFichier).find(cle);
		if (res != null) {
			res.afficheGroupePersonnel();
		}

		/**element non existant*/
		System.out.println("recherche d un element non existant");
		res = DAOFactory.getGroupePersonnelDAO(nomFichier).find("xxxxxxxyyyyyyyy");
		if (res != null) {
			res.afficheGroupePersonnel();
		}   
	}



	@Test
	public void testUpdate() {

		System.out.println("\n Test de mise à jours de groupe de personnel dans la table");

		/**mise a jour - groupe personnel déjà existant dans la table
		    gp3 remplace gp1*/
		DAOFactory.getGroupePersonnelDAO(nomFichier).update(gp3);


      	/**affichage du fichier du groupe de personnels*/
		GroupePersonnelDAO.getInstance(nomFichier).afficheFichierGroupePersonnels();
	}




}
