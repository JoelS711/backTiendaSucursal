package back.consolidated.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import back.consolidated.model.Consolidated;
import back.consolidated.repository.ConsolidatedRepository;

public class ConsolidatedController {

	@Autowired
	ConsolidatedRepository consolidatedRepository;

	@GetMapping("/consolidated")
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

	}

	@GetMapping("/consolidated/city/{city}")
	public ResponseEntity<List<Consolidated>> getConsolidadosByCiudad(@PathVariable("city") String city) {
		try {
			List<Consolidated> consolidados = new ArrayList<Consolidated>();

			consolidatedRepository.findByCity(city).forEach(consolidados::add);

			if (consolidados.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(consolidados, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	/*@PostMapping("/consolidated/agregar/{cod}")
	public ResponseEntity<Consolidated> sumNewVentaToConsolidado(@PathVariable("cod") String cod) {
		List<Consolidated> list = consolidatedRepository.findAll();
		if(list.isEmpty()) {
			consolidatedRepository.save(new Consolidated("Bogota",0L));
			consolidatedRepository.save(new Consolidated("Cali",0L));
			consolidatedRepository.save(new Consolidated("Medellin",0L));
		}
		System.out.println(cod);
		try {
			if (cod.equals("1")) {
				Consolidated aux = consolidatedRepository.findByCity("Bogota").get(0);
				Optional<Consolidated> consolidadoData = Optional.of(aux);

				if (consolidadoData.isPresent()) {
					Consolidated _consolidado = consolidadoData.get();
					_consolidado.setTotalventas(_consolidado.getTotalventas() + 1);
					return new ResponseEntity<>(consolidatedRepository.save(_consolidado), HttpStatus.ACCEPTED);
				}
			}else if (cod.equals("3")) {
				Consolidated aux = consolidatedRepository.findByCity("Cali").get(0);
				Optional<Consolidated> consolidadoData = Optional.of(aux);

				if (consolidadoData.isPresent()) {
					Consolidated _consolidado = consolidadoData.get();
					_consolidado.setTotalventas(_consolidado.getTotalventas() + 1);
					return new ResponseEntity<>(consolidatedRepository.save(_consolidado), HttpStatus.ACCEPTED);
				}
			}else if (cod.equals("2")) {
				Consolidated aux = consolidatedRepository.findByCity("Medellin").get(0);
				Optional<Consolidated> consolidadoData = Optional.of(aux);

				if (consolidadoData.isPresent()) {
					Consolidated _consolidado = consolidadoData.get();
					_consolidado.setTotalventas(_consolidado.getTotalventas() + 1);
					return new ResponseEntity<>(consolidatedRepository.save(_consolidado), HttpStatus.ACCEPTED);
				}
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	
}
