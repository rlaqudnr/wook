package net.kbw.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Question {
	@Id // id속성 기본키로
	@GeneratedValue Long id;
	
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "question")
	@OrderBy("id DESC")
	private List<Answer> answers;

	
	
	
	@SuppressWarnings("unused")
	private String title;
	@Lob
	private String contents;
	
public Question() {}
public Question(User writer, String title, String contents) {
	super();
	
	this.writer = writer;
	this.title = title;
	this.contents = contents;
	this.createDate =LocalDateTime.now();
}


public boolean isSameWriter(User SessionedUser) {
	return this.writer.equals(SessionedUser);
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}

////
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Question other = (Question) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}


public String getFormatCreateDate() {
	if(createDate == null) {
		return "";
	}
	
	return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
}




public void update(String title, String contents) {
	// TODO Auto-generated method stub
	this.title = title;
	this.contents = contents;
}

}