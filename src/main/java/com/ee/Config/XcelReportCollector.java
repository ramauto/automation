package com.ee.Config;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
public class XcelReportCollector
{
	private XSSFWorkbook wb;
	private XSSFSheet sheet;
	private FileOutputStream fos;
	private FileInputStream fis;
	private File testReport;
	private Properties fileNameDecider;

	public XcelReportCollector()
	{
		try
		{
			fileNameDecider = new Properties();
			fileNameDecider.load(new FileInputStream(new File("Report.properties")));
			System.out.println(fileNameDecider.getProperty("dateTime"));
			testReport = new File("TestReports\\"+fileNameDecider.getProperty("dateTime")+"EE Automation"+".xlsx");
			if(testReport.exists() == false)
			{
				System.out.println("Creating a new workbook '" + testReport + "'");
				wb = new XSSFWorkbook();
			}
			else
			{
				final InputStream is = new FileInputStream(testReport);
				try {
					wb = new XSSFWorkbook(is);
				}
				finally {
					is.close();
				}
			}
			fos = new FileOutputStream(testReport);
			//wb = new XSSFWorkbook();

		}
		catch(Exception e)
		{
			System.out.println("Exception in creating test report workbook");
			System.out.println(e.getMessage());
		}
	}

	public void createFeatureReport(String sheetName, ArrayList <String> testStepsResults) throws IOException
	{
		/* Code here to create rows for teststeps sresults */
		sheet = wb.createSheet(sheetName);
		XSSFCellStyle passStyle = wb.createCellStyle();
		XSSFFont passFont = wb.createFont();
		passFont.setColor(IndexedColors.DARK_GREEN.getIndex());
		passStyle.setFont(passFont);
		XSSFCellStyle failStyle = wb.createCellStyle();
		XSSFFont failFont = wb.createFont();
		failFont.setColor(IndexedColors.DARK_RED.getIndex());
		failStyle.setFont(failFont);

		createFeatureReportHeader();

		short rowNum = 2;

		for(String rowMessage : testStepsResults)
		{
			String[] colMessage = rowMessage.split(",");
			short colNum=0;
			XSSFRow Row = sheet.createRow(rowNum++);
			for(int i=0;i<colMessage.length;i++)
			{
				String cellMessage=colMessage[i];
				XSSFCell cell = Row.createCell(colNum++);

				cell.setCellValue((String) cellMessage.trim());
				if(cellMessage.compareTo("PASS")==0)
				{
					cell.setCellStyle(passStyle);
				}
				if(cellMessage.compareTo("FAIL")==0)
				{
					cell.setCellStyle(failStyle);
				}
				if(i==3)
				{
					cell.setCellValue(Long.parseLong(cellMessage.trim()));
				}

			}

		}

		//wb.createSheet("Summary");
		createFeatureReportFooter();
		wb.write(fos);
		//wb.close();
		//fos.close();
	}
	public void createFeatureReportHeader()
	{
		XSSFCell cell0,cell1,cell2, cell3;
		XSSFRow firstRow = sheet.createRow(0);
		sheet.addMergedRegion((new CellRangeAddress(0,0,0,3)));
		cell0 = firstRow.createCell(0);
		//XSSFColor background = new XSSFColor(Color.CYAN);
		XSSFCellStyle firstRowstyle = wb.createCellStyle();
		firstRowstyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		firstRowstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		firstRowstyle.setAlignment(HorizontalAlignment.CENTER);
		cell0.setCellValue(sheet.getSheetName());
		cell0.setCellStyle(firstRowstyle);

		XSSFRow secondRow = sheet.createRow(1);
		XSSFCellStyle firstCellStyle = wb.createCellStyle();
		XSSFCellStyle secondCellStyle = wb.createCellStyle();
		XSSFCellStyle thirdCellStyle = wb.createCellStyle();
		
		cell0 = secondRow.createCell(0);
		firstCellStyle.setAlignment(HorizontalAlignment.CENTER);
		firstCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		firstCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell0.setCellValue("TestCases");
		cell0.setCellStyle(firstCellStyle);

		cell1 = secondRow.createCell(1);
		secondCellStyle.setAlignment(HorizontalAlignment.CENTER);
		secondCellStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
		secondCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell1.setCellValue("Results");
		cell1.setCellStyle(secondCellStyle);


		cell2 = secondRow.createCell(2);
		thirdCellStyle.setAlignment(HorizontalAlignment.CENTER);
		thirdCellStyle.setFillForegroundColor(IndexedColors.BROWN.getIndex());
		thirdCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell2.setCellValue("Remarks");
		cell2.setCellStyle(thirdCellStyle);
	}
	public void createFeatureReportFooter()
	{
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		//sheet.autoSizeColumn(4);
	}
	public void createSummarySheet() throws IOException
	{
		XSSFSheet summary = wb.createSheet("TestSummary");
		XSSFCreationHelper helper = wb.getCreationHelper();
		XSSFRow firstrow = summary.createRow(0);
		XSSFRow secondRow = summary.createRow(1);
		summary.addMergedRegion(new CellRangeAddress(0,0,1,4));

		XSSFCell cell00 = firstrow.createCell(0);
		XSSFCellStyle firstRowStyle = wb.createCellStyle();
		firstRowStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		firstRowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		firstRowStyle.setAlignment(HorizontalAlignment.CENTER);
		cell00.setCellStyle(firstRowStyle);
		XSSFCell cell01 = firstrow.createCell(1);
		cell01.setCellValue("Steps");
		cell01.setCellStyle(firstRowStyle);

		XSSFCell cell10 = secondRow.createCell(0);
		XSSFCell cell11 = secondRow.createCell(1);
		XSSFCell cell12 = secondRow.createCell(2);
		XSSFCell cell13 = secondRow.createCell(3);

		cell10.setCellValue("Features");

		XSSFCellStyle secondCellStyle = wb.createCellStyle();
		secondCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		secondCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		secondCellStyle.setAlignment(HorizontalAlignment.CENTER);
		cell11.setCellValue("Passed");
		cell11.setCellStyle(secondCellStyle);

		XSSFCellStyle thirdCellStyle = wb.createCellStyle();
		thirdCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		thirdCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		thirdCellStyle.setAlignment(HorizontalAlignment.CENTER);
		cell12.setCellValue("Failed");
		cell12.setCellStyle(thirdCellStyle);

		XSSFCellStyle fourthCellStyle = wb.createCellStyle();
		fourthCellStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
		fourthCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		fourthCellStyle.setAlignment(HorizontalAlignment.CENTER);
		cell13.setCellValue("Total");
		cell13.setCellStyle(fourthCellStyle);

/*		XSSFCellStyle fifthCellStyle = wb.createCellStyle();
		fourthCellStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
		fourthCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		fourthCellStyle.setAlignment(HorizontalAlignment.CENTER);
		cell13.setCellValue("TotalTime");
		cell13.setCellStyle(fifthCellStyle);*/

		short startRowNum = 2;

		for(Sheet sh: wb)
		{
			if((summary.getSheetName().compareToIgnoreCase(sh.getSheetName())!=0))
			{
				XSSFRow row = summary.createRow(startRowNum++);
				CreationHelper createHelper = wb.getCreationHelper();
				//XSSFHyperlink link = (XSSFHyperlink) createHelper.createHyperlink(HyperlinkType.DOCUMENT);//createHyperlink
				//XSSFCellStyle linkStyle = wb.createCellStyle();
				//XSSFFont linkFont = wb.createFont();
				//linkFont.setUnderline(FontUnderline.SINGLE);
				//linkFont.setColor(IndexedColors.BLUE.getIndex());
				//linkStyle.setFont(linkFont);
				//link.setAddress("'"+sh.getSheetName()+"'!A1");
				XSSFCell linkCell = row.createCell(0);
				linkCell.setCellValue(sh.getSheetName());
				//linkCell.setHyperlink(link);
				//linkCell.setCellStyle(linkStyle);
				row.createCell(1).setCellFormula("COUNTIF("+sh.getSheetName()+"!B:B,\"PASS\")");
				row.createCell(2).setCellFormula("COUNTIF("+sh.getSheetName()+"!B:B,\"FAIL\")");
				row.createCell(3).setCellFormula("SUM(B"+(row.getRowNum()+1)+",C"+(row.getRowNum()+1)+")");
			}
		}

		XSSFRow lastRow = summary.createRow(startRowNum);
		XSSFCell lastCell0 = lastRow.createCell(0);
		XSSFCell lastCell1 = lastRow.createCell(1);
		XSSFCell lastCell2 =lastRow.createCell(2);
		XSSFCell lastCell3 = lastRow.createCell(3);
		lastCell0.setCellValue("Total");
		lastCell1.setCellFormula("SUM(B3:B"+lastRow.getRowNum()+")");
		lastCell2.setCellFormula("SUM(C3:C"+lastRow.getRowNum()+")");
		lastCell3.setCellFormula("SUM(D3:D"+lastRow.getRowNum()+")");

		summary.autoSizeColumn(0);
		summary.autoSizeColumn(1);
		summary.autoSizeColumn(2);
		summary.setSelected(true);
		wb.setSheetOrder("TestSummary", 0);
		wb.setActiveSheet(0);
		summary.showInPane(0,0);
		wb.write(fos);
	}
}
