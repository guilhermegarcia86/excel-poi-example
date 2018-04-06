package br.com.exemplo.excel.poi.excelpoi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.exemplo.excel.poi.excelpoi.service.ExcelService;

@RestController
public class ExcelController {

	@Autowired
	private ExcelService excelService;

	@GetMapping("/download")
	public ResponseEntity<byte[]> downloadExcel() throws IOException {

		byte[] excel = this.excelService.baixarExtratoExcel();

		return ResponseEntity.ok().headers(this.createHeader(excel.length))
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(excel);

	}

	private HttpHeaders createHeader(int length) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/vnd.ms-excel;");
		headers.set("content-length", Integer.toString(length));
		headers.set("Content-Disposition", "attachment; filename=extrato.xlsx");
		return headers;
		
	}

}
