package main.test;

import java.time.Month;


import org.junit.Test;

import main.java.Personnel;


public class TestPersonnel {
	/*
	 * on affiche tous les identifiants du personnel
	 *    il en manque certains (nom, prenom, fonction, dateNaissance, numeroTelephone)
	 *    certains sont invalides (dateNaissance)
	 */
	
	private static final String TELEPHONE1 = new String("0130857496");
	private static final String TELEPHONE2 = new String("0130857496");
	
	private static final String NOM1 = new String("Strick");
	private static final String PRENOM1 = new String("Cormorran");
	
	private static final String NOM_VIDE = new String(" ");
	private static final String PRENOM2 = new String("Robin");
	
	private static final String NOM3 = new String("Desmoulins");
	private static final String PRENOM_VIDE = new String(" ");
	
	private static final String NOM4 = new String("Tartempion");
	private static final String PRENOM4 = new String("Toto ");
	
	private static final String NOM5 = new String("Polochon");
	private static final String PRENOM5 = new String("Sebastien ");

	
 	@Test
	public void testpersonnel() {

		System.out.println("testpersonnel");
		System.out.println("\n");

	    Personnel perso = new Personnel.Builder (NOM1, PRENOM1)	    		
	    		      .fonction("detective")
	    		      .dateNaissance (1980,Month.MAY,24)
	    		      .numeroTelephone(TELEPHONE1)
	    		      .numeroTelephone(TELEPHONE2)
	    	          .build() ; 
	    
		System.out.println(perso.getPrenom() + " " + perso.getNom() + " " + perso.getDateNaissance() +
				" "+ perso.getFonction()+ " " + perso.getNumeroTelephone() + "\n" );
		System.out.println("\n");	    		 	 
}
 	
 	
 	@Test
	public void testpersonnelNomInvalide() {

		System.out.println("testpersonnelNomInvalide");
		System.out.println("\n");

	    Personnel perso2 = new Personnel.Builder (NOM_VIDE, PRENOM2)	    		
	    		      .fonction("assistante")
	    		      .dateNaissance (1980,Month.DECEMBER,22)
	    		      .numeroTelephone(TELEPHONE1)
	    		      .numeroTelephone(TELEPHONE2)
	    	          .build() ; 
	    
		System.out.println(perso2.getPrenom() + " " + perso2.getNom() + " " + perso2.getDateNaissance() +
				" "+ perso2.getFonction()+ " " + perso2.getNumeroTelephone()  + "\n");
	
	    
      System.out.println ("Le nom est vide "+  NOM_VIDE.isEmpty() + "\n"); 
		    		 	 
}
 	
 	@Test
	public void testpersonnelPrenomInvalide() {

		System.out.println("testpersonnelPrenomInvalide");
		System.out.println("\n");

	    Personnel perso3 = new Personnel.Builder (NOM3, PRENOM_VIDE)	    		
	    		      .fonction("journaliste")
	    		      .dateNaissance (1780,Month.MARCH,30)
	    		      .numeroTelephone(TELEPHONE1)
	    	          .build() ; 
	    
		System.out.println(perso3.getPrenom() + " " + perso3.getNom() + " " + perso3.getDateNaissance() +
				" "+ perso3.getFonction()+ " " + perso3.getNumeroTelephone() + "\n");
	
			System.out.println ("Le prenom est vide "+  PRENOM_VIDE.isEmpty() + "\n"); 
		}
	   	    		 	 

 	
 	
 	@Test
	public void testpersonnelFonctionInvalide() {

		System.out.println("testpersonnelFonctionInvalide");
		System.out.println("\n");

	    Personnel perso4 = new Personnel.Builder (NOM4, PRENOM4)	    		
	    		      .fonction(" ")
	    		      .dateNaissance (1780,Month.MARCH,30)
	    		      .numeroTelephone(TELEPHONE1)
	    	          .build() ; 
	    
		System.out.println(perso4.getPrenom() + " " + perso4.getNom() + " " + perso4.getDateNaissance() +
				" "+ perso4.getFonction()+ " " + perso4.getNumeroTelephone() + "\n");

	   	
		if (perso4.getFonction() == " ") {
			System.out.println (" La fonction est vide \n"); 
		}
}
 	
 
 	@Test
	public void testpersonnelNumeroTelephoneInvalide() {

		System.out.println("testpersonnelNumeroTelephoneInvalide");
		System.out.println("\n");

	    Personnel perso5 = new Personnel.Builder (NOM5, PRENOM5)	    		
	    		      .fonction("professeur d'histoire ")
	    		      .dateNaissance ( 1,Month.FEBRUARY,1)
	    		      .numeroTelephone(" ")
	    	          .build() ; 
	    
		System.out.println(perso5.getPrenom() + " " + perso5.getNom() + " " + perso5.getDateNaissance() +
				" "+ perso5.getFonction()+ " " + perso5.getNumeroTelephone()  + "\n" );
	
		System.out.println ("Le numero de telephone est vide "+ perso5.getNumeroTelephone().isEmpty() + "\n"); 
       	}
 	
 	@Test
	public void testpersonnelDateNaissanceInvalide() {

		System.out.println("testpersonnelDateNaissanceInvalide");
		System.out.println("\n");

	    Personnel perso6 = new Personnel.Builder (NOM5, PRENOM5)	    		
	    		      .fonction("professeur d'histoire ")
	    		      .dateNaissance ( 1,Month.FEBRUARY,40)
	    		      .numeroTelephone(TELEPHONE2)
	    	          .build() ; 
	    
		System.out.println(perso6.getPrenom() + " " + perso6.getNom() + " " + perso6.getDateNaissance() +
				" "+ perso6.getFonction()+ " " + perso6.getNumeroTelephone()  + "\n" );
		
       	}
	   	    		 	 
}
