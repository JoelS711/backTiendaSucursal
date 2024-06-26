package back.client.provider.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import back.client.provider.model.Supplier;

public interface SupplierRepository extends MongoRepository <Supplier, String> {
	Optional<Supplier> findByNit(Long nit);
	List<Supplier> findByNamesupplier(String namesupplier);
	List<Supplier> findByEmail(String email);
	
	
	void deleteByNit(Long nit);
	void deleteByNamesupplier(String namesupplier);
	void deleteByEmail(String email);
}
