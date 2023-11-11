package br.com.fiap.mercado.view.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import br.com.fiap.mercado.view.jdbc.Conexao;
import br.com.fiap.mercado.view.model.Investimento;

public class InvestimentoDAO {

	Conexao conexao = new Conexao();

	public List<Investimento> getAll() {

		List<Investimento> listaInvestimentos = new ArrayList<>();
		try {
			Connection conn = conexao.conectar();
			if (conn != null) {

				String consulta = "SELECT * FROM T_INVESTIMENTO";
				try (PreparedStatement pstmt = conn.prepareStatement(consulta)) {
					ResultSet rs = pstmt.executeQuery();

					while (rs.next()) {
						Investimento investimento = new Investimento();
						investimento.set_cd_investimento(rs.getInt("CD_INVESTIMENTO"));
						investimento.set_ds_investimento(rs.getString("DS_INVESTIMENTO"));
						investimento.set_vl_investimento(rs.getDouble("VL_INVESTIMENTO"));
						investimento.set_dt_investimento(rs.getDate("DT_INVESTIMENTO"));
						investimento.set_cd_usuario(rs.getInt("CD_USUARIO"));

						listaInvestimentos.add(investimento);
					}rs.close();
				} catch (SQLException ex) {
					System.out.println("Erro ao recuperar todos os registros de investimento: " + ex.getMessage());
				}
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao recuperar todos os registros de investimento: " + ex.getMessage());
		} finally {
			conexao.desconectar();
		}
		return listaInvestimentos;
	}

	public void incluir(Investimento investimento) throws SQLException {
		String incluir = "INSERT INTO T_INVESTIMENTO (CD_INVESTIMENTO, DS_INVESTIMENTO, VL_INVESTIMENTO, DT_INVESTIMENTO, CD_USUARIO) "
				+ "VALUES (?, ?, ?, ?, ?)";

		Connection conn = conexao.conectar();
		try {
			conn = conexao.conectar();
			PreparedStatement pstmt = conn.prepareStatement(incluir);
			pstmt.setInt(1, investimento.get_cd_investimento());
			pstmt.setString(2, investimento.get_ds_investimento());
			pstmt.setDouble(3, investimento.get_vl_investimento());
			java.util.Date utilDate = investimento.get_dt_investimento();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			pstmt.setDate(4, sqlDate);
			pstmt.setInt(5, investimento.get_cd_usuario());

			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro ao inserir dados na tabela Investimento: " + ex.getMessage());
		} finally {
			conexao.desconectar();
		}
	}

	public void alterarPorCdInvestimento(Investimento investimento) {
		String update = "UPDATE T_INVESTIMENTO SET DS_INVESTIMENTO = ?, VL_INVESTIMENTO = ?, DT_INVESTIMENTO = ?, CD_USUARIO = ? WHERE CD_INVESTIMENTO = ?";
		Conexao conexao = new Conexao();
		Connection conn = null;
		try {
			conn = conexao.conectar();
			PreparedStatement pstmt = conn.prepareStatement(update);
			pstmt.setString(1, investimento.get_ds_investimento());
			pstmt.setDouble(2, investimento.get_vl_investimento());
			java.util.Date utilDate = investimento.get_dt_investimento();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			pstmt.setDate(3, sqlDate);
			pstmt.setInt(4, investimento.get_cd_usuario());
			pstmt.setInt(5, investimento.get_cd_investimento());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro ao atualizar dados da tabela Investimento: " + ex.getMessage());
		} finally {
			conexao.desconectar();
		}
	}

	public Investimento consultarPorMovimentacao(String cd_investimento) {
		Conexao conexao = new Conexao();
		Investimento investimento = null;
		Connection conn = null;
		try {
			String consulta = "SELECT * FROM T_INVESTIMENTO WHERE CD_INVESTIMENTO = ?";
			conn = conexao.conectar();
			PreparedStatement pstmt = conn.prepareStatement(consulta);
			pstmt.setString(1, cd_investimento);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				investimento = new Investimento();
				investimento.set_cd_investimento(rs.getInt("CD_INVESTIMENTO"));
				investimento.set_ds_investimento(rs.getString("DS_INVESTIMENTO"));
				investimento.set_vl_investimento(rs.getDouble("VL_INVESTIMENTO"));
				investimento.set_dt_investimento(rs.getDate("DT_INVESTIMENTO"));
				investimento.set_cd_usuario(rs.getInt("CD_USUARIO"));
			}
		} catch (SQLException ex) {
			System.out.println("Não conseguiu consultar os dados da Tabela Investimento: " + ex.getMessage());
		} finally {
			conexao.desconectar();
		}

		return investimento;
	}

	public void excluir(Investimento investimento) {
		String delete = "DELETE FROM T_INVESTIMENTO WHERE CD_INVESTIMENTO = ?";
		Conexao conexao = new Conexao();
		Connection conn = null;
		try {
			conn = conexao.conectar();
			PreparedStatement pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, investimento.get_cd_investimento());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro ao excluir dados da tabela Investimento: " + ex.getMessage());
		} finally {
			conexao.desconectar();
		}
	}
}
