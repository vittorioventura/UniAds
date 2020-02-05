package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VideoModel implements DataAccesObjectInterface<Video>{
	private DriverManagerConnectionPool dmcp = null;	

	public VideoModel(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Immagine Model creation....");
	}
	
	private static final String TABLE_NAME = "video";

	@Override
	public synchronized void doSave(Video video) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		  
		String insertSQL = "INSERT INTO " + VideoModel.TABLE_NAME
				+ " (email_utente_video, titolo_annuncio, nomeVideo, video) VALUES (?, ?, ?, ?)";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, video.getAnnuncio().getUtente().getEmail());
			preparedStatement.setString(2, video.getAnnuncio().getTitolo());
			preparedStatement.setString(3, video.getNomeVideo());
			preparedStatement.setBytes(4, video.getVideo());
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
	public synchronized Video doRetrieveByKey(Video video) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Video bean = new Video();
		String nomeVideo = video.getNomeVideo();
		
		String selectSQL = "SELECT * FROM " + VideoModel.TABLE_NAME + " WHERE nomeVideo = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nomeVideo);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean.setVideo(rs.getBytes("video"));
				bean.setNomeVideo(rs.getString("nomeVideo"));
				Annuncio annuncio= new Annuncio();
				GenericUser utente = null;
				utente.setEmail(rs.getString("email"));
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
	public synchronized boolean doDelete(Video video) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		
		String deleteSQL = "DELETE FROM " + VideoModel.TABLE_NAME + " WHERE nomeVideo = ?";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, video.getNomeVideo());

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
	public synchronized ArrayList<Video> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Video> video = new ArrayList<Video>();

		String selectSQL = "SELECT * FROM " + VideoModel.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order.toLowerCase();
		}

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Video bean = new Video();

				bean.setNomeVideo(rs.getString("nomeVideo"));
				
				Annuncio annuncio = new Annuncio();
				GenericUser utente = null;
				utente.setEmail(rs.getString("email"));
				annuncio.setUtente(utente);
				bean.setAnnuncio(annuncio);
				bean.setVideo(rs.getBytes("video"));
				video.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
		return video;
	}

	


}
