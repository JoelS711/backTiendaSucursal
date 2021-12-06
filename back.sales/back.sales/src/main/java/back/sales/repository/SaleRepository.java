package back.sales.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import back.sales.model.Sales;

public interface SaleRepository extends MongoRepository<Sales, String> {

	List<Sales> findBySaleCode(Long salecode);
	List<Sales> findByIdentification(Long identification);
	
	
	void deleteBySaleCode(Long salecode);
	void deleteByIndentification(Long identification);
}
