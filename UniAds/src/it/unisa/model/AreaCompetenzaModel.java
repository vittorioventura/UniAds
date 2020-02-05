package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AreaCompetenzaModel implements DataAccesObjectInterface<AreaCompetenza> {
	private DriverManagerConnectionPool dmcp = null;	

	public AreaCompetenzaModel(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Universita Model creation....");
	}
	
	private static final String TABLE_NAME = "consegna_in";

	@Override
	public synchronized void doSave(AreaCompetenza area) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + AreaCompetenzaModel.TABLE_NAME
				+ " (prezzo, agenzia_corriere, nome_regione, email_utente) VALUES (?, ?, ?, ?)";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setDouble(1, area.getPrezzo());
			preparedStatement.setString(2, area.getAgenzia());
			preparedStatement.setString(3, area.getNomeRegione());
			preparedStatement.setString(4, area.getEmailCorriere());
			
			
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
	}

	@Override
	public synchronized AreaCompetenza doRetrieveByKey(AreaCompetenza area) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		AreaCompetenza bean = new AreaCompetenza();
		String agenzia = area.getAgenzia();
		String email = area.getEmailCorriere();
		String selectSQL = "SELECT * FROM " + AreaCompetenzaModel.TABLE_NAME + " WHERE agenzia_corriere = ? AND email_utente = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, agenzia);
			preparedStatement.setString(2, email);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setEmailCorriere(rs.getString("email_utente"));
				bean.setAgenzia(rs.getString("agenzia_corriere"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setNomeRegione(rs.getString("nome_regione"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return bean;
	}

	@Override
	public synchronized boolean doDelete(AreaCompetenza area) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + AreaCompetenzaModel.TABLE_NAME + " WHERE agenzia_corriere = ? AND nome_regione= ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, area.getAgenzia());
			preparedStatement.setString(2, area.getNomeRegione());
			
			result = preparedStatement.executeUpdate();

			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized ArrayList<AreaCompetenza> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<AreaCompetenza> uni = new ArrayList<AreaCompetenza>();

		String selectSQL = "SELECT * FROM " + AreaCompetenzaModel.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order.toLowerCase();
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				AreaCompetenza bean = new AreaCompetenza();
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setEmailCorriere(rs.getString("email_utente"));
				bean.setNomeRegione(rs.getString("nome_regione"));
				bean.setAgenzia(rs.getString("agenzia_corriere"));
				uni.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return uni;
	}



}
