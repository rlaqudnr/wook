package net.kbw.wook;

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
	
	
	@GetMapping("")
	public String home(Model model,@PageableDefault(sort="id",direction = Sort.Direction.DESC ,size=5)Pageable pageable,String keyword) {
	
	Serch serch = new Serch();
			if(serch.Serch(keyword)) {
				System.out.println("ㄳ");
			
		  Page<Question> question = questionRepository.findByTitleContaining(keyword,pageable);
		
			model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
	        model.addAttribute("next", pageable.next().getPageNumber());
	        
	        model.addAttribute("hasNext",question.hasNext());
	        model.addAttribute("hasPrev",question.hasPrevious());
		
			model.addAttribute("question",question);
		
		    if(question.getTotalPages() == 0) {
		    	System.out.println("ㄳ2");
		    	Page<Question> questions = questionRepository.findAll(pageable);
				
				
				model.addAttribute("question",questions);
				model.addAttribute("previous",pageable.previousOrFirst().getPageNumber());
		        model.addAttribute("next",pageable.next().getPageNumber());
		     
		        model.addAttribute("hasNext",questions.hasNext());
		        model.addAttribute("hasPrev",questions.hasPrevious());
				
			    	return "/user/index";
		    	
		    	
		    }
		    return "/user/index";
	
		}else {
			System.out.println("ㄳ1");
		Page<Question> question = questionRepository.findAll(pageable);
		
		
		model.addAttribute("question",question);
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
     
        model.addAttribute("hasNext",question.hasNext());
        model.addAttribute("hasPrev",question.hasPrevious());
		//model.addAttribute("question",questionRepository.findAll());
		
	return "/user/index";
	
	
	}
			
}
	
}

			

	

