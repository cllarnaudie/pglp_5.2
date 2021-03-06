package main.java.dao;

import main.java.GroupePersonnel;
import main.java.Personnel;
import main.java.dao.xml.GroupePersonnelDAO;
import main.java.dao.xml.PersonnelDAO;

/**
 * Fabrique pour l instanciation des DAO
 * @author claire
 *
 */
public class DAOFactory extends AbstractDAOFactory {
	
	public DAO <Personnel> getPersonnelDAO(String nomFichier) {
		
		return PersonnelDAO.getInstance(nomFichier);
	}
	
	public static DAO <GroupePersonnel> getGroupePersonnelDAO(String nomFichier) {
		
		return GroupePersonnelDAO.getInstance(nomFichier);
	}
}
