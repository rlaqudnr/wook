package net.kbw.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SerchRepository extends JpaRepository<Question,String> {

	List<Question> findByTitleContaining(String keyword);
	
	
	

}
