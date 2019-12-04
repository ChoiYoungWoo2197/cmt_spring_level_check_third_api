package co.kr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthorityInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyInterCeptor - preHandle" + getClass().getSimpleName() + " url : " + request.getRequestURI().toString().trim());
		HttpSession session = request.getSession();

		String id = (String)session.getAttribute("shop"); 

		if(id == null) {
			System.out.println("세션 없음");
			response.sendRedirect(request.getContextPath() + "/shop/");
			return false;
		}

		System.out.println(id +" : 세션 있음");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyInterCeptor - postHandle" + getClass().getSimpleName() + " url : " + request.getRequestURI().toString().trim());
		super.postHandle(request, response, handler, modelAndView);
	}
}
