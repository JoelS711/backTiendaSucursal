package back.report.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import back.report.model.Report;

public interface ReportRepository extends MongoRepository<Report, String>{
	List<Report> findByCity(String city);
	Optional<Report> findByCityAndIdentification(String city, long indentification);
}
