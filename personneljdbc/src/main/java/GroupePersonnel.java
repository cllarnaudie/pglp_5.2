package main.java;
import java.util.ArrayList; 



public  class GroupePersonnel {

	// liste de personnel
	private ArrayList <Personnel> personnels; 
	
	public GroupePersonnel() {
		personnels = new ArrayList<Personnel>(); 
	}
	
	
	/**
	 * Ajouter un personnel à la liste de personnel
	 * @param personnel
	 */
	public void ajouter_personnel(Personnel personnel) {
		if (personnels.contains(personnel) == true) {
			System.out.println ("Le membre est déjà dans la liste \n"); 
		}
		else {
			personnels.add(personnel); 
			System.out.println ("La personne " + personnel.getPrenom() + " " + 
					personnel.getNom() + " a ete ajoute de la liste \n"); 
		}
	}
	
	/**
	 * Supprimer un personnel de la liste des personnels
	 * @param personnel
	 */
	public void supprimer_personnel(Personnel personnel) {
		if (personnels.isEmpty() == true) {
			System.out.println ("La liste est vide \n"); 
		}
		else {
			
			if (personnels.contains(personnel) == true) {
				personnels.remove(personnel); 
				System.out.println ("La personne " + personnel.getPrenom() + " " + 
				personnel.getNom() + " a ete supprime de la liste \n"); 
			}
			
		}
	
	}
	


	
	
	
}

