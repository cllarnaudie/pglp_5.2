package test.java;

import org.junit.Test;

import main.java.Personnel;
import main.java.Personnel.Builder;


public class TestPersonnel {
	
 	@Test
	public void testSerialisationDeserialisation() {

		System.out.println("\n Test de sérialisation");
		
	    Personnel perso = new Builder("nom1","prenom1")
	    		      .fonction("chef")
	    		      .numeroTelephone("0213456")
	    		      .numeroTelephone("01020304")
	    		      .build() ; 
	    
	    
	    /**affichage du résultat */
	    perso.affichePersonnel();
	    
	    /**serialisation*/
	    perso.serialisationFichier("tutu");
	    
	    System.out.println("Test de Deserialisation") ;
	    
	    Personnel res = perso.deserialisationFichier("tutu");
	    
	    /**affichage du résultat*/
	    if (res != null) {
	    	res.affichePersonnel();
	    }

	}
 	
 	
 	
 	@Test
	public void testWritePersonnelJson() {

		System.out.println("\n Test de sérialisation en json");
		
	    Personnel perso = new Builder("cedric","larnaudie")
	    		      .fonction("frere")
	    		      .numeroTelephone("123456789")
	    		      .numeroTelephone("13245896")
	    		      .build() ; 
	    
	    
	    /**affichage du résultat*/ 
	    perso.affichePersonnel();
	    
	    /**serialisation*/
	    String jsonString = perso.serialisationJson();
	    
	    System.out.println(jsonString);
	    
	    System.out.println("Test de Deserialisation avec Json") ;
	    
	    Personnel elt = perso.deSerialisationJson(jsonString);
	    
	    elt.affichePersonnel();
	    
	}
 	 
}
