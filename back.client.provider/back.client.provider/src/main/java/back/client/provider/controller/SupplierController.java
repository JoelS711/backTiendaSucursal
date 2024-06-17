package back.client.provider.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import back.client.provider.model.Supplier;
import back.client.provider.repository.SupplierRepository;

//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SupplierController {

	@Autowired
	SupplierRepository supplierRepository;

	// GetMapping

	@GetMapping("/suppliers")
	public ResponseEntity<List<Supplier>> getAllSupplier(@RequestParam(required = false) String namesupplier) {
		try {
			List<Supplier> suppliers = new ArrayList<Supplier>();

			if (namesupplier == null) {
				supplierRepository.findAll().forEach(suppliers::add);
			} else {
				supplierRepository.findByNamesupplier(namesupplier).forEach(suppliers::add);
			}
			if (suppliers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(suppliers, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/suppliers/id/{id}")
	public ResponseEntity<Supplier> getSupplierById(@PathVariable("id") String id) {
		Optional<Supplier> supplierData = supplierRepository.findById(id);

		if (supplierData.isPresent()) {
			return new ResponseEntity<>(supplierData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/suppliers/namesupplier/{namesupplier}")
	public ResponseEntity<Supplier> getSupplierByName(@PathVariable("namesupplier") String namesupplier) {

		Supplier aux = supplierRepository.findByNamesupplier(namesupplier).get(0);
		Optional<Supplier> supplierData = Optional.of(aux);
		if (supplierData.isPresent()) {
			return new ResponseEntity<>(supplierData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/suppliers/email/{email}")
	public ResponseEntity<Supplier> getSupplierByEmail(@PathVariable("email") String email) {
		Supplier aux = supplierRepository.findByEmail(email).get(0);
		Optional<Supplier> supplierData = Optional.of(aux);

		if (supplierData.isPresent()) {
			return new ResponseEntity<>(supplierData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	//Se realiza la busqueda por el NIT del proveedor

	@GetMapping("/suppliers/nit/{nit}")
	public ResponseEntity<Supplier> getSupplierByNit(@PathVariable("nit") Long nit) {
		Optional<Supplier> suppliers = supplierRepository.findByNit(nit);
		
		if(suppliers.isPresent()) {
			Supplier supplier = suppliers.get();
			return new ResponseEntity<>(supplier, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	// PostMapping

	@PostMapping("/suppliers")
	public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
		try {
			Optional<Supplier> existingSupplier = supplierRepository.findByNit(supplier.getNit());
			if (existingSupplier.isPresent()) {
				return new ResponseEntity<>(null, HttpStatus.IM_USED);
			}else {
				Supplier _supplier = supplierRepository.save(new Supplier(supplier.getNit(),supplier.getNamesupplier(),supplier.getAddress(),supplier.getEmail(), supplier.getPhone()));
				return new ResponseEntity<>(_supplier, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// PutMapping

	// RECOMENDACIÓN, ENVIAR JSON SIN ID, PERO EL SI ES OBLIGATORIO EN LA URL YA QUE CON ESTE DATO SE SABE CUAL SE DEBE BUSCAR
	@PutMapping("/suppliers/id/{id}")
	public ResponseEntity<Supplier> updateSupplierById(@PathVariable("id") String id, @RequestBody Supplier supplier) {
		Optional<Supplier> supplierData = supplierRepository.findById(id);

		if (supplierData.isPresent()) {
			Supplier _supplier = supplierData.get();
			_supplier.setNit(supplier.getNit());
			_supplier.setAddress(supplier.getAddress());
			_supplier.setEmail(supplier.getEmail());
			_supplier.setNamesupplier(supplier.getNamesupplier());
			_supplier.setPhone(supplier.getPhone());

			return new ResponseEntity<>(supplierRepository.save(_supplier), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/suppliers/nit/{nit}")
	public ResponseEntity<Supplier> updateSupplierByNit(@PathVariable("nit") long nit, @RequestBody Supplier supplier) {
		
		Optional<Supplier> supplierData = supplierRepository.findByNit(nit);
		if (supplierData.isPresent()) {
			Supplier _supplier = supplierData.get();
			_supplier.setNit(supplier.getNit());
			_supplier.setAddress(supplier.getAddress());
			_supplier.setEmail(supplier.getEmail());
			_supplier.setNamesupplier(supplier.getNamesupplier());
			_supplier.setPhone(supplier.getPhone());
			return new ResponseEntity<>(supplierRepository.save(_supplier), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	// DeleteMapping

	@DeleteMapping("supplier/id/{id}")
	public ResponseEntity<HttpStatus> deleteSupplierById(@PathVariable("id") String id) {
		try {
			supplierRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@DeleteMapping("/suppliers/nit/{nit}")
	public ResponseEntity<HttpStatus> deleteSupplierByNit(@PathVariable("nit") long nit) {

		try {
			supplierRepository.deleteByNit(nit);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/suppliers/namesupplier/{namesupplier}")
	public ResponseEntity<HttpStatus> deleteSupplierByNamesupplier(@PathVariable("namesupplier") String namesupplier) {
		try {
			supplierRepository.deleteByNamesupplier(namesupplier);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/suppliers/email/{email}")
	public ResponseEntity<HttpStatus> deleteSupplierByEmail(@PathVariable("email") String email) {
		try {
			supplierRepository.deleteByEmail(email);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/suppliers")
	public ResponseEntity<HttpStatus> deleteAllSuppliers() {
		try {
			supplierRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
