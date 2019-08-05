package com.rabobank.statementprocessor.utils.export;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rabobank.statementprocessor.constants.Constants;
import com.rabobank.statementprocessor.model.dto.Account;

public class ExcelGenerator {

  private static final String RABO_BANK_CUSTOMER_VALIDATION = "RaboBankCustomerValidation";

  public static ByteArrayInputStream generate(List<Account> accounts) throws IOException {
    try (
      Workbook workbook = new XSSFWorkbook();
      ByteArrayOutputStream out = new ByteArrayOutputStream();) {

      Sheet sheet = workbook.createSheet(RABO_BANK_CUSTOMER_VALIDATION);

      Font headerFont = workbook.createFont();
      headerFont.setBold(true);
      headerFont.setColor(IndexedColors.BLUE.getIndex());

      CellStyle headerCellStyle = workbook.createCellStyle();
      headerCellStyle.setFont(headerFont);

      // Row for Header
      Row headerRow = sheet.createRow(0);

      // Header
      for (int col = 0; col < Constants.HEADERS.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(Constants.HEADERS[col].toUpperCase());
        cell.setCellStyle(headerCellStyle);
      }

      AtomicInteger rowIdx = new AtomicInteger(1);
      accounts.stream().forEach(e -> {
        Row row = sheet.createRow(rowIdx.getAndIncrement());
        row.createCell(0).setCellValue(e.getReference());
        row.createCell(1).setCellValue(e.getAccountNumber());
        row.createCell(2).setCellValue(e.getDescription());
        row.createCell(3).setCellValue(e.getStartBalance());
        row.createCell(4).setCellValue(e.getMutation());
        row.createCell(5).setCellValue(e.getEndBalance());
      });

      workbook.write(out);
      return new ByteArrayInputStream(out.toByteArray());
    }
  }
}
