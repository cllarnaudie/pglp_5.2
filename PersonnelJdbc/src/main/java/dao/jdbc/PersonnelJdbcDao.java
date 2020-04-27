package main.java.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import main.java.Personnel;
import main.java.Personnel.Builder;
import main.java.dao.DAO;


/**
 * 
 * CRUD pour acceder a la table PERSONNEL sur la base de donnees DERBY via jdbc
 * 
 * 
 * La table PERSONNEL contient les colonnes      
 *        nom
 *        prenom
 *        fonction
 *        date
 *        dateNaissance
 *        idPerso
 * 
 * Les telephones de la personne sont dans la table TELEPHONES en utilisant idPerso
 * 
 * 
 * @author claire
 *
 */

public class PersonnelJdbcDao extends DAO <Personnel>  {
	
	
	// connexion a la base de donnees
	Connection connect ;
	
	
	/**
	 * 
	 * @param nomBase
	 * @param repertoireBase
	 */
	
	public PersonnelJdbcDao(String nomBase,String repertoireBase) {
		
		JdbcConnexionDerby driver = new JdbcConnexionDerby(nomBase, repertoireBase);
		
		connect = driver.getChaineConnexion();
	}
	
	
	/**
	 * Creation d'un personnel dans la table PERSONNEL
	 * @param perso : personnel
	 * @return
	 */

	
	public Personnel create(Personnel perso) {
		if (perso != null) {

			PreparedStatement prepare =null;
			Date datePerso = null;

			// si la connexion a la base est OK et que l element n existe pas deja en base
			if ( (connect != null)  && (getIdPersonnel(perso)== -1)) {

				try {
					
					String chaineSQL = "INSERT INTO PERSONNEL (nom,prenom,fonction,dateNaissance) VALUES (?,?,?,?)";

					prepare = connect.prepareStatement(chaineSQL);
					prepare.setString(1, perso.getNom());
					prepare.setString(2, perso.getPrenom());
					prepare.setString(3, perso.getFonction());

					datePerso = Date.valueOf(perso.getDateNaissance());
					prepare.setDate(4, datePerso);

					int result = prepare.executeUpdate();
					
					

					assert result == 1 ;

					// creation des telephones
					if ((perso.getNumeroTelephone() != null) && (!perso.getNumeroTelephone().isEmpty())) {
						int id = getIdPersonnel(perso);
						if (id != -1) {
							TelephoneJdbcDao daoTelephone = new TelephoneJdbcDao(connect);
							daoTelephone.create(perso,id);
						}

					}

				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}

		}

	}

	return perso;
}

	
	
	
	/**
	 * Mise a jour d'un personnel dans la table PERSONNEL
	 * @param perso : personnel
	 * @return
	 */

	public Personnel update(Personnel perso) {
		
		if (perso != null) {

			PreparedStatement prepare =null;
			Date datePerso = null;

		if (connect != null) {

			try {
				String chaineSQL;

				if (perso != null) {						
					chaineSQL = "UPDATE PERSONNEL SET dateNaissance = ?, fonction = ? WHERE nom = ? AND prenom = ?  ";

					prepare = connect.prepareStatement(chaineSQL);
                    
					datePerso = Date.valueOf(perso.getDateNaissance());
					prepare.setDate(1, datePerso);
					prepare.setString(2, perso.getFonction());
					prepare.setString(3, perso.getNom());
					prepare.setString(4, perso.getPrenom());
					
					int result = prepare.executeUpdate();

					// si aucune mise à jour possible 
					//       faire une creatio
					if (result == 0) {
						create(perso);
					}
					
					
					// mise a jour des telephones
					if (!perso.getNumeroTelephone().isEmpty()) {
						int id = getIdPersonnel(perso);
						if (id != -1) {
							TelephoneJdbcDao daoTelephone = new TelephoneJdbcDao(connect);
							daoTelephone.update(perso,id);
						}
					    
					}
				}
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	return perso;
		
	}


	
	/**
	 *Suppression d'un personnel dans la table PERSONNEL
	 * @param perso : personnel a supprimer
	 * @return
	 */
	
	public void delete(Personnel perso) {

		if (perso != null) {

			PreparedStatement prepare = null;
			
			// identifiant de l usager en base
			int idPerso=  getIdPersonnel(perso);
			
			
			// si connexion a la base est OK et si l usager est enregistre en base
			if ( (connect != null) && (idPerso!= -1)) {
								
				try {
					// suppression des telephones de l usager
					deleteTelephones(perso, idPerso);
					

					String sqlChaine = "DELETE FROM PERSONNEL WHERE nom = ? AND prenom = ? ";

					prepare = connect.prepareStatement(sqlChaine);

					prepare.setString(1, perso.getNom());
					prepare.setString(2, perso.getPrenom());
					
					prepare.executeUpdate();
					

				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}	
			}

		}		
	}

	
/**
 * Suppression des telephones de l usager
 * @param perso
 * @param idPerso
 */
	public void deleteTelephones(Personnel perso, int idPerso) {

		// suppression des telephones
		if ((!perso.getNumeroTelephone().isEmpty()) &&
			(idPerso != -1)) {
				TelephoneJdbcDao daoTelephone = new TelephoneJdbcDao(connect);
				daoTelephone.delete(idPerso);
			

		}
	}

	
	/**
	 * Recherche d'un personnel à partir de son nom
	 * @param id : nom du personnel
	 * @return
	 * 
	 */
	@Override
	public Personnel find(String id) {

		Personnel perso = null;
		if (id != null) {

			PreparedStatement prepare =null;

			if (connect != null) {

				try {
					prepare = connect.prepareStatement("SELECT * FROM PERSONNEL WHERE nom= ? ");

					prepare.setString(1,id);

					ResultSet result = prepare.executeQuery();


					while (result.next()) {

						LocalDate dateRes = null;

						Date dateNaissance = result.getDate("dateNaissance");
						if (dateNaissance != null) {
							dateRes = dateNaissance.toLocalDate();

						}

						String nom = result.getString("nom");
						String prenom = result.getString("prenom");
						String fonction = result.getString("fonction");
						int idPerso = result.getInt("idPerso");
						
						TelephoneJdbcDao daoTelephone = new TelephoneJdbcDao(connect);
						ArrayList<String> numeros = daoTelephone.find(idPerso);
						
						if (dateRes != null) {
							perso = new Builder(nom,prenom)
									.fonction(fonction)
									.dateNaissance(dateRes.getYear(), dateRes.getMonth(), dateRes.getDayOfMonth())
									.telephones(numeros)
									.build();
						}
						else {
							perso = new Builder(nom,prenom)
									.fonction(fonction)
									.telephones(numeros)
									.build();
						}
						
					}

				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}

			}

		}

		return perso;
	}
	
	
	
	/**
	 * Recherche de l id du personnel
	 * @param perso
	 * @return
	 */
	public int getIdPersonnel(Personnel perso) {

		int id = -1;
		PreparedStatement prepare = null;
		if (connect != null) {

			try {
				prepare = connect.prepareStatement("SELECT * FROM PERSONNEL WHERE nom= ? and prenom=?");

				prepare.setString(1, perso.getNom());
				prepare.setString(2, perso.getPrenom());

				ResultSet result = prepare.executeQuery();

				while (result.next()) {

					id = result.getInt("IdPerso");

				}
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return id;
	}

	
	
	/**
	 * Fermeture de la connexion a la table
	 */
	public void closeConnection() {
		
		JdbcConnexionDerby.closeConnexion(connect);
		
	}
	
}
