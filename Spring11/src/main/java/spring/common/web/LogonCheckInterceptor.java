package spring.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import spring.domain.User;

/*fileaName : LogonCheckInterceptor.java
 *  o Controller : ȣ���� Interceptor �� ���� ��ó��/��ó��/�Ϸ�ó���� ����
 *   -preHandle() : Controller ȣ���� ��ó��
 *   (true return ==> Controller ȣ�� / false return ==> Controller ��ȣ��)
 *   -postHandle() : controller ȣ�� �� ��ó��
 *   -afterCompleton() : view ������ ó��
 *   ==> �α����� ȸ���̸� Controller ȣ�� : true return
 *   ==> �� �α����� ȸ���̸� Controller �� ȣ�� : flase return
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
		
		//==> �α��� ����Ȯ��
		HttpSession session = request.getSession(true);
		User sessionUser = null;
		if((sessionUser = (User)session.getAttribute("sessionUser"))==null){
			sessionUser = new User();
		}
		
		//==> �α����� ȸ���̶�� 
		if(sessionUser.isActive()) {
			//==> �α��� ���¿��� �α��� �õ� ��...
			String uri = request.getRequestURI();
			if(uri.indexOf("logonAction")!=-1||uri.indexOf("logon")!=-1) {
				request.getRequestDispatcher("/user002/home.jsp").forward(request, reponse);
				System.out.println("[�α��� ����..�α��� �� ���ʿ� �� �䱸...]");
				System.out.println("[LogonCheckInterceptor end....]\n");
				return false;
			}
			System.out.println("[�α����� ����...]");
			System.out.println("[LogonCheckInterceptor end....]\n");
			return true;
		}else { //=> �� �α����� ȸ���̶�� 
			//==> �α��� �õ� ��
			String uri = request.getRequestURI();
			if(uri.indexOf("logonAction")!=-1||uri.indexOf("logon")!=-1) {
				System.out.println("[�α� �õ� ����...]");
				System.out.println("[LogonCheckInterceptor end....]\n");
				return true;
			}
			
			request.getRequestDispatcher("/user002/logon.jsp").forward(request, reponse);
			System.out.println("[�α��� ����...]");
			System.out.println("[LogonCheckInterceptor end....]\n");
			return false;
			
		}
	}

}//end of class
