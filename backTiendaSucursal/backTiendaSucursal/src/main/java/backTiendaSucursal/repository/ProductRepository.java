package backTiendaSucursal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import backTiendaSucursal.model.Products;

public interface ProductRepository extends MongoRepository<Products, String> {

	
}
