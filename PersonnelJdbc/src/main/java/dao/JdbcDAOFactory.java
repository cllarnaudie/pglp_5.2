package main.java.dao;

import main.java.Personnel;
import main.java.dao.jdbc.PersonnelJdbcDao;

/**
 * Fabrique pour l instanciation des DAO
 * @author claire
 *
 */
public class JdbcDAOFactory extends AbstractDAOFactory {
	
	
	public DAO <Personnel> getPersonnelDAO(String repertoireBase) {
		
		return  new PersonnelJdbcDao("usersdb", repertoireBase);
		
	}
		
}
