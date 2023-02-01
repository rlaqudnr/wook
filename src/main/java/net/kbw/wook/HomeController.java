package net.kbw.wook;

import org.h2.index.PageBtreeLeaf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.kbw.domain.Question;
import net.kbw.domain.QuestionRepository;
import net.kbw.domain.Serch;

@Controller

public class HomeController {
	@Autowired
	private QuestionRepository questionRepository;

	//검색 페이징이 된 게시글 리스트를 보여준다 
	@GetMapping("")
	public String home(Model model,
			@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5) Pageable pageable,String keyword) {
            
		Serch serch = new Serch();
		if (serch.Serch(keyword)) {

			Page<Question> question = questionRepository.findByTitleContaining(keyword, pageable);

			model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
			model.addAttribute("next", pageable.next().getPageNumber());

			model.addAttribute("hasNext", question.hasNext());
			model.addAttribute("hasPrev", question.hasPrevious());

			model.addAttribute("keyword", keyword);

			model.addAttribute("question", question);

			if (question.getTotalPages() == 0) {
				//검색을 했는데 값이 없을때 기본페이지로

				return "/user/index";

			}
			return "/user/index";

		} else {
			//
			//게시글 리스트를 보여준다. serch class에 값이 없을경우 일반 페이징 화면이 나옴
			Page<Question> question = questionRepository.findAll(pageable);

			model.addAttribute("question", question);

			model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());

			model.addAttribute("next", pageable.next().getPageNumber());

			model.addAttribute("hasNext", question.hasNext());
			model.addAttribute("hasPrev", question.hasPrevious());

			keyword = "";
			model.addAttribute("keyword", keyword);
			

			return "/user/index";

		}

	}

}
