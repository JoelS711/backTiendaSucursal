package back.report.controller;

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
			Report add = reportRepository.save(new Report(report.getCity(),report.getIdentification(), report.getName(), report.getTotal()));
			return new ResponseEntity<>(add, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
