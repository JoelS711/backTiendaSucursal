package back.client.provider.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
public class Client {
	@Id
	private String id;

	@Indexed(unique = true)
	private long identification;
	private String address;
	private String email;
	private String name;
	private String phone;

	public Client() {
	}

	public Client(long identification, String address, String email, String name, String phone) {
		super();
		this.identification = identification;
		this.address = address;
		this.email = email;
		this.name = name;
		this.phone = phone;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", identification=" + identification + ", address=" + address + ", email=" + email
				+ ", name=" + name + ", phone=" + phone + "]";
	}
	
	

}
