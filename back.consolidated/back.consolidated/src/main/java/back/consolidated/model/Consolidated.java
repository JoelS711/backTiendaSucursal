package back.consolidated.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "consolidated")
public class Consolidated {

	@Id
	private String id;
	private String ciudad;
	private Long totalventas;
	
	public Consolidated() {
	}

	public Consolidated(String ciudad, Long totalventas) {
		super();
		this.ciudad = ciudad;
		this.totalventas = totalventas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Long getTotalventas() {
		return totalventas;
	}

	public void setTotalventas(Long totalventas) {
		this.totalventas = totalventas;
	}
}
