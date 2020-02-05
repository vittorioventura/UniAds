package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegioneModel implements DataAccesObjectInterface<Regione>{
	private DriverManagerConnectionPool dmcp = null;	

	public RegioneModel(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Regione Model creation....");
	}
	
	private static final String TABLE_NAME = "regione";

	@Override
	public synchronized void doSave(Regione regione) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + RegioneModel.TABLE_NAME
				+ " (nome) VALUES (?)";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, regione.getNome());
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
	public synchronized Regione doRetrieveByKey(Regione regione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Regione bean = new Regione();
		String nome = regione.getNome();
		
		String selectSQL = "SELECT * FROM " + RegioneModel.TABLE_NAME + " WHERE nome = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean.setNome(rs.getString("nome"));
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
	public synchronized boolean doDelete(Regione regione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + RegioneModel.TABLE_NAME + " WHERE nome = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, regione.getNome());

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
	public synchronized ArrayList<Regione> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Regione> regioni = new ArrayList<Regione>();

		String selectSQL = "SELECT * FROM " + RegioneModel.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order.toLowerCase();
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Regione bean = new Regione();

				bean.setNome(rs.getString("nome"));
				regioni.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return regioni;
	}
}
