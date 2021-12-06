package back.sales.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sales")
public class Sales {

	@Id
	private String id;
	private long identification;
	
	@Indexed(unique=true)
	private long salecode;
	private ArrayList<SaleDetail> saledetail;
	private double ivasale;
	private double totalsale;
	private double salevalue;
	
	public Sales() {
		this.saledetail = new ArrayList<SaleDetail>();
	}

	public Sales(long identification, long salecode, ArrayList<SaleDetail> saledetail, double ivasale, double totalsale,
			double salevalue) {
		super();
		this.identification = identification;
		this.salecode = salecode;
		this.saledetail = saledetail;
		this.ivasale = ivasale;
		this.totalsale = totalsale;
		this.salevalue = salevalue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getIdentification() {
		return identification;
	}

	public void setIdentification(long identification) {
		this.identification = identification;
	}

	public long getSalecode() {
		return salecode;
	}

	public void setSalecode(long salecode) {
		this.salecode = salecode;
	}

	public ArrayList<SaleDetail> getSaledetail() {
		return saledetail;
	}

	public void setSaledetail(ArrayList<SaleDetail> saledetail) {
		this.saledetail = saledetail;
	}

	public double getIvasale() {
		return ivasale;
	}

	public void setIvasale(double ivasale) {
		this.ivasale = ivasale;
	}

	public double getTotalsale() {
		return totalsale;
	}

	public void setTotalsale(double totalsale) {
		this.totalsale = totalsale;
	}

	public double getSalevalue() {
		return salevalue;
	}

	public void setSalevalue(double salevalue) {
		this.salevalue = salevalue;
	}



	
	
	
	
	
}
