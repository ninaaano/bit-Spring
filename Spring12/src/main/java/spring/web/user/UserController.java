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
      System.out.println("[ logon() start.... ]"); //<= ������
      
      //==> Client�� ������ Message ����
      String message ="[logon()] ���̵�, �н����� 3���̻� �Է��ϼ���.";
      
      //==> Model (data)
      model.addAttribute("message", message);
      
      System.out.println("[ logon() end.... ]\n");
      
      return"/user/logon.jsp";
      
   }
   
   //==> �ܼ� Navigation
   @RequestMapping("/home")
   public String home(Model model) {
      System.out.println("[ logon() start.... ]"); //<= ������
      String message ="[home()] WELCOME";
      
      //==> Model (data)
      model.addAttribute("message", message);
      
      System.out.println("[ logon() end.... ]\n");
      return"/user/home.jsp";
   }
   
   //Business Logic ���� / Navigation
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
      
      System.out.println("[action :: " + viewName +"]"); //<= �������..
      
      //==> Client�� ������ Message ����
      String message = null;
      if(viewName.equals("/user/home.jsp")) {
         message = "[LogonAction()] WELCOME!";
         
      }else {
         message="[LogonAction() ] ���̵� , �н����� 3�� �̻� �Է��ϼ���.";
      }
      
      //==> Model(data)
      model.addAttribute("message",message);
      System.out.println("[logonAction() method = RequestMethod.POST end....]");
      
      return viewName;
   }
   
   //����(logon ��������) Ȯ�� / navigation
   @RequestMapping("/logout")
   public String logout(Model model, HttpSession session) {
      System.out.println("[logonout() start.....]"); //<== �����..
      
      //==> logon���� ����
      session.removeAttribute("sessionUser");
      
      //==> Client�� ������ Message ����
      String message ="[Logout()] ���̵�, �н����� 3���̻� �Է��ϼ���.";
      
      //Model(data)
      model.addAttribute("message", message);
      
      
      System.out.println("[Logout() end....] \n");
      
      return "/user/logon.jsp";
   }
}