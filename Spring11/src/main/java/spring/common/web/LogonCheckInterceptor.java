package spring.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import spring.domain.User;

/*fileaName : LogonCheckInterceptor.java
 *  o Controller : 호출전 Interceptor 를 통해 선처리/후처리/완료처리를 수행
 *   -preHandle() : Controller 호출전 선처리
 *   (true return ==> Controller 호출 / false return ==> Controller 미호출)
 *   -postHandle() : controller 호출 후 후처리
 *   -afterCompleton() : view 생성후 처리
 *   ==> 로그인한 회원이면 Controller 호출 : true return
 *   ==> 비 로그인한 회원이며 Controller 미 호출 : flase return
 */
public class LogonCheckInterceptor extends HandlerInterceptorAdapter{

	//field
	
	//constructor
	public LogonCheckInterceptor() {
		System.out.println("==> LogonCheckInterceptor() default Constructor call......");
		// TODO Auto-generated constructor stub
	}
	
	//method
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse reponse,
			Object handler) throws Exception{
		System.out.println("\n[LogonCheckInterceptor start....]");
		
		//==> 로그인 유무확인
		HttpSession session = request.getSession(true);
		User sessionUser = null;
		if((sessionUser = (User)session.getAttribute("sessionUser"))==null){
			sessionUser = new User();
		}
		
		//==> 로그인한 회원이라면 
		if(sessionUser.isActive()) {
			//==> 로그인 상태에서 로그인 시도 중...
			String uri = request.getRequestURI();
			if(uri.indexOf("logonAction")!=-1||uri.indexOf("logon")!=-1) {
				request.getRequestDispatcher("/user002/home.jsp").forward(request, reponse);
				System.out.println("[로그인 상태..로그인 후 불필요 한 요구...]");
				System.out.println("[LogonCheckInterceptor end....]\n");
				return false;
			}
			System.out.println("[로그인한 상태...]");
			System.out.println("[LogonCheckInterceptor end....]\n");
			return true;
		}else { //=> 미 로그인한 회원이라면 
			//==> 로그인 시도 중
			String uri = request.getRequestURI();
			if(uri.indexOf("logonAction")!=-1||uri.indexOf("logon")!=-1) {
				System.out.println("[로그 시도 상태...]");
				System.out.println("[LogonCheckInterceptor end....]\n");
				return true;
			}
			
			request.getRequestDispatcher("/user002/logon.jsp").forward(request, reponse);
			System.out.println("[로그인 이전...]");
			System.out.println("[LogonCheckInterceptor end....]\n");
			return false;
			
		}
	}

}//end of class
