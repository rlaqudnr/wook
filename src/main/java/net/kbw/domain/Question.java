package net.kbw.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
	@Id // id속성 기본키로
	@GeneratedValue
	private Long id;
	
	
	private String writer;
	

	private String title;
	private String contents;
	
public Question() {}
public Question( String writer, String title, String contents) {
	super();
	
	this.writer = writer;
	this.title = title;
	this.contents = contents;
}


}
