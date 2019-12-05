package co.kr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthorityInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyInterCeptor - preHandle" + getClass().getSimpleName() + " url : " + request.getRequestURI().toString().trim());
		System.out.println("API 인터셉터 수신받은 url의 파라미터값 : "+request.getParameter("authorityid"));
		
		String authorityid = request.getParameter("authorityid");
		HttpSession session = request.getSession();
		session.setAttribute("authorityid", authorityid);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyInterCeptor - postHandle" + getClass().getSimpleName() + " url : " + request.getRequestURI().toString().trim());
		super.postHandle(request, response, handler, modelAndView);
	}
}
