package back.report.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import back.report.model.Report;
import back.report.repository.ReportRepository;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8086")
@RestController
@RequestMapping("/api")
public class ReportController {

	@Autowired
	ReportRepository reportRepository;
	
	@PostMapping("/report")
	public ResponseEntity<Report> addReport(@RequestBody Report report){
		try {
			Optional<Report> existingReport = reportRepository.findByCityAndIdentification(report.getCity(), report.getIdentification());
			if (existingReport.isPresent()) {
				Report existing = existingReport.get();
				existing.setTotal(existing.getTotal()+report.getTotal());
				reportRepository.save(existing);
				return new ResponseEntity<>(existing, HttpStatus.OK);
			}else {
				
				Report add = reportRepository.save(report);
				return new ResponseEntity<>(add, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
