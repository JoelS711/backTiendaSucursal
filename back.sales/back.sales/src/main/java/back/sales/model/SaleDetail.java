package back.sales.model;

public class SaleDetail {

	private long productquantity;
	private long codeproduct;
	private double totalsale;
	private double salevalue;
	private double ivavalue;
	
	public SaleDetail() {
	}

	public SaleDetail(long productquantity, long codeproduct, double totalsale, double salevalue, double ivavalue) {
		super();
		this.productquantity = productquantity;
		this.codeproduct = codeproduct;
		this.totalsale = totalsale;
		this.salevalue = salevalue;
		this.ivavalue = ivavalue;
	}

	public long getProductquantity() {
		return productquantity;
	}

	public void setProductquantity(long productquantity) {
		this.productquantity = productquantity;
	}

	public long getCodeproduct() {
		return codeproduct;
	}

	public void setCodeproduct(long codeproduct) {
		this.codeproduct = codeproduct;
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

	public double getIvavalue() {
		return ivavalue;
	}

	public void setIvavalue(double ivavalue) {
		this.ivavalue = ivavalue;
	}
	
	
	
}
