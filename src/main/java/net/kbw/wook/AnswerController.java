package net.kbw.wook;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.kbw.domain.Answer;
import net.kbw.domain.AnswerRepository;
import net.kbw.domain.Question;
import net.kbw.domain.QuestionRepository;
import net.kbw.domain.User;

@Controller

@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {

	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/create")
	//댓글쓰기
	public String create(@PathVariable Long questionId, String contents, HttpSession session) {

		
		//로그인 안됐을때 로그인 불가
		if (!HttpSessionUtils.isLoginUser(session)) {

			return "redirect:/users/loginForm";
		}
		//

		User loginUser = HttpSessionUtils.getUserFromSession(session);

		Question question = questionRepository.findById(questionId).get();
		Answer answer = new Answer(loginUser, contents, question);

		answerRepository.save(answer);

		return String.format("redirect:/questions/%d", questionId);

	}

	//댓글수정
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long questionId, String contents) {

		
		Answer answer = answerRepository.findById(questionId).get();
	
		answer.update(contents);
	
		answerRepository.save(answer);
		
		
	
		return "redirect:/questions/{id}";
	}

	//댓글수정 화면
	@GetMapping("/updateform/{id}")
	public String updateform(@PathVariable Long questionId, Model model, HttpSession session) {
		User suser = HttpSessionUtils.getUserFromSession(session);

		Answer answer = answerRepository.findById(questionId).get();

	
		if (!HttpSessionUtils.isLoginUser(session)) {

			// 로그인이 안됐을때 로그인폼으로
			return "/users/loginForm";
		}

		// 로그인은 했으나 작성자가 아닐때 수정,삭제를 못하게함
		if (!answer.isSameWriter(suser)) {

			return "/users/loginForm";

		}
		model.addAttribute("answers", answerRepository.findById(questionId).get());

		return "/qna/AnswerForm";

	}

    //댓글 삭제
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long questionId, HttpSession session) {

		User suser = HttpSessionUtils.getUserFromSession(session);
		Answer answer = answerRepository.findById(questionId).get();

		if (!HttpSessionUtils.isLoginUser(session)) {

			// 로그인이 안됐을때 로그인폼으로
			return "/users/loginForm";
		}

		// 로그인은 했으나 작성자가 아닐때 수정,삭제를 못하게함
		if (!answer.isSameWriter(suser)) {

			return "/users/loginForm";

		}

		
		answerRepository.deleteById(questionId);

		return "redirect:/questions/{id}";

	}
}
