package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.GenericUser.Ruolo;

public class AmministratoreModel implements DataAccesObjectInterface<Amministratore>{
	private DriverManagerConnectionPool dmcp = null;	

	public AmministratoreModel(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Amministratore Model creation....");
	}
	
	private static final String TABLE_NAME = "utente";

	@Override
	public synchronized void doSave(Amministratore amministratore) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + AmministratoreModel.TABLE_NAME
				+ " (email, password, nome, cognome,indirizzo,ruolo) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, amministratore.getEmail());
			preparedStatement.setString(2, amministratore.getPassword());
			preparedStatement.setString(3, amministratore.getNome());
			preparedStatement.setString(4, amministratore.getCognome());
			preparedStatement.setString(5, amministratore.getIndirizzo());
			preparedStatement.setString(6, amministratore.getRuolo().toString());
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
	public synchronized Amministratore doRetrieveByKey(Amministratore amministratore) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Amministratore bean = new Amministratore();
		String email = amministratore.getEmail();
		
		String selectSQL = "SELECT * FROM " + AmministratoreModel.TABLE_NAME + " WHERE email = ?";

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
	public synchronized boolean doDelete(Amministratore amministratore) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + AmministratoreModel.TABLE_NAME + " WHERE email = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, amministratore.getEmail());

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
	public synchronized ArrayList<Amministratore> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Amministratore> amministratori = new ArrayList<Amministratore>();

		String selectSQL = "SELECT * FROM " + AmministratoreModel.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order.toLowerCase();
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Amministratore bean = new Amministratore();

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
				amministratori.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return amministratori;
	}

}
