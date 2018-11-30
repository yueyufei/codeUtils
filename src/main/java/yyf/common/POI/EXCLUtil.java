package yyf.common.POI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EXCLUtil {
	private Workbook workbook;

	public EXCLUtil(Workbook workboook) {
        this.workbook = workboook;
    }

	public EXCLUtil(InputStream is, String version) throws FileNotFoundException, IOException {
        if ("2003".equals(version)) {
			workbook = new HSSFWorkbook(is);// .xls
        } else {
			workbook = new XSSFWorkbook(is);// .xlsx
        }
    }

	/**
	 * 
	 * 读取 Excel 第一页所有数据
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public List<List<String>> readEXCL() throws Exception {
		return readEXCL(0, 0, getRowCount(0) - 1);
	}

	/**
	 * 
	 * 读取指定sheet 页所有数据
	 * 
	 * @param sheetIx
	 *            指定 sheet 页，从 0 开始
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> readEXCL(int sheetIndex) throws Exception {
		return readEXCL(sheetIndex, 0, getRowCount(sheetIndex) - 1);
	}

	public List<List<String>> readEXCL(int sheetIndex, int startRow, int endRow) {
		List<List<String>> list = new ArrayList<List<String>>();
		if (existSheet(sheetIndex)) {
			if (endRow > getRowCount(sheetIndex)) {
				endRow = getRowCount(sheetIndex);
			}
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			int cols = sheet.getRow(0).getLastCellNum(); // 第一行总列数
			for (int i = startRow; i <= endRow; i++) {
				List<String> rowList = new ArrayList<String>();
				Row row = sheet.getRow(i);
				for (int j = 0; j < cols; j++) {
					if (row == null) {
						rowList.add(null);
						continue;
					}
					rowList.add(row.getCell(j).getStringCellValue());
				}
				list.add(rowList);
			}
		}
		return list;
	}

	public boolean write(List<List<String>> data) throws IOException {
		Boolean result = write(data, 0, 0);
		return result;
	}
	public boolean newSheetWrite(List<List<String>> data, String sheetName, boolean isNewSheet) throws IOException {
		Sheet sheet = null;
		if (isNewSheet) {
			sheet = workbook.createSheet(sheetName);
		} else {
			sheet = workbook.createSheet();
		}
		int sheetIndex = workbook.getSheetIndex(sheet);
		return write(data, sheetIndex, 0);
	}

	public boolean appendWrite(List<List<String>> data, int sheetIndex, boolean isAppend) throws IOException {
		if (isAppend) {
			return write(data, sheetIndex, getRowCount(sheetIndex));
		} else {
			clearSheet(sheetIndex);
			return write(data, sheetIndex, 0);
		}
	}

	public Boolean write(List<List<String>> data, int sheetIndex, int startRow) {
		Sheet sheet = null;
		int rowsNum = data.size();
		int rowCount = getRowCount(sheetIndex);
		if (existSheet(sheetIndex)) {
			sheet = workbook.getSheetAt(sheetIndex);
			int lastRow = sheet.getLastRowNum();
			if (rowCount > 0) {
				startRow = lastRow + 1;
				sheet.shiftRows(startRow, rowCount, rowsNum);
			}
		} else {
			sheet = workbook.createSheet();
		}
		for (int i = 0; i < rowsNum; i++) {
			Row row = sheet.createRow(i + startRow);
			for (int j = 0; j < data.get(i).size(); j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(data.get(i).get(j) + "");
			}
		}
		return true;
	}

	/**
	 * 清空sheet,sheet存在并清空，不存在返回false
	 * 
	 * @param sheetIx
	 * @return
	 * @throws IOException
	 */
	public boolean clearSheet(int sheetIndex) throws IOException {
		if (existSheet(sheetIndex)) {
			String sheetname = getSheetName(sheetIndex);
			removeSheetAt(sheetIndex);
			workbook.createSheet(sheetname);
			setSheetOrder(sheetname, sheetIndex);
			return true;
		}
		return false;
	}

	/**
	 * 获取sheet名称,存在返回名称，不存在返回null
	 * 
	 * @param sheetIx
	 * @return
	 * @throws IOException
	 */
	public String getSheetName(int sheetIndex) throws IOException {
		if (existSheet(sheetIndex)) {
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			return sheet.getSheetName();
		}
		return null;
	}

	/**
	 * 
	 * 设置sheet 页的索引
	 * 
	 * @param sheetName
	 *            Sheet 名称
	 * @param sheetIndex
	 *            Sheet 索引，从0开始
	 */
	public void setSheetOrder(String sheetName, int sheetIndex) {
		workbook.setSheetOrder(sheetName, sheetIndex);
	}

	/**
	 * 
	 * 设置sheet名称，长度为1-31，不能包含后面任一字符: ：\ / ? * [ ]
	 * 
	 * @param sheetIx
	 *            指定 Sheet 页，从 0 开始，//
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public boolean setSheetName(int sheetIndex, String sheetName) throws IOException {
		workbook.setSheetName(sheetIndex, sheetName);
		return true;
	}

	/**
	 * 删除指定的sheet,如果存在就删除并返回true,不存在返回false
	 * 
	 * @param sheetIndex
	 * @return
	 * @throws IOException
	 */
	public boolean removeSheetAt(int sheetIndex) {
		if (existSheet(sheetIndex)) {
			workbook.removeSheetAt(sheetIndex);
			return true;
		}
		return false;
	}

	/**
	 * 判断是否存在某个sheet
	 * 
	 * @param sheetIndex
	 * @return
	 */
	public Boolean existSheet(int sheetIndex) {
		Sheet sheet = null;
		try {
			sheet = workbook.getSheetAt(sheetIndex);
			if (sheet != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断是否存在制定名称的sheet，如果存在则返回该sheet，不存在就创建指定名称的sheet
	 * 
	 * @param sheetName
	 * @return
	 */
	public Sheet existSheetName(String sheetName) {
		Sheet sheet = null;
		try {
			sheet = workbook.getSheet(sheetName);
			if (sheet != null) {
				return sheet;
			} else {
				sheet = workbook.createSheet(sheetName);
				return sheet;
			}
		} catch (Exception e) {
			sheet = workbook.createSheet(sheetName);
			return sheet;
		}
	}

	/**
	 * 返回sheet行数
	 * 
	 * @param sheetIndex
	 * @return
	 */
	public int getRowCount(int sheetIndex) {
		if (existSheet(sheetIndex)) {
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			if (sheet.getPhysicalNumberOfRows() == 0) {
				return 0;
			}
			return sheet.getLastRowNum() + 1;
		}
		return 0;
	}

	/**
	 * 
	 * 根据后缀判断是否为 Excel 文件，后缀匹配xls和xlsx
	 * 
	 * @param pathname
	 * @return
	 * 
	 */
	public static boolean isExcel(String pathname) {
		if (pathname == null) {
			return false;
		}
		return pathname.endsWith(".xls") || pathname.endsWith(".xlsx");
	}

	public Boolean write2File(String path) {
		try {
			if (isExcel(path)) {
				FileOutputStream os = new FileOutputStream(new File(path));
				os.flush();
				workbook.write(os);
				os.close();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}

	public static void main(String[] args) {
		try {
			EXCLUtil excl = new EXCLUtil(new FileInputStream(new File("a.xlsx")), "2017");
			List<List<String>> list = excl.readEXCL();
			System.out.println(list.toString());
		} catch (Exception e) {
		}
	}
}
