package test.java.jdbc;

import java.sql.Connection;
import org.junit.Test;
import main.java.dao.jdbc.JdbcConnexionDerby;

public class TestJdbcConnexionDerby {

	@Test
	public void testConnexion() {
		
		JdbcConnexionDerby driver = new JdbcConnexionDerby("usersdb", "/home/devc/tools/derby/");
	      
		/**connexion a la base*/
		Connection connect = driver.getChaineConnexion();
		
		/**fermeture de la connexion*/
		JdbcConnexionDerby.closeConnexion(connect);
		
	}

}
