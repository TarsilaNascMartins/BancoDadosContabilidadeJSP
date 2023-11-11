package br.com.fiap.mercado.view.jdbc;

//Conexão ao banco de dados

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

	// Infos fixas para conexão ao banco

	private Connection conn = null;
	private Statement stm = null;
	private ResultSet rs = null;

	private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521/ORCL";
	private static final String USUARIO = "RM98328";
	private static final String SENHA = "210598";

	// Método para estabelecer uma conexão ao banco
	public Connection conectar() throws SQLException {
		Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conn;
	}

	// Método de consulta ao banco de dados

	public ResultSet executarConsulta(String consulta) throws SQLException {
		try (Connection conn = conectar(); Statement stm = conn.createStatement()) {
			return stm.executeQuery(consulta);
		}
	}

	// Método de execução de comandos ao banco de dados (INSERT, UPDATE, DELETE)

	public boolean executarDML(String dml) throws SQLException {
		try (Connection conn = conectar(); Statement stm = conn.createStatement()) {
			return stm.execute(dml);
		}
	}

	// Método para desconectar e fechar recursos

	public void desconectar() {
		fecharResultSet(this.rs);
		fecharStatement(this.stm);
		fecharConnection(this.conn);
	}

	// Método de desconexão ao banco de dados com tratamento de possíveis erros

	private void fecharConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException ex) {
			System.err.println("Erro de SQL: " + ex.getMessage());
			if (ex.getSQLState().equals("45000")) {
				System.err.println("Erro de violação de chave única. Verifique os dados e tente novamente.");
			} else if (ex.getSQLState().equals("23505")) {
				System.err.println("Erro de chave duplicada. Verifique se já existe um registro com os mesmos dados.");
			} else {
				System.err.println("Erro desconhecido no banco de dados.");
			}
		}
	}

	// Método para fechar resultados de consultas

	private void fecharResultSet(ResultSet resultado) {
		try {
			if (resultado != null && !resultado.isClosed()) {
				resultado.close();
			}
		} catch (SQLException ex) {
			System.err.println("Erro ao fechar o resultado da consulta: " + ex.getMessage());
		}
	}

	public void fecharStatement(Statement stm) {
		try {
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao fechar o procedimento de consulta.");
		}
	}

}