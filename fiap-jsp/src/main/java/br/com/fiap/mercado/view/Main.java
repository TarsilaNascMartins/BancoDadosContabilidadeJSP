package br.com.fiap.mercado.view;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.mercado.view.dao.GanhoDAO;
import br.com.fiap.mercado.view.dao.GastoDAO;
import br.com.fiap.mercado.view.dao.InvestimentoDAO;
import br.com.fiap.mercado.view.model.Ganho;
import br.com.fiap.mercado.view.model.Gasto;
import br.com.fiap.mercado.view.model.Investimento;

public class Main {
	public static void main(String[] args) throws SQLException {

		GastoDAO gastoDAO = new GastoDAO();
		GanhoDAO ganhoDAO = new GanhoDAO();
		InvestimentoDAO investimentoDAO = new InvestimentoDAO();

		List<Ganho> listaGanhos = ganhoDAO.getAll();
		List<Gasto> listaGastos = gastoDAO.getAll();
		List<Investimento> listaInvestimentos = investimentoDAO.getAll();
		char opcao = ' ';

		do {
			Gasto gasto = null;
			Ganho ganho = null;
			Investimento investimento = null;
			opcao = menu();

			switch (opcao) {
			case 'I':
				System.out.println("Você escolheu INCLUIR");
				char tipo = escolherTipoMovimento();

				switch (tipo) {
				case '1':
					gasto = coletarDados();
					gastoDAO.incluir(gasto);
					break;
				case '2':
					ganho = coletarDadosGanhos();
					ganhoDAO.incluir(ganho);
					break;
				case '3':
					investimento = coletarDadosInvestimento();
					investimentoDAO.incluir(investimento);
					break;
				case '4':
					break;
				default:
					System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
				}
				break;

			case 'E':
				System.out.println("Você escolheu EXCLUIR");
				char tipo2 = escolherTipoMovimento();

				switch (tipo2) {
				case '1':
					String codigoExcluir = consultarPorMovimentacao();
					gasto = gastoDAO.consultarPorMovimentacao(codigoExcluir);

					if (gasto != null) {
						gastoDAO.excluir(gasto);
						System.out.println("Registro de gasto excluído com sucesso.");
					} else {
						System.out.println("Gasto não encontrado.");
					}
					break;
				case '2':
					String codigoExcluirGanho = consultarPorMovimentacaoGanho();
					ganho = ganhoDAO.consultarPorMovimentacao(codigoExcluirGanho);

					if (ganho != null) {
						ganhoDAO.excluir(ganho);
						System.out.println("Registro de ganho excluído com sucesso.");
					} else {
						System.out.println("Ganho não encontrado.");
					}
					break;
				case '3':
					String codigoExcluirInvestimento = consultarPorMovimentacaoInvestimento();
					investimento = investimentoDAO.consultarPorMovimentacao(codigoExcluirInvestimento);

					if (investimento != null) {
						investimentoDAO.excluir(investimento);
						System.out.println("Registro de investimento excluído com sucesso.");
					} else {
						System.out.println("Investimento não encontrado.");
					}
					break;
				case '4':
					break;
				default:
					System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
				}
				break;

			case 'A':
				System.out.println("Você escolheu ALTERAR");
				char tipo3 = escolherTipoMovimento();

				switch (tipo3) {
				case '1':
					gasto = coletarDados();
					gastoDAO.alterarPorCdGasto(gasto);
					break;
				case '2':
					ganho = coletarDadosGanhos();
					ganhoDAO.alterarPorCdganho(ganho);
					break;
				case '3':
					investimento = coletarDadosInvestimento();
					investimentoDAO.alterarPorCdInvestimento(investimento);
					break;
				case '4':
					break;
				default:
					System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
				}
				break;

			case 'C':
				System.out.println("Você escolheu CONSULTAR");
				char tipo4 = escolherTipoMovimento();

				switch (tipo4) {
				case '1':
					String codigoConsultar = consultarPorMovimentacao();
					gasto = gastoDAO.consultarPorMovimentacao(codigoConsultar);

					if (gasto != null) {
						mostrarDadosGasto(gasto);
					} else {
						System.out.println("Gasto não encontrado.");
					}
					break;
				case '2':
					String codigoConsultarganho = consultarPorMovimentacaoGanho();
					ganho = ganhoDAO.consultarPorMovimentacao(codigoConsultarganho);

					if (ganho != null) {
						mostrarDadosGanho(ganho);
					} else {
						System.out.println("Ganho não encontrado.");
					}
					break;
				case '3':
					String codigoConsultarinvestimento = consultarPorMovimentacaoInvestimento();
					investimento = investimentoDAO.consultarPorMovimentacao(codigoConsultarinvestimento);

					if (investimento != null) {
						mostrarDadosInvestimento(investimento);
					} else {
						System.out.println("Investimento não encontrado.");
					}
					break;

				case '4':
					break;
				default:
					System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
				}
				break;

			case 'T':
				System.out.println("Você escolheu observar Tabela Completa");
				char tipo5 = escolherTipoMovimento();

				switch (tipo5) {
				case '1':
					System.out.println(listaGastos);
					for (Gasto listagasto : listaGastos) {

						System.out.println("############### DADOS DO GASTO #################");
						System.out.println("Código do Gasto: " + listagasto.get_cd_gasto());
						System.out.println("Código da Movimentação: " + listagasto.get_cd_movimentacao());
						System.out.println("Código do Extrato: " + listagasto.get_cd_extrato());
						System.out.println("Quantidade de Parcelas: " + listagasto.get_qtd_parcelas());
						System.out.println("Valor da Movimentação: " + listagasto.get_vl_movimentacao());
						System.out.println("Tipo da Receita: " + listagasto.get_tp_despesa());
						System.out.println("Nome da Movimentação: " + listagasto.get_nm_movimentacao());
						System.out.println("Descrição da Movimentação: " + listagasto.get_ds_movimentacao());
						System.out.println("Data da Movimentação: " + listagasto.get_dt_movimentacao());
						System.out.println("############### DADOS DO GASTO #################\n");
					}

					break;
				case '2':

					for (Ganho listaganho : listaGanhos) {
						System.out.println("############### DADOS DO GANHO #################");
						System.out.println("Código do Ganho: " + listaganho.get_cd_ganho());
						System.out.println("Código da Movimentação: " + listaganho.get_cd_movimentacao());
						System.out.println("Código do Extrato: " + listaganho.get_cd_extrato());
						System.out.println("Quantidade de Parcelas: " + listaganho.get_qtd_parcelas());
						System.out.println("Valor da Movimentação: " + listaganho.get_vl_movimentacao());
						System.out.println("Tipo da Receita: " + listaganho.get_tp_receita());
						System.out.println("Nome da Movimentação: " + listaganho.get_nm_movimentacao());
						System.out.println("Descrição da Movimentação: " + listaganho.get_ds_movimentacao());
						System.out.println("Data da Movimentação: " + listaganho.get_dt_movimentacao());
						System.out.println("############### DADOS DO GANHO #################\n");
					}

					break;
				case '3':
					for (Investimento listainvestimento : listaInvestimentos) {
						System.out.println("############### DADOS DO INVESTIMENTO #################");
						System.out.println("Código do Investimento: " + listainvestimento.get_cd_investimento());
						System.out.println("Descrição do Investimento: " + listainvestimento.get_ds_investimento());
						System.out.println("Valor do Investimento: " + listainvestimento.get_vl_investimento());
						System.out.println("Data do Investimento: " + listainvestimento.get_dt_investimento());
						System.out.println("Código do Usuário: " + listainvestimento.get_cd_usuario());
						System.out.println("############### DADOS DO INVESTIMENTO #################\n");
					}
					break;

				case '4':
					break;
				default:
					System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
				}
				break;
			}
		} while (opcao != 'S');
	}

