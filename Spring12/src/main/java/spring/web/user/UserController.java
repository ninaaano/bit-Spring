package spring.web.user;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import spring.domain.User;
import spring.service.user.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

   // field
   @Autowired
   @Qualifier("userService")
   private UserService userService;

   // Constructor
   public UserController() {
      System.out.println("==> UserController default Constructor call...");
   }


   @RequestMapping("/logon")
   public String logon(Model model) {
      System.out.println("[ logon() start.... ]"); //<= 디버깅용
      
      //==> Client에 전달할 Message 생성
      String message ="[logon()] 아이디, 패스워드 3자이상 입력하세요.";
      
      //==> Model (data)
      model.addAttribute("message", message);
      
      System.out.println("[ logon() end.... ]\n");
      
      return"/user/logon.jsp";
      
   }
   
   //==> 단순 Navigation
   @RequestMapping("/home")
   public String home(Model model) {
      System.out.println("[ logon() start.... ]"); //<= 디버깅용
      String message ="[home()] WELCOME";
      
      //==> Model (data)
      model.addAttribute("message", message);
      
      System.out.println("[ logon() end.... ]\n");
      return"/user/home.jsp";
   }
   
   //Business Logic 수행 / Navigation
   @RequestMapping(value = "logonAction", method = RequestMethod.POST)
   public String logonAction(@ModelAttribute("user") User user, 
                        Model model, HttpSession session) throws Exception{
      System.out.println("[logonAction() method] :: RequestMethod.POST");
      
      String viewName="/user/logon.jsp";
      
      User returnUser = userService.getUser(user.getUserId());
      if(returnUser.getPassword().equals(user.getPassword())){
         returnUser.setActive(true);
         user = returnUser;
      }
      
      if(user.isActive()) {
         viewName = "/user/home.jsp";
         session.setAttribute("sessionUser", user);
      }
      
      System.out.println("[action :: " + viewName +"]"); //<= 디버기잉..
      
      //==> Client에 전달할 Message 생성
      String message = null;
      if(viewName.equals("/user/home.jsp")) {
         message = "[LogonAction()] WELCOME!";
         
      }else {
         message="[LogonAction() ] 아이디 , 패스워스 3자 이상 입력하세요.";
      }
      
      //==> Model(data)
      model.addAttribute("message",message);
      System.out.println("[logonAction() method = RequestMethod.POST end....]");
      
      return viewName;
   }
   
   //권한(logon 정보삭제) 확인 / navigation
   @RequestMapping("/logout")
   public String logout(Model model, HttpSession session) {
      System.out.println("[logonout() start.....]"); //<== 디버깅..
      
      //==> logon정보 삭제
      session.removeAttribute("sessionUser");
      
      //==> Client에 전달할 Message 생성
      String message ="[Logout()] 아이디, 패스워드 3자이상 입력하세요.";
      
      //Model(data)
      model.addAttribute("message", message);
      
      
      System.out.println("[Logout() end....] \n");
      
      return "/user/logon.jsp";
   }
}