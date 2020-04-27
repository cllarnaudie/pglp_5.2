package main.java.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.Personnel;

/**
 * Telephones d un personnel
 * @author claire
 * 
 * La table TELEPHONES contient deux colonnes
 *     
 *     idPerso : identifiant du personnel
 *     telephone : telephone du personnel 
 *
 */

public class TelephoneJdbcDao {

	/**connexion a la base de donnees*/
	Connection connect ;

	/**
	 * Constructeur
	 * @param connect
	 */
	public TelephoneJdbcDao(Connection connect) {

		this.connect = connect;
	}


	/**
	 * Creation des telephones dans la table TELEPHONES
	 * @param perso
	 * @param idPerso
	 * @return
	 */
	public Personnel create(Personnel perso, int idPerso) {
		if (perso != null) {

			PreparedStatement prepare = null;

			if ( (connect != null) && 
					(perso.getNumeroTelephone() != null) &&
					(!perso.getNumeroTelephone().isEmpty())){

				try {
					String chaineSQL;

					for (String tel : perso.getNumeroTelephone()) {

						chaineSQL = "INSERT INTO TELEPHONES (telephone,IdPerso) VALUES (?,?)";

						prepare = connect.prepareStatement(chaineSQL);
						prepare.setString(1, tel);
						prepare.setInt(2, idPerso);

						int result = prepare.executeUpdate();

						assert result == 1;
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
	 *Suppression des telephones dans la table TELEPHONES
	 * @param idPerso : id du personnel 
	 * @return
	 */
	public void delete(int idPerso) {

		PreparedStatement prepare = null;

		if (connect != null) {

			try {

				String sqlChaine = "DELETE FROM TELEPHONES WHERE idPerso = ? ";

				prepare = connect.prepareStatement(sqlChaine);

				prepare.setInt(1, idPerso);

				prepare.executeUpdate();


			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}	
		}
	}


	/**
	 * Mise a jour des telephones de l usager
	 * @param perso
	 * @param idPerso
	 * @return
	 */
	public Personnel update(Personnel perso, int idPerso) {

		if (perso != null) {
			// suppression des telephones de l usager
			delete(idPerso);

			// creation des telephones de l usager
			create(perso,idPerso);

		}

		return perso;
	}



	/**
	 * Recherche des telephones
	 * @param idPerso
	 * @return
	 */
	public ArrayList<String> find(int idPerso) {

		ArrayList<String> telephones = new ArrayList<>();

		PreparedStatement prepare = null;

		if (connect != null) {

			try {
				prepare = connect.prepareStatement("SELECT * FROM TELEPHONES WHERE idPerso= ? ");

				prepare.setInt(1, idPerso);

				ResultSet result = prepare.executeQuery();


				while (result.next()) {

					String telephone =result.getString("telephone");

					telephones.add(telephone); 
				}

			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return telephones;
	}

}
