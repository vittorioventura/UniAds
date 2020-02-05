package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.GenericUser.Ruolo;

public class UtenteModel implements DataAccesObjectInterface<Utente>{
	private DriverManagerConnectionPool dmcp = null;	

	public UtenteModel(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Utente Model creation....");
	}
	
	private static final String TABLE_NAME = "utente";

	@Override
	public synchronized void doSave(Utente utente) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UtenteModel.TABLE_NAME
				+ " (email, password, nome, cognome,indirizzo,ruolo) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente.getEmail());
			preparedStatement.setString(2, utente.getPassword());
			preparedStatement.setString(3, utente.getNome());
			preparedStatement.setString(4, utente.getCognome());
			preparedStatement.setString(5, utente.getIndirizzo());
			preparedStatement.setString(6, utente.getRuolo().toString());
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
	public synchronized Utente doRetrieveByKey(Utente utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Utente bean = new Utente();
		String email = utente.getEmail();
		
		String selectSQL = "SELECT * FROM " + UtenteModel.TABLE_NAME + " WHERE email = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPassword(rs.getString("password"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				if(rs.getString("ruolo").equals("AMMINISTRATORE")) {
					bean.setRuolo(Ruolo.AMMINISTRATORE);
				}
				else if(rs.getString("ruolo").equals("CORRIERE")) {
					bean.setRuolo(Ruolo.CORRIERE);
				}
				else {
					bean.setRuolo(Ruolo.UTENTE);
				}
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
	public synchronized boolean doDelete(Utente utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + UtenteModel.TABLE_NAME + " WHERE email = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, utente.getEmail());

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
	public synchronized ArrayList<Utente> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Utente> utenti = new ArrayList<Utente>();

		String selectSQL = "SELECT * FROM " + UtenteModel.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order.toLowerCase();
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Utente bean = new Utente();

				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPassword(rs.getString("password"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				if(rs.getString("ruolo").equals("AMMINISTRATORE")) {
					bean.setRuolo(Ruolo.AMMINISTRATORE);
				}
				else if(rs.getString("ruolo").equals("CORRIERE")) {
					bean.setRuolo(Ruolo.CORRIERE);
				}
				else {
					bean.setRuolo(Ruolo.UTENTE);
				}
				utenti.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return utenti;
	}


}
