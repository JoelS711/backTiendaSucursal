package back.client.provider.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "suppliers")
public class Supplier {
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	private long nit;
	private String namesupplier;
	private String address;
	private String email;
	private String phone;
	

	public Supplier() {
	}


	public Supplier(long nit, String namesupplier, String address, String email, String phone) {
		super();
		this.nit = nit;
		this.namesupplier = namesupplier;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getNit() {
		return nit;
	}
	public void setNit(long nit) {
		this.nit = nit;
	}
	public String getNamesupplier() {
		return namesupplier;
	}
	public void setNamesupplier(String namesupplier) {
		this.namesupplier = namesupplier;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Override
	public String toString() {
		return "Supplier [id=" + id + ", nit=" + nit + ", namesupplier=" + namesupplier + ", address=" + address
				+ ", email=" + email + ", phone=" + phone + "]";
	}
	
	

}
