package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImmagineModel implements DataAccesObjectInterface<Immagine>{
	private DriverManagerConnectionPool dmcp = null;	

	public ImmagineModel(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Immagine Model creation....");
	}
	
	private static final String TABLE_NAME = "immagine";

	@Override
	public synchronized void doSave(Immagine immagine) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		  
		String insertSQL = "INSERT INTO " + ImmagineModel.TABLE_NAME
				+ " (email_utente_immagine, titolo_annuncio, nomeImmagine, immagine) VALUES (?, ?, ?, ?)";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, immagine.getAnnuncio().getUtente().getEmail());
			preparedStatement.setString(2, immagine.getAnnuncio().getTitolo());
			preparedStatement.setString(3, immagine.getNomeImmagine());
			preparedStatement.setBytes(4, immagine.getImg());
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

	@SuppressWarnings("null")
	@Override
	public synchronized Immagine doRetrieveByKey(Immagine immagine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Immagine bean = new Immagine();
			
		String selectSQL = "SELECT * FROM " + ImmagineModel.TABLE_NAME + " WHERE email_utente_immagine = ? AND titolo_annuncio = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, immagine.getAnnuncio().getUtente().getEmail());
			preparedStatement.setString(2, immagine.getAnnuncio().getTitolo());
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean.setImg(rs.getBytes("immagine"));
				bean.setNomeImmagine(rs.getString("nomeImmagine"));
				Annuncio annuncio= new Annuncio();
				GenericUser utente = null;
				utente.setEmail(rs.getString("email_utente_immagine"));
				annuncio.setUtente(utente);
				bean.setAnnuncio(annuncio);
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
	public synchronized boolean doDelete(Immagine immagine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + ImmagineModel.TABLE_NAME + " WHERE nomeImmagine = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, immagine.getNomeImmagine());

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

	@SuppressWarnings("null")
	@Override
	public synchronized ArrayList<Immagine> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<Immagine> immagini = new ArrayList<Immagine>();

		String selectSQL = "SELECT * FROM " + ImmagineModel.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order.toLowerCase();
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Immagine bean = new Immagine();

				bean.setNomeImmagine(rs.getString("nomeImmagine"));
				
				Annuncio annuncio = new Annuncio();
				GenericUser utente = null;
				utente.setEmail(rs.getString("email_utente_immagine"));
				annuncio.setUtente(utente);
				bean.setAnnuncio(annuncio);
				bean.setImg(rs.getBytes("immagine"));
				immagini.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return immagini;
	}
	
	@SuppressWarnings("null")
	public synchronized ArrayList<Immagine> doRetrieveImmagini(String email , String titolo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<Immagine> immagini = new ArrayList<Immagine>();

		String selectSQL = "SELECT * FROM " + ImmagineModel.TABLE_NAME+ " WHERE email_utente_immagine = ? AND titolo_annuncio = ?";;

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, titolo);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Immagine bean = new Immagine();

				bean.setNomeImmagine(rs.getString("nomeImmagine"));
				
				bean.setImg(rs.getBytes("immagine"));
				immagini.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return immagini;
	}

	

}
