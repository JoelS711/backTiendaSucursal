package back.consolidated.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import back.consolidated.model.Consolidated;



public interface ConsolidatedRepository extends MongoRepository<Consolidated, String>{

	List<Consolidated> findByCity(String city);
}
