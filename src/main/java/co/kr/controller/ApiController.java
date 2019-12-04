package co.kr.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import co.kr.dao.OrderDao;
import co.kr.domain.Order;

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
	public List<Order> home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		//OrderDaoImpl  orderServiceImple = new OrderDaoImpl();
		List<Order> orderList = new ArrayList<Order>();
		
		try {
			//orderList = orderServiceImple.selectAll(dataSource);
			orderList = dao.selectAll();
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
			
			String formattedDate = dateFormat.format(date);
			
			model.addAttribute("serverTime", formattedDate );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orderList;
	}
	

	
}
