package main.test;

import org.junit.Test;

import main.java.GroupePersonnel;
import main.java.Personnel;


public class TestGroupePersonnel {

	@Test
	public void testSerialisationDeserialisation() {

		System.out.println("\n Test de sérialisation");

		GroupePersonnel gp = new GroupePersonnel ();  	
		Personnel p1 = new Personnel.Builder("Gertrude", "Germaine")
				.build(); 
		Personnel p2 = new Personnel.Builder("Gribouille", "Larnaudie")
				.build(); 

		gp.ajouter_personnel(p1);
		gp.ajouter_personnel(p2);


		/**affichage du résultat */
		gp.afficheGroupePersonnel();

		/**serialisation*/
		gp.serialisationFichier("tutu");

		System.out.println("\nTest de Deserialisation");

		GroupePersonnel res = gp.deserialisationFichier("tutu");


		/**affichage du résultat*/
		if (res != null) {
			res.afficheGroupePersonnel();
		}
	}


	@Test
	public void testWriteGroupePersonnelJson() {

		System.out.println("\n Test de sérialisation en json");

		GroupePersonnel gp = new GroupePersonnel();  	
		Personnel p1 = new Personnel.Builder("Camille", "Desmoulins")
				.build(); 
		Personnel p2 = new Personnel.Builder("Robin", "Ellacot")
				.build(); 

		gp.ajouter_personnel(p1);
		gp.ajouter_personnel(p2);


		/**affichage du résultat */
		gp.afficheGroupePersonnel();

		
		/**serialisation*/
		String jsonString = gp.serialisationJson();

		System.out.println(jsonString);

		System.out.println("Test de Deserialisation avec Json");

		GroupePersonnel elt = gp.deSerialisationJson(jsonString);

		elt.afficheGroupePersonnel();

	}
}
