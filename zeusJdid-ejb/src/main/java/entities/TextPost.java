package entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TextPost
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class TextPost extends Post implements Serializable {

	
	private String content;
	private static final long serialVersionUID = 1L;

	public TextPost() {
		super();
	}   
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
   
}
