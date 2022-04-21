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
 * :return type : ModelAndView 적용
 * :Method parameter
 * -client Data :@modelAttribute / @RequestParam 적절이 사용
 * - 필요시 :Servlet API사용
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
	
	//==> 단순 navigation
	@RequestMapping("/logon.do")
	public ModelAndView logon() {
		System.out.println("[logon() start....]"); //<== 디버깅용
		
		//==> Client에 전달할 message생성
		String message = "[logon()] 아이디 패스워드 3자이상 입력하세요";
		
		//==>Model(data) / View(jsp) 정보를 갖는 ModelAndView생성
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/logon.jsp");
		modelAndView.addObject("message",message);
		System.out.println("[logon() end....]\n");
		
		return modelAndView;
	}
	
	//==> 단순 Navigation
	@RequestMapping("/home.do")
	public ModelAndView home(HttpSession session) {
		System.out.println("[home() start....]"); //<==디버깅용
		
		String message = "[home() ] WELCOME";
		
		//===>Model(data) / View(jsp)정보를 갖는 ModelAndView 생성
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/home.jsp");
		modelAndView.addObject("message",message);
		
		System.out.println("[home() end....]\n");
		
		return modelAndView;
	}
	
	//단순 Navigation
	@RequestMapping(value="/logonAction.do",method=RequestMethod.GET)
	public ModelAndView logonAction() {
		System.out.println("[logonAction() method = RequestMethod.GET start...]");
		
		//==> Model(data)/ View(jsp) 정보를 갖는 ModelAndView생성
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/logon.do");
		
		System.out.println("[logonAction() method = RequestMethod.GET end....]\n");
		
		return modelAndView;
	}
	
	//Business Logic  수행 /Nacigation
	@RequestMapping(value="/logonAction.do", method=RequestMethod.POST)
	public ModelAndView logonAction(@ModelAttribute("user") User user, HttpSession session) throws Exception{
		System.out.println("[logonAction() method = RequestMethod.POST start...]");
		
		String viewName = "/user/logon.jsp";
		//////////////
		////UserService 를 이용 Business logic 수행/.////
		///logonAction Method 에 throws Exception 추가함///
		///////////////////////////////////
		//==> CONTROLLER :: Business logic처리
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
		
		System.out.println("[action:"+viewName+"]"); //<==디버깅용
		
		//==> client에 전달할 Message생성
		String message = null;
		if(viewName.equals("/user/home.jsp")) {
			message = "[LogonAction()] SELCOME";
		}else {
			message ="[LogonAction()] 아이디, 패스워드 3자이상 입력하세요";
		}
		
		//==> Model(data) / View(jsp) 정보를 갖는 ModelAndView 생성
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("message",message);
		
		System.out.println("[logonAction() method = RequestMethod.POST end....]\n");
		
		return modelAndView;
	}
	
	//권한 (logon 정보삭제) 확인
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpSession session) {
		System.out.println("[Logout() start...]"); //<==디버깅용
		
		//==> logon 정보 삭제 
		session.removeAttribute("sessionUser");
		
		//==>client 에 전달할 Message 생성
		String message = "[Logout()] 아이디, 패스워드 3자이상 입력하세요.";
		
		//==> Model(data) / View(jsp) 정보를 갖는 ModelAndView생성
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/logon.jsp");
		modelAndView.addObject("message",message);
		System.out.println("[Logout() end....]\n");
		
		return modelAndView;
	}
}