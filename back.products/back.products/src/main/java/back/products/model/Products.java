package back.products.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Products {

	@Id
	private String id;
	
	private String code;
	private String name;
	private String nitprovider;
	private String purchaseprice;
	private String iva;
	private String saleprice;
	
	
	
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Products(String code, String name, String nitprovider, String purchaseprice, String iva, String saleprice) {
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



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getNitprovider() {
		return nitprovider;
	}



	public void setNitprovider(String nitprovider) {
		this.nitprovider = nitprovider;
	}



	public String getPurchaseprice() {
		return purchaseprice;
	}



	public void setPurchaseprice(String purchaseprice) {
		this.purchaseprice = purchaseprice;
	}



	public String getIva() {
		return iva;
	}



	public void setIva(String iva) {
		this.iva = iva;
	}



	public String getSaleprice() {
		return saleprice;
	}



	public void setSaleprice(String saleprice) {
		this.saleprice = saleprice;
	}



//	@Override
//	public String toString() {
//		return "Products [id=" + id + ", code=" + code + ", name=" + name + ", nitprovider=" + nitprovider
//				+ ", purchaseprice=" + purchaseprice + ", iva=" + iva + ", saleprice=" + saleprice + "]";
//	}
	
	
	
	
}
