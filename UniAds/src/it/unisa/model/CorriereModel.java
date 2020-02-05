package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.GenericUser.Ruolo;

public class CorriereModel implements DataAccesObjectInterface<Corriere>  {
	private DriverManagerConnectionPool dmcp = null;	

	public CorriereModel(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Corriere Model creation....");
	}
	
	private static final String TABLE_NAME = "corriere";

	@Override
	public synchronized void doSave(Corriere corriere) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + CorriereModel.TABLE_NAME
				+ " (email_utente, agenzia, password, ruolo) VALUES (?, ?, ?, ?)";
		
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, corriere.getEmail());
			preparedStatement.setString(2, corriere.getNomeAgenzia());
			preparedStatement.setString(3, corriere.getPassword());
			preparedStatement.setString(4, corriere.getRuolo().toString());
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
	public synchronized Corriere doRetrieveByKey(Corriere corriere) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Corriere bean = new Corriere();
		String email = corriere.getEmail();
		String selectSQL = "SELECT * FROM " + CorriereModel.TABLE_NAME + " WHERE email_utente = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean.setEmail(rs.getString("email_utente"));
				bean.setNomeAgenzia(rs.getString("agenzia"));
				bean.setPassword(rs.getString("password"));
				
				UtenteModel modelUtente = new UtenteModel(dmcp);	
				Utente utente = new Utente();
				utente.setEmail(bean.getEmail());
				
				String nome = modelUtente.doRetrieveByKey(utente).getNome();
				bean.setNome(nome);
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
	public synchronized boolean doDelete(Corriere corriere) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + "utente" + " WHERE email = ?";
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, corriere.getEmail());

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
	public synchronized ArrayList<Corriere> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Corriere> corrieri = new ArrayList<Corriere>();

		String selectSQL = "SELECT * FROM " + CorriereModel.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order.toLowerCase();
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Corriere bean = new Corriere();

				bean.setEmail(rs.getString("email_utente"));
				bean.setNomeAgenzia(rs.getString("agenzia"));
				if(rs.getString("ruolo").equals("AMMINISTRATORE")) {
					bean.setRuolo(Ruolo.AMMINISTRATORE);
				}
				else if(rs.getString("ruolo").equals("CORRIERE")) {
					bean.setRuolo(Ruolo.CORRIERE);
				}
				else {
					bean.setRuolo(Ruolo.UTENTE);
				}
				corrieri.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return corrieri;
	}

}
