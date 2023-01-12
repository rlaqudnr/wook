package net.kbw.domain;

import javax.persistence.Entity;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import antlr.collections.List;


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


