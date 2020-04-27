package test.java.jdbc;

import java.time.Month;
import org.junit.Test;

import main.java.Personnel;
import main.java.Personnel.Builder;
import main.java.dao.AbstractDAOFactory;
import main.java.dao.AbstractDAOFactory.DaoType;
import main.java.dao.jdbc.PersonnelJdbcDao;

/**
 * Tests de la classe JdbcDaoPersonnel
 * @author claire
 *
 */

public class TestJPersonnelJdbcDao {
	
	@Test
	public void testCreate() {
		
		System.out.println("\nTests de creation");
		
		/**recuperation de la Dao et ouverture de la connexion a la base*/
		PersonnelJdbcDao  daoPersonnel = (PersonnelJdbcDao)AbstractDAOFactory.getFactory(DaoType.JDBC).getPersonnelDAO( "/home/devc/tools/derby/");
		
		  
		 Personnel perso = new Builder("durant","audrey")
  		      .fonction("chef")
  		      .build(); 
		 
		 daoPersonnel.create(perso);
		 
		   
		Personnel res = daoPersonnel.find("durant");
				 
		if (res != null) {
				System.out.println("Creation et recherche OK");
				res.affichePersonnel();
		}
		else {
			System.out.println("Creation NOK");
		}
		
		daoPersonnel.delete(perso);
			
		/**fermeture de la connexion a la base*/
		daoPersonnel.closeConnection();
	}
	
	
	
	/**
	 * Tests de mise a jour
	 */
	
	@Test
	public void testUpDate() {
		
		System.out.println("\nTests de mises a jour");
		
		
		/**recuperation de la Dao et ouverture de la connexion a la base*/
		PersonnelJdbcDao  daoPersonnel = (PersonnelJdbcDao)AbstractDAOFactory.getFactory(DaoType.JDBC).getPersonnelDAO( "/home/devc/tools/derby/");

		
		/**mise a jour d un personnel existant en base*/
		Personnel perso2 = new Builder("dupond","felicie")
				.fonction("chef")
				.numeroTelephone("02134567")
				.numeroTelephone("01020304")
				.dateNaissance(2010, Month.MAY, 24)
				.build(); 

		daoPersonnel.create(perso2);

		Personnel perso = new Builder("dupond","felicie")
				.fonction("chef")
				.numeroTelephone("02134567")
				.numeroTelephone("01020102")
				.dateNaissance(2010, Month.MAY, 27)
				.build(); 

		daoPersonnel.update(perso);
		
		Personnel res = daoPersonnel.find("dupond") ;
		 
		 if (res != null) {
			 System.out.println("Mise a jour  OK");
			 res.affichePersonnel();
		 }
		 /**suppression de l element pour pouvoir refaire le test*/
		 daoPersonnel.delete(perso);
		 
		 
		/** mise a jour d un element non existant en base*/
		Personnel perso1 = new Builder("dupond","felicie")
				.fonction("chef")
				.numeroTelephone("0213456")
				.numeroTelephone("01020304")
				.dateNaissance(2010, Month.MAY, 29)
				.build(); 

		daoPersonnel.update(perso1);
		res = daoPersonnel.find("dupond") ;
		 
		 if (res != null) {
			 System.out.println("\nMise a jour et recherche  OK");
			 res.affichePersonnel();
		 }
		 /**suppression de l element pour pouvoir refaire le test*/
		 daoPersonnel.delete(perso1);
		 
		/**fermeture de la connexion a la base*/
		 daoPersonnel.closeConnection();

	}
	

	/**
	 * Test de suppression
	 */
	@Test
	public void testDelete() {
	
		 System.out.println("\nTests de suppression");
		 		 
		/**recuperation de la Dao et ouverture de la connexion a la base*/
		PersonnelJdbcDao  daoPersonnel = (PersonnelJdbcDao)AbstractDAOFactory.getFactory(DaoType.JDBC).getPersonnelDAO( "/home/devc/tools/derby/");

		 
		 Personnel perso2 = new Builder("larnodi","isa")
  		      .fonction("chef")
  		      .numeroTelephone("0213456")
  		      .numeroTelephone("01020304")
  		      .build(); 
		 
		 daoPersonnel.create(perso2);
		 
		 daoPersonnel.delete(perso2);
		 
		 Personnel res = daoPersonnel.find(perso2.getNom());
		 
		 if (res == null) {
			 System.out.println("Suppression et recherche OK");
		 }
		 
		/**fermeture de la connexion a la base*/
		 daoPersonnel.closeConnection();
	}
	
}
