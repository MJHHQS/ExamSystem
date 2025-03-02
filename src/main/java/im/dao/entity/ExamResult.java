package im.dao.entity;

import java.io.Serializable;
import java.util.Objects;

public class ExamResult implements Serializable {

	private static final long serialVersionUID = 1L;

	
	String title ; 
	String answer;
	String ChangeAnswer;
	
	public ExamResult() {
		// TODO Auto-generated constructor stub
	}
	
	public ExamResult(String title,String answer,String ChangeAnswer) {
		this.title = title ; 
		this.answer = answer ; 
		this.ChangeAnswer = ChangeAnswer ; 
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getChangeAnswer() {
		return ChangeAnswer;
	}
	public void setChangeAnswer(String changeAnswer) {
		ChangeAnswer = changeAnswer;
	}
	
	public int hashCode() {
		return Objects.hash(ChangeAnswer, answer, title);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExamResult other = (ExamResult) obj;
		return Objects.equals(ChangeAnswer, other.ChangeAnswer) && Objects.equals(answer, other.answer)
				&& Objects.equals(title, other.title);
	}
	public String toString() {
		return "ExamResult [title=" + title + ", answer=" + answer + ", ChangeAnswer=" + ChangeAnswer + "]";
	}
	
	
	
}
