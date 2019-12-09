package co.kr.service;

import java.util.List;
import co.kr.vo.Total;

public interface ExcelService {
	public List<Total> upLoadExcelFile(String excelFile);
}