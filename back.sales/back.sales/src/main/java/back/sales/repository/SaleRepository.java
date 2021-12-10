package back.sales.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import back.sales.model.Sales;

public interface SaleRepository extends MongoRepository<Sales, String> {

	List<Sales> findBySalecode(Long salecode);
	List<Sales> findByIdentification(Long identification);
	
	
	void deleteBySalecode(Long salecode);
	void deleteByIdentification(Long identification);
}
