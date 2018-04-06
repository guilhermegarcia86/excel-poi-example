package br.com.exemplo.excel.poi.excelpoi.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import br.com.exemplo.excel.poi.excelpoi.model.Extrato;

@Service
public class ExcelService {

	private List<Extrato> extratos = new ArrayList<>();
	private static String[] columns = { "Descrição", "Sinal", "Saldo" };

	public byte[] baixarExtratoExcel() throws IOException {
		extratos.add(new Extrato("Descrrição 1", "+", 123));
		extratos.add(new Extrato("Descrrição 2", "-", 456));
		extratos.add(new Extrato("Descrrição 3", "+", 432));
		extratos.add(new Extrato("Descrrição 4", "-", 876));
		extratos.add(new Extrato("Descrrição 5", "+", 432));
		extratos.add(new Extrato("Descrrição 6", "-", 27));
		extratos.add(new Extrato("Descrrição 7", "+", 786));
		extratos.add(new Extrato("Descrrição 8", "-", 678));
		extratos.add(new Extrato("Descrrição 9", "+", 512));

		XSSFWorkbook workbook = new XSSFWorkbook();
		CreationHelper createHelper = workbook.getCreationHelper();
		Sheet sheet = workbook.createSheet("Extratos");
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		Row headerRow = sheet.createRow(0);

		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		int rowNum = 1;

		for (Extrato extrato : extratos) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(extrato.getDescricao());
			row.createCell(1).setCellValue(extrato.getSinal());
			row.createCell(2).setCellValue(extrato.getSaldo().toString());

		}

		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		workbook.write(bos);

		// Closing the workbook
		workbook.close();

		return bos.toByteArray();
	}

}
