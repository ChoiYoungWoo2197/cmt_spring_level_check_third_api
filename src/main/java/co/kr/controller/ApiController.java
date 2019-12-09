package co.kr.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import co.kr.dao.TotalDao;
import co.kr.service.ExcelService;
import co.kr.vo.Total;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ApiController {
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	@Autowired
	private TotalDao dao;
	
	@Autowired
	private ExcelService excelService;

	@Resource(name = "uploadPath")
	String upLoadPath;
	
	@ResponseBody
	@RequestMapping(value = "orders" , method = RequestMethod.GET)
	public List<Total> api(Locale locale, HttpSession session, HttpServletRequest request) {
		logger.info("api call {}.", locale);
		List<Total> totalList = new ArrayList<Total>();
		try {
			String authority = (String) session.getAttribute("authorityid");
			if(authority.equals("1234567")) {
				totalList = dao.selectAll();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalList;
	}

	@RequestMapping(value = "/upload" , method = RequestMethod.GET)
	public String upLoadView(Locale locale, HttpServletRequest request) {
		logger.info("api upload call {}.", locale);
		return "upload";
	}

	@RequestMapping(value="/excelUpload", method=RequestMethod.POST, produces="text/plain")
	public String upload(MultipartHttpServletRequest request, Model mode) throws Exception {
		System.out.println("excelUpload call!!");
		
		try {
	        MultipartFile file = null;
	        Iterator<String> iterator = request.getFileNames();
	        if(iterator.hasNext()) {
	            file = request.getFile(iterator.next());
	        }
	        
	        String tempFileName = file.getOriginalFilename();
	        File tempFile = new File(upLoadPath, tempFileName);
	        FileCopyUtils.copy(file.getBytes(), tempFile);
	        
			List<Total> totalList = excelService.uploadExcelFile(upLoadPath + "\\" + file.getOriginalFilename());
			
			if(tempFile.exists()) {
				tempFile.delete();
			}
			
			dao.UpdateAll(totalList);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "upload";

	}

}