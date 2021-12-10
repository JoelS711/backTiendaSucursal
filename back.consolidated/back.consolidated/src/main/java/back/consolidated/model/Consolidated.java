package back.consolidated.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "consolidated")
public class Consolidated {

	@Id
	private String id;
	private String city;
	private Long totalventas;
	
	public Consolidated() {
	}
	
	

	public Consolidated(String city, Long totalventas) {
		super();
		this.city = city;
		this.totalventas = totalventas;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getTotalventas() {
		return totalventas;
	}

	public void setTotalventas(Long totalventas) {
		this.totalventas = totalventas;
	}

	
	
}
