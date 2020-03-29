package main.java;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
/**
 * classe immuable donc final devant classe 
 * une classe immuable est une classe dont les instances sont immuables par conception et implémentation
 * c est une classe dont les instances ne peuvent pas etre modifiees apres implementation
 * */
public final class Personnel implements Composant{
	
    private final String nom;
	private final String prenom; 
	private final String fonction; 
	private final LocalDate dateNaissance; 
	private final ArrayList <String> numeroTelephone; 
	
	public  static class Builder  {
	    private  String nom;
		private  String prenom; 
		private  String fonction; 
		private  LocalDate dateNaissance; 
		private  ArrayList <String> numeroTelephone; 
	
	public Builder (String nom, String prenom){

		this.nom = nom; 
        this.prenom = prenom; 
		this.fonction = "inconnu";
		this.dateNaissance = LocalDate.of(1, Month.JANUARY, 1); 
		this.numeroTelephone = new ArrayList<String> (); 
		
	}
	
	public Builder fonction (String fonction) {
		this.fonction = fonction; 
		return this; 
	}
	
	public Builder dateNaissance (int annee, Month mois, int jour) {
		try {
					
		dateNaissance = LocalDate.of(annee, mois, jour); 
		}catch (DateTimeException  e) {
			System.out.println(" date de naissance incorrecte - initilialisee à la valeur par defaut \n");
			this.dateNaissance = LocalDate.of(1, Month.JANUARY, 1); 
		}
		return this; 
		
	}
	
	
	
	public Builder numeroTelephone(String numero) {
		numeroTelephone.add(numero); 
		return this; 
		}
	
	public Personnel build () {
		return new  Personnel (this); 
	}
		
}
	public Personnel (Builder builder) {
		nom = builder.nom; 
		prenom = builder.prenom; 
		fonction = builder.fonction; 
		dateNaissance = builder.dateNaissance; 
		numeroTelephone = builder.numeroTelephone; 
		
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public String getFonction() {
		return fonction;
	}
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	public ArrayList<String> getNumeroTelephone() {
		return numeroTelephone;
	}
	
	
	
	
}

