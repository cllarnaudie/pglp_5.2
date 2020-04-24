package main.java.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.Personnel;
import main.java.Personnel.Builder;
import main.java.dao.DAO;

public class JdbcDAOPersonnel extends DAO <Personnel>  {


	// connexion a la base de donnees
	Connection connect ;


	/**
	 * 
	 * @param nomBase
	 * @param repertoireBase
	 */
	public JdbcDAOPersonnel(String nomBase,String repertoireBase) {

		JdbcConnexionDerby driver = new JdbcConnexionDerby(nomBase, repertoireBase);

		connect = driver.getChaineConnexion();
	}


	/**
	 * CReation d'un personnel dans la table PERSONNEL
	 * @param perso
	 * @return
	 */


	public Personnel create(Personnel perso) {
		if (perso != null) {

			PreparedStatement prepare =null;
			Date datePerso = null ;

			if (connect != null) {

				try {
					String chaineSQL;

					if (perso.getDateNaissance() != null) {						
						chaineSQL = "INSERT INTO PERSONNEL (nom,prenom,fonction,dateNaissance) VALUES (?,?,?,?)";

						prepare = connect.prepareStatement(chaineSQL);
						prepare.setString(1, perso.getNom());
						prepare.setString(2, perso.getPrenom());
						prepare.setString(3, perso.getFonction());

						datePerso = Date.valueOf(perso.getDateNaissance());
						prepare.setDate(4, datePerso);

					}
					else {
						chaineSQL = "INSERT INTO PERSONNEL (nom,prenom,fonction) VALUES (?,?,?)";
						prepare = connect.prepareStatement(chaineSQL);

						prepare.setString(1, perso.getNom());
						prepare.setString(2, perso.getPrenom());
						prepare.setString(3, perso.getFonction());
					}


					int result = prepare.executeUpdate();

					assert result == 1 ;
				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}

			}
		}
		return perso;
	}




	/**
	 * Creation d'un personnel dans la table Personnel
	 * @param perso
	 * @return
	 */

	public Personnel update(Personnel perso) {

		if (perso != null) {

			PreparedStatement prepare =null;
			Date datePerso = null ;

			if (connect != null) {

				try {
					String chaineSQL;

					if (perso.getDateNaissance() != null) {						
						chaineSQL = "UPDATE PERSONNEL SET dateNaissance = ? WHERE nom = ? AND prenom = ? AND fonction = ? ";

						prepare = connect.prepareStatement(chaineSQL);

						datePerso = Date.valueOf(perso.getDateNaissance());
						prepare.setDate(1, datePerso);
						prepare.setString(2, perso.getNom());
						prepare.setString(3, perso.getPrenom());
						prepare.setString(4, perso.getFonction());


						datePerso = Date.valueOf(perso.getDateNaissance());
						prepare.setDate(4, datePerso);

					}

					int result = prepare.executeUpdate();

					assert result == 1 ;
				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

		return perso;

	}



	/**
	 *Suppression d'un personnel dans la table Personnel
	 * @param perso : personnel a supprimer
	 * 
	 */
	public void delete(Personnel perso) {

		if (perso != null) {

			PreparedStatement prepare =null;

			if (connect != null) {

				try {

					String sqlChaine = "DELETE FROM PERSONNEL WHERE nom = ? AND prenom = ? AND fonction = ? ";

					prepare = connect.prepareStatement(sqlChaine);

					prepare.setString(1, perso.getNom());
					prepare.setString(2, perso.getPrenom());
					prepare.setString(3, perso.getFonction());

					int result = prepare.executeUpdate();

					assert result == 1 ;
				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}	
			}

		}		
	}


	/**
	 * @param id : nom du personnel
	 * @return perso
	 */
	@Override
	public Personnel find(String id) {

		Personnel  perso =null;
		if (id != null) {

			PreparedStatement prepare =null;

			if (connect != null) {

				try {
					prepare = connect.prepareStatement("SELECT * FROM PERSONNEL WHERE nom= ? ");

					prepare.setString(1, id);

					ResultSet result = prepare.executeQuery();

					if (result.first()) {
						perso = new Builder (result.getString("nom"),result.getString("prenom") )
								.fonction (result.getString("fonction"))
								.build();
					}

				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}

			}
		}

		return perso;
	}



	public static void main(String[] args) throws Exception {

		JdbcDAOPersonnel daoPersonnel = new JdbcDAOPersonnel("usersdb", "/home/devc/tools/derby/");

		Personnel perso = new Builder("larnaudie","audrey")
				.fonction("chef")
				.numeroTelephone("0213456")
				.numeroTelephone("01020304")
				.build() ; 

		daoPersonnel.create(perso);

		Personnel res = daoPersonnel.find(perso.getNom()) ;

		if (res != null) {
			res.affichePersonnel();
		}

	}

}

