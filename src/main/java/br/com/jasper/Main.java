package br.com.jasper;

import java.sql.Connection;
import java.util.Scanner;

import br.com.jasper.jdbc.JdbcConnection;
import br.com.jasper.service.JasperService;

public class Main {

	public static void main(String[] args) throws Exception {
		Connection connection = JdbcConnection.connection();

		System.out.print(
				"Bem vindo, escolha uma das opções para gerar um relatório de exemplo: " + "\n 1) Exibir Relatório 1"
						+ "\n 2) Exportar para PDF  o Relatório 1" + "\n 3) Exibir o Relatório Challenge "
						+ "\n 4) Abrir um relatorio com um Sub Relatório " + "\n - OUTRO NÚMERO PARA ENCERRAR \n\n");

		Scanner scanner;
		Boolean repeat = Boolean.TRUE;

		while (Boolean.TRUE.equals(repeat)) {
			try {
				scanner = new Scanner(System.in);
				Integer option = scanner.nextInt();

				switch (option) {
					case 1:
						openReportOne("relatorios/jrxml/relatorio_01.jrxml", connection);
						repeat = Boolean.FALSE;
						break;
					case 2:
						exportToPDF("relatorios/jrxml/relatorio_01.jrxml", connection);
						repeat = Boolean.FALSE;
						break;
					case 3:
						openJasperFile("relatorios/jasper/challenge_01.jasper", connection);
						repeat = Boolean.FALSE;
						break;
					case 4:
						openJasperFileWithSubreport("relatorios/jasper/relatorio_02.jasper", connection);
						repeat = Boolean.FALSE;
						break;
					default:
						repeat = Boolean.FALSE;
				}

			} catch (Exception e) {
				System.err.println("Opção inválida.");
			}

		}

		connection.close();
	}

	private static void openJasperFileWithSubreport(String path, Connection connection) {
		JasperService service = new JasperService();
		service.addParams("SUB_REPORT_DIR", "relatorios/jasper/");
		service.openJasperFileWithSubreport(path, connection);
	}

	private static void openJasperFile(String path, Connection connection) {
		JasperService service = new JasperService();
		service.openJasperFile(path, connection);
	}

	private static void exportToPDF(String path, Connection connection) {
		JasperService service = new JasperService();
		service.exportToPDF(path, connection);
	}

	private static void openReportOne(String path, Connection connection) {
		JasperService service = new JasperService();
		service.openJasperViewer(path, connection);
	}

}