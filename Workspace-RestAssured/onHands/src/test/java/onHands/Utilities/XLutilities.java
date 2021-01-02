package onHands.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLutilities {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFCell cell;
	public static XSSFRow row;
	public static XSSFSheet sheet;
	public static int rowcount, cellcount;
	public static String data;

	public static int getRowCount(String xlFile, String xlSheet) 
	{
		try {
			fi = new FileInputStream(xlFile);
			wb = new XSSFWorkbook(fi);
			sheet = wb.getSheet(xlSheet);
			rowcount = sheet.getLastRowNum();
			wb.close();
			fi.close();

		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occured while getting Row count");
		}
		return rowcount;
	}


	public static int getCellCount(String xlFile, String xlSheet, int rownum) {
		try {
			fi = new FileInputStream(xlFile);
			wb = new XSSFWorkbook(fi);
			sheet = wb.getSheet(xlSheet);
			row = sheet.getRow(rownum);
			cellcount = row.getLastCellNum();
			wb.close();
			fi.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occured while getting cell count");
		}
		return cellcount;

	}

	public static String getCellData(String xlFile, String xlSheet, int rownum, int collnum) throws IOException {
		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(xlSheet);
		row = sheet.getRow(rownum);
		cell = row.getCell(collnum);
		try
		{
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		}
		catch(Exception e)
		{
			data ="";
		}
		finally {
			try {
				
			
		wb.close();
		fi.close();
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error occured while closing  xlfile");
			}
		}
		return data;
	}

	public static void setCellData(String xlFile, String xlSheet, int rownum, int collnum, String data)    {

		try {
			fi = new FileInputStream(xlFile);
			wb = new XSSFWorkbook(fi);
			sheet = wb.getSheet(xlSheet);
			row = sheet.getRow(rownum);
			cell = row.getCell(collnum);
			cell.setCellValue(data);
			fo = new FileOutputStream(xlFile);
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occured while setting data");
		}
		finally {

			try {
				wb.write(fo);
				wb.close();
				fi.close();
				fo.close();}
			catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error occured while closing  xlfile");

			}

		}

	}


}

