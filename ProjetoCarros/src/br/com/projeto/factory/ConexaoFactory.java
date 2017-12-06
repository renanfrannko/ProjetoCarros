package br.com.projeto.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	private static final String USUARIO = "sql10209201";
	private static final String SENHA = "SNPRSmYmEK";
	private static final String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10209201";

	public static Connection conectar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
		}
		Connection conexao = null;
		try {
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
			System.out.println("Sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Fail");
		}
		return conexao;
	}

	public static void main(String[] args) {

		Connection connection = ConexaoFactory.conectar();

	}

}
