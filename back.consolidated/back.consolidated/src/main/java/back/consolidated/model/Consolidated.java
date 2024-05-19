package back.consolidated.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "consolidated")
public class Consolidated {

	@Id
	private String id;
	private String city;
	private double iva;
	private double totalsale;
	private double total;
	
	public Consolidated() {
	}

	public Consolidated(String city, double iva, double totalsale, double total) {
		super();
		this.city = city;
		this.iva = iva;
		this.totalsale = totalsale;
		this.total = total;
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

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getTotalsale() {
		return totalsale;
	}

	public void setTotalsale(double totalsale) {
		this.totalsale = totalsale;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
	

	

	
	
}
