package net.kbw.wook;

import javax.persistence.Converter;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String create(@PathVariable Long questionId, String contents, HttpSession session) {

		if (!HttpSessionUtils.isLoginUser(session)) {

			return "/users/loginForm";
		}
		//
		User loginUser = HttpSessionUtils.getUserFromSession(session);

		Question question = questionRepository.findById(questionId).get();
		Answer answer = new Answer(loginUser, contents, question);
		answerRepository.save(answer);

		return String.format("redirect:/questions/%d", questionId);

	}

	@GetMapping("/update")
	public String update(@PathVariable Long Id, String contents) {

		Answer answer = answerRepository.findById(Id).get();

		answer.update(contents);

		answerRepository.save(answer);

		return "redirect:/questions/{id}";

	}

	@GetMapping("/updateform")
	public String updateform(@PathVariable Long questionId, Model model) {

		model.addAttribute("answers", answerRepository.findById(questionId).get());

		return "/qna/AnswerForm";

	}

	@GetMapping("/delete")
	public String delete(@PathVariable Long questionId) {

		answerRepository.deleteById(questionId);

		return "/";

	}
}
