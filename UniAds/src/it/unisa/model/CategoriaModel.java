package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaModel implements DataAccesObjectInterface<Categoria> {
	private DriverManagerConnectionPool dmcp = null;	

	public CategoriaModel(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Categoria Model creation....");
	}
	
	private static final String TABLE_NAME = "categoria";

	@Override
	public synchronized void doSave(Categoria categoria) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + CategoriaModel.TABLE_NAME
				+ " (nome) VALUES (?)";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, categoria.getNome());
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
	public synchronized Categoria doRetrieveByKey(Categoria categoria) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Categoria bean = new Categoria();
		String nome = categoria.getNome();
		
		String selectSQL = "SELECT * FROM " + CategoriaModel.TABLE_NAME + " WHERE nome = ?";

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
	public synchronized boolean doDelete(Categoria categoria) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + CategoriaModel.TABLE_NAME + " WHERE nome = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, categoria.getNome());

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
	public synchronized ArrayList<Categoria> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Categoria> categorie = new ArrayList<Categoria>();

		String selectSQL = "SELECT * FROM " + CategoriaModel.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order.toLowerCase();
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Categoria bean = new Categoria();

				bean.setNome(rs.getString("nome"));
				categorie.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return categorie;
	}
	
}
