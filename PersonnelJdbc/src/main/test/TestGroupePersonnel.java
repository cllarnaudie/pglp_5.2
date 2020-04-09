package main.test;

import org.junit.Test;

import main.java.GroupePersonnel;
import main.java.Personnel;

public class TestGroupePersonnel {
	

	@Test
	public void testGroupePersonnelAjouterPersonne() {

		System.out.println("testGroupePersonnelAjouterPersonne \n");

		GroupePersonnel gp = new GroupePersonnel ();  	
		Personnel p1 = new Personnel.Builder("Gertrude", "Germaine")
				       .build(); 

		  gp.ajouter_personnel(p1);
			    		     
}
	
	@Test
	public void testGroupePersonnelSupprimerPersonnel() {

		System.out.println("testGroupePersonnelSupprimerPersonnel \n");

		GroupePersonnel gp2 = new GroupePersonnel ();  	
		Personnel p2 = new Personnel.Builder("Jean", "Bouysous")
				        .build(); 

		
		gp2.ajouter_personnel(p2);
		gp2.supprimer_personnel(p2);
	    		     
}
	
	

	@Test
	public void testGroupeAjouterMemePersonnel() {

		System.out.println("testGroupeAjouterMemePersonnel \n");

		GroupePersonnel gp2 = new GroupePersonnel ();  	
		Personnel p3 = new Personnel.Builder("Marcel ", "Pagnol ")
				       .build();

		
		gp2.ajouter_personnel(p3);
		gp2.ajouter_personnel(p3);
	}
	    	     



	@Test
	public void testGroupePersonnelSupprimerPersonneDeuxFois() {

		System.out.println("testGroupePersonnelSupprimerPersonneDeuxFois \n");

		GroupePersonnel gp3 = new GroupePersonnel ();  	
		Personnel p4 = new Personnel.Builder("More", "Thomas ")
				       .build();
		
		gp3.ajouter_personnel(p4);
		gp3.supprimer_personnel(p4);
		gp3.supprimer_personnel(p4);
		   	    		     
}
	

}
