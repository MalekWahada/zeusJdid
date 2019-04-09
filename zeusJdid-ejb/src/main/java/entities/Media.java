package entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Media
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Media extends Post  implements Serializable {

	
	private String path_url;
	private String description;
	private static final long serialVersionUID = 1L;

	public Media() {
		super();
	}   
	public String getPath_url() {
		return this.path_url;
	}

	public void setPath_url(String path_url) {
		this.path_url = path_url;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
   
}
