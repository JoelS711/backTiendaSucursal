package back.consolidated.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import back.consolidated.model.Consolidated;
import back.consolidated.repository.ConsolidatedRepository;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8085")
@RestController
@RequestMapping("/api")
public class ConsolidatedController {

	@Autowired
	ConsolidatedRepository consolidatedRepository;

	/*@GetMapping("/consolidated")
	public ResponseEntity<List<Consolidated>> getAllConsolidados(@RequestParam(required = false) String city) {
		try {
			List<Consolidated> consolidados = new ArrayList<Consolidated>();

			if (city == null) {
				consolidatedRepository.findAll().forEach(consolidados::add);
			} else {
				consolidatedRepository.findByCity(city).forEach(consolidados::add);
			}

			if (consolidados.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(consolidados, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}*/

	@GetMapping("/consolidated/city/{city}")
	public ResponseEntity<Consolidated> getConsolidadosByCiudad(@PathVariable("city") String city) {
		try {
			Optional<Consolidated> consolidate = consolidatedRepository.findByCity(city);



			if (consolidate.isPresent()) {
				Consolidated consolidated = consolidate.get();
				
				return new ResponseEntity<>(consolidated, HttpStatus.OK);
			}else {
				
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@PostMapping("/consolidated")
	public ResponseEntity<Consolidated> addConsolidated(@RequestBody Consolidated consolidated){
		try {
			Optional<Consolidated> existingConsolidated = consolidatedRepository.findByCity(consolidated.getCity());
			if(existingConsolidated.isPresent()) {
				Consolidated existing = existingConsolidated.get();
				existing.setIva(existing.getIva() + consolidated.getIva());
				existing.setTotalsale(existing.getTotalsale() + consolidated.getTotalsale());
				existing.setTotal(existing.getTotal() + consolidated.getTotal());
				consolidatedRepository.save(existing);
				return new ResponseEntity<>(existing, HttpStatus.OK);
			}else {
				Consolidated add = consolidatedRepository.save(consolidated);
				return new ResponseEntity<>(add, HttpStatus.CREATED);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	
}
