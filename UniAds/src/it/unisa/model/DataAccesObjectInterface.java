package it.unisa.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DataAccesObjectInterface <T>{

		public void doSave(T item) throws SQLException;

		public boolean doDelete(T item) throws SQLException;

		public T doRetrieveByKey(T item) throws SQLException;
		
		public ArrayList<T> doRetrieveAll(String order) throws SQLException;
}


