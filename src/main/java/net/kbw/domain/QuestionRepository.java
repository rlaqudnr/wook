package net.kbw.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public interface QuestionRepository extends JpaRepository<Question, Long>{

	java.util.List<Question> findByTitleContaining(String keyword);

	Page<Question> findByTitleContaining(String keyword, Pageable pageable);

	
	
	
//	 @Query("SELECT p FROM Article p ORDER BY p.id DESC")
//	    List findAllDesc();
//
//	Object findAllDesc(java.util.List<Question> questionList);
//	
	
	
	
	}


