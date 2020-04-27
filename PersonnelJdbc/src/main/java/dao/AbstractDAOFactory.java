package main.java.dao;

import main.java.Personnel;

public abstract class AbstractDAOFactory {
	public enum DaoType {JDBC, AUTRE }
	
	public abstract DAO <Personnel>  getPersonnelDAO(String param1);
	
	
	public static AbstractDAOFactory getFactory(DaoType type) {
		if (type == DaoType.JDBC) {
			return new JdbcDAOFactory();			
		}
		
		else {
			return new DAOFactory();
		}
	}

}
