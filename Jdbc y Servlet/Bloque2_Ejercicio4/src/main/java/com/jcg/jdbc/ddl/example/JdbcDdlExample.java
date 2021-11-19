package com.jcg.jdbc.ddl.example;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class JdbcDdlExample implements DbQueryConstants {

	// JDBC Driver Name & Database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306";

	// JDBC Database Credentials
	static final String JDBC_USER = "root";
	static final String JDBC_PASS = "contrasenanosegura123123";
	

	public final static Logger logger = Logger.getLogger(JdbcDdlExample.class);

	public static void main(String[] args) {

		Connection connObj = null;
		Statement stmtOBj = null;
		try {
			Class.forName(JDBC_DRIVER);
			connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);

			stmtOBj = connObj.createStatement();

			// Crea la base de datos
			logger.info("\n=======Base de datos " + DATABASE_NAME + " Creada=======\n");			
			stmtOBj.executeUpdate(CREATE_DATABASE_QUERY);

			//Usa la base de datos recien crada
			stmtOBj.executeUpdate(USE_DATABASE_QUERY);

			//Crea la tabla
			logger.info("\n=======Tabla " + TABLE_NAME + " Creada=======\n");			
			stmtOBj.executeUpdate(CREATE_TABLE_QUERY);
			
			//Inserta cosas
			logger.info("\n=======Cosa " + TABLE_NAME + " insertada=======\n");			
			stmtOBj.executeUpdate(INSERT_COSA);

			logger.info("\n=======Muestra la estructura de la base de datos=======\n");
			showDbTableStructure();

			//Borra los datos de la tabla recien creada ;-;
			logger.info("\n=======Borra una columna de la tabla=======\n");
			stmtOBj.executeUpdate(DROP_COLUMN);

			logger.info("\n=======Muestra la estructura de la base de datos=======\n");
			showDbTableStructure();	

			//Borra la tabla
			logger.info("\n=======Borra la tabla=======\n");
			stmtOBj.executeUpdate(DROP_TABLE);

			//Borra la base de datos
			logger.info("\n=======Borra la base de datos=======\n");
			stmtOBj.executeUpdate(DROP_DATABASE);
		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				if(stmtOBj != null) {
					stmtOBj.close();	// Close Statement Object
				}
				if(connObj != null) {
					connObj.close();	// Close Connection Object
				}
			} catch (Exception sqlException) {
				sqlException.printStackTrace();
			}
		}
	}

	// This Method Is Used To Print The Table Structure
	private static void showDbTableStructure() throws SQLException {
		StringBuilder builderObj = new StringBuilder();
		DatabaseMetaData metaObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS).getMetaData();
		ResultSet resultSetObj = metaObj.getColumns(DATABASE_NAME, null, TABLE_NAME, "%");

		builderObj.append(TABLE_NAME + " Columns Are?= (");
		while (resultSetObj.next()) {
			String columnName = resultSetObj.getString(4);
			builderObj.append(columnName).append(", ");
		}
		builderObj.deleteCharAt(builderObj.lastIndexOf(",")).deleteCharAt(builderObj.lastIndexOf(" ")).append(")").append("\n");
		logger.info(builderObj.toString());
	}
}