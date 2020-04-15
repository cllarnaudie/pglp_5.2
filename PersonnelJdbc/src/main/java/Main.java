package main.java;

import main.java.Personnel.Builder;

public class Main {

	private static Main instance; 

	private Main() {

	}

	public static Main getInstance() {
		if (instance == null) {
			instance = new Main(); 
		}
		return instance; 
	}

	public static void main(String[] args) {

		/** premier scenario serialisation personne*/
		Personnel perso = new Builder("Cormorran", "Strike")
				.fonction("dectetive")
				.numeroTelephone("0213456")
				.numeroTelephone("01020304")
				.build(); 

		perso.affichePersonnel();

		perso.serialisationFichier("tutu");

		System.out.println("\nTest de Deserialisation");

		perso.deserialisationFichier("tutu");
		/** fin premier scenario  serialisation personne*/


		/** deuxieme scenario serialisation personne gson*/
		Personnel perso2 = new Builder("Robin", "Ellacot")
				.fonction("assitante-dectetive")
				.numeroTelephone("123456789")
				.numeroTelephone("13245896")
				.build(); 

		perso2.affichePersonnel();

		String jsonString = perso2.serialisationJson();

		System.out.println(jsonString);

		System.out.println("\nTest de Deserialisation avec Json");

		Personnel elt = perso.deSerialisationJson(jsonString);

		elt.affichePersonnel();

		/** fin deuxieme scenario serialisation personne gson */


		/** premier scenario serialisation groupe*/
		GroupePersonnel gp = new GroupePersonnel();  	
		Personnel p1 = new Personnel.Builder("Gertrude", "Germaine")
				.build(); 
		Personnel p2 = new Personnel.Builder("Gribouille", "Larnaudie")
				.build(); 

		gp.ajouter_personnel(p1);
		gp.ajouter_personnel(p2);

		gp.afficheGroupePersonnel();

		gp.serialisationFichier("tutu");

		System.out.println("\nTest de Deserialisation");

		gp.deserialisationFichier("tutu");
		/** fin premier scenario  serialisation groupe*/


		/** deuxieme scenario serialisation groupe gson*/

		GroupePersonnel gp2 = new GroupePersonnel();  	
		Personnel p3 = new Personnel.Builder("Camille", "Desmoulins")
				.build(); 
		Personnel p4 = new Personnel.Builder("Robin", "Ellacot")
				.build(); 

		gp2.ajouter_personnel(p3);
		gp2.ajouter_personnel(p4);

		gp.afficheGroupePersonnel();

		String jsonString2 = gp2.serialisationJson();

		System.out.println(jsonString2);

		System.out.println("\nTest de Deserialisation avec Json");

		GroupePersonnel elt2 = gp2.deSerialisationJson(jsonString2);

		elt2.afficheGroupePersonnel();

		/** fin deuxieme scenario serialisation groupe gson */

	}

}



