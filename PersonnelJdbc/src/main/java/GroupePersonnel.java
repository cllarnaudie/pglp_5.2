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
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder; 

public  class GroupePersonnel  implements Serializable{
	
	private ArrayList <Personnel> personnels; 
	
	private  static  final  long serialVersionUID= 150 ; 

	public GroupePersonnel() {
		personnels = new ArrayList<Personnel>(); 
	}


	/**
	 * Ajouter un personnel a la liste de personnel
	 * @param personnel
	 */
	public void ajouter_personnel(Personnel personnel) {
		if (personnels.contains(personnel) == true) {
			System.out.println ("Le membre est déjà dans la liste \n"); 
		}
		else {
			personnels.add(personnel); 
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


	/**
	 * Serialisation 
	 * @param personne
	 * @param fichier
	 */
	public void serialisationFichier(String nomFichier) {

		ObjectOutputStream oos=null ;
		try {
			File fichier =  new File(nomFichier) ;

			FileOutputStream file = new FileOutputStream(fichier);

			/**ouverture d un flux sur un fichier*/
			oos =  new ObjectOutputStream(
					new BufferedOutputStream(
							file) );

			/**serialization de l objet*/
			oos.writeObject(this) ;

			oos.flush();
			oos.close();
			file.close();

		}
		catch (IOException ex) {
			
			ex.printStackTrace();
		}
	}

	
	
	/**
	 * Deserialisation
	 *
	 * @param fichier
	 */
	public GroupePersonnel deserialisationFichier(String nomFichier) {
		GroupePersonnel res = null;
		try {
			File fichier =  new File(nomFichier) ;
			FileInputStream file = new FileInputStream(fichier);

			/**ouverture d un flux sur un fichier*/
			ObjectInputStream ois =  new ObjectInputStream(
					new BufferedInputStream(
							file));

			/**deserialization de l objet*/
			res = (GroupePersonnel)ois.readObject() ;

			ois.close();
			file.close();
		}
		
		catch (IOException ex) {

			ex.printStackTrace();
		}
		catch (ClassNotFoundException ex) {

			ex.printStackTrace();
		}	
		return res ;
	}


	public String  serialisationJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String jsonString = gson.toJson(this);

		return jsonString;

	}
	
	/**
	 * @param jsonString
	 * @return
	 */
	public GroupePersonnel deSerialisationJson(String jsonString  ) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		GroupePersonnel res = gson.fromJson(jsonString, GroupePersonnel.class);

		return res;

	}
	
	
	
	/**
	 * Affiche les attributs de la classe
	 * */
	 
	 
	public void afficheGroupePersonnel() {

		for ( Personnel elt : personnels) {

			elt.affichePersonnel();
		}
	}
	
	/**
	 * Retourne la fonction du groupe
	 * @return
	 */
	public String getFunction() {

		String res = new String("");
		
		if ( (personnels != null) && (!personnels.isEmpty())) {
			res = personnels.get(0).getFonction();
		}
		return res;
	}

}

