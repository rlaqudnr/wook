package net.kbw.wook;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.kbw.domain.Question;
import net.kbw.domain.User;
import net.kbw.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	// 회원정보수정
	@GetMapping("/{id}/updatemy")
	public String updatemy(@PathVariable Long id, Model model, HttpSession session) {

		User suser = HttpSessionUtils.getUserFromSession(session);

		if (!HttpSessionUtils.isLoginUser(session)) {

			// 로그인이 안됐을때 로그인폼으로
			return "/users/loginForm";
		}

		if (!suser.matchId(id)) {

			return "redirect:/";

		} else {

			User user = userRepository.findById(id).get();

			model.addAttribute("user", user);
			return "/user/uupdateform";
		}
	}

	// 회원가입
	@GetMapping("/form")
	public String form() {

		return "/user/form";

	}

	// 회원수정
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, User newUser) {

		System.out.println(id);

		User user = userRepository.findById(id).get();
		user.update(newUser);
		userRepository.save(user);
		return "redirect:/users";

	}

	// 로그인페이지
	@GetMapping("/loginForm")
	public String loginForm() {

		return "/user/login";

	}

	// 로그인 백엔드
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {

		User user = userRepository.findByUserId(userId);

		// 로그인시 아이디가 일치하지 않을때.
		if (user == null) {
			return "redirect:/users/loginForm";

		}

		// 아이디는 일치하지만 비밀번호가 틀릴때
		if (!user.matchPassword(password)) {

			return "redirect:/users/loginForm";

		}

		// 로그인 성공후 세션담기
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		return "redirect:/";

	}

	// 로그아웃
	@GetMapping("/logout")

	public String logout(HttpSession session) {
		// model.addAttribute("users",users);

		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);

		return "redirect:/";

	}

	// 회원가입
	@PostMapping("")
	public String create(User user, HttpSession session) {

		userRepository.save(user);

		return "redirect:/";

	}

	// 회원목록
	@GetMapping("")

	public String list(Model model) {

		List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

		model.addAttribute("users", users);

		return "/user/list";

	}

}
