package main.java;
/*
 * il reste apllication singleton implemente
 */

import java.time.Month;

public class MainApllicationSingleton {
	
	private static MainApllicationSingleton INSTANCE; 
	
	private MainApllicationSingleton() {
		
	}
	
	public static MainApllicationSingleton getInstance () {
		if (INSTANCE == null) {
			INSTANCE = new MainApllicationSingleton(); 
		}
		return INSTANCE; 
	}

     public static void main (String [] args) {
    	 
    	 GroupePersonnel gp = new GroupePersonnel ();  	
 		Personnel p1 = new Personnel.Builder("Gertrude", "Germaine")
 				       .build(); 
 	
		Personnel p2 = new Personnel.Builder("Jean", "Bouysous")
				        .build(); 
		
		Personnel p3 = new Personnel.Builder("Marcel ", "Pagnol ")
			       .build();

		
	    System.out.println(p3.getPrenom() + " " + p3.getNom() + " " + p3.getDateNaissance() +
					" "+ p3.getFonction()+ " " + p3.getNumeroTelephone() + "\n");
	
 		
 		gp.ajouter_personnel(p1);
 		gp.ajouter_personnel(p2);
 		gp.ajouter_personnel(p3);
 		
 		gp.supprimer_personnel(p1);
 		gp.supprimer_personnel(p2);
 		
 		

	    Personnel perso = new Personnel.Builder ("Desmoulins", "Camille")	    		
	    		      .fonction("journaliste")
	    		      .dateNaissance (1980,Month.MAY,24)
	    		      .numeroTelephone("0130859674")
	    		      .numeroTelephone("0160325487")
	    	          .build() ; 
	    
	    System.out.println(perso.getPrenom() + " " + perso.getNom() + " " + perso.getDateNaissance() +
				" "+ perso.getFonction()+ " " + perso.getNumeroTelephone() );
		System.out.println("\n");	
 	
     }
    	 
}

