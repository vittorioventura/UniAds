package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PreferitiModel implements DataAccesObjectInterface<Preferiti>{
	private DriverManagerConnectionPool dmcp = null;	

	public PreferitiModel(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Preferiti Model creation....");
	}
	
	private static final String TABLE_NAME = "desidera";

	@Override
	public synchronized void doSave(Preferiti preferiti) throws SQLException {
  
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + PreferitiModel.TABLE_NAME
				+ " (email_annuncio, titolo_annuncio,mail_utente) VALUES (?, ?, ?)";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, preferiti.getEmailUtenteAnnuncio());
			preparedStatement.setString(2, preferiti.getTitoloAnnuncio());
			preparedStatement.setString(3, preferiti.getEmailUtente());

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
	public synchronized Preferiti doRetrieveByKey(Preferiti preferiti) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Preferiti bean = new Preferiti();
		String email = preferiti.getEmailUtente();
		String titolo = preferiti.getTitoloAnnuncio();
		String emailAnnuncio = preferiti.getEmailUtenteAnnuncio();
		String selectSQL = "SELECT * FROM " + PreferitiModel.TABLE_NAME + " WHERE mail_utente = ? AND titolo_annuncio = ? AND email_annuncio = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, titolo);
			preparedStatement.setString(3, emailAnnuncio);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean.setEmailUtente(rs.getString("mail_utente"));
				bean.setTitoloAnnuncio(rs.getString("titolo_annuncio"));
				bean.setEmailUtenteAnnuncio(rs.getString("email_annuncio"));
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
	public synchronized boolean doDelete(Preferiti preferiti) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + PreferitiModel.TABLE_NAME + " WHERE mail_utente = ? AND titolo_annuncio = ? AND email_annuncio = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, preferiti.getEmailUtente());
			preparedStatement.setString(2, preferiti.getTitoloAnnuncio());
			preparedStatement.setString(3, preferiti.getEmailUtenteAnnuncio());
			

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
	public synchronized ArrayList<Preferiti> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Preferiti> uni = new ArrayList<Preferiti>();

		String selectSQL = "SELECT * FROM " + PreferitiModel.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order.toLowerCase();
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Preferiti bean = new Preferiti();

				bean.setEmailUtente(rs.getString("mail_utente"));
				bean.setTitoloAnnuncio(rs.getString("titolo_annuncio"));
				bean.setEmailUtenteAnnuncio(rs.getString("email_annuncio"));
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
