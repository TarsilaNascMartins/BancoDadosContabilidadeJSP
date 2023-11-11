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
import br.com.fiap.mercado.view.model.Gasto;

public class GastoDAO {

	public List<Gasto> getAll() throws SQLException {
		List<Gasto> listagastos = new ArrayList<>();
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectar();

		if (conn != null) {
			String consulta = "SELECT * FROM T_gasto";
			try (PreparedStatement pstmt = conn.prepareStatement(consulta)) {
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					Gasto gasto = new Gasto();
					gasto.set_cd_gasto(rs.getInt("CD_gasto"));
					gasto.set_cd_movimentacao(rs.getInt("CD_MOVIMENTACAO"));
					gasto.set_cd_extrato(rs.getInt("CD_EXTRATO"));
					gasto.set_vl_movimentacao(rs.getDouble("VL_MOVIMENTACAO"));
					gasto.set_tp_despesa(rs.getString("TP_DESPESA"));
					gasto.set_nm_movimentacao(rs.getString("NM_MOVIMENTACAO"));
					gasto.set_ds_movimentacao(rs.getString("DS_MOVIMENTACAO"));
					gasto.set_dt_movimentacao(rs.getDate("DT_MOVIMENTACAO"));
					gasto.set_qtd_parcelas(rs.getInt("QTD_PARCELAS"));

					listagastos.add(gasto);
				}
			} catch (SQLException ex) {
				System.out.println("Erro ao recuperar todos os registros de gasto: " + ex.getMessage());
			} finally {
				conexao.desconectar();
			}
		}

		return listagastos;
	}

	/*
	 * public void incluir(Gasto gasto) { String incluir =
	 * "INSERT INTO T_GASTO VALUES ('" + gasto.get_cd_gasto() + "', '" +
	 * gasto.get_cd_movimentacao() + "', '" + gasto.get_cd_extrato() + "', '" +
	 * gasto.get_vl_movimentacao() + "', '" + gasto.get_tp_despesa() + "', '" +
	 * gasto.get_nm_movimentacao() + "', '" + gasto.get_ds_movimentacao() + "', '" +
	 * gasto.get_dt_movimentacao()+ "', '" + gasto.get_qtd_parcelas() + "')";
	 * Conexao conexao = new Conexao(); conexao.executarDML(incluir); }
	 */

	public void incluir(Gasto gasto) {
		String incluir = "INSERT INTO T_GASTO (CD_GASTO, CD_MOVIMENTACAO, CD_EXTRATO, VL_MOVIMENTACAO, TP_DESPESA, NM_MOVIMENTACAO, DS_MOVIMENTACAO, DT_MOVIMENTACAO, QTD_PARCELAS) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Conexao conexao = new Conexao();
		try {
			PreparedStatement pstmt = conexao.conectar().prepareStatement(incluir);
			pstmt.setInt(1, gasto.get_cd_gasto());
			pstmt.setInt(2, gasto.get_cd_movimentacao());
			pstmt.setInt(3, gasto.get_cd_extrato());
			pstmt.setDouble(4, gasto.get_vl_movimentacao());
			pstmt.setString(5, gasto.get_tp_despesa());
			pstmt.setString(6, gasto.get_nm_movimentacao());
			pstmt.setString(7, gasto.get_ds_movimentacao());
			pstmt.setDate(8, (Date) gasto.get_dt_movimentacao());
			pstmt.setInt(9, gasto.get_qtd_parcelas());

			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro ao inserir dados na tabela Gasto: " + ex.getMessage());
		} finally {
			conexao.desconectar();
		}
	}

	public Gasto consultarPorMovimentacao(String cd_gasto) {
		Conexao conexao = new Conexao();
		Gasto gasto = null;
		try {
			String consulta = "SELECT * FROM T_GASTO WHERE CD_GASTO = '" + cd_gasto + "'";
			ResultSet rs = conexao.executarConsulta(consulta);

			if (rs.next()) {
				gasto = new Gasto();
				gasto.set_cd_gasto(rs.getInt("CD_GASTO"));
				gasto.set_tp_despesa(rs.getString("TP_DESPESA"));
				gasto.set_cd_movimentacao(rs.getInt("CD_MOVIMENTACAO"));
				gasto.set_cd_extrato(rs.getInt("CD_EXTRATO"));
				gasto.set_nm_movimentacao(rs.getString("NM_MOVIMENTACAO"));
				gasto.set_ds_movimentacao(rs.getString("DS_MOVIMENTACAO"));
				gasto.set_vl_movimentacao(rs.getDouble("VL_MOVIMENTACAO"));
				gasto.set_dt_movimentacao(rs.getDate("DT_MOVIMENTACAO"));
				gasto.set_qtd_parcelas(rs.getInt("QTD_PARCELAS"));
			}
		} catch (SQLException ex) {
			System.out.println("Nao conseguiu consultar os dados da Tabela Gasto.");
		} finally {
			conexao.desconectar();
		}

		return gasto;
	}

	public void alterarPorCdGasto(Gasto gasto) throws SQLException {
		String update = "UPDATE T_GASTO SET NM_MOVIMENTACAO = '" + gasto.get_nm_movimentacao() + "', DS_MOVIMENTACAO = "
				+ gasto.get_ds_movimentacao() + "WHERE CD_GASTO = '" + gasto.get_cd_gasto() + "'";
		Conexao conexao = new Conexao();
		conexao.executarDML(update);
	}

	public void excluir(Gasto gasto) throws SQLException {
		String delete = "DELETE FROM  T_GASTO WHERE CD_GASTO='" + gasto.get_cd_gasto() + "'";

		Conexao conexao = new Conexao();
		conexao.executarDML(delete);
	}
}
