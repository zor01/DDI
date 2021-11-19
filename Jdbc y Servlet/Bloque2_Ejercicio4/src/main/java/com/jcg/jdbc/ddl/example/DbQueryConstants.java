package com.jcg.jdbc.ddl.example;

public interface DbQueryConstants {	
	public static final String TABLE_NAME = "Tabla_con_cosas";
	public static final String DATABASE_NAME = "Base_Datos_Ejemplo";

	public static final String USE_DATABASE_QUERY = "USE Base_Datos_Ejemplo";

	// CREATE DDL COMMAND
	public static final String CREATE_DATABASE_QUERY = "CREATE DATABASE IF NOT EXISTS Base_Datos_Ejemplo;";
	public static final String CREATE_TABLE_QUERY = "CREATE TABLE Tabla_con_cosas (IdCosa INT(11) NOT NULL, NomCosa VARCHAR(20) DEFAULT NULL, PRIMARY KEY (IdCosa));";

	// DROP DDL COMMAND
	public static final String DROP_TABLE = "DROP TABLE Tabla_con_cosas";
	public static final String DROP_DATABASE = "DROP DATABASE Base_Datos_Ejemplo;";
	public static final String DROP_COLUMN = "ALTER TABLE Tabla_con_cosas DROP COLUMN NomCosa;";
	
	//Meter cosas dentro
	public static final String INSERT_COSA = "INSERT INTO Tabla_con_cosas values ('1','SoyUnaCosa')";
}