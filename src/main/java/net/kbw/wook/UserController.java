package net.kbw.wook;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	@GetMapping("/login")
	public String login() {
		
		return "/user/login";
		
		
		
	}
	
	
	
	@PostMapping("")
	public String create(User user) {
	
		
		
		
		
		System.out.println(user);
		
		userRepository.save(user);
		 
		return "redirect:/user/list";
		
		
		
	} 
	
	@GetMapping("")
	
	public String list(Model model) {
		//model.addAttribute("users",users);
		 model.addAttribute("users",userRepository.findAll());
		 
		 System.out.println("시~발왜안댐");
		 
			return "/user/list";
	

}
	
	
	@GetMapping("/{id}/form")
	public String updateform(@PathVariable Long id,Model model) {
		
		
		
	User user =userRepository.findById(id).get();
	
	System.out.println(user.toString());

		model.addAttribute("user",user);
		
		return "/user/updateform";
	}
	
	

	

	
}
