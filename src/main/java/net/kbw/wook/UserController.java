package net.kbw.wook;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.kbw.domain.User;
import net.kbw.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	
	
	
	
	
	
	@GetMapping("/form")
	public String form() {
		
		return "/user/form";
		
		
	}
	
	
	@PostMapping("/update")
	public String update(Long id, User newUser) {
		
		
		System.out.println(id);
		
		User user =userRepository.findById(id).get();
		user.update(newUser);
		userRepository.save(user);
		return "redirect:/users";
		
		
		
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		
		
		
		
		
		return "/user/login";
		
		
		
	}
	
	
	
	
	@PostMapping("/login")
	public String login(String userId,String password ,HttpSession session) {
		
		User user =userRepository.findByUserId(userId);
		
		if(user == null) {
			return "redirect:/users/loginForm";
			
		}
		if(!user.matchPassword(password)) {
			
			return "redirect:/users/loginForm";
		}
		
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		return "redirect:/";
		
		
		
	}
	
	
	
@GetMapping("/logout")
	
	public String logout( HttpSession session) {
		//model.addAttribute("users",users);
		
		 session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		 
			return "redirect:/";
	

}
	
	
	@PostMapping("")
	public String create(User user ,HttpSession session) {
	
		
		
		
		
		System.out.println(user);
		
		userRepository.save(user);
		//session.setAttribute("user", user);
		return "redirect:/";
		
		
		
	} 
	
	@GetMapping("")
	
	public String list(Model model) {
		//model.addAttribute("users",users);
		 model.addAttribute("users",userRepository.findAll());
		 
		 System.out.println("시~발왜안댐");
		 
			return "/user/list";
	

}
	
	
	@GetMapping("/{id}/form")
	public String updateform(@PathVariable Long id,Model model ,HttpSession session) {
//		if(HttpSessionUtils.isLoginUser(session)) {
//			return "redirect:/users/loginForm";
//		}     
		
		
		 User suser = HttpSessionUtils.getUserFromSession(session);
		 if(!suser.matchId(id)) {
			 
			  return "redirect:/";
			  
			  
			 //throw new IllegalStateException("자산의 정보만 수정");
			 
			 
		 }else {
				User user =userRepository.findById(id).get();
				
				model.addAttribute("user",user);
				
				return "/user/updateform";
			}
		 }
		 
		
	
	
}
