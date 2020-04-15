package main.java.dao;

import main.java.GroupePersonnel;
import main.java.Personnel;

/**
 * Fabrique pour l instanciation des DAO
 * @author devc
 *
 */
public class DAOFactory {
	
	public static DAO<Personnel> getPersonnelDAO(String nomFichier) {
		
		return PersonnelDAO.getInstance(nomFichier);
	}
	
	public static DAO<GroupePersonnel> getGroupePersonnelDAO(String nomFichier) {
		
		return GroupePersonnelDAO.getInstance(nomFichier);
	}
}



