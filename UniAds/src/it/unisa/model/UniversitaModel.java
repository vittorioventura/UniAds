package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UniversitaModel implements DataAccesObjectInterface<Universita> {
	private DriverManagerConnectionPool dmcp = null;	

	public UniversitaModel(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Universita Model creation....");
	}
	
	private static final String TABLE_NAME = "universita";

	@Override
	public synchronized void doSave(Universita universita) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UniversitaModel.TABLE_NAME
				+ " (sigla, localita) VALUES (?, ?)";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, universita.getSigla());
			preparedStatement.setString(2, universita.getLocalita());
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
	public synchronized Universita doRetrieveByKey(Universita universita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Universita bean = new Universita();
		String sigla = universita.getSigla();
		
		String selectSQL = "SELECT * FROM " + UniversitaModel.TABLE_NAME + " WHERE sigla = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, sigla);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean.setSigla(rs.getString("sigla"));
				bean.setLocalita(rs.getString("localita"));
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
	public synchronized boolean doDelete(Universita universita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + UniversitaModel.TABLE_NAME + " WHERE sigla = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, universita.getSigla());

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
	public synchronized ArrayList<Universita> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Universita> uni = new ArrayList<Universita>();

		String selectSQL = "SELECT * FROM " + UniversitaModel.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order.toLowerCase();
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Universita bean = new Universita();

				bean.setSigla(rs.getString("sigla"));
				bean.setLocalita(rs.getString("localita"));
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
