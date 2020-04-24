package main.java.dao;


public abstract class AbstractDAOFactory {
	public enum DaoType {JDBC, AUTRE }
	

	public static AbstractDAOFactory getFactory(DaoType type) {
		if (type == DaoType.JDBC) {
			return new JdbcDAOFactory();
		}
		
		else {
			return new DAOFactory();
		}	
	}
}


