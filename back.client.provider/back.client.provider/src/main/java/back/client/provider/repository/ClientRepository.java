package back.client.provider.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import back.client.provider.model.Client;

public interface ClientRepository extends MongoRepository<Client, String> {

	List<Client> findByIdentification(Long identification);
	List<Client> findByName(String name);
	List<Client> findByEmail(String email);
	
	
	void deleteByIdentification(Long identification);
	void deleteByName(String name);
	void deleteByEmail(String email);
}