	public static char escolherTipoMovimento() {
		Scanner s = new Scanner(System.in);
		char tipo = ' ';

		System.out.println("Escolha o tipo de movimento ");
		System.out.println("\t(1) Gasto");
		System.out.println("\t(2) Ganho");
		System.out.println("\t(3) Investimento");
		System.out.println("\t(4) Voltar");
		System.out.print("Opção: ");

		tipo = s.nextLine().charAt(0);
		System.out.println(tipo);
		return tipo;
	}

	public static char menu() {
		Scanner s = new Scanner(System.in);
		char opcao = ' ';

		System.out.println("Escolha a sua opcao: ");
		System.out.println("\t(I)ncluir");
		System.out.println("\t(E)xcluir");
		System.out.println("\t(A)lterar");
		System.out.println("\t(C)onsultar");
		System.out.println("\t(T)abela Completa");
		System.out.println("\t(S)air");
		System.out.print("\nOpcao: ");
		opcao = s.nextLine().toUpperCase().charAt(0);

		return opcao;
	}

	public static String consultarPorMovimentacao() {
		Scanner s = new Scanner(System.in);
		System.out.print("Digite o código do gasto: ");
		return s.nextLine();
	}

	public static String consultarPorMovimentacaoGanho() {
		Scanner s = new Scanner(System.in);
		System.out.print("Digite o código do ganho: ");
		return s.nextLine();
	}

	public static String consultarPorMovimentacaoInvestimento() {
		Scanner s = new Scanner(System.in);
		System.out.print("Digite o código do investimento: ");
		return s.nextLine();
	}

	public static Gasto coletarDados() {
		Scanner s = new Scanner(System.in);
		Gasto gasto = new Gasto();
		System.out.print("Digite o código do gasto: ");
		gasto.set_cd_gasto(s.nextInt());
		System.out.print("Digite o código da movimentação: ");
		gasto.set_cd_movimentacao(s.nextInt());
		System.out.print("Digite o código do extrato: ");
		gasto.set_cd_extrato(s.nextInt());
		System.out.print("Digite a quantidade de parcelas: ");
		gasto.set_qtd_parcelas(s.nextInt());
		System.out.print("Digite o valor da movimentação: ");
		gasto.set_vl_movimentacao(s.nextDouble());
		s.nextLine();
		System.out.print("Digite o tipo de despesa: ");
		gasto.set_tp_despesa(s.nextLine());
		System.out.print("Digite o nome da movimentação: ");
		gasto.set_nm_movimentacao(s.nextLine());
		System.out.print("Digite a descrição da movimentação: ");
		gasto.set_ds_movimentacao(s.nextLine());
		System.out.print("Digite a data da movimentação (AAAA-MM-DD): ");
		gasto.set_dt_movimentacao(Date.valueOf(s.nextLine()));

		return gasto;
	}

