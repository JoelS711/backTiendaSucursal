package back.consolidated.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import back.consolidated.model.Consolidated;



public interface ConsolidatedRepository extends MongoRepository<Consolidated, String>{

	Optional<Consolidated> findByCity(String city);
}
