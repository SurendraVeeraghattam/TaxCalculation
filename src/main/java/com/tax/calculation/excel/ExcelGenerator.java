package com.tax.calculation.excel;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tax.calculation.model.IncomeDetModel;
import com.tax.calculation.model.UserDetModel;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelGenerator {
	

	   private List<UserDetModel> userDetModel;
	   private XSSFWorkbook workbook;
	   private XSSFSheet sheet;
	   
	   public ExcelGenerator(List < UserDetModel > userDetModel) {
	        this.userDetModel = userDetModel;
	        workbook = new XSSFWorkbook();
	    }
	    private void writeHeader() {
	        sheet = workbook.createSheet("TaxCalculationData");
	        Row row = sheet.createRow(0);
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(16);
	        style.setFont(font);
	        createCell(row, 0, "UserId", style);
	        createCell(row, 1, "UserName", style);
	        createCell(row, 2, "FinacialYear", style);
	        createCell(row, 3, "Age", style);
	        createCell(row, 4, "Earn", style);
	        createCell(row, 5, "IncomeValue", style);
	        createCell(row, 6, "InterestValue",style);
	        createCell(row, 7, "OtherSourceValue",style);
	        createCell(row, 8, "Tax",style);
	        createCell(row, 9, "NetSalary",style);
	    }
	    
	    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
	        sheet.autoSizeColumn(columnCount);
	        Cell cell = row.createCell(columnCount);
	        if (valueOfCell instanceof Integer) {
	            cell.setCellValue((Integer) valueOfCell);
	        } else if (valueOfCell instanceof Long) {
	            cell.setCellValue((Long) valueOfCell);
	        } else if (valueOfCell instanceof String) {
	            cell.setCellValue((String) valueOfCell);
	        }

	        cell.setCellStyle(style);
	    }
	    
	    private void write() {
	        int rowCount = 1;
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setFontHeight(14);
	        style.setFont(font);
	        int count = 0;
	        for (UserDetModel record: userDetModel) {
	            Row row = sheet.createRow(rowCount++);
	            int columnCount = 0;
	            createCell(row, columnCount++, record.getUserId(), style);
	            createCell(row, columnCount++, record.getUserName(), style);
	            createCell(row, columnCount++, record.getFinacialYear(), style);
	            createCell(row, columnCount++, record.getAge(), style);
	            createCell(row, columnCount++, record.getEarn(), style);
	            createCell(row, columnCount++, record.getIncomeDetModel().IncomeValue, style);
	            createCell(row, columnCount++, record.getIncomeDetModel().InterestValue, style);
	            createCell(row, columnCount++, record.getIncomeDetModel().OtherSourceValue, style);
	            createCell(row, columnCount++, record.getIncomeDetModel().Tax, style);
	            createCell(row, columnCount++, record.getIncomeDetModel().NetSalary, style);
	            count++;
	        }
	    }
	    public void generateExcelFile(HttpServletResponse response) throws IOException {
	        writeHeader();
	        write();
	        ServletOutputStream outputStream = response.getOutputStream();
	        workbook.write(outputStream);
	        workbook.close();
	        outputStream.close();
	    }

}