	public static Ganho coletarDadosGanhos() {
		Scanner s = new Scanner(System.in);
		Ganho ganho = new Ganho();
		System.out.print("Digite o código do ganho: ");
		ganho.set_cd_ganho(s.nextInt());
		System.out.print("Digite o código da movimentação: ");
		ganho.set_cd_movimentacao(s.nextInt());
		System.out.print("Digite o código do extrato: ");
		ganho.set_cd_extrato(s.nextInt());
		System.out.print("Digite a quantidade de parcelas: ");
		ganho.set_qtd_parcelas(s.nextInt());
		System.out.print("Digite o valor da movimentação: ");
		ganho.set_vl_movimentacao(s.nextDouble());
		s.nextLine();
		System.out.print("Digite o tipo de receita: ");
		ganho.set_tp_receita(s.nextLine());
		System.out.print("Digite o nome da movimentação: ");
		ganho.set_nm_movimentacao(s.nextLine());
		System.out.print("Digite a descrição da movimentação: ");
		ganho.set_ds_movimentacao(s.nextLine());
		System.out.print("Digite a data da movimentação (AAAA-MM-DD): ");
		ganho.set_dt_movimentacao(Date.valueOf(s.nextLine()));

		return ganho;
	}

	public static Investimento coletarDadosInvestimento() {
		Scanner s = new Scanner(System.in);
		Investimento investimento = new Investimento();
		System.out.print("Digite o código do investimento: ");
		investimento.set_cd_investimento(s.nextInt());
		System.out.print("Digite a descrição do investimento: ");
		investimento.set_ds_investimento(s.next());
		System.out.print("Digite o valor do investimento: ");
		investimento.set_vl_investimento(s.nextDouble());
		s.nextLine();
		System.out.print("Digite a data do investimento (AAAA-MM-DD): ");
		investimento.set_dt_investimento(Date.valueOf(s.nextLine()));
		System.out.print("Digite o código do usuário: ");
		investimento.set_cd_usuario(s.nextInt());

		return investimento;
	}

	public static void mostrarDadosGasto(Gasto gasto) {
		if (gasto != null) {
			System.out.println("\n############### DADOS DO GASTO #################");
			System.out.println("Código do Gasto: " + gasto.get_cd_gasto());
			System.out.println("Código da Movimentação: " + gasto.get_cd_movimentacao());
			System.out.println("Código do Extrato: " + gasto.get_cd_extrato());
			System.out.println("Quantidade de Parcelas: " + gasto.get_qtd_parcelas());
			System.out.println("Valor da Movimentação: " + gasto.get_vl_movimentacao());
			System.out.println("Tipo de Despesa: " + gasto.get_tp_despesa());
			System.out.println("Nome da Movimentação: " + gasto.get_nm_movimentacao());
			System.out.println("Descrição da Movimentação: " + gasto.get_ds_movimentacao());
			System.out.println("Data da Movimentação: " + gasto.get_dt_movimentacao());
			System.out.println("############### DADOS DO GASTO #################\n");
		}
	}

	public static void mostrarDadosGanho(Ganho ganho) {
		if (ganho != null) {
			System.out.println("\n############### DADOS DO GANHO #################");
			System.out.println("Código do Ganho: " + ganho.get_cd_ganho());
			System.out.println("Código da Movimentação: " + ganho.get_cd_movimentacao());
			System.out.println("Código do Extrato: " + ganho.get_cd_extrato());
			System.out.println("Quantidade de Parcelas: " + ganho.get_qtd_parcelas());
			System.out.println("Valor da Movimentação: " + ganho.get_vl_movimentacao());
			System.out.println("Tipo da Receita: " + ganho.get_tp_receita());
			System.out.println("Nome da Movimentação: " + ganho.get_nm_movimentacao());
			System.out.println("Descrição da Movimentação: " + ganho.get_ds_movimentacao());
			System.out.println("Data da Movimentação: " + ganho.get_dt_movimentacao());
			System.out.println("############### DADOS DO GASTO #################\n");
		}
	}

	public static void mostrarDadosInvestimento(Investimento investimento) {
		if (investimento != null) {
			System.out.println("\n############### DADOS DO INVESTIMENTO #################");
			System.out.println("Código do Investimento: " + investimento.get_cd_investimento());
			System.out.println("Descrição do Investimento: " + investimento.get_ds_investimento());
			System.out.println("Valor do Investimento: " + investimento.get_vl_investimento());
			System.out.println("Data do Investimento: " + investimento.get_dt_investimento());
			System.out.println("Código do Usuário: " + investimento.get_cd_usuario());
			System.out.println("############### DADOS DO INVESTIMENTO #################\n");
		}
	}

}