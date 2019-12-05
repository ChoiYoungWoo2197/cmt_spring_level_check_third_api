package co.kr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import co.kr.dao.OrderDao;
import co.kr.vo.Total;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ApiController {
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	@Autowired
	private OrderDao dao;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
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

}
