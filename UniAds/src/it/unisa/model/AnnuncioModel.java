package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnnuncioModel implements DataAccesObjectInterface<Annuncio>{

	private DriverManagerConnectionPool dmcp = null;	

	public AnnuncioModel(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Annuncio Model creation....");
	}
	
	private static final String TABLE_NAME = "annuncio";

	@Override
	public synchronized void doSave(Annuncio annuncio) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + AnnuncioModel.TABLE_NAME
				+ " (email_utente, nome_categoria, valutazione, sigla_uni,titolo,tipo,descrizione,acquisto_online) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, annuncio.getUtente().getEmail());
			preparedStatement.setString(2, annuncio.getCategoria().getNome());
			preparedStatement.setInt(3, annuncio.getValutazione());
			preparedStatement.setString(4, annuncio.getSiglaUni());
			preparedStatement.setString(5, annuncio.getTitolo());
			preparedStatement.setString(6, annuncio.getTipo());
			preparedStatement.setString(7, annuncio.getDescrizione());
			preparedStatement.setBoolean(8, annuncio.isAcquistoOnline());
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
	public synchronized Annuncio doRetrieveByKey(Annuncio annuncio) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Annuncio bean = new Annuncio();
		String email = annuncio.getUtente().getEmail();
		String titolo = annuncio.getTitolo();
		
		String selectSQL = "SELECT * FROM " + AnnuncioModel.TABLE_NAME + " WHERE email_utente = ? AND titolo = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, titolo);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				Utente utente = new Utente();
				utente.setEmail(rs.getString("email_utente"));
				bean.setUtente(utente);
				
				Categoria categoria = new Categoria();
				categoria.setNome(rs.getString("nome_categoria"));
				bean.setCategoria(categoria);
				
				bean.setValutazione(rs.getInt("valutazione"));
				bean.setSiglaUni(rs.getString("sigla_uni"));
				bean.setTitolo(rs.getString("titolo"));
				bean.setTipo(rs.getString("tipo"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setAcquistoOnline(rs.getBoolean("acquisto_online"));
				
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
	public synchronized boolean doDelete(Annuncio annuncio) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		

		String deleteSQL = "DELETE FROM " + AnnuncioModel.TABLE_NAME + " WHERE email_utente = ?"+" AND titolo= ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, annuncio.getUtente().getEmail());
			preparedStatement.setString(2, annuncio.getTitolo());

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
	public synchronized ArrayList<Annuncio> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Annuncio> products = new ArrayList<Annuncio>();

		String selectSQL = "SELECT * FROM " + AnnuncioModel.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order.toLowerCase();
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Annuncio bean = new Annuncio();
			
				Utente utente = new Utente();
				utente.setEmail(rs.getString("email_utente"));
				bean.setUtente(utente);
			
				Categoria categoria = new Categoria();
				categoria.setNome(rs.getString("nome_categoria"));
				bean.setCategoria(categoria);
				bean.setValutazione(rs.getInt("valutazione"));
				bean.setSiglaUni(rs.getString("sigla_uni"));
				bean.setTitolo(rs.getString("titolo"));
				bean.setTipo(rs.getString("tipo"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setAcquistoOnline(rs.getBoolean("acquisto_online"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return products;
	}


}
