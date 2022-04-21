package spring.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.domain.User;
import spring.service.user.UserService;

/*
 * filename : UserController.java
 * -ControllerCoding Policy
 * :return type : ModelAndView ����
 * :Method parameter
 * -client Data :@modelAttribute / @RequestParam ������ ���
 * - �ʿ�� :Servlet API���
 */

@Controller
public class UserController {

   // field
	///////////////////////////////////
	//////Business logic UserService DI.//////////////
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	//////////////////////////////
	
	//Constructor
	public UserController() {
		System.out.println("==> UserController default Constructor call.....");
	}
	
	//==> �ܼ� navigation
	@RequestMapping("/logon.do")
	public ModelAndView logon() {
		System.out.println("[logon() start....]"); //<== ������
		
		//==> Client�� ������ message����
		String message = "[logon()] ���̵� �н����� 3���̻� �Է��ϼ���";
		
		//==>Model(data) / View(jsp) ������ ���� ModelAndView����
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/logon.jsp");
		modelAndView.addObject("message",message);
		System.out.println("[logon() end....]\n");
		
		return modelAndView;
	}
	
	//==> �ܼ� Navigation
	@RequestMapping("/home.do")
	public ModelAndView home(HttpSession session) {
		System.out.println("[home() start....]"); //<==������
		
		String message = "[home() ] WELCOME";
		
		//===>Model(data) / View(jsp)������ ���� ModelAndView ����
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/home.jsp");
		modelAndView.addObject("message",message);
		
		System.out.println("[home() end....]\n");
		
		return modelAndView;
	}
	
	//�ܼ� Navigation
	@RequestMapping(value="/logonAction.do",method=RequestMethod.GET)
	public ModelAndView logonAction() {
		System.out.println("[logonAction() method = RequestMethod.GET start...]");
		
		//==> Model(data)/ View(jsp) ������ ���� ModelAndView����
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/logon.do");
		
		System.out.println("[logonAction() method = RequestMethod.GET end....]\n");
		
		return modelAndView;
	}
	
	//Business Logic  ���� /Nacigation
	@RequestMapping(value="/logonAction.do", method=RequestMethod.POST)
	public ModelAndView logonAction(@ModelAttribute("user") User user, HttpSession session) throws Exception{
		System.out.println("[logonAction() method = RequestMethod.POST start...]");
		
		String viewName = "/user/logon.jsp";
		//////////////
		////UserService �� �̿� Business logic ����/.////
		///logonAction Method �� throws Exception �߰���///
		///////////////////////////////////
		//==> CONTROLLER :: Business logicó��
		//UserDAO userDAO = new UserDAO();
		//userDAO.getUser(user);
		User returnUser = userService.getUser(user.getUserId());
		if(returnUser.getPassword().equals(user.getPassword())) {
			returnUser.setActive(true);
			user = returnUser;
		}
		////////////////////////////
		
		if(user.isActive()) {
			viewName = "/user/home.jsp";
			session.setAttribute("sessionUser", user);
		}
		
		System.out.println("[action:"+viewName+"]"); //<==������
		
		//==> client�� ������ Message����
		String message = null;
		if(viewName.equals("/user/home.jsp")) {
			message = "[LogonAction()] SELCOME";
		}else {
			message ="[LogonAction()] ���̵�, �н����� 3���̻� �Է��ϼ���";
		}
		
		//==> Model(data) / View(jsp) ������ ���� ModelAndView ����
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("message",message);
		
		System.out.println("[logonAction() method = RequestMethod.POST end....]\n");
		
		return modelAndView;
	}
	
	//���� (logon ��������) Ȯ��
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpSession session) {
		System.out.println("[Logout() start...]"); //<==������
		
		//==> logon ���� ���� 
		session.removeAttribute("sessionUser");
		
		//==>client �� ������ Message ����
		String message = "[Logout()] ���̵�, �н����� 3���̻� �Է��ϼ���.";
		
		//==> Model(data) / View(jsp) ������ ���� ModelAndView����
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/logon.jsp");
		modelAndView.addObject("message",message);
		System.out.println("[Logout() end....]\n");
		
		return modelAndView;
	}
}