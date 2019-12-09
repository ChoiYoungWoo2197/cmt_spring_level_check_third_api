package co.kr.service;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Repository;
import co.kr.vo.Total;

@Repository
public class ExcelServiceImpl implements ExcelService {

	@Override
	public List<Total> upLoadExcelFile(String excelFile) {
		// TODO Auto-generated method stub
		 List<Total> list = new ArrayList<Total>();
	        try {
	            //OPCPackage opcPackage = OPCPackage.open(excelFile.getInputStream());
	            //XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
	            Workbook workbook = WorkbookFactory.create(new FileInputStream(excelFile));
	            // 첫번째 시트 불러오기
	            Sheet sheet = workbook.getSheetAt(0);
	            
	            for(int i=1; i<sheet.getLastRowNum() + 1; i++) {
	            	Total total = new Total();
	                Row row = sheet.getRow(i);
	                
	                // 행이 존재하기 않으면 패스
	                if(null == row) {
	                    continue;
	                }
	                
	                // 행의 두번째 열(첫번째부터 받아오기) 
	                total.setOrderId(Integer.parseInt(cellValue(row.getCell(0))));
	                total.setCustomId(Integer.parseInt(cellValue(row.getCell(1))));
	                total.setCustomName(cellValue(row.getCell(2)));
	                total.setProductId(Integer.parseInt(cellValue(row.getCell(3))));
	                total.setProductName(cellValue(row.getCell(4)));
	                
	                list.add(total);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	}
	
    public static String cellValue(Cell cell) {
 
        String value = null;
        if (cell == null)
            value = "";
        else {
            switch (cell.getCellType()) { // cell 타입에 따른 데이타 저장
            case Cell.CELL_TYPE_FORMULA:
                value = cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    // you should change this to your application date format
                    SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    value = ""
                            + objSimpleDateFormat.format(cell
                                    .getDateCellValue());
                } else {
                    value = ""
                            + String.format("%.0f",
                                    new Double(cell.getNumericCellValue()));
                }
                break;
            case Cell.CELL_TYPE_STRING:
                value = "" + cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                // value=""+cell.getBooleanCellValue();
                value = "";
                break;
            case Cell.CELL_TYPE_ERROR:
                value = "" + cell.getErrorCellValue();
                break;
            default:
            }
        }
 
        return value.trim();
    }


}