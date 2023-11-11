package br.com.fiap.mercado.view.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.mercado.view.jdbc.Conexao;
import br.com.fiap.mercado.view.model.Ganho;

public class GanhoDAO {

	Conexao conexao = new Conexao();

	public List<Ganho> getAll() {
		List<Ganho> listaGanhos = new ArrayList<>();

		try {
			Connection conn = conexao.conectar();
			if (conn != null) {
				String consulta = "SELECT * FROM T_GANHO";
				try (PreparedStatement pstmt = conn.prepareStatement(consulta)) {
					ResultSet rs = pstmt.executeQuery();

					while (rs.next()) {
						Ganho ganho = new Ganho();
						ganho.set_cd_ganho(rs.getInt("CD_GANHO"));
						ganho.set_cd_movimentacao(rs.getInt("CD_MOVIMENTACAO"));
						ganho.set_cd_extrato(rs.getInt("CD_EXTRATO"));
						ganho.set_vl_movimentacao(rs.getDouble("VL_MOVIMENTACAO"));
						ganho.set_tp_receita(rs.getString("TP_RECEITA"));
						ganho.set_nm_movimentacao(rs.getString("NM_MOVIMENTACAO"));
						ganho.set_ds_movimentacao(rs.getString("DS_MOVIMENTACAO"));
						ganho.set_dt_movimentacao(rs.getDate("DT_MOVIMENTACAO"));
						ganho.set_qtd_parcelas(rs.getInt("QTD_PARCELAS"));

						listaGanhos.add(ganho);
					}
				} catch (SQLException ex) {
					System.out.println("Erro ao recuperar todos os registros de ganho: " + ex.getMessage());
				}
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao recuperar todos os registros de ganho: " + ex.getMessage());
		} finally {
			conexao.desconectar();
		}

		return listaGanhos;
	}

	public void incluir(Ganho ganho) {
		String incluir = "INSERT INTO T_ganho (CD_ganho, CD_MOVIMENTACAO, CD_EXTRATO, VL_MOVIMENTACAO, TP_RECEITA, NM_MOVIMENTACAO, DS_MOVIMENTACAO, DT_MOVIMENTACAO, QTD_PARCELAS) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;

		try {
			conn = conexao.conectar();
			PreparedStatement pstmt = conn.prepareStatement(incluir);
			pstmt.setInt(1, ganho.get_cd_ganho());
			pstmt.setInt(2, ganho.get_cd_movimentacao());
			pstmt.setInt(3, ganho.get_cd_extrato());
			pstmt.setDouble(4, ganho.get_vl_movimentacao());
			pstmt.setString(5, ganho.get_tp_receita());
			pstmt.setString(6, ganho.get_nm_movimentacao());
			pstmt.setString(7, ganho.get_ds_movimentacao());
			pstmt.setDate(8, (Date) ganho.get_dt_movimentacao());
			pstmt.setInt(9, ganho.get_qtd_parcelas());

			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro ao inserir dados na tabela ganho: " + ex.getMessage());
		} finally {
			conexao.desconectar();
		}
	}

	public Ganho consultarPorMovimentacao(String cd_ganho) {
		Conexao conexao = new Conexao();
		Ganho ganho = null;
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			conn = conexao.conectar();
			String consulta = "SELECT * FROM T_ganho WHERE CD_ganho = '" + cd_ganho + "'";
			stm = conn.createStatement();
			rs = stm.executeQuery(consulta);

			if (rs.next()) {
				ganho = new Ganho();
				ganho.set_cd_ganho(rs.getInt("CD_ganho"));
				ganho.set_tp_receita(rs.getString("TP_RECEITA"));
				ganho.set_cd_movimentacao(rs.getInt("CD_MOVIMENTACAO"));
				ganho.set_cd_extrato(rs.getInt("CD_EXTRATO"));
				ganho.set_nm_movimentacao(rs.getString("NM_MOVIMENTACAO"));
				ganho.set_ds_movimentacao(rs.getString("DS_MOVIMENTACAO"));
				ganho.set_vl_movimentacao(rs.getDouble("VL_MOVIMENTACAO"));
				ganho.set_dt_movimentacao(rs.getDate("DT_MOVIMENTACAO"));
				ganho.set_qtd_parcelas(rs.getInt("QTD_PARCELAS"));
			}
		} catch (SQLException ex) {
			System.out.println("Nao conseguiu consultar os dados da Tabela ganho: " + ex.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.err.println("Erro ao fechar o ResultSet: " + e.getMessage());
				}
			}
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					System.err.println("Erro ao fechar o Statement: " + e.getMessage());
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.err.println("Erro ao desconectar: " + e.getMessage());
				}
			}
		}

		return ganho;
	}

	public void alterarPorCdganho(Ganho ganho) {
		String update = "UPDATE T_ganho SET CD_MOVIMENTACAO = ?, CD_EXTRATO = ?, QTD_PARCELAS = ?, VL_MOVIMENTACAO = ?, TP_RECEITA = ?, NM_MOVIMENTACAO = ?, DS_MOVIMENTACAO = ?, DT_MOVIMENTACAO = ? WHERE CD_ganho = ?";
		Conexao conexao = new Conexao();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = conexao.conectar();
			pstmt = conn.prepareStatement(update);
			pstmt.setInt(1, ganho.get_cd_movimentacao());
			pstmt.setInt(2, ganho.get_cd_extrato());
			pstmt.setInt(3, ganho.get_qtd_parcelas());
			pstmt.setDouble(4, ganho.get_vl_movimentacao());
			pstmt.setString(5, ganho.get_tp_receita());
			pstmt.setString(6, ganho.get_nm_movimentacao());
			pstmt.setString(7, ganho.get_ds_movimentacao());
			pstmt.setDate(8, (Date) ganho.get_dt_movimentacao());
			pstmt.setInt(9, ganho.get_cd_ganho());

			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro ao atualizar dados na tabela ganho: " + ex.getMessage());
		} finally {
			conexao.desconectar();
		}
	}

	public void excluir(Ganho ganho) throws SQLException {
		String delete = "DELETE FROM  T_ganho WHERE CD_ganho='" + ganho.get_cd_ganho() + "'";

		Conexao conexao = new Conexao();
		conexao.executarDML(delete);
	}
}
