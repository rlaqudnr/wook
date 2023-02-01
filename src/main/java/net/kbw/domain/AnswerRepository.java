package net.kbw.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long>{

	List<Answer> findBywriter_id(Long id);

	void deleteBywriter_id(Long id);



	









}
