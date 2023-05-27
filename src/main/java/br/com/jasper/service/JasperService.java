package br.com.jasper.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import br.com.jasper.util.Property;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class JasperService {

	private Map<String, Object> params = new LinkedHashMap<>();

	public void addParams(String key, Object value) {
		this.params.put(key, value);
	}

	public void openJasperFileWithSubreport(String jasperFile, Connection connection) {
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream(jasperFile);
			JasperPrint print = JasperFillManager.fillReport(is, params, connection);
			JasperViewer viewer = new JasperViewer(print);
			viewer.setVisible(Boolean.TRUE);

		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public void openJasperFile(String jasperFile, Connection connection) {
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream(jasperFile);
			JasperPrint print = JasperFillManager.fillReport(is, params, connection);
			JasperViewer viewer = new JasperViewer(print);
			viewer.setVisible(Boolean.TRUE);

		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public void exportToPDF(String fileName, Connection connection) {
		JasperReport report = this.compileJrxml(fileName);
		try {
			JasperPrint print = JasperFillManager.fillReport(report, params, connection);

			Property prop = Property.getPropertie();
			String path = prop.getUserProfile() + "jasper-" + UUID.randomUUID() + ".pdf";
			OutputStream out = new FileOutputStream(path);
			JasperExportManager.exportReportToPdfStream(print, out);
			
			System.err.print("Arquivo exportado para o diretorio: " + path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openJasperViewer(String fileName, Connection connection) {
		JasperReport report = this.compileJrxml(fileName);
		try {
			JasperPrint print = JasperFillManager.fillReport(report, params, connection);

			JasperViewer viewer = new JasperViewer(print);
			viewer.setVisible(Boolean.TRUE);

		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private JasperReport compileJrxml(String fileName) {
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
			return JasperCompileManager.compileReport(is);
		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;
	}

}