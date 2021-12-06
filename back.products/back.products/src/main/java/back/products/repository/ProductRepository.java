package back.products.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import back.products.model.Products;



public interface ProductRepository extends MongoRepository<Products, String> {

	List<Products> findByCode(Long code);
	
	List<Products> findByName(String name);
}
