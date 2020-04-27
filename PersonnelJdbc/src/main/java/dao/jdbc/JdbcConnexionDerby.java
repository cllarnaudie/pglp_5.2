package main.java.dao.jdbc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * Connection au SGBD  DERBY
 * 
 * La base usersdb et les tables de la base ont ete crees via l utilitaire ij de DERBY
 *
 * @author claire
 *
 */

public class JdbcConnexionDerby {
	
	/**nom de la base de donnees*/
	private String nomBase;
	
	/**repertoire de la base de donnees*/
	private String repertoireBase;
	
	
	
	
	/**
	 * Constructeur
	 * @param nomBase : nom de la base de donnees
	 * @param repertoireBase : repertoire de la base de donnees
	 * 
	 */
	public JdbcConnexionDerby(String nomBase,String repertoireBase) {
		
		try {
			this.nomBase = nomBase;
			this.repertoireBase = repertoireBase;
		
			// charge le driver derby en mode embarque
	    	Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		}
		catch ( ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * Retourne la chaine de connexion a la base de donnéees
	 * @return
	 */
	public Connection getChaineConnexion() {
		 
		Connection connect = null;
		
		String url = "jdbc:derby:"+ repertoireBase + nomBase +";create=true";
		
		try {
			
			connect = DriverManager.getConnection(url);
			
			if (connect == null) {
				System.out.println("Connexion à la table refusee");

			}

		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return connect;
	}
	
	
	/**
	 * Fermeture de la connexion jdbc
	 * @param connect
	 */
	public static void closeConnexion(Connection connect) {

		if (connect != null) {

			try {
				connect.close();
			}

			catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
	}
	
		
}
