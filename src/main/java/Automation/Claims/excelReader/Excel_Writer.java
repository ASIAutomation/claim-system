package Automation.Claims.excelReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Writer {

	public FileOutputStream fileout;
	public String path;
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;


	public Excel_Writer()
	{
		//this.path = path;
		try
		{
			//fileout = new FileOutputStream(path);
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("Results");
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
