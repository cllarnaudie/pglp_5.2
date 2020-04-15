package main.java;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class Personnel implements Composant, Serializable {

	private final String nom;
	private final String prenom; 
	private final String fonction; 
	private final LocalDate dateNaissance; 
	private final  ArrayList<String> numeroTelephone; 

	private static final long serialVersionUID = 150;


	public  static  class Builder {
		
		private String  nom;
		private String prenom; 
		private String fonction; 
		private LocalDate dateNaissance; 
		private ArrayList<String> numeroTelephone; 

		public Builder(String nom, String prenom) {

			this.nom = nom; 
			this.prenom = prenom; 
			this.fonction = "inconnu";
			this.dateNaissance = LocalDate.of(1, Month.JANUARY, 1); 
			this.numeroTelephone = new ArrayList<String>(); 
			
		}

		public Builder fonction(String fonction) {
			this.fonction = fonction; 
			return this; 
		}

		public Builder dateNaissance(int annee, Month mois, int jour) {
			try {

				dateNaissance = LocalDate.of(annee, mois, jour); 
			} catch (DateTimeException e) {
				System.out.println(" date de naissance incorrecte - initilialisee à la valeur par defaut \n");
				this.dateNaissance = LocalDate.of(1, Month.JANUARY, 1); 
			}
			return this; 
		}

		public Builder numeroTelephone(String numero) {
			numeroTelephone.add(numero); 
			return this; 
		}

		public Personnel build() {
			return new Personnel(this); 
		}

	}
	
	public Personnel(Builder builder) {
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


	/**
	 * Serialisation et ecriture dans le fichier
	 * @param nomFichier
	 */
	public void serialisationFichier(String nomFichier) {

		ObjectOutputStream oos = null;
		try {
			File fichier = new File(nomFichier);

			FileOutputStream file = new FileOutputStream(fichier);

			/**ouverture d un flux sur un fichier*/
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
							file));


			/**serialization de l objet*/
			oos.writeObject(this);

			oos.flush();
			oos.close();
			file.close();
		}

		catch (IOException ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}
	}



	/**
	 * Deserialisation 
	 * @param nomFichier
	 * @return
	 */
	public Personnel deserialisationFichier(String nomFichier) {
		Personnel res = null;
		try {
			File fichier = new File(nomFichier);
			FileInputStream file = new FileInputStream(fichier);

			/**ouverture d un flux sur un fichier*/
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(
							file));

			/**deserialization de l objet*/
			res = (Personnel)ois.readObject();

			ois.close();
			file.close();

		}

		catch (IOException ex) {

			ex.printStackTrace();
		}

		catch (ClassNotFoundException ex) {

			ex.printStackTrace();
		}	
		return res;
	}


	/**
	 * @return jsonString
	 */
	public String serialisationJson() {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String jsonString = gson.toJson(this);

		return jsonString;
	}


	/**
	 * @param jsonString
	 * @return res
	 */
	public Personnel deSerialisationJson(String jsonString) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Personnel res = gson.fromJson(jsonString, Personnel.class);

		return res;
	}

	/**
	 * Affiche les attributs de la classe
	 * 
	 */
	public void affichePersonnel() {

		System.out.println(nom);
		System.out.println(prenom);
		System.out.println(fonction);
		System.out.println(dateNaissance.toString());

		if (numeroTelephone != null) {
			for (int i = 0; i < numeroTelephone.size(); i++) {
				System.out.println(numeroTelephone.get(i));
			}
		}	
		System.out.println("\n"); 
	}	
}

