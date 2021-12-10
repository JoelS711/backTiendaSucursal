package back.products.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Products {

	@Id
	private String id;
	
	@Indexed(unique=true)
	private Long code;
	private String name;
	private Long nitprovider;
	private Double purchaseprice;
	private Double iva;
	private Double saleprice;
	
	
	
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Products(Long code, String name, Long nitprovider, Double purchaseprice, Double iva, Double saleprice) {
		super();
		this.code = code;
		this.name = name;
		this.nitprovider = nitprovider;
		this.purchaseprice = purchaseprice;
		this.iva = iva;
		this.saleprice = saleprice;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Long getCode() {
		return code;
	}



	public void setCode(Long code) {
		this.code = code;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Long getNitprovider() {
		return nitprovider;
	}



	public void setNitprovider(Long nitprovider) {
		this.nitprovider = nitprovider;
	}



	public Double getPurchaseprice() {
		return purchaseprice;
	}



	public void setPurchaseprice(Double purchaseprice) {
		this.purchaseprice = purchaseprice;
	}



	public Double getIva() {
		return iva;
	}



	public void setIva(Double iva) {
		this.iva = iva;
	}



	public Double getSaleprice() {
		return saleprice;
	}



	public void setSaleprice(Double saleprice) {
		this.saleprice = saleprice;
	}



	
	
	
	
	
}
